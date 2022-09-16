package com.pletenchaos.pletenchaos.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pletenchaos.pletenchaos.model.binding.MaterialBinding;
import com.pletenchaos.pletenchaos.model.entity.MaterialEntity;
import com.pletenchaos.pletenchaos.model.entity.PictureEntity;
import com.pletenchaos.pletenchaos.model.entity.UserEntity;
import com.pletenchaos.pletenchaos.repository.MaterialRepository;
import com.pletenchaos.pletenchaos.repository.PictureRepository;
import com.pletenchaos.pletenchaos.repository.UserRepository;
import com.pletenchaos.pletenchaos.service.interfaces.IMaterialService;
import com.pletenchaos.pletenchaos.service.interfaces.IPictureService;
import com.pletenchaos.pletenchaos.utils.ConvertObjUtil;
import com.pletenchaos.pletenchaos.utils.exceptions.NotFoundEntity;

@Service
public class MaterialServiceImpl implements IMaterialService {

	private final MaterialRepository materialRepo;

	private final UserRepository userRepo;

	private final ModelMapper mapper;

	private final IPictureService pictureService;

	private final PictureRepository pictureRepo;

	@Autowired
	public MaterialServiceImpl(MaterialRepository materialRepo, UserRepository userRepo, ModelMapper mapper,
			IPictureService pictureService, PictureRepository pictureRepo) {
		this.materialRepo = materialRepo;
		this.userRepo = userRepo;
		this.mapper = mapper;
		this.pictureService = pictureService;
		this.pictureRepo = pictureRepo;
	}

	@Override
	public boolean isUnique(String name) {
		MaterialEntity material = materialRepo.findByName(name).orElse(null);
		return material != null;
	}

	@Override
	public boolean addMaterial(MaterialBinding binding, String loginName) {

		UserEntity user = userRepo.findByloginName(loginName)
				.orElseThrow(() -> new NotFoundEntity(String.format("User with name %s not found!", loginName)));

		MaterialEntity material = ConvertObjUtil.convert(binding, MaterialEntity.class, mapper);
		material.setAuthor(user);
		material.setTotalPrice(calculateTotalPrice(material));
		materialRepo.save(material);

		Long pictureId;
		try {
			pictureId = pictureService.uploadPicture(binding.getPicture(), loginName, material.getName());
		} catch (IOException e) {
			return false;
		}

		PictureEntity picture = pictureRepo.findById(pictureId)
				.orElseThrow(() -> new NotFoundEntity(String.format("Picture with Id %s not found!", pictureId)));

		List<PictureEntity> pictures = new ArrayList<>();
		pictures.add(picture);
		material.setPictures(pictures);
		materialRepo.save(material);

		return true;
	}

	@Transactional
	@Override
	public List<MaterialBinding> getMaterialsByUser(String loginName) {
		UserEntity user = userRepo.findByloginName(loginName)
				.orElseThrow(() -> new NotFoundEntity(String.format("User with name %s not found!", loginName)));

		return user.getMaterials().stream().map(m -> {
			MaterialBinding binding = ConvertObjUtil.convert(m, MaterialBinding.class, mapper);
			binding.setPictureUrl(m.getPictures().iterator().next().getUrl());
			return binding;

		}).collect(Collectors.toList());
	}

	@Override
	public boolean isOwner(String username, Long id) {
		Optional<MaterialEntity> material = materialRepo.findById(id);

		if (material.isEmpty()) {
			return false;
		}
		MaterialEntity materialEntity = material.get();

		return materialEntity.getAuthor().getLoginName().equals(username);
	}

	@Transactional
	@Override
	public MaterialBinding getMaterialById(Long id) {
		MaterialEntity material = findById(id);
		MaterialBinding binding = ConvertObjUtil.convert(material, MaterialBinding.class, mapper);
		binding.setPictureUrl(material.getPictures().iterator().next().getUrl());
		return binding;
	}

	@Transactional
	@Override
	public void deleteMaterial(Long id) {
		MaterialEntity material = findById(id);
		List<PictureEntity> pictures = material.getPictures();
		pictureService.deletePicture(pictures.iterator().next().getId());
		materialRepo.deleteById(id);
	}

	private Double calculateTotalPrice(MaterialEntity material) {

		return material.getPrice() * material.getQuantity();
	}

	private MaterialEntity findById(Long id) {
		return materialRepo.findById(id)
				.orElseThrow(() -> new NotFoundEntity(String.format("Material with id %s is not found!", id)));
	}

}

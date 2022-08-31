package com.pletenchaos.pletenchaos.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pletenchaos.pletenchaos.model.binding.NewMaterialBinding;
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
	public boolean addMaterial(NewMaterialBinding binding, String loginName) {

		UserEntity user = userRepo.findByloginName(loginName)
				.orElseThrow(() -> new NotFoundEntity(String.format("User with name %s not found!", loginName)));

		MaterialEntity material = ConvertObjUtil.convert(binding, MaterialEntity.class, mapper);
		material.setAuthor(user);
		material.setTotalPrice(calculateTotalPrice(material));
//		material.setPicture(null);
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

	private Double calculateTotalPrice(MaterialEntity material) {

		return material.getPrice() * material.getQuantity();
	}

}

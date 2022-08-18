package com.pletenchaos.pletenchaos.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pletenchaos.pletenchaos.model.binding.NewMaterialBinding;
import com.pletenchaos.pletenchaos.model.entity.MaterialEntity;
import com.pletenchaos.pletenchaos.repository.MaterialRepository;
import com.pletenchaos.pletenchaos.service.interfaces.IMaterialService;
import com.pletenchaos.pletenchaos.utils.ConvertObjUtil;

@Service
public class MaterialServiceImpl implements IMaterialService {

	private final MaterialRepository materialRepo;

	private final ModelMapper mapper;

	@Autowired
	public MaterialServiceImpl(MaterialRepository materialRepo, ModelMapper mapper) {
		this.materialRepo = materialRepo;
		this.mapper = mapper;
	}

	@Override
	public boolean isUnique(String name) {
		MaterialEntity material = materialRepo.findByName(name).orElse(null);
		return material != null;
	}

	@Override
	public boolean addMaterial(NewMaterialBinding binding) {
		MaterialEntity material = ConvertObjUtil.convert(binding, MaterialEntity.class, mapper);
		return false;
	}

}

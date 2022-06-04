package com.pletenchaos.pletenchaos.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pletenchaos.pletenchaos.model.binding.RoleBinding;
import com.pletenchaos.pletenchaos.model.entity.RoleEntity;
import com.pletenchaos.pletenchaos.model.entity.enums.RoleEnum;
import com.pletenchaos.pletenchaos.repository.RoleRepository;
import com.pletenchaos.pletenchaos.service.interfaces.IRoleService;
import com.pletenchaos.pletenchaos.utils.ConvertObjUtil;
import com.pletenchaos.pletenchaos.utils.exceptions.NotFoundEntity;

@Service
public class RoleServiceImpl implements IRoleService {

	private final RoleRepository roleRepo;

	private final ModelMapper mapper;

	@Autowired
	public RoleServiceImpl(RoleRepository roleRepo, ModelMapper mapper) {
		this.roleRepo = roleRepo;
		this.mapper = mapper;
	}

	@Override
	public RoleBinding findByRole(RoleEnum role) {
		RoleEntity roleEntity = roleRepo.findByRole(role)
				.orElseThrow(() -> new NotFoundEntity(String.format("Role with name %s not found!", role.name())));
		return ConvertObjUtil.convert(roleEntity, RoleBinding.class, mapper);
	}

	@Override
	public void initRoles() {
		if (roleRepo.count() == 0) {
			RoleBinding adminRole = new RoleBinding();
			adminRole.setRole(RoleEnum.ADMIN);

			RoleBinding userRole = new RoleBinding();
			userRole.setRole(RoleEnum.USER);

			roleRepo.saveAll(List.of(ConvertObjUtil.convert(userRole, RoleEntity.class, mapper),
					ConvertObjUtil.convert(adminRole, RoleEntity.class, mapper)));
		}

	}

}

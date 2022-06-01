package com.pletenchaos.pletenchaos.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pletenchaos.pletenchaos.model.binding.NewUserBinding;
import com.pletenchaos.pletenchaos.model.entity.UserEntity;
import com.pletenchaos.pletenchaos.repository.UserRepository;
import com.pletenchaos.pletenchaos.service.interfaces.IRoleService;
import com.pletenchaos.pletenchaos.service.interfaces.IUserService;
import com.pletenchaos.pletenchaos.utils.ConvertObjUtil;

@Service
public class UserServiceImpl implements IUserService {

	private final UserRepository userRepo;

	private final ModelMapper mapper;

	private final IRoleService roleService;

	@Autowired
	public UserServiceImpl(UserRepository userRepo, ModelMapper mapper, IRoleService roleService) {
		this.userRepo = userRepo;
		this.mapper = mapper;
		this.roleService = roleService;
	}

	@Override
	public boolean matchPassword(NewUserBinding newUser) {
		return newUser.getPassword().equals(newUser.getConfirmPassword());
	}

	@Override
	public boolean isUniqueEmail(String email) {
		UserEntity entity = userRepo.findByEmail(email).orElse(null);
		return entity != null;
	}

	@Override
	public boolean isUniqueUsername(String username) {
		UserEntity entity = userRepo.findByloginName(username).orElse(null);
		return entity != null;
	}

	@Override
	public boolean register(NewUserBinding newUser) {
		UserEntity userEntity = ConvertObjUtil.convert(newUser, UserEntity.class, mapper);

		// first user is register as Administrator
//		RoleEntity roleEntity = Con userRepo.count() == 0 ? roleService.findByRole(RoleEnum.ADMIN) :roleService.findByRole(RoleEnum.USER) ;

		return false;
	}

}

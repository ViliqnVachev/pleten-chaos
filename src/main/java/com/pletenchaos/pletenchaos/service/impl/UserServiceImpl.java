package com.pletenchaos.pletenchaos.service.impl;

import java.util.Arrays;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pletenchaos.pletenchaos.model.binding.NewUserBinding;
import com.pletenchaos.pletenchaos.model.entity.RoleEntity;
import com.pletenchaos.pletenchaos.model.entity.UserEntity;
import com.pletenchaos.pletenchaos.model.entity.enums.RoleEnum;
import com.pletenchaos.pletenchaos.repository.UserRepository;
import com.pletenchaos.pletenchaos.service.interfaces.IRoleService;
import com.pletenchaos.pletenchaos.service.interfaces.IUserService;
import com.pletenchaos.pletenchaos.utils.ConvertObjUtil;
import com.pletenchaos.pletenchaos.utils.exceptions.NotFoundEntity;

@Service
public class UserServiceImpl implements IUserService {

	private final UserRepository userRepo;

	private final ModelMapper mapper;

	private final IRoleService roleService;

	private final PasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceImpl(UserRepository userRepo, ModelMapper mapper, IRoleService roleService,
			PasswordEncoder passwordEncoder) {
		this.userRepo = userRepo;
		this.mapper = mapper;
		this.roleService = roleService;
		this.passwordEncoder = passwordEncoder;
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
		try {
			UserEntity userEntity = ConvertObjUtil.convert(newUser, UserEntity.class, mapper);

			// first user is register as Administrator
			RoleEntity roleEntity = ConvertObjUtil
					.convert((userRepo.count() == 0 ? roleService.findByRole(RoleEnum.ADMIN)
							: roleService.findByRole(RoleEnum.USER)), RoleEntity.class, mapper);
			userEntity.setPassword(passwordEncoder.encode(newUser.getPassword()));
			userEntity.setActive(true);
			userEntity.setRoles(Arrays.asList(roleEntity));
			userEntity.setStatistics(null);

			userRepo.save(userEntity);
		} catch (IllegalArgumentException | NotFoundEntity e) {
			return false;
		}
		return true;
	}

}

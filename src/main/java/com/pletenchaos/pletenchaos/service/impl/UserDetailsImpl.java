package com.pletenchaos.pletenchaos.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pletenchaos.pletenchaos.model.entity.UserEntity;
import com.pletenchaos.pletenchaos.repository.UserRepository;
import com.pletenchaos.pletenchaos.utils.exceptions.NotFoundEntity;

@Service
public class UserDetailsImpl implements UserDetailsService {

	private final UserRepository userRepo;

	@Autowired
	public UserDetailsImpl(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = userRepo.findByloginName(username)
				.orElseThrow(() -> new NotFoundEntity("User entity is not found"));
		return convertToUser(user);
	}

	private UserDetails convertToUser(UserEntity user) {

		List<GrantedAuthority> auhtorities = user.getRoles().stream()
				.map(r -> new SimpleGrantedAuthority(String.format("ROLE_%s", r.getRole().name())))
				.collect(Collectors.toList());

		return new User(user.getLoginName(), user.getPassword(), user.isActive(), true, true, true, auhtorities);

	}

}

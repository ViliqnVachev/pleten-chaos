package com.pletenchaos.pletenchaos.service.interfaces;

import com.pletenchaos.pletenchaos.model.binding.NewUserBinding;

public interface IUserService {

	boolean matchPassword(NewUserBinding newUser);

	boolean isUniqueEmail(String email);

	boolean isUniqueUsername(String username);

	boolean register(NewUserBinding newUser);

}

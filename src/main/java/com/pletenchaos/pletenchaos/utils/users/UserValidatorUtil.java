package com.pletenchaos.pletenchaos.utils.users;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pletenchaos.pletenchaos.model.binding.NewUserBinding;
import com.pletenchaos.pletenchaos.service.interfaces.IUserService;

public class UserValidatorUtil {

	public static boolean isValid(RedirectAttributes attributes, NewUserBinding newUser, BindingResult bindingResult,
			IUserService userService) {
		boolean valid = true;

		if (!userService.matchPassword(newUser)) {
			attributes.addFlashAttribute("isNotEquals", true);
			valid = false;
		}

		if (!userService.isUniqueEmail(newUser.getEmail())) {
			attributes.addFlashAttribute("isNotUniqueEmail", true);
			valid = false;
		}

		if (!userService.isUniqueUsername(newUser.getUsername())) {
			attributes.addFlashAttribute("isNotUnique", true);
			valid = false;
		}

		return valid;
	}

}

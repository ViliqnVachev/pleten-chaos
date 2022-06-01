package com.pletenchaos.pletenchaos.web;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pletenchaos.pletenchaos.model.binding.NewUserBinding;
import com.pletenchaos.pletenchaos.service.interfaces.IUserService;
import com.pletenchaos.pletenchaos.utils.common.PathConstants;
import com.pletenchaos.pletenchaos.utils.common.ViewConstants;
import com.pletenchaos.pletenchaos.utils.users.UserValidatorUtil;

@Controller
public class RegisterController {

	private final IUserService userService;

	public RegisterController(IUserService userService) {
		this.userService = userService;
	}

	@GetMapping(PathConstants.REGISTER)
	public String getRegisterView() {
		return ViewConstants.REGISTER_VIEW;
	}

	@PostMapping(PathConstants.REGISTER)
	public String register(@Valid NewUserBinding newUser, BindingResult bindingResult, RedirectAttributes attributes) {

		// check for errors
		if (bindingResult.hasErrors()) {
			attributes.addFlashAttribute("newUserBinding", newUser)//
					.addFlashAttribute("org.springframework.validation.BindingResult.newUserBinding",
							bindingResult);
			return PathConstants.REDIRECT_REGISTER;
		}

		if (!UserValidatorUtil.isValid(attributes, newUser, bindingResult, userService)) {
			attributes.addFlashAttribute("newUserBinding", newUser)//
					.addFlashAttribute("org.springframework.validation.BindingResult.newUserBinding", bindingResult);
			return PathConstants.REDIRECT_REGISTER;
		}
		
		userService.register(newUser);

		return PathConstants.REDIRECT_LOGIN;
	}

	@ModelAttribute
	public NewUserBinding newUserBinding() {
		return new NewUserBinding();
	}

}

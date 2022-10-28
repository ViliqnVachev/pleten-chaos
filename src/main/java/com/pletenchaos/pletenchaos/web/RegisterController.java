package com.pletenchaos.pletenchaos.web;

import javax.validation.Valid;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pletenchaos.pletenchaos.model.binding.NewUserBinding;
import com.pletenchaos.pletenchaos.service.interfaces.IUserService;
import com.pletenchaos.pletenchaos.utils.common.PathConstants;
import com.pletenchaos.pletenchaos.utils.common.Views;
import com.pletenchaos.pletenchaos.utils.validators.objects.ObjectValidator;
import com.pletenchaos.pletenchaos.web.events.EmailEvent;

@Controller
public class RegisterController {

	private final IUserService userService;
	private final ApplicationEventPublisher publisher;

	public RegisterController(IUserService userService, ApplicationEventPublisher publisher) {
		this.userService = userService;
		this.publisher = publisher;
	}

	@GetMapping(PathConstants.REGISTER)
	public String getRegisterView() {
		return Views.REGISTER_VIEW;
	}

	@PostMapping(PathConstants.REGISTER)
	public String register(@Valid NewUserBinding newUser, BindingResult bindingResult, RedirectAttributes attributes) {

		// check for errors
		if (bindingResult.hasErrors()
				|| !ObjectValidator.validateUser(attributes, newUser, bindingResult, userService)) {
			attributes.addFlashAttribute("newUserBinding", newUser)//
					.addFlashAttribute("org.springframework.validation.BindingResult.newUserBinding", bindingResult);
			return PathConstants.REDIRECT_REGISTER;
		}

		// TODO: Check for exceptions
		boolean isRegiterSuccess = userService.register(newUser);

		if (isRegiterSuccess) {
			ApplicationEvent event = new EmailEvent(this, newUser.getLoginName(), newUser.getEmail());
			publisher.publishEvent(event);
		}

		return PathConstants.REDIRECT_LOGIN;
	}

	@ModelAttribute
	public NewUserBinding newUserBinding() {
		return new NewUserBinding();
	}

}

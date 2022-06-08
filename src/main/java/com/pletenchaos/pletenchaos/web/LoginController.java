package com.pletenchaos.pletenchaos.web;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pletenchaos.pletenchaos.utils.common.PathConstants;
import com.pletenchaos.pletenchaos.utils.common.ViewConstants;

@Controller
public class LoginController {

	@GetMapping(PathConstants.LOGIN)
	public String getLoginView() {
		return ViewConstants.LOGIN_VIEW;
	}

	@PostMapping(PathConstants.LOGIN_ERROR)
	public String failedLogin(
			@ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username,
			RedirectAttributes attributes) {
		attributes.addFlashAttribute("username", username).addFlashAttribute("bad_credential", true);
		return PathConstants.REDIRECT_LOGIN;
	}

}

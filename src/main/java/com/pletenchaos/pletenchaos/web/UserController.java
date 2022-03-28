package com.pletenchaos.pletenchaos.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.pletenchaos.pletenchaos.utils.common.ViewConstants;

@Controller
public class UserController {

	@GetMapping
	public String getLoginView() {
		return ViewConstants.LOGIN_VIEW;
	}

	@GetMapping("/register")
	public String getRegisterView() {
		return ViewConstants.REGISTER_VIEW;
	}

	@GetMapping("/home")
	public String getRegisterHome() {
		return "home";
	}

}

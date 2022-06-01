package com.pletenchaos.pletenchaos.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.pletenchaos.pletenchaos.utils.common.PathConstants;
import com.pletenchaos.pletenchaos.utils.common.ViewConstants;

@Controller
public class HomeCotnroller {

	@GetMapping(PathConstants.LOGIN)
	public String getLoginView() {
		return ViewConstants.LOGIN_VIEW;
	}

	@GetMapping(PathConstants.HOME)
	public String getRegisterHome() {
		return ViewConstants.HOME_VIEW;
	}

}

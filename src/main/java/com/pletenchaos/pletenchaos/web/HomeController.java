package com.pletenchaos.pletenchaos.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.pletenchaos.pletenchaos.utils.common.PathConstants;
import com.pletenchaos.pletenchaos.utils.common.Views;

@Controller
public class HomeController {


	@GetMapping(PathConstants.HOME)
	public String getRegisterHome() {
		return Views.HOME_VIEW;
	}

}

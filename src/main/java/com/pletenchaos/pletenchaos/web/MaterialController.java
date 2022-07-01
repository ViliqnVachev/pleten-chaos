package com.pletenchaos.pletenchaos.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pletenchaos.pletenchaos.utils.common.PathConstants;
import com.pletenchaos.pletenchaos.utils.common.ViewConstants;

@Controller
@RequestMapping(PathConstants.MATERIAL)
public class MaterialController {

	@GetMapping(PathConstants.ADD_MATERIAL)
	public String getMaterial() {
		return ViewConstants.ADD_MATERIAL_VIEW;
	}

}

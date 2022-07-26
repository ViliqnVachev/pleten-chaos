package com.pletenchaos.pletenchaos.web;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pletenchaos.pletenchaos.model.binding.AddMaterialBinding;
import com.pletenchaos.pletenchaos.utils.common.PathConstants;
import com.pletenchaos.pletenchaos.utils.common.ViewConstants;

@Controller
@RequestMapping(PathConstants.MATERIAL)
public class MaterialController {

	@GetMapping(PathConstants.ADD_MATERIAL)
	public String getMaterial() {
		return ViewConstants.ADD_MATERIAL_VIEW;
	}

	@PostMapping(PathConstants.ADD_MATERIAL)
	public String addMaterial(@Valid AddMaterialBinding addMaterila, BindingResult bindingResult,
			RedirectAttributes attributes) {
		return null;
	}

}

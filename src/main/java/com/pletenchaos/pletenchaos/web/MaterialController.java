package com.pletenchaos.pletenchaos.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pletenchaos.pletenchaos.model.binding.NewMaterialBinding;
import com.pletenchaos.pletenchaos.service.interfaces.IMaterialService;
import com.pletenchaos.pletenchaos.utils.common.PathConstants;
import com.pletenchaos.pletenchaos.utils.common.ViewConstants;

@Controller
@RequestMapping(PathConstants.MATERIAL)
public class MaterialController {

	private final IMaterialService materialService;

	@Autowired
	public MaterialController(IMaterialService materialService) {
		this.materialService = materialService;
	}

	@GetMapping(PathConstants.ADD_MATERIAL)
	public String getMaterialView(Model model) {
		return ViewConstants.ADD_MATERIAL_VIEW;
	}

	@PostMapping(PathConstants.ADD_MATERIAL)
	public String addMaterial(@Valid NewMaterialBinding newMaterialBinding, BindingResult bindingResult,
			RedirectAttributes attributes) {

		// check for errors
		if (bindingResult.hasErrors()) {
			attributes.addFlashAttribute("newMaterialBinding", newMaterialBinding)//
					.addFlashAttribute("org.springframework.validation.BindingResult.newMaterialBinding",
							bindingResult);
			return PathConstants.REDIRECT_ADD_MATERIAL;
		}
		return null;
	}

	@ModelAttribute
	public NewMaterialBinding newMaterialBinding() {
		return new NewMaterialBinding();
	}

}
package com.pletenchaos.pletenchaos.utils.validators.users;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.pletenchaos.pletenchaos.model.binding.MaterialBinding;
import com.pletenchaos.pletenchaos.service.interfaces.IMaterialService;

public class MaterialValidatorUtil {
	public static void validateView(Model model, IMaterialService materialService, String binding, Long id) {
		BindingResult bindingResult = (BindingResult) model
				.getAttribute("org.springframework.validation.BindingResult.materialBinding");
		if (bindingResult == null) {
			MaterialBinding material = materialService.getMaterialById(id);
			model.addAttribute(binding, material);
			model.addAttribute("canDelete", true);
			return;
		}
		model.addAttribute(binding, bindingResult.getTarget());
		model.addAttribute("org.springframework.validation.BindingResult.materialBinding", bindingResult);
		model.addAttribute("canDelete", false);
	}
}

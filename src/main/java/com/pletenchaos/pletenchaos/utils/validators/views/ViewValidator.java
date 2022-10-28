package com.pletenchaos.pletenchaos.utils.validators.views;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.pletenchaos.pletenchaos.model.binding.MaterialBinding;
import com.pletenchaos.pletenchaos.model.binding.OrderBinding;
import com.pletenchaos.pletenchaos.service.interfaces.IMaterialService;

public class ViewValidator {
	public static void validateMaterialView(Model model, IMaterialService materialService, String binding, Long id) {
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


	public static void validateOrderView(Model model, OrderBinding orderBinding, String binding) {
		BindingResult bindingResult = (BindingResult) model
				.getAttribute("org.springframework.validation.BindingResult.orderBinding");
		if (bindingResult == null) {
			model.addAttribute(binding, orderBinding);
			return;
		}
		model.addAttribute(binding, bindingResult.getTarget());
		model.addAttribute("org.springframework.validation.BindingResult.orderBinding", bindingResult);
	}
}

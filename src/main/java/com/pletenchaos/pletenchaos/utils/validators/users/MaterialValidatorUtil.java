package com.pletenchaos.pletenchaos.utils.validators.users;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pletenchaos.pletenchaos.model.binding.NewMaterialBinding;
import com.pletenchaos.pletenchaos.service.interfaces.IMaterialService;

public class MaterialValidatorUtil {
	public static boolean isValid(RedirectAttributes attributes, NewMaterialBinding newMaterialBinding,
			BindingResult bindingResult, IMaterialService materialService) {
		boolean valid = true;

		if (materialService.isUnique(newMaterialBinding.getName())) {
			attributes.addFlashAttribute("isNotUnique", true);
			valid = false;
		}

		return valid;
	}
}

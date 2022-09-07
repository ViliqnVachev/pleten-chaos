package com.pletenchaos.pletenchaos.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pletenchaos.pletenchaos.model.binding.MaterialBinding;
import com.pletenchaos.pletenchaos.service.interfaces.IMaterialService;
import com.pletenchaos.pletenchaos.utils.common.PathConstants;
import com.pletenchaos.pletenchaos.utils.common.ViewConstants;
import com.pletenchaos.pletenchaos.utils.validators.users.MaterialValidatorUtil;

@Controller
@RequestMapping(PathConstants.MATERIAL)
public class MaterialController {

	private final IMaterialService materialService;

	@Autowired
	public MaterialController(IMaterialService materialService) {
		this.materialService = materialService;
	}

	@GetMapping(PathConstants.ADD_MATERIAL)
	public String getMaterialView() {
		return ViewConstants.ADD_MATERIAL_VIEW;
	}

	@GetMapping(PathConstants.AVAILABLE_MATERIAL)
	public String getAvailableMaterialView(Model model, @AuthenticationPrincipal User user) {
		List<MaterialBinding> materials = materialService.getMaterialsByUser(user.getUsername());
		model.addAttribute("materials", materials);
		return ViewConstants.AVAILABLE_MATERIAL_VIEW;
	}

	@PreAuthorize("@materialServiceImpl.isOwner(#principal.name, #id)")
	@GetMapping(PathConstants.ID)
	public String getMaterial(@PathVariable Long id, Model model) {
		MaterialBinding material = materialService.getMaterialById(id);
		model.addAttribute("material", material);
		return null;
	}

	@PostMapping(PathConstants.ADD_MATERIAL)
	public String addMaterial(@Valid MaterialBinding materialBinding, BindingResult bindingResult,
			RedirectAttributes attributes, @AuthenticationPrincipal User user) {

		// check for errors
		if (bindingResult.hasErrors()
				|| !MaterialValidatorUtil.isValid(attributes, materialBinding, bindingResult, materialService)) {
			attributes.addFlashAttribute("materialBinding", materialBinding)//
					.addFlashAttribute("org.springframework.validation.BindingResult.newMaterialBinding",
							bindingResult);
			return PathConstants.REDIRECT_ADD_MATERIAL;
		}

		// TODO: check for errors
		if(	!materialService.addMaterial(materialBinding, user.getUsername())) {
			return null;
		}

		return PathConstants.REDIRECT_AVAILABLE_MATERIAL;
	}

	@ModelAttribute
	public MaterialBinding materialBinding() {
		return new MaterialBinding();
	}

}
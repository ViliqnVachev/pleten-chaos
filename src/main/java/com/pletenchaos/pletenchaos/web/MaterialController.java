package com.pletenchaos.pletenchaos.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pletenchaos.pletenchaos.model.binding.MaterialBinding;
import com.pletenchaos.pletenchaos.model.binding.UpdateMaterialBinding;
import com.pletenchaos.pletenchaos.service.interfaces.IMaterialService;
import com.pletenchaos.pletenchaos.utils.common.PathConstants;
import com.pletenchaos.pletenchaos.utils.common.Views;
import com.pletenchaos.pletenchaos.utils.validators.views.ViewValidator;

@Controller
@RequestMapping(PathConstants.MATERIAL)
public class MaterialController {

	private static final String MATERIAL_BINDING = "materialBinding";

	private final IMaterialService materialService;

	@Autowired
	public MaterialController(IMaterialService materialService) {
		this.materialService = materialService;
	}

	@GetMapping(PathConstants.ADD)
	public String getAddMaterialView() {
		return Views.ADD_MATERIAL_VIEW;
	}

	@GetMapping(PathConstants.AVAILABLE_MATERIAL)
	public String getAvailableMaterialView(Model model, @AuthenticationPrincipal User user) {
		List<MaterialBinding> materials = materialService.getMaterialsByUser(user.getUsername());
		model.addAttribute("materials", materials);
		return Views.AVAILABLE_MATERIAL_VIEW;
	}

	@PreAuthorize("@materialServiceImpl.isOwner(#principal.name, #id)")
	@GetMapping(PathConstants.ID)
	public String getMaterial(@PathVariable Long id, Model model, HttpServletRequest request) {
		ViewValidator.validateMaterialView(model, materialService, MATERIAL_BINDING, id);
		return Views.MATERILA_DETAIL;
	}

	@PreAuthorize("@materialServiceImpl.isOwner(#principal.name, #id)")
	@DeleteMapping(PathConstants.DELETE_MATERIAL)
	public String deleteMaterial(@PathVariable Long id) {
		materialService.deleteMaterial(id);
		return PathConstants.REDIRECT_AVAILABLE_MATERIAL;
	}

	@PreAuthorize("@materialServiceImpl.isOwner(#principal.name, #id)")
	@PutMapping(PathConstants.UPDATE_MATERIAL)
	public String updateMaterial(@PathVariable Long id, @Valid UpdateMaterialBinding updatedMaterial,
			BindingResult bindingResult, RedirectAttributes attributes) {

		// check for errors
		if (bindingResult.hasErrors()) {
			attributes.addFlashAttribute(MATERIAL_BINDING, updatedMaterial)//
					.addFlashAttribute("org.springframework.validation.BindingResult.materialBinding", bindingResult);
			return PathConstants.REDIRECT_MATERIAL_DETAIL.replace("{id}", String.valueOf(id));
		}
		materialService.updateMaterial(id, updatedMaterial);
		return PathConstants.REDIRECT_AVAILABLE_MATERIAL;
	}

	@PostMapping(PathConstants.ADD)
	public String addMaterial(@Valid MaterialBinding materialBinding, BindingResult bindingResult,
			RedirectAttributes attributes, @AuthenticationPrincipal User user) {

		// check for errors
		if (bindingResult.hasErrors()) {
			attributes.addFlashAttribute(MATERIAL_BINDING, materialBinding)//
					.addFlashAttribute("org.springframework.validation.BindingResult.materialBinding", bindingResult);
			return PathConstants.REDIRECT_ADD_MATERIAL;
		}

		// TODO: check for errors
		if (!materialService.addMaterial(materialBinding, user.getUsername())) {
			return null;
		}

		return PathConstants.REDIRECT_AVAILABLE_MATERIAL;
	}

	@ModelAttribute
	public MaterialBinding materialBinding() {
		return new MaterialBinding();
	}
}
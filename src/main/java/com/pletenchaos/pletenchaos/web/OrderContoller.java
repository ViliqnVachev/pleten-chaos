package com.pletenchaos.pletenchaos.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pletenchaos.pletenchaos.model.binding.MaterialBinding;
import com.pletenchaos.pletenchaos.model.binding.OrderBinding;
import com.pletenchaos.pletenchaos.service.interfaces.IMaterialService;
import com.pletenchaos.pletenchaos.service.interfaces.IOrderService;
import com.pletenchaos.pletenchaos.utils.common.PathConstants;
import com.pletenchaos.pletenchaos.utils.common.Views;
import com.pletenchaos.pletenchaos.utils.factories.BindingFactory;
import com.pletenchaos.pletenchaos.utils.validators.views.ViewValidator;

@Controller
@RequestMapping(PathConstants.ORDER)
public class OrderContoller {

	private static final String BINDING_ORDER = "orderBinding";

	private final IOrderService orderService;

	private final IMaterialService materialService;

	@Autowired
	public OrderContoller(IOrderService orderService, IMaterialService materialService) {
		this.orderService = orderService;
		this.materialService = materialService;
	}

	@GetMapping(PathConstants.ADD)
	public String getAddOrderView(Model model, @AuthenticationPrincipal User user) {
		List<MaterialBinding> materials = materialService.getMaterialsByUser(user.getUsername());
		OrderBinding orderBinding = BindingFactory.createOrder(materials);

		ViewValidator.validateOrderView(model, orderBinding, BINDING_ORDER);
		return Views.ADD_ORDER_VIEW;
	}

	@PostMapping(PathConstants.ADD)
	public String addOrder(@Valid OrderBinding orderBinding, BindingResult bindingResult, RedirectAttributes attributes,
			@AuthenticationPrincipal User user) {
		// check for errors
		if (bindingResult.hasErrors()) {
			attributes.addFlashAttribute(BINDING_ORDER, orderBinding)//
					.addFlashAttribute("org.springframework.validation.BindingResult.orderBinding", bindingResult);
			return PathConstants.REDIRECT_ADD_ORDER;
		}

		// TODO: check for errors
		if (!orderService.addOrder(orderBinding, user.getUsername())) {
			return null;
		}

		return PathConstants.REDIRECT_HOME;
	}

	@ModelAttribute
	public OrderBinding orderBinding() {
		return new OrderBinding();
	}
}

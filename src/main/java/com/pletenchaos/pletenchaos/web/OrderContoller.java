package com.pletenchaos.pletenchaos.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pletenchaos.pletenchaos.model.binding.OrderBinding;
import com.pletenchaos.pletenchaos.service.interfaces.IMaterialService;
import com.pletenchaos.pletenchaos.service.interfaces.IOrderService;
import com.pletenchaos.pletenchaos.utils.common.PathConstants;
import com.pletenchaos.pletenchaos.utils.common.Views;

@Controller
@RequestMapping(PathConstants.ORDER)
public class OrderContoller {

	private final IOrderService orderService;

	private final IMaterialService materialService;

	@Autowired
	public OrderContoller(IOrderService orderService, IMaterialService materialService) {
		this.orderService = orderService;
		this.materialService = materialService;
	}

	@GetMapping(PathConstants.ADD)
	public String getAddOrderView() {
		return Views.ADD_ORDER_VIEW;
	}

	@ModelAttribute
	public OrderBinding orderBinding() {
		return new OrderBinding();
	}

}

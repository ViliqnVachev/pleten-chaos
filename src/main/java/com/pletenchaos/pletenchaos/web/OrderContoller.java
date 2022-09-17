package com.pletenchaos.pletenchaos.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pletenchaos.pletenchaos.service.interfaces.IOrderService;
import com.pletenchaos.pletenchaos.utils.common.PathConstants;
import com.pletenchaos.pletenchaos.utils.common.Views;

@Controller
@RequestMapping(PathConstants.ORDER)
public class OrderContoller {

	private final IOrderService orderService;

	@Autowired
	public OrderContoller(IOrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping(PathConstants.ADD)
	public String getAddOrderView() {
		return Views.ADD_ORDER_VIEW;
	}

}

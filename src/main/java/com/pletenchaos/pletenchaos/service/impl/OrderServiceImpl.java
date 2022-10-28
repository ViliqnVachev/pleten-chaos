package com.pletenchaos.pletenchaos.service.impl;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.pletenchaos.pletenchaos.model.binding.OrderBinding;
import com.pletenchaos.pletenchaos.service.interfaces.IOrderService;

@Service
public class OrderServiceImpl implements IOrderService {

	@Override
	public boolean addOrder(@Valid OrderBinding orderBinding, String username) {
		// TODO Auto-generated method stub
		return false;
	}

}

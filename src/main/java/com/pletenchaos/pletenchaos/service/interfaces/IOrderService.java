package com.pletenchaos.pletenchaos.service.interfaces;

import javax.validation.Valid;

import com.pletenchaos.pletenchaos.model.binding.OrderBinding;

public interface IOrderService {

	boolean addOrder(@Valid OrderBinding orderBinding, String username);

}

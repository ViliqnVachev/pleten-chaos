package com.pletenchaos.pletenchaos.utils.factories;

import java.util.List;

import com.pletenchaos.pletenchaos.model.binding.MaterialBinding;
import com.pletenchaos.pletenchaos.model.binding.OrderBinding;

public class BindingFactory {
	public static OrderBinding createOrder(List<MaterialBinding> materials) {
		OrderBinding orderBinding = new OrderBinding();
		orderBinding.setMaterials(materials);
		return orderBinding;
	}
}

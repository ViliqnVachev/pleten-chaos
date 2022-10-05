package com.pletenchaos.pletenchaos.model.binding;

import java.util.List;

public class OrderBinding extends BaseBindingModel {

	private List<MaterialBinding> materials;

	public OrderBinding() {
	}

	public List<MaterialBinding> getMaterials() {
		return materials;
	}

	public void setMaterials(List<MaterialBinding> materials) {
		this.materials = materials;
	}
}

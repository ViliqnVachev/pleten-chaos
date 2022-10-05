package com.pletenchaos.pletenchaos.model.binding;

import com.pletenchaos.pletenchaos.model.entity.enums.RoleEnum;

public class RoleBinding extends BaseModel {

	private RoleEnum role;

	public RoleBinding() {
	}

	public RoleEnum getRole() {
		return role;
	}

	public void setRole(RoleEnum role) {
		this.role = role;
	}

}

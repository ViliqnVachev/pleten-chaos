package com.pletenchaos.pletenchaos.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.pletenchaos.pletenchaos.model.entity.enums.RoleEnum;

@Entity
@Table(name = "roles")
public class RoleEntity extends BaseEntity {

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private RoleEnum role;

	public RoleEntity() {
	}

	public RoleEnum getRole() {
		return role;
	}

	public void ListRole(RoleEnum role) {
		this.role = role;
	}

}

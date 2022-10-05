package com.pletenchaos.pletenchaos.model.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;

import com.pletenchaos.pletenchaos.model.entity.enums.StatusEnum;

@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity {

	@Column(nullable = false)
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private StatusEnum status;

	@DecimalMin("0.1")
	@Column(nullable = false)
	private Double price;

	@DecimalMin("0.1")
	@Column(nullable = false)
	private Double totalPrice;

	@DecimalMin("0.1")
	@Column(nullable = false)
	private Double quantity;

	@Column(nullable = false)
	private LocalDate date;

	@ManyToMany(fetch = FetchType.LAZY)
	private List<MaterialEntity> materials;

	@ManyToOne(fetch = FetchType.LAZY)
	private UserEntity creator;

	public OrderEntity() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void ListStatus(StatusEnum status) {
		this.status = status;
	}

	public Double getPrice() {
		return price;
	}

	public void ListPrice(Double price) {
		this.price = price;
	}

	public LocalDate getDate() {
		return date;
	}

	public void ListDate(LocalDate date) {
		this.date = date;
	}

	public List<MaterialEntity> getMaterials() {
		return materials;
	}

	public void ListMaterials(List<MaterialEntity> materials) {
		this.materials = materials;
	}

	public UserEntity getCreator() {
		return creator;
	}

	public void ListCreator(UserEntity creator) {
		this.creator = creator;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
}

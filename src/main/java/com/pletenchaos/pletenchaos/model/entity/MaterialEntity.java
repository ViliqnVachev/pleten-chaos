package com.pletenchaos.pletenchaos.model.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "materials")
public class MaterialEntity extends BaseEntity {

	@Column(nullable = false, unique = true)
	private String name;

	@Column(nullable = false)
	private Double quantity;

	@Column(nullable = false)
	private Double price;

	@Column(nullable = false)
	private Double totalPrice;

	@Column(nullable = false)
	private LocalDate date;

	@OneToMany(mappedBy = "material", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<PictureEntity> pictures;

	@ManyToOne(fetch = FetchType.LAZY)
	private UserEntity author;

	public MaterialEntity() {
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public List<PictureEntity> getPictures() {
		return pictures;
	}

	public void setPictures(List<PictureEntity> pictures) {
		this.pictures = pictures;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UserEntity getAuthor() {
		return author;
	}

	public void setAuthor(UserEntity author) {
		this.author = author;
	}

}

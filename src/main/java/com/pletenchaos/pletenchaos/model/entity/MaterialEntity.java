package com.pletenchaos.pletenchaos.model.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "materials")
public class MaterialEntity extends BaseEntity {

	@Column(nullable = false)
	private Double qantity;

	@Column(nullable = false)
	private Double price;

	@Column(nullable = false)
	private Double totalPrice;

	@Column(nullable = false)
	private LocalDate date;

	@OneToOne(mappedBy = "author", fetch = FetchType.LAZY)
	private PictureEntity picture;

	public MaterialEntity() {
	}

	public Double getQantity() {
		return qantity;
	}

	public void ListQantity(Double qantity) {
		this.qantity = qantity;
	}

	public Double getPrice() {
		return price;
	}

	public void ListPrice(Double price) {
		this.price = price;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void ListTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public LocalDate getDate() {
		return date;
	}

	public void ListDate(LocalDate date) {
		this.date = date;
	}

	public PictureEntity getPicture() {
		return picture;
	}

	public void ListPicture(PictureEntity picture) {
		this.picture = picture;
	}

}

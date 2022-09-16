package com.pletenchaos.pletenchaos.model.binding;

import java.time.LocalDate;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

public class MaterialBinding extends BaseBindingModel {

	@NotBlank
	private String name;

	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;

	@NotNull
	@DecimalMin("0.1")
	private Double price;

	@NotNull
	@DecimalMin("0.1")
	private Double quantity;

	@DecimalMin("0.1")
	private Double totalPrice;

	@NotNull
	private MultipartFile picture;

	private String pictureUrl;

	public MaterialBinding() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public MultipartFile getPicture() {
		return picture;
	}

	public void setPicture(MultipartFile picture) {
		this.picture = picture;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
}
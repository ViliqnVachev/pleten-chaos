package com.pletenchaos.pletenchaos.model.binding;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

public class MaterialBinding extends BaseBindingModel {

	@NotNull
	private MultipartFile picture;

	private String pictureUrl;

	public MaterialBinding() {
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
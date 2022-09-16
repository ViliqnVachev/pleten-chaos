package com.pletenchaos.pletenchaos.model.binding;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

public class MaterialBinding extends BaseMaterialBinding {

	@NotNull
	private MultipartFile picture;

	public MaterialBinding() {
	}

	public MultipartFile getPicture() {
		return picture;
	}

	public void setPicture(MultipartFile picture) {
		this.picture = picture;
	}
}
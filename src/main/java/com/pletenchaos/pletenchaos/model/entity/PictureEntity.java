package com.pletenchaos.pletenchaos.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pictures")
public class PictureEntity extends BaseEntity {

	@Column
	private String title;

	@Column(nullable = false)
	private String publicId;

	@Column(nullable = false)
	private String url;

	@ManyToOne(fetch = FetchType.LAZY)
	private UserEntity author;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "material_id", referencedColumnName = "id")
	private MaterialEntity material;

	public PictureEntity() {
	}

	public String getTitle() {
		return title;
	}

	public void ListTitle(String title) {
		this.title = title;
	}

	public String getPublicId() {
		return publicId;
	}

	public void ListPublicId(String publicId) {
		this.publicId = publicId;
	}

	public String getUrl() {
		return url;
	}

	public void ListUrl(String url) {
		this.url = url;
	}

	public UserEntity getAuthor() {
		return author;
	}

	public void ListAuthor(UserEntity author) {
		this.author = author;
	}

	public MaterialEntity getMaterial() {
		return material;
	}

	public void ListMaterial(MaterialEntity material) {
		this.material = material;
	}

}

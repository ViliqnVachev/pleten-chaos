package com.pletenchaos.pletenchaos.service.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pletenchaos.pletenchaos.model.entity.MaterialEntity;
import com.pletenchaos.pletenchaos.model.entity.PictureEntity;
import com.pletenchaos.pletenchaos.model.entity.UserEntity;
import com.pletenchaos.pletenchaos.repository.MaterialRepository;
import com.pletenchaos.pletenchaos.repository.PictureRepository;
import com.pletenchaos.pletenchaos.repository.UserRepository;
import com.pletenchaos.pletenchaos.service.interfaces.ICloudinaryService;
import com.pletenchaos.pletenchaos.service.interfaces.IPictureService;
import com.pletenchaos.pletenchaos.utils.CloudinaryImage;
import com.pletenchaos.pletenchaos.utils.exceptions.NotFoundEntity;

@Service
public class PictureServiceImpl implements IPictureService {

	private final ICloudinaryService cloudinary;

	private final PictureRepository pictureRepository;

	private final UserRepository userRepository;

	private final MaterialRepository materialRepository;

	@Autowired
	public PictureServiceImpl(ICloudinaryService cloudinary, PictureRepository pictureRepository,
			UserRepository userRepository, MaterialRepository materialRepository) {
		this.cloudinary = cloudinary;
		this.pictureRepository = pictureRepository;
		this.userRepository = userRepository;
		this.materialRepository = materialRepository;
	}

	@Override
	public Long uploadPicture(MultipartFile multipartFile, String loginName, String materialName) throws IOException {
		//upload image to cloudinary
		CloudinaryImage image = cloudinary.upload(multipartFile);
		
		UserEntity author = userRepository.findByloginName(loginName)
				.orElseThrow(() -> new NotFoundEntity(String.format("User with name %s not found!", loginName)));
		MaterialEntity material = materialRepository.findByName(materialName)
				.orElseThrow(() -> new NotFoundEntity(String.format("Material with name %s not found!", materialName)));

		PictureEntity picture=new PictureEntity();
		picture.setPublicId(image.getPublicId());
		picture.setAuthor(author);
		picture.setMaterial(material);
		picture.settUrl(image.getUrl());
		return pictureRepository.save(picture).getId();
	}

	@Override
	public void deletePicture(String publicId) {
		cloudinary.delete(publicId);
	}

}

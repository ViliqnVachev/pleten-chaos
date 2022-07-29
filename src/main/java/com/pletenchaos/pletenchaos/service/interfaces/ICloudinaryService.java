package com.pletenchaos.pletenchaos.service.interfaces;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.pletenchaos.pletenchaos.utils.CloudinaryImage;

public interface ICloudinaryService {
	CloudinaryImage upload(MultipartFile file) throws IOException;

	boolean delete(String publicId);
}
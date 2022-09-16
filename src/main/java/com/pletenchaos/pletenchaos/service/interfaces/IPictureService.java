package com.pletenchaos.pletenchaos.service.interfaces;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface IPictureService {

	Long uploadPicture(MultipartFile multipartFile, String loginName, String materialName) throws IOException;

	void deletePicture(Long pictureId);

}

package com.pletenchaos.pletenchaos.config;

import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import com.cloudinary.Cloudinary;
import com.pletenchaos.pletenchaos.utils.common.CloudinaryConstants;

@Configuration
public class AppConfiguration {

	private final CloudinaryConfig cnf;

	@Autowired
	public AppConfiguration(CloudinaryConfig cnf) {
		this.cnf = cnf;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new Pbkdf2PasswordEncoder();
	}

	@Bean
	public ModelMapper modelmapper() {
		return new ModelMapper();
	}

	@Bean
	public Cloudinary cloudinary() {
		return new Cloudinary(Map.of(CloudinaryConstants.CLOUD_NAME, cnf.getCloudName(), CloudinaryConstants.API_KEY,
				cnf.getApiKey(), CloudinaryConstants.API_SECRET, cnf.getApiSecret()));
	}

}

package com.pletenchaos.pletenchaos.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundEntity extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotFoundEntity(String message) {
		super(message);
	}

}

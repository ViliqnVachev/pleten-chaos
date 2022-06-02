package com.pletenchaos.pletenchaos.web.events;

import org.springframework.context.ApplicationEvent;

public class EmailEvent extends ApplicationEvent {

	private static final long serialVersionUID = 7099057708183571937L;

	private String username;


	private String email;

	public EmailEvent(Object source, String username, String email) {
		super(source);
		this.username = username;
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}
}

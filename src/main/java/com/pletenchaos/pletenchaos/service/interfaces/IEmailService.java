package com.pletenchaos.pletenchaos.service.interfaces;

public interface IEmailService {
	void sendEmail(String to, String subject, String message);
}

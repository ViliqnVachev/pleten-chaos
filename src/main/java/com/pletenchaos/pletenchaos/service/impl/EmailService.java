package com.pletenchaos.pletenchaos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.pletenchaos.pletenchaos.service.interfaces.IEmailService;
import com.pletenchaos.pletenchaos.utils.common.EmailConstants;

@Service
public class EmailService implements IEmailService {

	private final JavaMailSender mailSender;

	@Autowired
	public EmailService(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Override
	public void sendEmail(String to, String subject, String message) {

		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(to);
		mailMessage.setFrom(EmailConstants.SENDER);
		mailMessage.setSubject(subject);
		mailMessage.setText(message);
		mailSender.send(mailMessage);
	}

}

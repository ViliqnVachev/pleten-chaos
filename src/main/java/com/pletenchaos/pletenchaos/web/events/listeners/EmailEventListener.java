package com.pletenchaos.pletenchaos.web.events.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.pletenchaos.pletenchaos.service.interfaces.IEmailService;
import com.pletenchaos.pletenchaos.utils.common.EmailConstants;
import com.pletenchaos.pletenchaos.web.events.EmailEvent;

@Component
public class EmailEventListener {

	private final static Logger LOGGER = LoggerFactory.getLogger(EmailEventListener.class);

	private final IEmailService emailService;

	@Autowired
	public EmailEventListener(IEmailService emailService) {
		this.emailService = emailService;
	}

	@EventListener(EmailEvent.class)
	public void onRegister(EmailEvent event) {
		emailService.sendEmail(event.getEmail(), EmailConstants.SUBJECT,
				String.format(EmailConstants.REGISTER_CONTENT_TEMPLATE, event.getUsername()));

		LOGGER.info("Sending an email");
	}

}

package com.scientific.publications.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

/**
 * Utility class that encapsulating calls to the message archive of the
 * {@link MessageSource}
 */
@Service
public class BundleMessage {

	/**
	 * retrieve the messages from the messages.properties
	 */
	@Autowired
	private MessageSource messageSource;

	/**
	 * load message
	 *
	 * @param message code
	 * @param args parameters
	 * @return message
	 */
	public String getMessage(String message, Object... args) {
		return messageSource.getMessage(message, args, LocaleContextHolder.getLocale());
	}

	/**
	 * load message from {@link FieldError}
	 *
	 * @param fieldError Encapsulates a field error
	 * @return message
	 */
	public String getMessage(FieldError fieldError) {
		return messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
	}

}

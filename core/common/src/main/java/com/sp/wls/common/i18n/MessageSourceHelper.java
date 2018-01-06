package com.sp.wls.common.i18n;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

public class MessageSourceHelper {
	private MessageSource messageSource;

	public MessageSourceHelper() {
	}

	public MessageSourceHelper(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public String getMessage(String messageKey) {
		return messageSource.getMessage(messageKey, new Object[0], LocaleContextHolder.getLocale());
	}

	public String getMessage(String messageKey, Object... parameters) {
		return messageSource.getMessage(messageKey, parameters, LocaleContextHolder.getLocale());
	}

	public String getMessage(Locale locale, String messageKey) {
		return messageSource.getMessage(messageKey, new Object[0], locale);
	}

	public String getMessage(Locale locale, String messageKey, Object... parameters) {
		return messageSource.getMessage(messageKey, parameters, locale);
	}

}

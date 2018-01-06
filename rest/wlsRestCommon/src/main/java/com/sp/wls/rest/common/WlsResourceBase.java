package com.sp.wls.rest.common;

import com.sp.wls.common.Context;
import com.sp.wls.common.exceptions.WlsException;
import com.sp.wls.common.i18n.MessageSourceHelper;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

import javax.ws.rs.HeaderParam;

public abstract class WlsResourceBase implements MessageSourceAware {

	protected MessageSourceHelper messageSourceHelper;
	private MessageSource messageSource;

	@SuppressWarnings("FieldCanBeLocal")
	@HeaderParam("TenantId")
	private String tenantId = "";

	protected Context getContext() {
		Context context = new Context();
		context.setTenantId(tenantId);
		return context;
	}

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
		messageSourceHelper = new MessageSourceHelper(messageSource);
	}

	protected String convertReasonsToText(WlsException e) {
		return e.convertReasonsToText(messageSource);
	}
}

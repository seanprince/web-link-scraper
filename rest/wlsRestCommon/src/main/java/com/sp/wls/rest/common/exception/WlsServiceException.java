package com.sp.wls.rest.common.exception;

import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public abstract class WlsServiceException extends RuntimeException {

	private String type;
	private String title;
	private int httpStatusCode;
	private String instance;
	private List<Pair<String, String>> additionalData;

	WlsServiceException(String type, String title, String message, int httpStatusCode, List<Pair<String, String>> additionalData) {
		super(message);

		this.type = type;
		this.title = title;
		this.httpStatusCode = httpStatusCode;
		this.additionalData = additionalData;
	}

	public String getType() {
		return type;
	}

	public String getTitle() {
		return title;
	}

	public int getHttpStatusCode() {
		return httpStatusCode;
	}

	public String getDetail() {
		return getMessage();
	}

	public String getInstance() {
		return instance;
	}

	public void setInstance(String instance) {
		this.instance = instance;
	}

	public List<Pair<String, String>> getAdditionalData() {
		return additionalData;
	}
}

package com.sp.wls.rest.common.exception;

import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class BadRequestWlsServiceException extends WlsServiceException {

	private static final int STATUS_CODE = 400;

	public BadRequestWlsServiceException(String type, String title, String message) {
		super(type, title, message, STATUS_CODE, null);
	}

	public BadRequestWlsServiceException(String type, String title, String message, List<Pair<String, String>> additionalData) {
		super(type, title, message, STATUS_CODE, additionalData);
	}
}

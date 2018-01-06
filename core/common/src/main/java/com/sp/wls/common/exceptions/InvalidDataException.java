package com.sp.wls.common.exceptions;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.logging.Log;

import java.util.List;

public class InvalidDataException extends WlsException {

	public InvalidDataException(String message) {
		super(message);
	}

	public InvalidDataException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidDataException(String message, Iterable<Reason> reasons) {
		super(message, reasons);
	}

	public InvalidDataException(String message, Throwable cause, Iterable<Reason> reasons) {
		super(message, cause, reasons);
	}

	public InvalidDataException(Log logger, Severity severity, String message) {
		super(logger, severity, message);
	}

	public InvalidDataException(Log logger, Severity severity, String message, Throwable cause) {
		super(logger, severity, message, cause);
	}

	public InvalidDataException(Log logger, Severity severity, String message, Iterable<Reason> reasons) {
		super(logger, severity, message, reasons);
	}

	public InvalidDataException(Log logger, Severity severity, String message, Throwable cause, Iterable<Reason> reasons) {
		super(logger, severity, message, cause, reasons);
	}

	public InvalidDataException(Log logger, Severity severity, String message, Iterable<Reason> reasons, List<Pair<String, String>> additionalData) {
		super(logger, severity, message, reasons, additionalData);
	}
}

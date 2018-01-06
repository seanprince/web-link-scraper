package com.sp.wls.common.exceptions;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.logging.Log;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public abstract class WlsException extends RuntimeException {

	private List<Reason> reasons;

	public List<Reason> getReasons() {
		return reasons;
	}

	private List<Pair<String, String>> additionalData;

	public List<Pair<String, String>> getAdditionalData() {
		return additionalData;
	}

	protected WlsException(String message) {
		super(message);
	}

	protected WlsException(String message, Throwable cause) {
		super(message, cause);
	}

	protected WlsException(String message, Iterable<Reason> reasons) {
		super(message);
		this.reasons = addReasonsToList(reasons);
	}

	protected WlsException(String message, Throwable cause, Iterable<Reason> reasons) {
		super(message, cause);
		this.reasons = addReasonsToList(reasons);
	}

	protected WlsException(Log logger, Severity severity, String message) {
		super(message);
		this.Log(logger, severity, message);
	}

	protected WlsException(Log logger, Severity severity, String message, Throwable cause) {
		super(message, cause);
		this.Log(logger, severity, message, cause);
	}

	protected WlsException(Log logger, Severity severity, String message, Iterable<Reason> reasons) {
		super(message);
		this.Log(logger, severity, message);
		this.reasons = addReasonsToList(reasons);
	}

	protected WlsException(Log logger, Severity severity, String message, Throwable cause, Iterable<Reason> reasons) {
		super(message, cause);
		this.Log(logger, severity, message, cause);
		this.reasons = addReasonsToList(reasons);
	}

	public WlsException(Log logger, Severity severity, String message, Iterable<Reason> reasons, List<Pair<String, String>> additionalData) {
		super(message);
		this.Log(logger, severity, message);
		this.reasons = addReasonsToList(reasons);
		this.additionalData = additionalData;
	}

	private void Log(Log logger, Severity severity, String message) {
		switch (severity) {
		case ERROR:
			logger.error(message);
			break;
		case WARNING:
			logger.warn(message);
			break;
		case INFO:
			logger.info(message);
			break;
		}
	}

	private void Log(Log logger, Severity severity, String message, Throwable cause) {
		switch (severity) {
		case ERROR:
			logger.error(message, cause);
			break;
		case WARNING:
			logger.warn(message, cause);
			break;
		case INFO:
			logger.info(message, cause);
			break;
		}
	}

	private List<Reason> addReasonsToList(Iterable<Reason> reasons) {
		List<Reason> list = new ArrayList<>();
		reasons.iterator().forEachRemaining(list::add);
		return list;
	}

	public static Reason reason(String code) {
		return reason(code, new Object[0]);
	}

	public static Reason reason(String code, Object... params) {
		return new Reason(code, params);
	}

	public static List<Reason> reasons(String code) {
		return reasons(code, new Object[0]);
	}

	public static List<Reason> reasons(String code, List<Pair<String, String>> additionalData) {
		return reasons(code, additionalData.stream()
		                               .map(c -> c.getKey() + "::" + c.getValue())
		                               .collect(Collectors.joining(System.lineSeparator())));
	}

	public static List<Reason> reasons(String code, Object... params) {
		return Collections.singletonList(reason(code, params));
	}

	public static class Reason {
		private String code;
		private Object[] params;

		Reason(String code, Object[] params) {
			this.code = code;
			this.params = params;
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public Object[] getParams() {
			return params;
		}

		public void setParams(Object[] params) {
			this.params = params;
		}
	}

	public enum Severity {
		INFO,
		WARNING,
		ERROR
	}

	public String convertReasonsToText(MessageSource messageSource) {
		StringBuilder stringBuilder = new StringBuilder();
		boolean first = true;
		List<WlsException.Reason> reasons = getReasons();
		if (reasons != null) {
			for (WlsException.Reason reason : reasons) {
				if (!first) {
					stringBuilder.append(System.lineSeparator());
					first = false;
				}
				stringBuilder.append(messageSource.getMessage(reason.getCode(), reason.getParams(), LocaleContextHolder.getLocale()));
			}
		}
		return stringBuilder.toString();
	}

}

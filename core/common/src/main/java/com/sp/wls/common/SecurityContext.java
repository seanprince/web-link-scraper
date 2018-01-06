package com.sp.wls.common;

public class SecurityContext {
	private String headerName;
	private String headerValue;

	private String securityType;

	public String getHeaderName() {
		return headerName;
	}

	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}

	public String getHeaderValue() {
		return headerValue;
	}

	public void setHeaderValue(String headerValue) {
		this.headerValue = headerValue;
	}

	public void setSecurityType(String securityType) {
		this.securityType = securityType;
	}

	public String getSecurityType() {
		return securityType;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof SecurityContext)) {
			return false;
		}

		SecurityContext that = (SecurityContext) o;

		if (headerName != null ? !headerName.equals(that.headerName) : that.headerName != null) {
			return false;
		}
		if (headerValue != null ? !headerValue.equals(that.headerValue) : that.headerValue != null) {
			return false;
		}
		return securityType != null ? securityType.equals(that.securityType) : that.securityType == null;
	}

	@Override
	public int hashCode() {
		int result = headerName != null ? headerName.hashCode() : 0;
		result = 31 * result + (headerValue != null ? headerValue.hashCode() : 0);
		result = 31 * result + (securityType != null ? securityType.hashCode() : 0);
		return result;
	}
}

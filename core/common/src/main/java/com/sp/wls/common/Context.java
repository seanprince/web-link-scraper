package com.sp.wls.common;

import java.time.Instant;

public class Context {

	private String tenantId;
	private String currentUser;
	private SecurityContext securityContext;

	public SecurityContext getSecurityContext() {
		return securityContext;
	}

	public void setSecurityContext(SecurityContext securityContext) {
		this.securityContext = securityContext;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(String currentUser) {
		this.currentUser = currentUser;
	}

	public Instant getTimestamp() {
		return Instant.now();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Context)) {
			return false;
		}

		Context context = (Context) o;

		if (tenantId != null ? !tenantId.equals(context.tenantId) : context.tenantId != null) {
			return false;
		}
		if (currentUser != null ? !currentUser.equals(context.currentUser) : context.currentUser != null) {
			return false;
		}
		return securityContext != null ? securityContext.equals(context.securityContext) : context.securityContext == null;
	}

	@Override
	public int hashCode() {
		int result = tenantId != null ? tenantId.hashCode() : 0;
		result = 31 * result + (currentUser != null ? currentUser.hashCode() : 0);
		result = 31 * result + (securityContext != null ? securityContext.hashCode() : 0);
		return result;
	}
}

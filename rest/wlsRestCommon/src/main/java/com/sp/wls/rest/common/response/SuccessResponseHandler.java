package com.sp.wls.rest.common.response;

import com.sp.wls.rest.common.annotation.ModifySuccessResponseStatus;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Response.Status;
import java.io.IOException;
import java.lang.annotation.Annotation;

import static javax.ws.rs.HttpMethod.DELETE;
import static javax.ws.rs.HttpMethod.POST;
import static javax.ws.rs.HttpMethod.PUT;

public class SuccessResponseHandler implements ContainerResponseFilter {

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {

		if (Status.OK.getStatusCode() != responseContext.getStatus()) {
			return;
		}

		Annotation[] annotations = responseContext.getEntityAnnotations();
		for (Annotation annotation : annotations) {
			if (annotation.annotationType() == ModifySuccessResponseStatus.class) {
				try {
					ModifySuccessResponseStatus modifySuccessResponseStatus = (ModifySuccessResponseStatus) annotation;
					if (!modifySuccessResponseStatus.enabled()) {
						return;
					}

					if (modifySuccessResponseStatus.statusCode() != Status.OK) {
						responseContext.setStatus(modifySuccessResponseStatus.statusCode().getStatusCode());
						return;
					}
					break;
				} catch (Exception e) {
				}
			}
		}

		switch (requestContext.getMethod()) {
		case POST: {
			responseContext.setStatus(Status.CREATED.getStatusCode());
			break;
		}
		case DELETE:
		case PUT: {
			responseContext.setStatus(Status.NO_CONTENT.getStatusCode());
			break;
		}
		}
	}
}

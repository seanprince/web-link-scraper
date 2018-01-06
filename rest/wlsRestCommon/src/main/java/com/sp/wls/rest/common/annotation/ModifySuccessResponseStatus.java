package com.sp.wls.rest.common.annotation;

import javax.ws.rs.core.Response.Status;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ModifySuccessResponseStatus {

	boolean enabled() default true;

	Status statusCode() default Status.OK;
}
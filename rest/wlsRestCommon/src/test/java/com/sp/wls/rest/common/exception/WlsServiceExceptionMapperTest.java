package com.sp.wls.rest.common.exception;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class WlsServiceExceptionMapperTest {

	@Test
	public void toResponse_includes_exception_details() {

		final String type = "Test exception type";
		final String title = "Test exception title";
		final String message = "Test exception message";

		BadRequestWlsServiceException exception = new BadRequestWlsServiceException(type, title, message);

		assertThat(exception.getType(), is(equalTo(type)));
		assertThat(exception.getTitle(), is(equalTo(title)));
		assertThat(exception.getDetail(), is(equalTo(message)));
		assertThat(exception.getHttpStatusCode(), is(equalTo(exception.getHttpStatusCode())));
	}
}

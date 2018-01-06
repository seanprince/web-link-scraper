package com.sp.wls.rest.common.exception;

import com.sp.wls.rest.common.transfer.ErrorResponse;
import com.sp.wls.rest.common.transfer.NameValuePair;
import org.apache.commons.lang3.tuple.Pair;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.util.ArrayList;

public class WlsServiceExceptionMapper implements ExceptionMapper<WlsServiceException> {

	public Response toResponse(WlsServiceException exception) {

		ErrorResponse response = new ErrorResponse();
		response.setTitle(exception.getTitle());
		response.setStatus(Integer.toString(exception.getHttpStatusCode()));
		response.setType(exception.getType());
		response.setDetail(exception.getDetail());
		response.setInstance(exception.getInstance());
		response.setAdditionalData(new ArrayList<>());

		if (exception.getAdditionalData() != null) {

			for (Pair<String, String> additionalData : exception.getAdditionalData()) {

				NameValuePair data = new NameValuePair();
				data.setName(additionalData.getKey());
				data.setValue(additionalData.getValue());

				response.getAdditionalData().add(data);
			}
		}

		return Response.status(exception.getHttpStatusCode()).entity(response).type(MediaType.APPLICATION_JSON_TYPE).build();
	}
}

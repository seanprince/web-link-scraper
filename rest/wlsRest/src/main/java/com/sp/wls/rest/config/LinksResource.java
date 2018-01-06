package com.sp.wls.rest.config;

import com.sp.wls.rest.common.transfer.LinksResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/")
public interface LinksResource {

	@GET
	@Path("links")
	@Produces({"application/json"})
    LinksResponse links(@QueryParam("url") String url);
}

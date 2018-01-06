package com.sp.wls.rest.config;

import com.sp.wls.common.exceptions.InvalidDataException;
import com.sp.wls.rest.common.WlsResourceBase;
import com.sp.wls.rest.common.exception.BadRequestWlsServiceException;
import com.sp.wls.rest.common.transfer.LinksResponse;
import com.sp.wls.services.config.LinksService;

public class LinksResourceImpl extends WlsResourceBase implements LinksResource {

	private LinksService linksService;

	@Override
	public LinksResponse links(String url) {
		try {
			return new LinksResponse(linksService.getLinks(url));
		} catch (InvalidDataException e) {
			throw new BadRequestWlsServiceException("error.rest.links.invalidData",
													"Invalid data",
													e.getMessage());
		}
	}

	public void setLinksService(LinksService linksService) {
		this.linksService = linksService;
	}
}

package com.sp.wls.rest.common.transfer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sp.wls.common.LocatedHyperlinkModel;

import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlType(name = "LinksResponse", propOrder = {"links"})
public class LinksResponse {

	private List<LocatedHyperlinkModel> scrapedLinks;

	public LinksResponse() {
	}

	public LinksResponse(HashSet<LocatedHyperlinkModel> links) {

		scrapedLinks = new ArrayList<>();

		for (LocatedHyperlinkModel link : links) {
			scrapedLinks.add(new LocatedHyperlinkModel(link.getLinkUrl(), link.getCssSelector()));
		}
	}

	public List<LocatedHyperlinkModel> getLinks() {
		return scrapedLinks;
	}

	public void setLinks(List<LocatedHyperlinkModel> links) {
		this.scrapedLinks = links;
	}
}

package com.sp.wls.services.config;

import com.sp.wls.common.LocatedHyperlinkModel;
import com.sp.wls.common.exceptions.InvalidDataException;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.HashSet;

public class LinksServiceImpl implements LinksService {

	LinksServiceImpl() {
	}

	@Override
	public HashSet<LocatedHyperlinkModel> getLinks(String urlStr) throws InvalidDataException {

		if (StringUtils.isEmpty(urlStr)) {
			throw new InvalidDataException("URL cannot be null or empty");
		}

		try {
			new URL(urlStr);
		} catch (MalformedURLException e) {
			throw new InvalidDataException("URL '" + urlStr + "' is malformed");
		}

		HashSet<LocatedHyperlinkModel> links = new HashSet<>();

		try {
			Document document = Jsoup.connect(urlStr).get();
			Elements linksOnPage = document.select("a[href]");

			for (Element linkElement : linksOnPage) {
				String scrapedUrl = linkElement.attr("abs:href");
				links.add(new LocatedHyperlinkModel(scrapedUrl, linkElement.cssSelector()));
			}
		} catch (HttpStatusException e) {
			throw new InvalidDataException("HTTP error response from URL '" + urlStr + "'");
		} catch (SocketTimeoutException e) {
			throw new InvalidDataException("Connection to URL '" + urlStr + "' timed out");
		} catch (IOException e) {
			throw new InvalidDataException("Error getting HTML document from URL '" + urlStr + "'");
		}

		return links;
	}
}

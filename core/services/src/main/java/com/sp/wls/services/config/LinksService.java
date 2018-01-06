package com.sp.wls.services.config;

import com.sp.wls.common.LocatedHyperlinkModel;
import com.sp.wls.common.exceptions.InvalidDataException;

import java.util.HashSet;

public interface LinksService {

		HashSet<LocatedHyperlinkModel> getLinks(String urlStr) throws InvalidDataException;
}

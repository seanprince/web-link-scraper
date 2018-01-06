package com.sp.wls.rest.common.utils;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class XssStringObjectMapper extends ObjectMapper {

    private static final String MODULE_NAME = "HTML XSS Serializer";
    private static final String SNAPSHOT_INFO = "SNAPSHOT_INFO";

    public XssStringObjectMapper() {

        SimpleModule module = new SimpleModule(MODULE_NAME, new Version(1, 0, 0, SNAPSHOT_INFO));
        module.addSerializer(String.class, new JsonHtmlXssSerializer());
        registerModule(module);
    }
}

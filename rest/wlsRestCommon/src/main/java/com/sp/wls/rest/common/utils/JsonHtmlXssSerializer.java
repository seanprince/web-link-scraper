package com.sp.wls.rest.common.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.owasp.encoder.Encode;

import java.io.IOException;

public class JsonHtmlXssSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        if (value != null) {
            String encodedValue = Encode.forHtml(value);
            jsonGenerator.writeString(encodedValue);
        }
    }
}

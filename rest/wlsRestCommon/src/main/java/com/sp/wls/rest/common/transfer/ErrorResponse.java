package com.sp.wls.rest.common.transfer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@XmlType(name = "ErrorResponse", propOrder = {"type", "title", "status", "detail", "instance", "additionalData"})
public class ErrorResponse {

	private String type;
	private String title;
	private String status;
	private String detail;
	private String instance;
	private List<NameValuePair> additionalData = new ArrayList<>();

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getInstance() {
		return instance;
	}

	public void setInstance(String instance) {
		this.instance = instance;
	}

	public List<NameValuePair> getAdditionalData() {
		return additionalData;
	}

	public void setAdditionalData(List<NameValuePair> additionalData) {
		this.additionalData = additionalData;
	}

	@Override
	public String toString() {

		String string = "class ErrorResponse {\n";
		string += "    type: " + toIndentedString(type) + "\n";
		string += "    title: " + toIndentedString(title) + "\n";
		string += "    status: " + toIndentedString(status) + "\n";
		string += "    detail: " + toIndentedString(detail) + "\n";
		string += "    instance: " + toIndentedString(instance) + "\n";
		string += "    additionalData: " + toIndentedString(additionalData) + "\n";
		string += "}";

		return string;
	}

	private static String toIndentedString(Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}


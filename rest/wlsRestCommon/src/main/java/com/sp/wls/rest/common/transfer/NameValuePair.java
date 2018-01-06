package com.sp.wls.rest.common.transfer;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "NameValuePair", propOrder = {"name", "value"})
public class NameValuePair {

	private String name;
	private String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		String string = "class NameValuePair {\n";
		string += "    name: " + toIndentedString(name) + "\n";
		string += "    value: " + toIndentedString(value) + "\n";
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

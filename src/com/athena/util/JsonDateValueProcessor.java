package com.athena.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class JsonDateValueProcessor implements JsonValueProcessor {
	private String datePattern = "yyyy-MM-dd HH:mm:ss";// 默认样式，可以在构造方法中修改

	public JsonDateValueProcessor() {
		super();
	}

	public JsonDateValueProcessor(String datePattern) {
		super();
		this.datePattern = datePattern;
	}

	@Override
	public Object processArrayValue(Object value, JsonConfig jsonConfig) {
		try {
			if (value instanceof Date) {
				SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
				Date date = (Date) value;
				return sdf.format(date);
			}
			return value == null ? null : value.toString();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Object processObjectValue(String value, Object arg1, JsonConfig jsonConfig) {
		return processArrayValue(value, jsonConfig);
	}

	public String getDatePattern() {
		return datePattern;
	}

	public void setDatePattern(String datePattern) {
		this.datePattern = datePattern;
	}

}

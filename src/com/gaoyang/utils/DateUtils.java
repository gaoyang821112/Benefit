package com.gaoyang.utils;

public class DateUtils {

	public static String TimeStamp2Date(String timestampString, String formats){    
		Long timestamp = Long.parseLong(timestampString)*1000;    
		String date = new java.text.SimpleDateFormat(formats).format(new java.util.Date(timestamp));    
		return date;    
	}
	
	public static String TimeStamp2DateByType(String timestampString, int type){
		String formats = "";
		if (type == 1) {
			formats = "mm:ss";
		} else if (type == 2){
			formats = "HH:mm";
		} else if (type == 3){
			formats = "MM:dd";
		}
		Long timestamp = Long.parseLong(timestampString)*1000;    
		String date = new java.text.SimpleDateFormat(formats).format(new java.util.Date(timestamp));    
		return date;    
	}
}

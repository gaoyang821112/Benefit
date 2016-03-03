package com.gaoyang.utils;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SPUtils {
	private static final String APP_CACHE_NAME = "com.cloudicty.namecard";
	public static final String TOKEN = "TOKEN";
	public static final String USERID = "USERID";
	public static final String USERNAME = "USERNAME";
	public static final String PASSWORD = "PASSWORD";
	public static final String FIRSTOPEN = "FIRSTOPEN";
	public static final String DEVICEID = "DEVICEID";
	public static final String APPINTERVAL = "APPINTERVAL";

	private Context context;
	
	public SPUtils(Context context) {
		super();
		this.context = context;
	}

	public void saveSpInfo(Map<String, Object> map) {
		SharedPreferences sp = context.getSharedPreferences(APP_CACHE_NAME, 0);
		SharedPreferences.Editor editor;
		map.put("username", "gaoyang");
		map.put("isFirst", true);
		editor = sp.edit();
		Set<Map.Entry<String, Object>> set = map.entrySet();
		for (Iterator<Map.Entry<String, Object>> it = set.iterator(); it
				.hasNext();) {
			Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
			if (entry.getValue() instanceof java.lang.String) {
				editor.putString(entry.getKey(), entry.getValue().toString());
			}
			if (entry.getValue() instanceof java.lang.Boolean) {
				editor.putBoolean(entry.getKey(), Boolean.parseBoolean(entry.getValue().toString()));
			}
		}
		editor.commit();
	}
	
	public void putString(String key, String value) {
		SharedPreferences sp = context.getSharedPreferences(APP_CACHE_NAME, 0);
		Editor editor = sp.edit();
		editor.putString(key, value);
		editor.commit();
	}
	
	public void putBoolean(String key, boolean value) {
		SharedPreferences sp = context.getSharedPreferences(APP_CACHE_NAME, 0);
		Editor editor = sp.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}
	
	public String getString(String key) {
		SharedPreferences sp = context.getSharedPreferences(APP_CACHE_NAME, 0);
		return sp.getString(key, "");
	}
	
	public Boolean getBoolean(String key) {
		SharedPreferences sp = context.getSharedPreferences(APP_CACHE_NAME, 0);
		return sp.getBoolean(key, true);
	}


	public void saveFirstOpen() {
		SharedPreferences sp = context.getSharedPreferences(APP_CACHE_NAME, 0);
		SharedPreferences.Editor editor;
		editor = sp.edit();
		editor.putBoolean(FIRSTOPEN, false);
		editor.commit();
	}

	public boolean isFirstOpen() {
		SharedPreferences sp = context.getSharedPreferences(APP_CACHE_NAME, 0);
		return sp.getBoolean(FIRSTOPEN, true);
	}

}

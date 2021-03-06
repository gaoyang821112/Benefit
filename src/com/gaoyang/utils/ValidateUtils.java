package com.gaoyang.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtils {
	/**
     * method is used for checking valid email id format.
     * 
     * @param email
     * @return boolean true for valid false for invalid
     */
    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }
    
    public static String a(Map<String, String> paramMap, String paramString) {
		TreeMap localTreeMap = new TreeMap(paramMap);
		if ((paramString == null) || ("".equals(paramString)))
			paramString = "asc";
		if ("desc".equals(paramString)) {
			localTreeMap = new TreeMap(Collections.reverseOrder());
			localTreeMap.putAll(paramMap);
		}
		String str = "";
		Iterator localIterator = localTreeMap.entrySet().iterator();
		while (localIterator.hasNext()) {
			Map.Entry localEntry = (Map.Entry) localIterator.next();
			str = str + (String) localEntry.getKey() + "="
					+ (String) localEntry.getValue() + "&";
		}
		str = str.substring(0, str.lastIndexOf("&"));
		return str;
	}
	
	
	public static String b(String paramString) throws NoSuchAlgorithmException {
		MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
		localMessageDigest.update(paramString.getBytes());
		byte[] arrayOfByte = localMessageDigest.digest();
		StringBuilder localStringBuilder = new StringBuilder();
		for (int i = 0; i < arrayOfByte.length; i++) {
			localStringBuilder.append(Character.forDigit(
					0xF & arrayOfByte[i] >>> 4, 16));
			localStringBuilder.append(Character.forDigit(0xF & arrayOfByte[i],
					16));
		}
		return localStringBuilder.toString().toUpperCase();
	}
	
	public static void main(String[] args) {
		HashMap localHashMap = new HashMap();
//		localHashMap.put("orderAmount", "8200");
		localHashMap.put("orderAmount", "4500");
        localHashMap.put("quantity", "1");
        localHashMap.put("orderCardType", "4");
        localHashMap.put("productId", "0143000004896");
        localHashMap.put("userId", "17A8AF7157CD46FBAC97188626CA08A0");
        String str1 = ValidateUtils.a(localHashMap, "asc");
        String mac = "";
		try {
			mac = ValidateUtils.b(str1 + "0102030405060708");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		System.out.println(mac);
	}
}

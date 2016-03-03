package com.gaoyang.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.json.JSONObject;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.gaoyang.R;
import com.gaoyang.common.API_URL;
import com.gaoyang.common.UIHelper;


public class HttpUtils {
	
	private final static String bounday = UUID.randomUUID().toString().replaceAll("-", "");
	
	private final static String TAG = "HttpUtils" ;
	public static InputStream getImageViewInputStream(String url_path)
			throws IOException {
		InputStream inputStream = null;
		URL url = new URL(url_path);
		if (url != null) {
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(3000);
			conn.setRequestMethod("GET");
			conn.setDoInput(true);
			conn.setUseCaches(false);
			int response_code = conn.getResponseCode();
			if (response_code == 200) {
				inputStream = conn.getInputStream();
			}
		}
		return inputStream;
	}

	public static byte[] getImageViewArray(String url_path) {
		byte[] data = null;
		InputStream inputStream = null;
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			URL url = new URL(url_path);
			if (url != null) {
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setConnectTimeout(3000);
				conn.setRequestMethod("GET");
				conn.setDoInput(true);
				conn.setUseCaches(false);
				int response_code = conn.getResponseCode();
				int len = 0;
				byte[] b_data = new byte[1024];

				if (response_code == 200) {
					inputStream = conn.getInputStream();
					while ((len = inputStream.read(b_data)) != -1) {
						byteArrayOutputStream.write(b_data, 0, len);
					}
					data = byteArrayOutputStream.toByteArray();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return data;
	}

//	public static String postUrl(String requestUrl, Map<String, String> paramMap, Context context){
//		boolean isNetwork = UIHelper.isNetworkConnected(context);
//		if (isNetwork){
//			StringBuffer strbuffer = new StringBuffer();
//			String paramUrl = "";
//			if ((paramMap != null) && (paramMap.size() > 0)) {     
//	            for (Entry<String, String> entry : paramMap.entrySet()) {     
//	                String key = String.valueOf(entry.getKey());     
//	                String value = String.valueOf(entry.getValue());     
//	                strbuffer.append(key);
//	                strbuffer.append("=");
//	                strbuffer.append(value);
//	                strbuffer.append("&");
//	            }
//	            paramUrl = strbuffer.toString();     
//	            paramUrl = "?" + paramUrl.substring(0, (paramUrl.length() - 1));
//	        } 
//			
//			HttpURLConnection connection = null;
//			StringBuffer sb = new StringBuffer("");
//			try {
//				URL url = new URL(API_URL.ZSYH_ADDRESS + requestUrl + paramUrl);
//				connection = (HttpURLConnection) url.openConnection();
//				connection.setDoOutput(true);
//				connection.setDoInput(true);
//				connection.setRequestMethod("POST");
//				connection.setConnectTimeout(10000);
//				connection.setUseCaches(false);
////				connection.setInstanceFollowRedirects(true);
////				connection.setRequestProperty("Content-Type", "text/html");
//				connection.connect();
//
//				// 读取响应
//				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//				String lines;
//				while ((lines = reader.readLine()) != null) {
//			//		lines = new String(lines.getBytes(), "utf-8");
//					lines = new String(lines.getBytes());
//					sb.append(lines);
//				}
//				// 断开连接
//				reader.close();
//				connection.disconnect();
//			} catch (MalformedURLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (ProtocolException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (UnsupportedEncodingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} finally {
//				if (connection != null) {
//					connection.disconnect();
//				}
//			}
//			return sb.toString();
//		}
//		else {
//			Toast.makeText(context, R.string.app_on_network, Toast.LENGTH_SHORT).show();
//			return "";
//		}
//	}
	
	public static String postUrl4ZSYH(String requestUrl, Map<String, String> paramMap, Context context){
		boolean isNetwork = UIHelper.isNetworkConnected(context);
		if (isNetwork){
			StringBuffer strbuffer = new StringBuffer();
			String paramUrl = "";
			if ((paramMap != null) && (paramMap.size() > 0)) {     
	            for (Entry<String, String> entry : paramMap.entrySet()) {     
	                String key = String.valueOf(entry.getKey());     
	                String value = String.valueOf(entry.getValue());     
	                strbuffer.append(key);
	                strbuffer.append("=");
	                strbuffer.append(value);
	                strbuffer.append("&");
	            }
	            paramUrl = strbuffer.toString();     
	            paramUrl = paramUrl.substring(0, (paramUrl.length() - 1));
	        } 
			
			HttpURLConnection connection = null;
			StringBuffer sb = new StringBuffer("");
			try {
				URL url = new URL(requestUrl);
				connection = (HttpURLConnection) url.openConnection();
				connection.setDoOutput(true);
		        connection.setDoInput(true);
		        connection.setRequestMethod("POST");
		        connection.setUseCaches(false);
		        connection.setInstanceFollowRedirects(true);
		        connection.setRequestProperty("Accept", "*/*");
				connection.connect();
				
				DataOutputStream writer = new DataOutputStream(connection.getOutputStream());
		        
		        writer.writeBytes(paramUrl);
		        writer.flush();
		        writer.close();

				// 读取响应
				BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String lines;
				while ((lines = reader.readLine()) != null) {
					lines = new String(lines.getBytes());
					sb.append(lines);
				}
				// 断开连接
				reader.close();
				connection.disconnect();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (connection != null) {
					connection.disconnect();
				}
			}
			return sb.toString();
		}
		else {
			if (context != null) 
				Toast.makeText(context, R.string.app_on_network, Toast.LENGTH_SHORT).show();
			return "";
		}
	}
	
	private static String getContentFileHeader(String fileName) throws Exception{
		String start_line = "--" + bounday;
		StringBuffer body = new StringBuffer("");
		body.append(start_line).append("\r\n");
		body.append("Content-Disposition:").append(" form-data; filename=\"" + fileName + "\"; ").append("name=\"files\"").append("\r\n");
		body.append("Content-Type: application/octet-stream").append("\r\n");
		body.append("\r\n");
		return body.toString();
	}
	
	private static String getContentNormal(String name , String value) throws Exception{
		String start_line = "--" + bounday;
		StringBuffer body = new StringBuffer("");
		body.append(start_line).append("\r\n");
		body.append("Content-Disposition:").append(" form-data; name=\"" + name + "\"").append("\r\n\r\n");
		body.append(value).append("\r\n");
		return body.toString();
	}
	
	private static byte[] getByte(File file) throws Exception{
		DataInputStream dis = new DataInputStream(new FileInputStream(file));
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		int len = 0 ;
		byte[] b = new byte[1024];
		while((len = dis.read(b)) != -1){
			baos.write(b, 0, len);
		}
		baos.close();
		dis.close();
		return baos.toByteArray();
	}
	
	
//	public static void main(String[] args) throws JSONException {
//    	Map<String, String> paramMap = new HashMap<String, String>();
//		paramMap.put("userName", "gaoyang");
//		paramMap.put("password", "sfgfgfgfgf");
//		String requestUrl = "app/member/login.do";
//		String json = HttpUtils.postUrl(requestUrl, paramMap);
//		System.out.println(json);
//		JSONObject jsonObj = new JSONObject(json);
//		System.out.println(jsonObj.getString("msg"));
//	}
}

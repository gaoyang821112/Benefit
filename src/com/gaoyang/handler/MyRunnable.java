package com.gaoyang.handler;

import java.util.Map;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.gaoyang.common.API_URL;
import com.gaoyang.utils.HttpUtils;

public class MyRunnable implements Runnable {
	
	private Context context;
	private Handler data_handler;
	private Map<String, String> params = null;
	private Map<String, String> logParams = null;

	public MyRunnable(Context context, Handler data_handler, Map<String, String> params, Map<String, String> logParams) {
		super();
		this.context = context;
		this.params = params;
		this.data_handler = data_handler;
		this.logParams = logParams;
	}
	
	public String postZSYHNew() throws Exception{
		String productUrl = API_URL.ZSYH_ADDRESS2 + API_URL.ZSYH.PRODUCT_9POINT_LIST_URL2;
		String result = HttpUtils.postUrl4ZSYH(productUrl, params, context);
		return result;
	}

	public String postZSYH() throws Exception{
		String orderUrl = API_URL.ZSYH_ADDRESS + API_URL.ZSYH.PRODUCT_9POINT_LIST_URL3;
		String result = HttpUtils.postUrl4ZSYH(orderUrl, params, context);
		return result;
	}
	
	@Override
	public void run() {
		try {
			for (int i = 0; i < 1000; i++) {
				Message msg = Message.obtain();
				msg.what = 1;
				try{
                    String returnUrl = "";
                    int sleepTime = 10;
                    if (logParams.get("type").equals("1") || logParams.get("type").equals("2")) {
                        returnUrl = postZSYHNew();
                    } else if (logParams.get("type").equals("3")) {
                        returnUrl = postZSYH();
                        sleepTime = 1000;
                    }
					String str = "用户名:" + logParams.get("userName") + " 商品：" + logParams.get("productName") + " 返回参数：" + returnUrl;
					msg.obj = str;
					Thread.sleep(sleepTime);
				}catch(Exception ex){
					msg.what = 0 ;
					msg.obj = ex.getMessage();
					data_handler.sendMessage(msg);
				}
				data_handler.sendMessage(msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
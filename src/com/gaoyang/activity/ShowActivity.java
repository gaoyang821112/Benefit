package com.gaoyang.activity;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gaoyang.R;
import com.gaoyang.common.API_URL;
import com.gaoyang.handler.MyRunnable;
import com.gaoyang.utils.HttpUtils;
import com.gaoyang.utils.ValidateUtils;

public class ShowActivity extends Activity {
	
	private TextView textView3;
	
	private Context context;
	
	private Button button;
	
	ExecutorService exec = null;
	
	boolean flag = true;
	
	//1 九积分 2 周三五折 3 老活动
	String QIANG_TYPE = "3";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show);
		context = this.getApplicationContext();
		textView3 = (TextView) this.findViewById(R.id.textview3);
		exec = Executors.newFixedThreadPool(30);
		
		Intent intent = getIntent();
		String[] userArray = intent.getStringExtra("userStr").substring(0,intent.getStringExtra("userStr").length() - 1).split(",");
		String[] productArray = intent.getStringExtra("productStr").substring(0,intent.getStringExtra("productStr").length() - 1).split(",");
		
		button = (Button) this.findViewById(R.id.button1);
		button.setOnClickListener(new View.OnClickListener() {
			@SuppressLint("NewApi")
			@Override
			public void onClick(View arg0) {
//				exec.shutdownNow();
//				data_handler.getLooper().quit();
				flag = false;
			}
		});
		for (int i = 0; i < userArray.length; i++) {
			for (int j = 0; j < productArray.length; j++) {
				String urlStr = "";
				String userId = "";
				String userPhone = "";
				if (userArray[i].equals("高洋")) {
					urlStr = MainActivity.userArray[0][1];
					userId = MainActivity.userArray[0][2];
					userPhone = MainActivity.userArray[0][3];
				} else if (userArray[i].equals("王志强")) {
					urlStr = MainActivity.userArray[1][1];
					userId = MainActivity.userArray[1][2];
					userPhone = MainActivity.userArray[1][3];
				} else if (userArray[i].equals("荣佳丽")) {
					urlStr = MainActivity.userArray[2][1];
					userId = MainActivity.userArray[2][2];
					userPhone = MainActivity.userArray[2][3];
				} else if (userArray[i].equals("宋强")) {
					urlStr = MainActivity.userArray[3][1];
					userId = MainActivity.userArray[3][2];
					userPhone = MainActivity.userArray[3][3];
				} else if (userArray[i].equals("王鹏")) {
					urlStr = MainActivity.userArray[4][1];
					userId = MainActivity.userArray[4][2];
					userPhone = MainActivity.userArray[4][3];
				} else if (userArray[i].equals("王悦")) {
					urlStr = MainActivity.userArray[5][1];
					userId = MainActivity.userArray[5][2];
					userPhone = MainActivity.userArray[5][3];
				} else if (userArray[i].equals("李成")) {
					urlStr = MainActivity.userArray[6][1];
					userId = MainActivity.userArray[6][2];
					userPhone = MainActivity.userArray[6][3];
				}
				Map<String, String> params = new HashMap<String, String>();
				
				params.put("quantity", "1");
				params.put("userId", userId);
				params.put("productId", productArray[j].split("-")[1]);

				HashMap localHashMap = new HashMap();
				if (QIANG_TYPE.equals("1")) {
					//九积分参数
					String productUrl = API_URL.ZSYH_ADDRESS2 + API_URL.ZSYH.PRODUCT_9POINT_LIST_URL2;
					params.put("p1", "39");
					params.put("body", "{\"isCanRush\":\"1\",\"payType\":\"1111\",\"labelId\":\"01\",\"productNo\":\"" + productArray[j].split("-")[1] + "\",\"moduleType\":\"1\",\"accountNum\":1,\"mobilePhone\":\"" + userPhone + "\",\"bakNo\":\"" + productArray[j].split("-")[2] + "\"}");
					params.put("p6", "483942674");
					params.put("p2", "appStore");
					params.put("p7", "C7C5BC31E5544437BE1D43CF7F735627");
					params.put("p3", "22FCB3CBBC534C61B4775AD4740DB4E7f");
					params.put("p8", "AB316C39-E6F1-42D6-B84C-1D3D81AB5B2D");
					params.put("p4", "9B4B634C-1C27-4466-AC38-0858B1C674D2");
					params.put("p9", "83608d17bdf54f49a9a10188d51722f8");
					params.put("p0", "i");
					params.put("p10", "1526957E-EE15-4A11-8B00-1CB483CE6F21");
					params.put("p5", userId);
					params.put("syshead", "{\"trans_code\":\"SI_ORD0001\",\"chnlId\":\"01\",\"chnlUserId\":\"" + userId + "\",\"sessionId\":\"83608d17bdf54f49a9a10188d51722f8\"}");
//					String result = HttpUtils.postUrl4ZSYH(productUrl, params, context);
					Map<String, String> logParams = new HashMap<String, String>();
					logParams.put("userName", userArray[i]);
					logParams.put("productName", productArray[j].split("-")[0]);
					MyRunnable runner = new MyRunnable(context, data_handler, urlStr, params, logParams);
					exec.execute(runner);
				} else if (QIANG_TYPE.equals("2")) {
					String productUrl = API_URL.ZSYH_ADDRESS2 + API_URL.ZSYH.PRODUCT_9POINT_LIST_URL2;
					params.put("p1", "37");
					params.put("body", "{\"isCanRush\":\"0\",\"payType\":\"1000\",\"labelId\":\"01\",\"productNo\":\"2016011817094\",\"moduleType\":\"1\",\"accountNum\":1,\"mobilePhone\":\"" + userPhone + "\",\"bakNo\":\"201601181709400006\"}");
					params.put("p6", "481416200");
					params.put("p2", "appStore");
					params.put("p7", "C7C5BC31E5544437BE1D43CF7F735627");
					params.put("p3", "22FCB3CBBC534C61B4775AD4740DB4E7f");
					params.put("p8", "8E49C999-DF28-49E5-9173-A41FEF5B86D0");
					params.put("p4", "C9DCC9B2-38B9-4C28-878D-A27F8432BA0E");
					params.put("p9", "18d40a4510134c84a271fd7fd941e33c");
					params.put("p0", "i");
					params.put("p10", "EDBAB3CF-5111-4E5E-BD59-C92427CEFC61");
					params.put("p5", userId);
					params.put("syshead", "{\"trans_code\":\"SI_ORD0016\",\"chnlId\":\"01\",\"chnlUserId\":\"" + userId + "\",\"sessionId\":\"18d40a4510134c84a271fd7fd941e33c\"}");
//					String result = HttpUtils.postUrl4ZSYH(productUrl, params, context);
					Map<String, String> logParams = new HashMap<String, String>();
					logParams.put("userName", userArray[i]);
					logParams.put("productName", productArray[j].split("-")[0]);
					MyRunnable runner = new MyRunnable(context, data_handler, urlStr, params, logParams);
					exec.execute(runner);
				} else if (QIANG_TYPE.equals("3")) {
					//国庆参数
					localHashMap.put("quantity", "1");
					localHashMap.put("userId", userId);
					localHashMap.put("orderCardType", "7");
					localHashMap.put("productId", "0143000005749");
					localHashMap.put("orderPoint", "9");
					params.put("quantity", "1");
					params.put("userId", userId);
					params.put("orderCardType", "7");
					params.put("productId", "0143000005749");
					params.put("orderPoint", "9");
					params.put("p0", "a");
					params.put("p1", "38");
					params.put("p2", "meizu");
					params.put("p3", "522e29c023924b379e53df1bf555587c8");
					params.put("p4", "6a6f20e5ec6b44ceb2cbb8a0586ea170");
					params.put("p5", userId);
					params.put("p6", "484577745");
					params.put("p7", "928817042e334b46b5322d21277da205");
					params.put("p8", "ce8f81db64cc44f7ba6832b5bab64034");
					params.put("p9", "null");
					params.put("p10", "f4b21ca7cf954307b0d203c1166b5ced");

					String str1 = ValidateUtils.a(localHashMap, "asc");
					String mac = "";
					try {
						mac = ValidateUtils.b(str1 + "0102030405060708");
					} catch (NoSuchAlgorithmException e) {
						e.printStackTrace();
					}
					params.put("mac", mac);
					
					Map<String, String> logParams = new HashMap<String, String>();
					logParams.put("userName", userId);
					logParams.put("productName", "0143000005749");
					MyRunnable runner = new MyRunnable(context, data_handler, urlStr, params, logParams);
					exec.execute(runner);
				}
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.show, menu);
		return true;
	}
	
	private Handler data_handler = new Handler(){
		public void handleMessage(Message msg) {
			if(msg.what == 1 && flag){
				String obj = (String)msg.obj;
				if (obj != null) {
					if (obj.contains("该商品还未开始销售，欢迎您选购其他商品")) {
						obj = "<font color='red'><b>" + obj + "<b></font>";
					} else if (obj.contains("抢购一空")) {
						obj = "<font color='blue'>" + obj + "</font>";
					} else if (obj.contains("挤爆了")) {
						obj = "<font color='black'>" + obj + "</font>";
					} else if (obj.contains("创建订单成功")) {
						obj = "<font color='green'>" + obj + "</font>";
					} else {
						obj = "<font color='grey'>" + obj + "</font>";
					}
					CharSequence charSequence = Html.fromHtml(obj);
					textView3.append(charSequence);
					textView3.append("\n");
				}
			}else{
				textView3.append("结束");
			}
		}
	};
	
}

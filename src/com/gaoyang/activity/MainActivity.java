package com.gaoyang.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gaoyang.R;
import com.gaoyang.common.API_URL;
import com.gaoyang.utils.HttpUtils;

public class MainActivity extends BaseActivity {
	
	private final static String TAG = "MainActivity";
	
	private List<CheckBox> checkboxList1 = new ArrayList<CheckBox>();
	
	private List<CheckBox> checkboxList2 = new ArrayList<CheckBox>();
	
	private Context context;
	
	private Button button;

	private String city = "10";

	String session_id = "e3b05f9d67564bdb8054f108e9884695";
	
	public static String[][] userArray = new String[][]{
															{"高洋", "?p0=a&p1=35&p2=f59b4efef5454bb18561c4dde7974c67&p3=522e29c023924b379e53df1bf555587c8&p4=8013b5f1ae704ce3a20075dcea7ded58&p5=17A8AF7157CD46FBAC97188626CA08A0&p6=477725532&p7=928817042e334b46b5322d21277da205&p8=bfcff26b5b294170b6bfa7cf31f2369b&p9=09642418d500463a9fd4f2f9eab03cbf&p10=018744f4a94c435099095ba6132d02b4", "17A8AF7157CD46FBAC97188626CA08A0", "186****2346"},
													  		{"王志强", "?p0=a&p1=31&p2=78faedc5eb27401980b6734a316f8be2&p3=D9D850D6908A54E77EAFAD2A4D3BE30C&p4=e69d447e339a4966955f26c4cd03a6bb&p5=c9d2cf6c8b3c4f519695ab59c35f00cb&p6=471678020&p7=4a47b3639a364d85a92088b79c4a6c0c&p8=f13d454eabcb4b3a98b8660f3e61188b&p9=null&p10=a943ef6af7814a4486ceebcab49d731f", "c9d2cf6c8b3c4f519695ab59c35f00cb", "185****4298"},
													  		{"荣佳丽", "?p0=a&p1=31&p2=13ed11a11b2040eda7aad3b15218ae0f&p3=FE73ED0E3803F5C41A8A573EFA114AF7&p4=4079cc4296164fa3a33535eb9dc99efe&p5=9861c5e60f3c48fd98579918c4c44a1f&p6=471677430&p7=967ecdf77abf4b1b8da34ae8a12b58b9&p8=7e10a746a41e4f9782be29420e6eb518&p9=null&p10=36c7dfb8db35402d9d05da0f6582751b", "9861c5e60f3c48fd98579918c4c44a1f", "186****2343"},
															{"宋强", "?p0=a&p1=31&p2=deb54f0fe5314204a8c04e3dc06d8168&p3=751DEB5D047AFF136BBDA42FBD99B4EC&p4=b2ff0501b28f418f9cacd6f377fd791d&p5=7c521c86114e4f8aa2c901a91d5d25c5&p6=471679289&p7=47155f6109224699a029e7ad6b32c302&p8=2570bbe079cc41a49e877ffb2bdc1274&p9=null&p10=9080f5dfea0a47ceaf03a06a0e33c56c", "7c521c86114e4f8aa2c901a91d5d25c5", "138****8430"},
													  		{"王鹏", "?p0=a&p1=33&p2=31b9f8a69297433aa0019f96d1ae0600&p3=ad51484ff3d04d28b35c7ba54cf8b352d&p4=97c7116e34dd479494e6ea87e98e8cc1&p5=03570d38aab14714831b62a132fd61e2&p6=475586395&p7=61d8cd34bf584448a05c745b8f390d47&p8=c0fe0f3441964ec39c5365898677b47e&p9=&p10=1f912c0f778741489fe32942c798afc3", "03570d38aab14714831b62a132fd61e2", "1369****3985"},
													  		{"王悦", "?p0=a&p1=33&p2=31b9f8a69297433aa0019f96d1ae0600&p3=ad51484ff3d04d28b35c7ba54cf8b352d&p4=97c7116e34dd479494e6ea87e98e8cc1&p5=03570d38aab14714831b62a132fd61e2&p6=475586395&p7=61d8cd34bf584448a05c745b8f390d47&p8=c0fe0f3441964ec39c5365898677b47e&p9=&p10=1f912c0f778741489fe32942c798afc3", "23e645508dde487c92069a231ff99d9f", "1351****5398"},
													  		{"李成", "?p0=a&p1=33&p2=31b9f8a69297433aa0019f96d1ae0600&p3=ad51484ff3d04d28b35c7ba54cf8b352d&p4=97c7116e34dd479494e6ea87e98e8cc1&p5=03570d38aab14714831b62a132fd61e2&p6=475586395&p7=61d8cd34bf584448a05c745b8f390d47&p8=c0fe0f3441964ec39c5365898677b47e&p9=&p10=1f912c0f778741489fe32942c798afc3", "1fa9b434100f45258ff4afc422a8a703", "1501****2836"},
															{"张伟", "?p0=a&p1=33&p2=31b9f8a69297433aa0019f96d1ae0600&p3=ad51484ff3d04d28b35c7ba54cf8b352d&p4=97c7116e34dd479494e6ea87e98e8cc1&p5=03570d38aab14714831b62a132fd61e2&p6=475586395&p7=61d8cd34bf584448a05c745b8f390d47&p8=c0fe0f3441964ec39c5365898677b47e&p9=&p10=1f912c0f778741489fe32942c798afc3", "d1ad7c70c25e42458cd87ce5abe9aeaf", "1381****0985"},
															{"肖博士", "?p0=a&p1=33&p2=31b9f8a69297433aa0019f96d1ae0600&p3=ad51484ff3d04d28b35c7ba54cf8b352d&p4=97c7116e34dd479494e6ea87e98e8cc1&p5=03570d38aab14714831b62a132fd61e2&p6=475586395&p7=61d8cd34bf584448a05c745b8f390d47&p8=c0fe0f3441964ec39c5365898677b47e&p9=&p10=1f912c0f778741489fe32942c798afc3", "c8d23e4a0fec4575a96ca09bd0670f83", "1852****2288"}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context = this.getApplicationContext();
		TextView textView1 = (TextView) this.findViewById(R.id.textview1);
		
//		CharSequence charSequence = Html.fromHtml("<big><a href='http://s.dianping.com/topic/8859320'>抢抢族</a></big>");
		CharSequence charSequence = Html.fromHtml("请选择人:");
		textView1.setText(charSequence);
		//点击的时候产生超链接
		textView1.setMovementMethod(LinkMovementMethod.getInstance());
		
		initUserCheckBox();
		initProductCheckBox2();
		button = (Button) this.findViewById(R.id.button);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				String userStr = "";
				String productStr = "";
				for (CheckBox checkbox1 : checkboxList1) {
					if (checkbox1.isChecked()) {
						userStr += checkbox1.getText() + ",";
					}
				}
				for (CheckBox checkbox2 : checkboxList2) {
					if (checkbox2.isChecked()) {
						productStr += checkbox2.getText() + ",";
					}
				}
				if (userStr.equals("") || productStr.equals("")) {
					Toast.makeText(context, "两样不能为空", Toast.LENGTH_SHORT).show();
					return;
				} else {
					Intent intent = new Intent(MainActivity.this, ShowActivity.class); 
					intent.putExtra("userStr", userStr);
					intent.putExtra("productStr", productStr);
					intent.putExtra("session_id", session_id);
					startActivity(intent);
				}
			}
		});
	}
	
	private void initUserCheckBox() {
		//动态加载布局
		LinearLayout linearLayout = (LinearLayout) this.findViewById(R.id.linearLayout1);
		for (int i = 0; i < userArray.length; i++) {
			CheckBox checkBox = (CheckBox) getLayoutInflater().inflate(R.layout.checkbox, null);
			checkBox.setText(userArray[i][0]);
			checkBox.setPadding(10, 10, 10, 10);
			checkboxList1.add(checkBox);
			linearLayout.addView(checkBox, i);
		}
	}
	
	private void initProductCheckBox1() {
		//动态加载布局
		LinearLayout linearLayout = (LinearLayout) this.findViewById(R.id.linearLayout2);
		String productUrl = API_URL.ZSYH.PRODUCT_LIST_URL;
		Map<String, String> params = new HashMap<String, String>();
		params.put("cityNo", city);
		params.put("pageIndex", "1");
		params.put("couponType", "3HoursRushBuy");
		String result = HttpUtils.postUrl4ZSYH(productUrl + userArray[0][1], params, context);
		JSONObject obj = null;
		int totalPage = 1;
		try {
			obj = new JSONObject(result);
			totalPage = Integer.parseInt(obj.getString("totalPages"));
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			for (int i = 0; i < totalPage; i++) {
				Map params1 = new HashMap();
				params1.put("cityNo", city);
				params1.put("pageIndex", i + 1);
				params1.put("couponType", "3HoursRushBuy");
				String pageResult = HttpUtils.postUrl4ZSYH(productUrl + userArray[0][1], params1, context);
				JSONObject obj2 = new JSONObject(pageResult);;
				JSONArray coupons = obj2.getJSONArray("coupons");
				
//				JSONObject jsonObject = new JSONObject(result);
//				JSONArray coupons = jsonObject.getJSONArray("coupons");
				for (int j = 0; j < coupons.length(); j++) {
					CheckBox checkBox = (CheckBox) getLayoutInflater().inflate(R.layout.checkbox, null);
					checkBox.setText(coupons.getJSONObject(j).getString("name") + "-" + coupons.getJSONObject(j).getString("productNo"));
					checkBox.setPadding(10, 10, 10, 10);
					checkboxList2.add(checkBox);
					linearLayout.addView(checkBox, j);
				}
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	private void initProductCheckBox2() {
		//动态加载布局
		LinearLayout linearLayout = (LinearLayout) this.findViewById(R.id.linearLayout2);
		String productUrl = API_URL.ZSYH_ADDRESS2 + API_URL.ZSYH.PRODUCT_9POINT_LIST_URL2;
		Map<String, String> params = new HashMap<String, String>();
		params.put("p1", "35");
		params.put("body", "{\"labelId\":\"04\",\"cityNo\":\"" + city + "\"}");
		params.put("p6", "478934852");
		params.put("p2", "F0DD376C-35A0-43DD-A79A-BEE9BC3C42BB");
		params.put("p7", "C7C5BC31E5544437BE1D43CF7F735627");
		params.put("p3", "22FCB3CBBC534C61B4775AD4740DB4E7f");
		params.put("p8", "C9A1B46D-58A5-46F8-83BC-46A928F75405");
		params.put("p4", "FB42BA36-C292-472C-B608-FCC55F9E62BE");
		params.put("p9", session_id);
		params.put("p0", "i");
		params.put("p10", "9D723935-D29E-454B-966B-9E2A5BADDBC3");
		params.put("p5", "17A8AF7157CD46FBAC97188626CA08A0");
		params.put("syshead", "{\"sessionId\":\"\" + session_id + \"\",\"trans_code\":\"SI_PRD0002\",\"pageIndex\":1,\"pageSize\":10,\"chnlUserId\":\"17A8AF7157CD46FBAC97188626CA08A0\",\"chnlId\":\"01\"}");
//		String result = HttpUtils.postUrl4ZSYH(productUrl, params, context);
//		JSONObject obj = null;
		int totalPage = 1;
//		try {
//			obj = new JSONObject(result);
//			totalPage = Integer.parseInt(obj.getString("totalPages"));
//		} catch (JSONException e1) {
//			e1.printStackTrace();
//		}
		try {
			for (int i = 0; i < totalPage; i++) {
				Map params1 = new HashMap();
				params1.put("p1", "35");
				params1.put("body", "{\"labelId\":\"04\",\"cityNo\":\"" + city + "\"}");
				params1.put("p6", "478934852");
				params1.put("p2", "F0DD376C-35A0-43DD-A79A-BEE9BC3C42BB");
				params1.put("p7", "C7C5BC31E5544437BE1D43CF7F735627");
				params1.put("p3", "22FCB3CBBC534C61B4775AD4740DB4E7f");
				params1.put("p8", "C9A1B46D-58A5-46F8-83BC-46A928F75405");
				params1.put("p4", "FB42BA36-C292-472C-B608-FCC55F9E62BE");
				params1.put("p9", session_id);
				params1.put("p0", "i");
				params1.put("p10", "9D723935-D29E-454B-966B-9E2A5BADDBC3");
				params1.put("p5", "17A8AF7157CD46FBAC97188626CA08A0");
				params1.put("syshead", "{\"sessionId\":\"" + session_id + "\",\"trans_code\":\"SI_PRD0002\",\"pageIndex\":" + (i + 1) + ",\"pageSize\":10,\"chnlUserId\":\"17A8AF7157CD46FBAC97188626CA08A0\",\"chnlId\":\"01\"}");
				
				String pageResult = HttpUtils.postUrl4ZSYH(productUrl, params1, context);
				JSONObject obj = new JSONObject(pageResult);;
				JSONObject bodyObj = (JSONObject) obj.get("body");
				JSONArray rows = bodyObj.getJSONArray("rows");
				
//				JSONObject jsonObject = new JSONObject(result);
//				JSONArray coupons = jsonObject.getJSONArray("coupons");
				for (int j = 0; j < rows.length(); j++) {
					CheckBox checkBox = (CheckBox) getLayoutInflater().inflate(R.layout.checkbox, null);
					checkBox.setText(rows.getJSONObject(j).getString("productName") + 
							"-" + rows.getJSONObject(j).getString("productNo") + 
							"-" + rows.getJSONObject(j).getString("bakNo"));
					checkBox.setPadding(10, 10, 10, 10);
					checkboxList2.add(checkBox);
					linearLayout.addView(checkBox, j);
				}

				JSONObject sysheadObj = (JSONObject) obj.get("syshead");
				session_id = sysheadObj.getString("session_id");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
}

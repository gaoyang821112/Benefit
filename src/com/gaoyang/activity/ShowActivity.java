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
    String QIANG_TYPE = "2";

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
        String session_id = intent.getStringExtra("session_id");

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
                } else if (userArray[i].equals("张伟")) {
                    urlStr = MainActivity.userArray[7][1];
                    userId = MainActivity.userArray[7][2];
                    userPhone = MainActivity.userArray[7][3];
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
                    params.put("p6", "486636627");
                    params.put("p2", "appStore");
                    params.put("p7", "C7C5BC31E5544437BE1D43CF7F735627");
                    params.put("p3", "22FCB3CBBC534C61B4775AD4740DB4E7f");
                    params.put("p8", "4B4E6622-05B0-426B-A6F6-10AA46144D3E");
                    params.put("p4", "45FB6947-1058-4DE8-8BA7-1FDA7D91302A");
                    params.put("p9", session_id);
                    params.put("p0", "i");
                    params.put("p10", "7CE31AB6-24F0-4B31-91E1-C9BDA14DBB1C");
                    params.put("p5", userId);
                    params.put("syshead", "{\"trans_code\":\"SI_ORD0001\",\"chnlId\":\"01\",\"chnlUserId\":\"" + userId + "\",\"sessionId\":\"" + session_id + "\"}");
//					String result = HttpUtils.postUrl4ZSYH(productUrl, params, context);
                    Map<String, String> logParams = new HashMap<String, String>();
                    logParams.put("userName", userArray[i]);
                    logParams.put("productName", productArray[j].split("-")[0]);
                    logParams.put("type", QIANG_TYPE);
                    MyRunnable runner = new MyRunnable(context, data_handler, params, logParams);
                    exec.execute(runner);
                } else if (QIANG_TYPE.equals("2")) {
                    String productUrl = API_URL.ZSYH_ADDRESS2 + API_URL.ZSYH.PRODUCT_9POINT_LIST_URL2;
                    params.put("p1", "40");
                    //哈根达斯 bakNo=201603303014500001 productNo=2016033030145
                    //星巴克
                    //西提牛排 bakNo=201603313058900001 productNo=2016033130589
                    //新石器烤肉 productNo=2016033030145 productNo=2016033030019
                    params.put("body", "{\"isCanRush\":\"0\",\"payType\":\"1000\",\"labelId\":\"01\",\"productNo\":\"2016032929714\",\"moduleType\":\"1\",\"accountNum\":1,\"mobilePhone\":\"" + userPhone + "\",\"bakNo\":\"201603292971400001\"}");
                    params.put("p6", "488038568");
                    params.put("p2", "appStore");
                    params.put("p7", "C7C5BC31E5544437BE1D43CF7F735627");
                    params.put("p3", "22FCB3CBBC534C61B4775AD4740DB4E7f");
                    params.put("p8", "B436E251-1A03-4E66-AC79-ABAAF1610CD2");
                    params.put("p4", "F94BEA24-7176-4FC1-B313-ECECB7C02388");
                    params.put("p9", session_id);
                    params.put("p0", "i");
                    params.put("p10", "5D31D2CD-7650-4F3B-8BA4-22F950459A97");
                    params.put("p5", userId);
                    params.put("syshead", "{\"trans_code\":\"SI_ORD0016\",\"chnlId\":\"01\",\"chnlUserId\":\"" + userId + "\",\"sessionId\":\"" + session_id + "\"}");
//					String result = HttpUtils.postUrl4ZSYH(productUrl, params, context);
                    Map<String, String> logParams = new HashMap<String, String>();
                    logParams.put("userName", userArray[i]);
                    logParams.put("productName", productArray[j].split("-")[0]);
                    logParams.put("type", QIANG_TYPE);
                    MyRunnable runner = new MyRunnable(context, data_handler, params, logParams);
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
                    params.put("p4", "caf017a00ed745a09b14c5ed520f449b");
                    params.put("p5", userId);
                    params.put("p6", "484629303");
                    params.put("p7", "928817042e334b46b5322d21277da205");
                    params.put("p8", "c05153c7b7014bf4b0b9b4fbf961a633");
                    params.put("p9", "5f03d13ddbbf43d4b3b8ecea08ab2933");
                    params.put("p10", "21e17da7fcdc406d804904b722b44088");

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
                    logParams.put("type", QIANG_TYPE);
                    MyRunnable runner = new MyRunnable(context, data_handler, params, logParams);
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
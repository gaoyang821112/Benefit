package com.gaoyang.common;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.gaoyang.R;

/**
 * 应用程序UI工具包：封装UI相关的一些操�?
 * 
 * @author liux (http://my.oschina.net/liux)
 * @version 1.0
 * @created 2012-3-21
 */
public class UIHelper {

	public final static int LISTVIEW_ACTION_INIT = 0x01;
	public final static int LISTVIEW_ACTION_REFRESH = 0x02;
	public final static int LISTVIEW_ACTION_SCROLL = 0x03;
	public final static int LISTVIEW_ACTION_CHANGE_CATALOG = 0x04;

	public final static int LISTVIEW_DATA_MORE = 0x01;
	public final static int LISTVIEW_DATA_LOADING = 0x02;
	public final static int LISTVIEW_DATA_FULL = 0x03;
	public final static int LISTVIEW_DATA_EMPTY = 0x04;

	public final static int LISTVIEW_DATATYPE_NEWS = 0x01;
	public final static int LISTVIEW_DATATYPE_BLOG = 0x02;
	public final static int LISTVIEW_DATATYPE_POST = 0x03;
	public final static int LISTVIEW_DATATYPE_TWEET = 0x04;
	public final static int LISTVIEW_DATATYPE_ACTIVE = 0x05;
	public final static int LISTVIEW_DATATYPE_MESSAGE = 0x06;
	public final static int LISTVIEW_DATATYPE_COMMENT = 0x07;

	public final static int REQUEST_CODE_FOR_RESULT = 0x01;
	public final static int REQUEST_CODE_FOR_REPLY = 0x02;
	
	public final static int PAGE_SIZE = 5;
	
	/**
	 * 用户登录或注�?
	 * 
	 * @param activity
	 */
	public static void logout(Activity activity) {
		AppContext ac = (AppContext) activity.getApplication();
		ac.Logout();
		//跳转界面  
		ToastMessage(activity, "logout success");
	}
	
	/**
	 * �?出程�?
	 * 
	 * @param cont
	 */
	public static void Exit(final Context cont) {
		AlertDialog.Builder builder = new AlertDialog.Builder(cont);
		builder.setIcon(android.R.drawable.ic_dialog_info);
		builder.setTitle(R.string.app_menu_sureexit);
		builder.setPositiveButton(R.string.sure,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// �?�?
						AppManager.getAppManager().AppExit(cont);
					}
				});
		builder.setNegativeButton(R.string.cancle, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				
			}
		});
		builder.show();
	}
	
	public static void ExitOnly(final Context cont) {
		AppManager.getAppManager().AppExit(cont);
	}
	
	/**
	 * 弹出Toast消息
	 * 
	 * @param msg
	 */
	public static void ToastMessage(Context cont, String msg) {
		Toast.makeText(cont, msg, Toast.LENGTH_SHORT).show();
	}
	
	
	/**
	 * 打开浏览器
	 * 
	 * @param context
	 * @param url
	 */
	public static void openBrowser(Context context, String url) {
		try {
			Uri uri = Uri.parse(url);
			Intent it = new Intent(Intent.ACTION_VIEW, uri);
			context.startActivity(it);
		} catch (Exception e) {
			e.printStackTrace();
			ToastMessage(context, "无法浏览此网�?");
		}
	}
	/**
	 * 获取TextWatcher对象
	 * 
	 * @param context
	 * @param tmlKey
	 * @return
	 */
	public static TextWatcher getTextWatcher(final Activity context,
			final String temlKey) {
		return new TextWatcher() {
			public void onTextChanged(CharSequence s, int start, int before,int count) {
				// 保存当前EditText正在编辑的内�?
				((AppContext) context.getApplication()).setProperty(temlKey,s.toString());
			}

			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}

			public void afterTextChanged(Editable s) {
			}
		};
	}
	
	/**
     * �?测网络是否可�?
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnectedOrConnecting();
    }
    
    /**
	 * 点击返回监听事件
	 * 
	 * @param activity
	 * @return
	 */
	public static View.OnClickListener finish(final Activity activity) {
		return new View.OnClickListener() {
			public void onClick(View v) {
				activity.finish();
			}
		};
	}
	
	
	private static Boolean isExit = false;  
	  
	public static void exitBy2Click(Activity activity) {  
	    Timer tExit = null;  
	    if (isExit == false) {  
	        isExit = true; // 准备�?�?  
	        ToastMessage(activity, "double click exit");  
	        tExit = new Timer();  
	        tExit.schedule(new TimerTask() {  
	            @Override  
	            public void run() {  
	                isExit = false; // 取消�?�?  
	            }  
	        }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任�?  
	    } else { 
//	    	activity.finish();
//	    	activity.finishActivity(100);
	    	AppContext.finishAllActivity();
	    	System.exit(0);
	    }  
	}  
	
}

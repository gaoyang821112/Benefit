/********************************************************************
 * Copyright    :   新英体育传媒集团                          © 2013 ssports.com 版权�?�?
 * 
 * Filename     :   .java
 * Author       :   zhangxyfs
 * Date         :   2013-4-15
 * Version      :   V1.00
 * Description  :   
 *
 * History      :   Modify Id  |  Date  |  Origin  |  Description
 *******************************************************************/
package com.gaoyang.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

public class ZToast {
	private static Handler handler = new Handler(Looper.getMainLooper());
	private static Toast toast = null;
	private static Object synObj = new Object();

	public static void showMsgShort(final Context act, final Object msg) {
		showMessage(act, msg, Toast.LENGTH_SHORT);
	}

	public static void showMsgLong(final Context act, final Object msg) {
		showMessage(act, msg, Toast.LENGTH_LONG);
	}
	
	public static void show(Context act, Object msg){
		Toast.makeText(act, msg.toString(), Toast.LENGTH_SHORT).show();
	}

	public static void showMessage(final Context act, final Object msg, final int len) {
		new Thread(new Runnable() {
			public void run() {
				handler.post(new Runnable() {
					@Override
					public void run() {
						synchronized (synObj) {
							if (toast != null) {
								toast.cancel();
								if(msg instanceof Integer){
									toast.setText(Integer.parseInt(msg.toString()));
								}else{
									toast.setText(msg.toString());
								}
								toast.setDuration(len);
							} else {
								toast = Toast.makeText(act, msg.toString(), len);
							}
							toast.show();
						}
					}
				});
			}
		}).start();
	}

	public static void cancelCurrentToast() {
		if (toast != null) {
			toast.cancel();
		}
	}
}

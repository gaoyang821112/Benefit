/********************************************************************
 * 
 * Filename     :   .java
 * Author       :   zhangxyfs
 * Date         :   2013-3-4
 * Version      :   V1.00
 * Description  :   
 *
 * History      :   Modify Id  |  Date  |  Origin  |  Description
 *******************************************************************/

package com.gaoyang.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.gaoyang.R;

public class Util {

	/**
	 * 获取可用内存
	 * 
	 * @param activity
	 * @return
	 */
	public static int getMemory(Activity activity) {
		ActivityManager activityManager = (ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE);
		return activityManager.getMemoryClass();
	}

	/**
	 * �?测是否有网络
	 * 
	 * @param inContext
	 * @return
	 */
	public static boolean isNetActive(Context inContext) {
		Context context = inContext.getApplicationContext();
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkinfo = connectivityManager.getActiveNetworkInfo();
		if(networkinfo!=null){
			String mobileNet = null;
			
			if (connectivityManager != null) {
				
				if(!isTablet(inContext)){
					 mobileNet = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState().toString();
				}
				
				String wifiNet = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState().toString();
				
				if ((mobileNet + "").equals("CONNECTED")) {
					return true;
				} else if ((wifiNet + "").equals("CONNECTED")) {
					return true;
				}
			}
		}
		return false;
	}

	public static void openVideoPlayer(Activity activity, String url, String error) {
		try {
			Uri uri = Uri.parse(url);
			Intent intent = new Intent("com.cooliris.media.MovieView");
			intent.setAction(Intent.ACTION_VIEW);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.setDataAndType(uri, "video/*");
			activity.startActivity(intent);
		} catch (Exception e) {
			e.printStackTrace();
			ZToast.show(activity, error);
		}
	}

	public static String setTextLenght(String text) {
		if (text.length() > 30) {
			text = text.substring(0, 30) + "...";
		}
		return text;
	}

	public static String setTextLenght(String text, int length) {
		if (text.length() > length) {
			text = text.substring(0, length) + "...";
		}
		return text;
	}

	public static int dip2px(Activity activity, float dipValue) {
		float scale = activity.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	public static int dip2px(Context context, float dipValue) {
		float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	public static int px2dip(Context context, float pxValue) {
		float scale = context.getResources().getDisplayMetrics().density;
		return (int) ((pxValue - 0.5f) / scale);
	}

	public static void openActivity(Activity activity, Class clzz) {
		Intent intent = new Intent(activity, clzz);
		activity.startActivity(intent);
		System.gc();
	}

	public static void openActivity(Activity activity, Class clzz, String flags, String value) {
		Intent intent = new Intent(activity, clzz);
		intent.putExtra(flags, value);
		activity.startActivity(intent);
		System.gc();
	}

	public static void openActivity(Activity activity, Class clzz, String[] flags, String[] value) {
		Intent intent = new Intent(activity, clzz);
		for (int i = 0; i < flags.length; i++) {
			intent.putExtra(flags[i], value[i]);
		}
		activity.startActivity(intent);
		System.gc();
	}

	/**
	 * 打开新的activity
	 * 
	 * @param activity
	 * @param clzz
	 */
	public static void openActivityD2U(Activity activity, Class clzz) {
		Intent intent = new Intent(activity, clzz);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.mv_open_d2u, R.anim.mv_close_d2u);
		System.gc();
	}

	public static void openActivityU2D(Activity activity, Class clzz) {
		Intent intent = new Intent(activity, clzz);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.mv_close_u2d, R.anim.mv_open_u2d);
		System.gc();
	}

	public static void openActivityL2R(Activity activity, Class clzz) {
		Intent intent = new Intent(activity, clzz);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.mv_close_l2r, R.anim.mv_open_l2r);
		System.gc();
	}

	public static void openActivityR2L(Activity activity, Class clzz) {
		Intent intent = new Intent(activity, clzz);
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.mv_close_r2l, R.anim.mv_open_r2l);
		System.gc();
	}

	public static void openActivityR2L(Activity activity, Class clzz, String[] flags, String[] value) {
		Intent intent = new Intent(activity, clzz);
		for (int i = 0; i < flags.length; i++) {
			intent.putExtra(flags[i], value[i]);
		}
		activity.startActivity(intent);
		activity.overridePendingTransition(R.anim.mv_close_r2l, R.anim.mv_open_r2l);
		System.gc();
	}

	public static void closeActivityL2R(Activity activity) {
		activity.overridePendingTransition(R.anim.mv_close_l2r, R.anim.mv_open_l2r);
		System.gc();
	}

	/**
	 * 将格式为yyyy-MM-dd转成MM月dd�?
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate(String date) {
		String da[] = date.split("-");
		return da[1] + "�?" + da[2] + "�?";
	}

	public static String formatDate1(String date) {
		String str = "";
		str += date.split("�?")[0];
		str += (date.split("�?")[1].split("�?"))[0];
		return str;
	}

	public static String formatDate2(String date) {
		String da[] = date.split(" ")[1].split(":");
		return da[0] + ":" + da[1];
	}

	public static String formatDateToEng(String dateStr) {
		Log.e("tag", dateStr);
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String month = new SimpleDateFormat("MM").format(date);
		if (month.equals("01")) {
			month = "JAN.";
		}
		if (month.equals("02")) {
			month = "FEB.";
		}
		if (month.equals("03")) {
			month = "MAR.";
		}
		if (month.equals("04")) {
			month = "APR.";
		}
		if (month.equals("05")) {
			month = "MAY";
		}
		if (month.equals("06")) {
			month = "JUN.";
		}
		if (month.equals("07")) {
			month = "JUL.";
		}
		if (month.equals("08")) {
			month = "AUG.";
		}
		if (month.equals("09")) {
			month = "SEP.";
		}
		if (month.equals("10")) {
			month = "OCT.";
		}
		if (month.equals("11")) {
			month = "NOV.";
		}
		if (!month.equals("12")) {
			month = "DEC.";
		}
		return month + new SimpleDateFormat(" dd, yyyy").format(date);
	}
	
	public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout & 
  Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
 }
	
	public static boolean isChinese(char c) {
		 Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
	     if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
	          || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
	         || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
	         || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
	         || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
	         || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
	         return true;
	     }
	     return false;
	 }
	
	public static String stringFilter(String str) throws PatternSyntaxException{
		 
		Pattern p = Pattern.compile("[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#�?%…�??& ;*（）—�??+|{}【�?��?�；：�?��?��?��?�，、？]");
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();		 
		}
	
	
	public static void ToastCorrect(Context ctx,EditText edittext){
		edittext.setCompoundDrawablesWithIntrinsicBounds(null, null, ctx.getResources().getDrawable(R.drawable.register_correct), null);
	}
	
	public static void ToastWrong(Context ctx,String content,EditText edittext){
		Toast.makeText(ctx, content, Toast.LENGTH_SHORT).show();
		edittext.setCompoundDrawablesWithIntrinsicBounds(null, null, ctx.getResources().getDrawable(R.drawable.register_wrong), null);
	}
}

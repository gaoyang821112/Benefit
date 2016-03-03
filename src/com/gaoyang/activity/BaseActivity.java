package com.gaoyang.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.KeyEvent;

import com.gaoyang.common.UIHelper;
import com.gaoyang.utils.SPUtils;

@SuppressLint("NewApi")
public abstract class BaseActivity extends Activity{
	
	protected SPUtils sp = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		sp = new SPUtils(this);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode == KeyEvent.KEYCODE_BACK)  
		{    
			UIHelper.exitBy2Click(this);
		}  
	    return false;  
	}
	
}

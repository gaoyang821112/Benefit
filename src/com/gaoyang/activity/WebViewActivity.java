package com.gaoyang.activity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.gaoyang.R;

/**
 * html加载专用
 */
public class WebViewActivity extends BaseActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview);
		String url = getIntent().getStringExtra("url");
		WebView myWebView = (WebView) findViewById(R.id.webview);
//		myWebView.setWebViewClient(new WebViewClient() {
//			@Override
//			public boolean shouldOverrideUrlLoading(WebView view, String url) {
//				return false;
//			}
//		});
		WebSettings webSettings = myWebView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		myWebView.loadUrl(url);
	}

}

package com.young.lee.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.young.lee.util.CustomRequest;
import com.young.lee.util.DateUtils;
import com.young.lee.util.Utils;
import com.young.lee.view.MarqueeView;

public class MainActivity extends BaseActivity {
	private TextView tv;
	private MarqueeView marqueeView;

	@Override
	public View bindView() {
		return null;
	}

	@Override
	public int bindLayout() {
		return R.layout.activity_main;
	}

	@Override
	public void initView(View view) {
		tv = (TextView) findViewById(R.id.tv);
		tv.setOnClickListener(this);
		tv = (TextView) findViewById(R.id.tv);
		marqueeView = (MarqueeView) findViewById(R.id.marqueeView);
		List<String> info = new ArrayList<String>();
		info.add("1. 大家好，我是孙福生。");
		info.add("2. 欢迎大家关注我哦！");
		info.add("3. GitHub帐号：sfsheng0322");
		info.add("4. 新浪微博：孙福生微博");
		info.add("5. 个人博客：sunfusheng.com");
		info.add("6. 微信公众号：孙福生");
		marqueeView.startWithList(info);
	}

	@Override
	public void initParms(Bundle parms) {
	}

	@Override
	public void doBusiness(Context mContext) {
		tv.setText("111");
		Log.i("Test", DateUtils.getSimpleLunarCalendar("2016-01-17")
				.getDateString());
		String url = "http://www.weather.com.cn/data/cityinfo/101120501.html";
		CustomRequest customRequest = new CustomRequest(Method.GET, url,
				new Listener<String>() {

					@Override
					public void onResponse(String arg0) {
					}
				}, new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
					}
				});
	}

	@Override
	public void widgetClick(View v) {
		switch (v.getId()) {
		case R.id.tv:
			Utils.sendEmail("1222");
			break;
		default:
			break;
		}
	}
}

package com.young.lee.baseandroid;

import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.young.lee.util.CustomRequest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
public class MainActivity extends BaseActivity {
	private TextView tv;

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
		tv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						SecondActivity.class);
				Bundle bundle = new Bundle();
				bundle.putString("key", "value");
				intent.putExtras(bundle);
				startActivity(intent);
			}
		});
	}

	@Override
	public void initParms(Bundle parms) {
	}

	@Override
	public void doBusiness(Context mContext) {
		tv.setText("111");
		String url = "http://www.weather.com.cn/data/cityinfo/101120501.html";
		CustomRequest customRequest=new CustomRequest(Method.GET, url, new Listener<String>() {

			@Override
			public void onResponse(String arg0) {
			}
		}, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError arg0) {
			}
		});
	}
}

package com.young.lee.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.young.lee.view.FlowLayout;
import com.young.lee.view.FlowLayout.OnTagItemClickListener;
import com.young.lee.view.MarqueeView;

public class MainActivity extends BaseActivity {
	private MarqueeView marqueeView;
	private List<String> info;
	private FlowLayout flowlayout;

	@Override
	public int bindLayout() {
		return R.layout.activity_main;
	}

	@Override
	public void initView(View view) {
		setSwipeBackEnable(false);
		marqueeView = (MarqueeView) findViewById(R.id.marqueeView);
		flowlayout = (FlowLayout) findViewById(R.id.dynamic_tag);

	}

	@Override
	public void initParms(Bundle parms) {
	}

	@Override
	public void setActivityStyle() {
	}

	@Override
	public void doBusiness(Context mContext) {
		info = new ArrayList<String>();
		info.add("JavaScript");
		info.add("CSS3");
		info.add("Html5");
		info.add("AngularJS");
		info.add("Node.js");
		info.add("Bootstrap");
		info.add("HTML/CSS");
		info.add("WebApp");
		info.add("PHP");
		info.add("JAVA");
		info.add("Linux");
		info.add("Python");
		info.add("C");
		info.add("C++");
		marqueeView.startWithList(info);
		marqueeView
				.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
					@Override
					public void onItemClick(int position, TextView textView) {
						Bundle bundel = new Bundle();
						bundel.putString("tag", textView.getText().toString());
						startActivity(SecondActivity.class, bundel);
					}
				});
		// ---------------------
		flowlayout.setOnTagItemClickListener(new OnTagItemClickListener() {

			@Override
			public void onClick(TextView tv) {
				Toast.makeText(MainActivity.this, tv.getText().toString(),
						Toast.LENGTH_SHORT).show();
			}
		});
		flowlayout.setTags(info);
	}

	@Override
	public void widgetClick(View v) {
	}

}

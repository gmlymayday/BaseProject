package com.young.lee.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends BaseActivity {
	private String tag;
	private TextView tv_tag_text;

	@Override
	public void initParms(Bundle parms) {
		tag = parms.getString("tag");
	}

	@Override
	public int bindLayout() {
		return R.layout.activity_second;
	}

	@Override
	public void setActivityStyle() {

	}

	@Override
	public void initView(View view) {
		tv_tag_text = $(R.id.tv_tag_text);
		tv_tag_text.setText(tag);
	}

	@Override
	public void doBusiness(Context mContext) {
	}

	@Override
	public void widgetClick(View v) {
	}

}

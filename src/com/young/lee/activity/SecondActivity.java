package com.young.lee.activity;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

public class SecondActivity extends BaseActivity {
	private String tag;
	private ScrollView scrollview;
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
		scrollview = $(R.id.scrollview);
		tv_tag_text.setText(tag);
		OverScrollDecoratorHelper.setUpStaticOverScroll(scrollview,
				OverScrollDecoratorHelper.ORIENTATION_VERTICAL);
	}

	@Override
	public void doBusiness(Context mContext) {
	}

	@Override
	public void widgetClick(View v) {
	}

}

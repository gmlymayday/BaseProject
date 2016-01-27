package com.young.lee.baseandroid;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends BaseActivity {
	private TextView tv;
	private Bundle bundle;

	@Override
	public void initParms(Bundle parms) {
		this.bundle = parms;
	}

	@Override
	public View bindView() {
		return null;
	}

	@Override
	public int bindLayout() {
		return R.layout.activity_second;
	}

	@Override
	public void initView(View view) {
		tv = (TextView) findViewById(R.id.tv_2);
		tv.setText(bundle.getString("key"));
	}

	@Override
	public void doBusiness(Context mContext) {
	}
}

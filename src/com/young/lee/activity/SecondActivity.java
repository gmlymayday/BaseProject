package com.young.lee.activity;

import java.lang.reflect.Field;

import com.young.lee.view.AvatarImageView;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;
import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

public class SecondActivity extends BaseActivity {
	private String tag;
	private ScrollView scrollview;
	private TextView tv_tag_text;
	private AvatarImageView iv_head;

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
		iv_head = $(R.id.iv_head);
		iv_head.getId();
		OverScrollDecoratorHelper.setUpStaticOverScroll(scrollview,
				OverScrollDecoratorHelper.ORIENTATION_VERTICAL);
	}

	@Override
	public void doBusiness(Context mContext) {
	}

	@Override
	public void widgetClick(View v) {
	}

	private int getRec() {
		try {
			Field field = R.drawable.class.getField("drawable");
			int i = field.getInt(new R.drawable());
			return i;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}

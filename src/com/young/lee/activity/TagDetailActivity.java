package com.young.lee.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.widget.ScrollView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.young.lee.util.Utils;

public class TagDetailActivity extends BaseActivity {
	private String tag;
	private PullToRefreshScrollView mPullRefreshScrollView;
	private TextView viewstub_wea;
	private CardView cv_cardview;

	@Override
	public void initParms(Bundle parms) {
		tag = parms.getString("tag");
	}

	@Override
	public int bindLayout() {
		return R.layout.activity_tag_detail;
	}

	@Override
	public void setActivityStyle() {
	}

	@Override
	public void initView(View view) {
		mPullRefreshScrollView = (PullToRefreshScrollView) view
				.findViewById(R.id.pull_refresh_scrollview);
		cv_cardview = (CardView) findViewById(R.id.cv_cardview);
		viewstub_wea = (TextView) findViewById(R.id.viewstub_wea);
		cv_cardview.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Utils.showToast(getApplicationContext(), "click");
			}
		});
		cv_cardview.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				Utils.showToast(getApplicationContext(), "longclick");
				return false;
			}
		});
		cv_cardview.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					cv_cardview.animate().translationZ(10).setDuration(100);
					Log.d("Test", "down");
					break;
				case MotionEvent.ACTION_UP:
					cv_cardview.animate().translationZ(0).setDuration(100);
					Log.d("Test", "up");
					v.performClick();
					return false;
				case MotionEvent.ACTION_CANCEL:
					cv_cardview.animate().translationZ(0).setDuration(100);
					Log.d("Test", "cancel");
					break;
				default:
					break;
				}
				return true;
			}
		});

		// 设置下拉刷新
		setPullToRefresh();
	}

	@Override
	public void doBusiness(Context mContext) {
		viewstub_wea.setText(tag);
	}

	@Override
	public void widgetClick(View v) {
	}

	private void setPullToRefresh() {
		mPullRefreshScrollView.getLoadingLayoutProxy().setPullLabel(
				getString(R.string.pull_to_refresh));
		mPullRefreshScrollView.getLoadingLayoutProxy().setRefreshingLabel(
				getString(R.string.refreshing));
		mPullRefreshScrollView.getLoadingLayoutProxy().setReleaseLabel(
				getString(R.string.leave_to_refresh));
		mPullRefreshScrollView
				.setOnRefreshListener(new OnRefreshListener<ScrollView>() {

					@Override
					public void onRefresh(
							PullToRefreshBase<ScrollView> refreshView) {
						stopRefresh();
					}
				});

	}

	private void stopRefresh() {
		// 停止正在刷新动画
		mPullRefreshScrollView.onRefreshComplete();
	}
}

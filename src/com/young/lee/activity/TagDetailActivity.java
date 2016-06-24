package com.young.lee.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

public class TagDetailActivity extends BaseActivity {
	private String tag;
	private PullToRefreshScrollView mPullRefreshScrollView;
	private TextView viewstub_wea;

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
		viewstub_wea = (TextView) findViewById(R.id.viewstub_wea);
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

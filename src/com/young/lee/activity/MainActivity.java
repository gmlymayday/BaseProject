package com.young.lee.activity;

import java.util.ArrayList;
import java.util.List;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;
import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.young.lee.util.Utils;
import com.young.lee.view.FlowLayout;
import com.young.lee.view.FlowLayout.OnTagItemClickListener;
import com.young.lee.view.MarqueeView;

public class MainActivity extends BaseActivity {
	private MarqueeView tv_marqueeView;
	private List<String> info;
	private FlowLayout flowlayout;
	private LinearLayout layout_main;

	@Override
	public int bindLayout() {
		return R.layout.activity_main;
	}

	@Override
	public void initView(View view) {
		setSwipeBackEnable(false);
		layout_main = (LinearLayout) findViewById(R.id.layout_main);
		tv_marqueeView = (MarqueeView) findViewById(R.id.tv_marqueeView);
		flowlayout = (FlowLayout) findViewById(R.id.dynamic_tag);
		OverScrollDecoratorHelper.setUpStaticOverScroll(layout_main,
				OverScrollDecoratorHelper.ORIENTATION_VERTICAL);
		findViewById(R.id.layout_cardview).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						startActivity(CardActivity.class);
					}
				});
		findViewById(R.id.layout_recyclerView).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						// 创建下载任务,downloadUrl就是下载链接
						String downloadUrl = "http://shouji.360tpcdn.com/160405/bd16978ccf84b88c9328ec03e72445c3/com.shbaiche.hunlianwang_24.apk";
						DownloadManager.Request request = new DownloadManager.Request(
								Uri.parse(downloadUrl));
						// 指定下载路径和下载文件名
						request.setDestinationInExternalPublicDir("/download/",
								"1.apk");
						// 获取下载管理器
						DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
						// 将下载任务加入下载队列，否则不会进行下载
						downloadManager.enqueue(request);
					}
				});

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
		tv_marqueeView.startWithList(info);
		tv_marqueeView
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
				Bundle bundel = new Bundle();
				bundel.putString("tag", tv.getText().toString());
				startActivity(TagDetailActivity.class, bundel);
			}
		});
		flowlayout.setTags(info);
	}

	@Override
	public void widgetClick(View v) {
		switch (v.getId()) {
		case R.id.layout_cardview:
			break;
		default:
			break;
		}
	}

}

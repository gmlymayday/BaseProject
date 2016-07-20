package com.young.lee.activity;

import java.util.ArrayList;
import java.util.List;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.database.Cursor;
import android.graphics.Outline;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewOutlineProvider;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.young.lee.util.Utils;
import com.young.lee.view.FlowLayout;
import com.young.lee.view.FlowLayout.OnTagItemClickListener;
import com.young.lee.view.MarqueeView;

public class MainActivity extends BaseActivity {
	private MarqueeView tv_marqueeView;
	private List<String> info;
	private FlowLayout flowlayout;
	private LinearLayout layout_main;
	private DownloadCompleteReceiver receiver;
	private DownloadManager downloadManager;
	private DownloadChangeObserver downloadObserver;
	private long lastDownloadId = 0;
	private static final Uri CONTENT_URI = Uri
			.parse("content://downloads/my_downloads");
	private MaterialRippleLayout layout_cardview;
	private LinearLayout layout_1;
	private TextView tv_miaopai;

	@Override
	public void initParms(Bundle parms) {
	}

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
		tv_miaopai = (TextView) findViewById(R.id.tv_miaopai);
		tv_miaopai.setText(getString(R.string.html_text));
		OverScrollDecoratorHelper.setUpStaticOverScroll(layout_main,
				OverScrollDecoratorHelper.ORIENTATION_VERTICAL);
		layout_1 = (LinearLayout) findViewById(R.id.layout_1);
		layout_cardview = (MaterialRippleLayout) findViewById(R.id.layout_cardview);
		layout_cardview.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(CardActivity.class);
			}
		});
		ViewOutlineProvider viewOutlineProvider = new ViewOutlineProvider() {

			@Override
			public void getOutline(View view, Outline outline) {
				outline.setRoundRect(10, 10, view.getWidth() - 10,
						view.getHeight() - 10, 5);
			}
		};
		layout_cardview.setOutlineProvider(viewOutlineProvider);
		layout_cardview.setClipToOutline(true);
		receiver = new DownloadCompleteReceiver();
		registerReceiver(receiver, new IntentFilter(
				DownloadManager.ACTION_DOWNLOAD_COMPLETE));
		findViewById(R.id.layout_recyclerView).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						Utils.showToast(getApplicationContext(), "开始下载");
						downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
						String downloadUrl = "http://shouji.360tpcdn.com/160623/0ce0968272bb6a6d76bcaa5d7526cf36/com.xingin.xhs_45110.apk";
						DownloadManager.Request request = new DownloadManager.Request(
								Uri.parse(downloadUrl));
						request.setDestinationInExternalPublicDir("/download/",
								"1.apk");
						request.setNotificationVisibility(Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
						request.setVisibleInDownloadsUi(true);
						lastDownloadId = downloadManager.enqueue(request);
						downloadObserver = new DownloadChangeObserver(null);
						getContentResolver().registerContentObserver(
								CONTENT_URI, true, downloadObserver);
					}
				});
	}

	class DownloadCompleteReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			queryDownloadStatus();
		}
	}

	class DownloadChangeObserver extends ContentObserver {
		public DownloadChangeObserver(Handler handler) {
			super(handler);
		}

		@Override
		public void onChange(boolean selfChange) {
			queryDownloadStatus();
		}
	}

	private void queryDownloadStatus() {
		DownloadManager.Query query = new DownloadManager.Query();
		query.setFilterById(lastDownloadId);
		Cursor c = downloadManager.query(query);
		if (c != null && c.moveToFirst()) {
			int status = c.getInt(c
					.getColumnIndex(DownloadManager.COLUMN_STATUS));

			int reasonIdx = c.getColumnIndex(DownloadManager.COLUMN_REASON);
			int titleIdx = c.getColumnIndex(DownloadManager.COLUMN_TITLE);
			int fileSizeIdx = c
					.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES);
			int bytesDLIdx = c
					.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR);
			String title = c.getString(titleIdx);
			int fileSize = c.getInt(fileSizeIdx);
			int bytesDL = c.getInt(bytesDLIdx);

			// Translate the pause reason to friendly text.
			int reason = c.getInt(reasonIdx);
			StringBuilder sb = new StringBuilder();
			sb.append(title).append("\n");
			sb.append("Downloaded:").append(bytesDL).append(" / ")
					.append(fileSize);
			Log.d("Test", sb.toString() + ";" + status);
			switch (status) {

			case DownloadManager.STATUS_PENDING:
				Log.d("Test", "开始下载");
				break;
			case DownloadManager.STATUS_RUNNING:
				Log.d("Test", "下载中");
				break;
			case DownloadManager.STATUS_PAUSED:
				Log.d("Test", "暂停下载");
				break;
			case DownloadManager.STATUS_SUCCESSFUL:
				Log.d("Test", "下载完成");
				break;
			case DownloadManager.STATUS_FAILED:
				Log.d("Test", "下载失败");
				downloadManager.remove(lastDownloadId);
				break;
			}
		}
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

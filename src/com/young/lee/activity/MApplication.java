package com.young.lee.activity;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.young.lee.util.CrashHandler;
import com.young.lee.util.VolleyBitmapCache;

public class MApplication extends Application {
	private ImageLoader mImageLoader;
	private RequestQueue mRequestQueue;
	/** 渠道ID **/
	public static String channelId = "Ajava";
	/** 应用程序版本versionName **/
	public static String version = "error";
	/** 设备ID **/
	public static String deviceId = "error";
	/** 日志输出标志 **/
	protected final String TAG = this.getClass().getSimpleName();
	private static Context context;
	private static Context instance;

	@Override
	public void onCreate() {
		super.onCreate();
		context = getApplicationContext();
		CrashHandler crashHandler = CrashHandler.getInstance();
		crashHandler.init(getApplicationContext());
		mRequestQueue = Volley.newRequestQueue(getBaseContext());
		mImageLoader = new ImageLoader(mRequestQueue, new VolleyBitmapCache());
	}

	/**
	 * [图片加载]
	 * 
	 * @return
	 */
	public ImageLoader getImageLoader() {
		return mImageLoader;
	}

	/**
	 * [消息队列]
	 * 
	 * @return
	 */
	public RequestQueue getRequestQueue() {
		return mRequestQueue;
	}

	/**
	 * [全局的Context]
	 * 
	 * @return
	 */
	public static Context getContext() {
		return context;
	}
}

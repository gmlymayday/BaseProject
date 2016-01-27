package com.young.lee.baseandroid;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.young.lee.util.VolleyBitmapCache;

public abstract class MApplication extends Application {
	private ImageLoader mImageLoader;
	private RequestQueue mRequestQueue;
	/** 对外提供整个应用生命周期的Context **/
	private static Context instance;
	/** 渠道ID **/
	public static String channelId = "Ajava";
	/** 应用程序版本versionName **/
	public static String version = "error";
	/** 设备ID **/
	public static String deviceId = "error";
	/** 日志输出标志 **/
	protected final String TAG = this.getClass().getSimpleName();

	@Override
	public void onCreate() {
		super.onCreate();
		mRequestQueue = Volley.newRequestQueue(getBaseContext());
		mImageLoader = new ImageLoader(mRequestQueue, new VolleyBitmapCache());
	}

	/**
	 * 图片加载
	 * 
	 * @return
	 */
	public ImageLoader getImageLoader() {
		return mImageLoader;
	}

	/**
	 * 消息队列
	 * 
	 * @return
	 */
	public RequestQueue getRequestQueue() {
		return mRequestQueue;
	}
}

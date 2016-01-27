package com.young.lee.baseandroid;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

public abstract class BaseActivity extends Activity {
	// 是否允许全屏
	private boolean mAllowFullScreen = true;
	/** 当前Activity渲染的视图View **/
	private View mContextView = null;
	/** 日志输出标志 **/
	protected final String TAG = this.getClass().getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "BaseActivity-->onCreate()");
		Bundle bundle = getIntent().getExtras();
		initParms(bundle);
		View mView = bindView();
		if (null == mView) {
			mContextView = LayoutInflater.from(this)
					.inflate(bindLayout(), null);
		} else {
			mContextView = mView;
		}
		if (mAllowFullScreen) {
			requestWindowFeature(Window.FEATURE_NO_TITLE);
		}
		setContentView(mContextView);
		initView(mContextView);
		doBusiness(this);
	}

	/**
	 * [初始化参数]
	 * 
	 * @param parms
	 */
	public abstract void initParms(Bundle parms);

	/**
	 * [绑定视图]
	 * 
	 * @return
	 */
	public abstract View bindView();

	/**
	 * [绑定布局]
	 * 
	 * @return
	 */
	public abstract int bindLayout();

	/**
	 * [初始化控件]
	 * 
	 * @param view
	 */
	public abstract void initView(final View view);

	/**
	 * [业务操作]
	 * 
	 * @param mContext
	 */
	public abstract void doBusiness(Context mContext);

	public View getView(View v, int id) {
		v.findViewById(id);
		return v;
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		Log.d(TAG, "onRestart()");
	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.d(TAG, "onStart()");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.d(TAG, "onResume()");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.d(TAG, "onPause()");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.d(TAG, "onStop()");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "onDestroy()");
	}

	/**
	 * 是否允许全屏
	 * 
	 * @param allowFullScreen
	 */
	public void setAllowFullScreen(boolean allowFullScreen) {
		this.mAllowFullScreen = allowFullScreen;
	}
}
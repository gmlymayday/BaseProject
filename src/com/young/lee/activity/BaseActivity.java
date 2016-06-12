package com.young.lee.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public abstract class BaseActivity extends Activity implements OnClickListener {
	/** 是否沉浸状态栏 **/
	private boolean isSetStatusBar = false;
	/** 是否允许全屏 **/
	private boolean mAllowFullScreen = false;
	/** 是否禁止旋转屏幕 **/
	private boolean isAllowScreenRoate = false;
	/** 返回键退出 **/
	private boolean isBackExit = false;
	/** 当前Activity渲染的视图View **/
	private View mContextView = null;
	/** 日志输出标志 **/
	protected final String TAG = this.getClass().getSimpleName();

	/** View点击 **/
	public abstract void widgetClick(View v);

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
		} else
			mContextView = mView;
		if (mAllowFullScreen) {
			requestWindowFeature(Window.FEATURE_NO_TITLE);
		}
		if (isSetStatusBar) {
			steepStatusBar();
		}
		setContentView(mContextView);
		if (!isAllowScreenRoate) {
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}
		initView(mContextView);
		doBusiness(this);
	}

	/**
	 * [沉浸状态栏]
	 */
	private void steepStatusBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			// 透明状态栏
			getWindow().addFlags(
					WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			// 透明导航栏
			getWindow().addFlags(
					WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
		}
	}

	/**
	 * [初始化参数]
	 * 
	 * @param parms
	 */
	public abstract void initParms(Bundle parms);

	@SuppressWarnings("unchecked")
	public <T extends View> T $(int resId) {
		return (T) super.findViewById(resId);
	}

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

	@Override
	public void onClick(View v) {
		widgetClick(v);
	}

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

	/**
	 * [页面跳转]
	 * 
	 * @param clz
	 */
	public void startActivity(Class<?> clz) {
		startActivity(clz, null);
	}

	/**
	 * [携带数据的页面跳转]
	 * 
	 * @param clz
	 * @param bundle
	 */
	public void startActivity(Class<?> clz, Bundle bundle) {
		Intent intent = new Intent();
		intent.setClass(this, clz);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivity(intent);
	}

	/**
	 * [含有Bundle通过Class打开编辑界面]
	 * 
	 * @param cls
	 * @param bundle
	 * @param requestCode
	 */
	public void startActivityForResult(Class<?> cls, Bundle bundle,
			int requestCode) {
		Intent intent = new Intent();
		intent.setClass(this, cls);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivityForResult(intent, requestCode);
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
	 * [是否允许全屏]
	 * 
	 * @param allowFullScreen
	 */
	public void setAllowFullScreen(boolean allowFullScreen) {
		this.mAllowFullScreen = allowFullScreen;
	}

	/**
	 * [是否设置沉浸状态栏]
	 * 
	 * @param allowFullScreen
	 */
	public void setSteepStatusBar(boolean isSetStatusBar) {
		this.isSetStatusBar = isSetStatusBar;
	}

	/**
	 * [是否允许屏幕旋转]
	 * 
	 * @param isAllowScreenRoate
	 */
	public void setScreenRoate(boolean isAllowScreenRoate) {
		this.isAllowScreenRoate = isAllowScreenRoate;
	}

	/**
	 * [是否连续两次返回退出]
	 * 
	 * @param allowFullScreen
	 */
	public void setBackExit(boolean isBackExit) {
		this.isBackExit = isBackExit;
	}

	private long exitTime;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			if (isBackExit) {
				if ((System.currentTimeMillis() - exitTime) > 2000) {
					Toast.makeText(getApplicationContext(), "再按一次退出程序",
							Toast.LENGTH_SHORT).show();
					exitTime = System.currentTimeMillis();
				} else {
					System.exit(0);
				}
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}

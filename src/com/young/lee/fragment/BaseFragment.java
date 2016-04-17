package com.young.lee.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment implements OnClickListener {
	/** 日志输出标志 **/
	protected final String TAG = this.getClass().getSimpleName();

	public abstract void widgetClick(View v);

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);
		Log.d(TAG, "BaseFragment-->onAttach()");
	}

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		Log.d(TAG, "BaseFragment-->onCreateView()");
		View view = LayoutInflater.from(getActivity()).inflate(bindLayout(),
				null);
		return view;
	}

	public abstract int bindLayout();

	@Override
	public void onClick(View v) {
		widgetClick(v);
	}

	@Override
	public void onResume() {
		Log.i(TAG, "BaseFragment-->onResume()");
		super.onResume();
	}

	@Override
	public void onPause() {
		Log.i(TAG, "BaseFragment-->onPause()");
		super.onPause();
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		Log.i(TAG, "BaseFragment-->onDestroyView()");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i(TAG, "BaseFragment-->onDestroy()");
	}
}

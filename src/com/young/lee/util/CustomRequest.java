package com.young.lee.util;

import java.util.HashMap;
import java.util.Map;

import com.android.volley.AuthFailureError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;

public class CustomRequest extends StringRequest {
	private Map<String, String> map;

	public CustomRequest(int method, String url, Listener<String> listener,
			ErrorListener errorListener) {
		super(method, url, listener, errorListener);
		map = new HashMap<String, String>();
	}

	@Override
	protected Map<String, String> getParams() throws AuthFailureError {
		return map;
	}

	// 设置参数
	public void setParam(String key, String value) {
		map.put(key, value);
	}
}

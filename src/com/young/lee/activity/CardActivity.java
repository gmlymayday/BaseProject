package com.young.lee.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.card.lib.CardDataItem;
import me.card.lib.CardSlidePanel;
import me.card.lib.CardSlidePanel.CardSwitchListener;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class CardActivity extends BaseActivity {
	private CardSwitchListener cardSwitchListener;
	private String url = "http://news-at.zhihu.com/api/3/news/latest";
	private CardSlidePanel slidePanel;
	private List<CardDataItem> dataList = new ArrayList<CardDataItem>();

	@Override
	public void initParms(Bundle parms) {
	}

	@Override
	public int bindLayout() {
		return R.layout.card_layout;
	}

	@Override
	public void setActivityStyle() {
	}

	@Override
	public void initView(View view) {
		slidePanel = (CardSlidePanel) findViewById(R.id.image_slide_panel);
		cardSwitchListener = new CardSwitchListener() {

			@Override
			public void onShow(int index) {
				Log.d("CardFragment", "正在显示-");
			}

			@Override
			public void onCardVanish(int index, int type) {
				Log.d("CardFragment", "正在消失-" + " 消失type=" + type);
			}

			@Override
			public void onItemClick(View cardView, int index) {
				Log.d("CardFragment", "卡片点击-");
			}
		};
		slidePanel.setCardSwitchListener(cardSwitchListener);
		prepareDataList();
	}

	@Override
	public void doBusiness(Context mContext) {
	}

	@Override
	public void widgetClick(View v) {
	}

	private void prepareDataList() {
		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Method.GET,
				url, null, new Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject arg0) {
						try {
							JSONObject jsonObject = new JSONObject(new String(
									arg0.toString().getBytes(), "utf-8"));
							JSONArray data = jsonObject.optJSONArray("stories");
							Gson gson = new Gson();
							ArrayList<CardDataItem> cards = gson.fromJson(
									data.toString(),
									new TypeToken<ArrayList<CardDataItem>>() {
									}.getType());
							dataList.addAll(cards);
							slidePanel.fillData(dataList);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}, new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError arg0) {
						Log.d("Test", arg0.toString());
					}
				}) {
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				HashMap<String, String> headers = new HashMap<String, String>();
				headers.put("charset", "utf-8");
				return headers;
			}

		};
		MApplication application = (MApplication) CardActivity.this
				.getApplication();
		application.getRequestQueue().add(jsonObjectRequest);
	}
}

package com.young.lee.activity;

import me.card.lib.CardFragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

public class CardActivity extends FragmentActivity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_card);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new CardFragment())
					.commitAllowingStateLoss();
		}
	}
}

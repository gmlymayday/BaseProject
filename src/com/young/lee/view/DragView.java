package com.young.lee.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DragView extends View {

	private int mLastX;
	private int mLastY;

	public DragView(Context context) {
		super(context);
		init();
	}

	public DragView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public DragView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		setBackgroundColor(Color.BLUE);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		int x = (int) ev.getX();
		int y = (int) ev.getY();
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mLastX = x;
			mLastY = y;
			break;
		case MotionEvent.ACTION_MOVE:
			int offsetX = x - mLastX;
			int offsetY = y - mLastY;
			// 调整layout的四个坐标
			layout(getLeft() + offsetX, getTop() + offsetY, getRight()
					+ offsetX, getBottom() + offsetY);
			break;
		}
		return true;
	}
}
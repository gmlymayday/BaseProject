package com.young.lee.view;

import com.young.lee.activity.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public class ProgressView extends View {
	private Context context;
	private Paint paint;
	// 圆环的颜色
	private int roundColor = Color.rgb(232, 222, 202);
	// 圆环进度的颜色
	private int roundProgressColor = Color.rgb(212, 70, 123);
	private int roundWidth = 15;
	private int maxProgress = 100;
	private int progress = 90;

	public ProgressView(Context context) {
		this(context, null);
	}

	public ProgressView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.context = context;
		paint = new Paint();
		TypedArray mtypedArray = context.obtainStyledAttributes(attrs,
				R.styleable.progress_circle);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		int centre = getWidth() / 2; // 获取圆心的x坐标
		int radius = (int) (centre - roundWidth / 2); // 圆环的半径
		paint.setColor(roundColor); // 设置圆环的颜色
		paint.setStyle(Paint.Style.STROKE); // 设置画笔类型
		paint.setStrokeWidth(roundWidth); // 设置圆环的宽度
		paint.setAntiAlias(true); // 消除锯齿
		RectF oval = new RectF(centre - radius, centre - radius, centre
				+ radius, centre + radius); // 用于定义的圆弧的形状和大小的界限

		float startAngle = -90, sweepAngle = (float) 360
				/ (float) (2 * maxProgress);
		canvas.drawArc(oval, startAngle, sweepAngle, false, paint);
		canvas.drawCircle(centre, centre, radius, paint);
		// 画进度圆环
		paint.setColor(roundProgressColor);
		float ProgressStartAngle = -90;
		float a = (float) progress / 100 * 360;
		canvas.drawArc(oval, ProgressStartAngle, a, false, paint);
		// 画同心圆1
		int radius1 = (int) (centre / 3*2); // 圆环的半径
		Paint paint1=new Paint();
		paint1.setColor(Color.rgb(251, 240, 225));
		canvas.drawCircle(centre, centre, radius1, paint1);
		int radius2 = (int) (centre / 4); // 圆环的半径
		Paint paint2=new Paint();
		paint2.setColor(Color.rgb(250, 234, 217));
		canvas.drawCircle(centre, centre, radius2, paint2);
		TextView tv=new TextView(context);
		tv.setGravity(Gravity.CENTER);
		tv.setText(String.valueOf(progress));
	}
}

package com.young.lee.util;

import android.util.Log;

/**
 * Log统一管理
 * 
 */
public class LUtils {
	private static final int PRINT_LOG_I = 1;
	private static final int PRINT_LOG_D = 2;
	private static final int PRINT_LOG_V = 3;
	private static final int PRINT_LOG_E = 4;

	private LUtils() {
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	public static boolean isDebug = true;
	private static final String TAG = "debug";

	public static void i(String msg) {
		i(TAG, msg);
	}

	public static void d(String msg) {
		d(TAG, msg);
	}

	public static void e(String msg) {
		e(TAG, msg);
	}

	public static void v(String msg) {
		v(TAG, msg);
	}

	// 下面是传入自定义tag的函�?
	public static void i(String tag, String msg) {
		if (isDebug)
			partLog(msg, PRINT_LOG_I);
	}

	public static void d(String tag, String msg) {
		if (isDebug)
			partLog(msg, PRINT_LOG_D);
	}

	public static void e(String tag, String msg) {
		if (isDebug)
			partLog(msg, PRINT_LOG_E);
	}

	public static void v(String tag, String msg) {
		if (isDebug)
			partLog(msg, PRINT_LOG_V);
	}

	/**
	 * 消除字符限制
	 * 
	 * @param str
	 */
	public static void partLog(String msg, int type) {
		msg = msg.trim();
		int index = 0;
		int maxLength = 4000;
		String sub;
		while (index < msg.length()) {
			// java的字符不允许指定超过总的长度end
			if (msg.length() <= index + maxLength) {
				sub = msg.substring(index);
			} else {
				sub = msg.substring(index, index + maxLength);
			}

			index += maxLength;
			switch (type) {
			case PRINT_LOG_I:
				Log.i(TAG, sub.trim());
				break;
			case PRINT_LOG_D:
				Log.d(TAG, sub.trim());
				break;
			case PRINT_LOG_V:
				Log.v(TAG, sub.trim());
				break;
			case PRINT_LOG_E:
				Log.e(TAG, sub.trim());
				break;
			}
		}
	}
}
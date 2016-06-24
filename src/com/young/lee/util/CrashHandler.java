package com.young.lee.util;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.Thread.UncaughtExceptionHandler;

import android.content.Context;
import android.os.Environment;
import android.os.Looper;
import android.text.TextUtils;

/**
 * 异常处理类
 */
public class CrashHandler implements UncaughtExceptionHandler {
	/**
	 * 系统默认的UncaughtException处理类
	 */
	private Thread.UncaughtExceptionHandler mDefaultHandler;
	private static CrashHandler INSTANCE;
	private static final boolean isCreateFile = true;// 是否创建错误文件

	private CrashHandler() {
	}

	public static CrashHandler getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new CrashHandler();
		}
		return INSTANCE;
	}

	/**
	 * 初始化,注册Context对象, 获取系统默认的UncaughtException处理器, 设置该CrashHandler为程序的默认处理器
	 * 
	 * @param ctx
	 */
	public void init(Context ctx) {
		mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
		Thread.setDefaultUncaughtExceptionHandler(this);
	}

	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		handleException(ex);
	}

	/**
	 * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成. 开发者可以根据自己的情况来自定义异常处理逻辑
	 * 
	 * @return true代表处理该异常，不再向上抛异常，
	 *         false代表不处理该异常(可以将该log信息存储起来)然后交给上层(这里就到了系统的异常处理)去处理，
	 *         简单来说就是true不会弹出那个错误提示框，false就会弹出
	 */
	private boolean handleException(final Throwable ex) {
		if (ex == null) {
			return false;
		}
		final StackTraceElement[] stack = ex.getStackTrace();
		final String message = ex.getMessage();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < stack.length; i++) {
			sb.append(stack[i].toString());
		}
		if (!TextUtils.isEmpty(Utils.email_username)) {
			Utils.sendEmail(sb.toString());// 将错误信息发送到指定邮箱
		}
		if (isCreateFile) {
			createErrorFile(stack, message);
		}
		return false;
	}

	private void createErrorFile(final StackTraceElement[] stack,
			final String message) {
		new Thread() {
			@Override
			public void run() {
				Looper.prepare();
				File file = new File(Environment.getExternalStorageDirectory(),
						Utils.CRASH_TEST);
				try {
					FileOutputStream fos = new FileOutputStream(file, true);
					fos.write(message.getBytes());
					for (int i = 0; i < stack.length; i++) {
						fos.write(stack[i].toString().getBytes());
					}
					fos.flush();
					fos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
				Looper.loop();
			}
		}.start();
	}
}
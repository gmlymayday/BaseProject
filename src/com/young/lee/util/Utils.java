package com.young.lee.util;

import java.io.File;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Fragment;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Environment;
import android.os.PowerManager;
import android.os.StatFs;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.text.Spanned;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Utils {
	private static final String TAG = "AppUtils";
	public static final String USER_NAME = "user_name";
	public static final String USER_PWD = "user_pwd";
	public static final String email_username = "";
	public static final String email_pwd = "";
	public static final String CRASH_TEST = "crash-test.log";
	private static Toast toast;

	private Utils() {
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	/**
	 * [获取应用程序名称]
	 * 
	 * @param context
	 * @return
	 */
	public static String getAppName(Context context) {
		try {
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo(
					context.getPackageName(), 0);
			int labelRes = packageInfo.applicationInfo.labelRes;
			return context.getResources().getString(labelRes);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * [获取应用程序版本号信息]
	 * 
	 * @param context
	 * @return
	 */
	public static int getVersionCode(Context context) {
		try {
			PackageManager manager = context.getPackageManager();
			PackageInfo info = manager.getPackageInfo(context.getPackageName(),
					0);
			return info.versionCode;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	/**
	 * [获取应用程序版本名称信息]
	 * 
	 * @param context
	 * @return 当前应用的版本名�?
	 */
	public static String getVersionName(Context context) {
		try {
			PackageManager packageManager = context.getPackageManager();
			PackageInfo packageInfo = packageManager.getPackageInfo(
					context.getPackageName(), 0);
			return packageInfo.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * [获取IP地址]
	 * 
	 * @return IP地址
	 */
	public static String getLocalIpAddress() {
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf
						.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress()) {
						return inetAddress.getHostAddress().toString();
					}
				}
			}
		} catch (SocketException e) {
			e.getStackTrace();
		}
		return null;
	}

	/**
	 * [获取SSID]
	 * 
	 * @param context
	 * @return
	 */
	public static String getSSID(Context context) {
		WifiManager wm = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		WifiInfo wi = wm.getConnectionInfo();
		return wi.getSSID();
	}

	/**
	 * [点亮屏幕] 权限<uses-permission android:name="android.permission.WAKE_LOCK" />
	 * 
	 * @param context
	 */
	@SuppressWarnings("deprecation")
	public static void lightScreen(Context context) {
		PowerManager pm = (PowerManager) context
				.getSystemService(Context.POWER_SERVICE);// 获取电源管理器对�?
		PowerManager.WakeLock wl = pm.newWakeLock(
				PowerManager.ACQUIRE_CAUSES_WAKEUP
						| PowerManager.SCREEN_DIM_WAKE_LOCK, "bright");
		wl.acquire();// 点亮屏幕
		wl.release();// 释放
	}

	/**
	 * [获取屏幕�?��的宽度]
	 * 
	 * @param context
	 * @return
	 */
	public static int getSmallScreenWidth(Context context) {
		Configuration config = context.getResources().getConfiguration();
		return config.smallestScreenWidthDp;
	}

	/**
	 * [获取SD卡的状�?]
	 */
	public static String getState() {
		return Environment.getExternalStorageState();
	}

	/**
	 * [�?��SDCard是否可用]
	 * 
	 * @return
	 */
	public static boolean isSDCardEnable() {
		return Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED);
	}

	/**
	 * [获取SD卡路径]
	 * 
	 * @return
	 */
	public static String getSDCardPath() {
		return Environment.getExternalStorageDirectory().getAbsolutePath()
				+ File.separator;
	}

	/**
	 * [获取SD卡的剩余容量]
	 * 
	 * @return
	 */
	public static long getSDCardAllSize() {
		if (isSDCardEnable()) {
			StatFs stat = new StatFs(getSDCardPath());
			@SuppressWarnings("deprecation")
			long availableBlocks = (long) stat.getAvailableBlocks() - 4;
			@SuppressWarnings("deprecation")
			long freeBlocks = stat.getAvailableBlocks();
			return freeBlocks * availableBlocks;
		}
		return 0;
	}

	/**
	 * [获取指定路径�?��空间的剩余可用容量字节数，单位byte]
	 * 
	 * @param filePath
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static long getFreeBytes(String filePath) {
		// 如果是sd卡的下的路径，则获取sd卡可用容�?
		if (filePath.startsWith(getSDCardPath())) {
			filePath = getSDCardPath();
		} else {// 如果是内部存储的路径，则获取内存存储的可用容�?
			filePath = Environment.getDataDirectory().getAbsolutePath();
		}
		StatFs stat = new StatFs(filePath);
		long availableBlocks = (long) stat.getAvailableBlocks() - 4;
		return stat.getBlockSize() * availableBlocks;
	}

	/**
	 * [获取系统存储路径]
	 * 
	 * @return
	 */
	public static String getRootDirectoryPath() {
		return Environment.getRootDirectory().getAbsolutePath();
	}

	/**
	 * [DP转PX]
	 * 
	 * @param context
	 * @param val
	 * @return
	 */
	public static int dp2px(Context context, float dpVal) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				dpVal, context.getResources().getDisplayMetrics());
	}

	/**
	 * [SP转PX]
	 * 
	 * @param context
	 * @param val
	 * @return
	 */
	public static int sp2px(Context context, float spVal) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
				spVal, context.getResources().getDisplayMetrics());
	}

	/**
	 * [PX转DP]
	 * 
	 * @param context
	 * @param pxVal
	 * @return
	 */
	public static float px2dp(Context context, float pxVal) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (pxVal / scale);
	}

	/**
	 * [PX转SP]
	 * 
	 * @param fontScale
	 * @param pxVal
	 * @return
	 */
	public static float px2sp(Context context, float pxVal) {
		return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
	}

	/**
	 * [获得Activity的宽度]
	 * 
	 * @param mActivity
	 * @return
	 */
	public final static int getWindowWidth(Activity activity) {
		DisplayMetrics dm = new DisplayMetrics();
		activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.widthPixels;
	}

	/**
	 * [打开网络设置界面]
	 * 
	 * @param activity
	 */
	public static void openSetting(Activity activity) {
		Intent intent = new Intent("/");
		ComponentName cm = new ComponentName("com.android.settings",
				"com.android.settings.WirelessSettings");
		intent.setComponent(cm);
		intent.setAction("android.intent.action.VIEW");
		activity.startActivityForResult(intent, 0);
	}

	/**
	 * [判断网络是否连接]
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isConnected(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (null != connectivity) {
			NetworkInfo info = connectivity.getActiveNetworkInfo();
			if (null != info && info.isConnected()) {
				if (info.getState() == NetworkInfo.State.CONNECTED) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * [判断是否是WiFi连接]
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isWifi(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (cm == null)
			return false;
		return cm.getActiveNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;
	}

	/**
	 * [判断网络是否为漫游]
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isNetworkRoaming(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			Log.w(TAG, "couldn't get connectivity manager");
		} else {
			NetworkInfo info = connectivity.getActiveNetworkInfo();
			if (info != null
					&& info.getType() == ConnectivityManager.TYPE_MOBILE) {
				TelephonyManager tm = (TelephonyManager) context
						.getSystemService(Context.TELEPHONY_SERVICE);
				if (tm != null && tm.isNetworkRoaming()) {
					Log.d(TAG, "network is roaming");
					return true;
				} else {
					Log.d(TAG, "network is not roaming");
				}
			} else {
				Log.d(TAG, "not using mobile network");
			}
		}
		return false;
	}

	/**
	 * [判断当前网络是否3G网络]
	 * 
	 * @param context
	 * @return boolean
	 */
	public static boolean is3G(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
		if (activeNetInfo != null
				&& activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
			return true;
		}
		return false;
	}

	/**
	 * [判断GPS是否打开]
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isGpsEnabled(Context context) {
		LocationManager locationManager = ((LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE));
		List<String> accessibleProviders = locationManager.getProviders(true);
		return accessibleProviders != null && accessibleProviders.size() > 0;
	}

	/**
	 * [获取缓存的大小]
	 * 
	 * @param context
	 * @return
	 */
	public static String getTotalCacheSize(Context context) {
		long cacheSize = 0;
		try {
			cacheSize = getFolderSize(context.getCacheDir());
			if (Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED)) {
				cacheSize += getFolderSize(context.getExternalCacheDir());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Formatter.formatFileSize(context, cacheSize);
	}

	/**
	 * [获取文件夹大小]
	 * 
	 * @param file
	 * @return
	 */
	public static long getFolderSize(File file) {
		long size = 0;
		try {
			File[] fileList = file.listFiles();
			for (int i = 0; i < fileList.length; i++) {
				if (fileList[i].isDirectory()) {
					size = size + getFolderSize(fileList[i]);
				} else {
					size = size + fileList[i].length();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return size;
	}

	/**
	 * [清空缓存]
	 * 
	 * @param context
	 */
	public static void clearAllCache(Context context) {
		deleteDir(context.getCacheDir());
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			deleteDir(context.getExternalCacheDir());
		}
	}

	/**
	 * [文件是否删除成功]
	 * 
	 * @param dir
	 * @return
	 */
	public static boolean deleteDir(File dir) {
		if (dir != null && dir.isDirectory()) {
			String[] children = dir.list();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		return dir.delete();
	}

	/**
	 * [判断手机是否有root权限]
	 */
	private static boolean hasRootPerssion() {
		PrintWriter PrintWriter = null;
		Process process = null;
		try {
			process = Runtime.getRuntime().exec("su");
			PrintWriter = new PrintWriter(process.getOutputStream());
			PrintWriter.flush();
			PrintWriter.close();
			return process.waitFor() == 0 ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (process != null) {
				process.destroy();
			}
		}
		return false;
	}

	/**
	 * [实现APK的安装]
	 * 
	 * @param apkPath
	 * @param context
	 * @return
	 */
	public static boolean install(String apkPath, Context context) {
		if (hasRootPerssion()) {
			return clientInstall(apkPath);
		} else {
			File file = new File(apkPath);
			if (!file.exists()) {
				return false;
			}
			Intent intent = new Intent();
			intent.setAction("android.intent.action.VIEW");
			intent.addCategory("android.intent.category.DEFAULT");
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			intent.setDataAndType(Uri.fromFile(file),
					"application/vnd.android.package-archive");
			context.startActivity(intent);
			return true;
		}
	}

	/**
	 * [Root静默安装]
	 */
	private static boolean clientInstall(String apkPath) {
		PrintWriter PrintWriter = null;
		Process process = null;
		try {
			process = Runtime.getRuntime().exec("su");
			PrintWriter = new PrintWriter(process.getOutputStream());
			PrintWriter.println("chmod 777 " + apkPath);
			PrintWriter
					.println("export LD_LIBRARY_PATH=/vendor/lib:/system/lib");
			PrintWriter.println("pm install -r " + apkPath);
			PrintWriter.flush();
			PrintWriter.close();
			return process.waitFor() == 0 ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (process != null) {
				process.destroy();
			}
		}
		return false;
	}

	/**
	 * [判断服务是否运行]
	 * 
	 * @param context
	 * @param serviceName
	 * @return
	 */
	public static boolean isServiceRun(Context context, String serviceName) {
		boolean isRun = false;
		ActivityManager activityManager = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<ActivityManager.RunningServiceInfo> serviceList = activityManager
				.getRunningServices(40);
		int size = serviceList.size();
		for (int i = 0; i < size; i++) {
			if (serviceList.get(i).service.getClassName().equals(serviceName) == true) {
				isRun = true;
				break;
			}
		}
		return isRun;
	}

	/**
	 * [判断手机号码的合法]
	 * 
	 * @param mobiles
	 * @return
	 */
	public static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern
				.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	/**
	 * [判断邮箱的合法]
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);
		return m.matches();
	}

	public static String char2Digits(String input) {
		return PhoneNumberUtils.convertKeypadLettersToDigits(input);
	}

	/**
	 * [关键字标红]
	 * 
	 * @param sourceString
	 * @param keyword
	 * @return
	 */
	public static Spanned keywordMadeRed(String sourceString, String keyword) {
		String result = "";
		if (sourceString != null && !"".equals(sourceString.trim())) {
			if (keyword != null && !"".equals(keyword.trim())) {
				result = sourceString.replaceAll(keyword,
						"<font color=\"red\">" + keyword + "</font>");
			} else {
				result = sourceString;
			}
		}
		return Html.fromHtml(result);
	}

	/**
	 * [动态计算ListView的高度]
	 * 
	 * @param listView
	 */
	public void setListViewHeightBasedOnChildren(ListView listView) {
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			return;
		}
		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}
		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
	}

	/**
	 * 关键字变色
	 * 
	 * @param holder
	 * @param name
	 */
	public void keywordsRed(TextView tv, String title, String keywords) {
		if (title != null && title.contains(keywords)) {
			int index = title.indexOf(keywords);
			int len = keywords.length();
			Spanned temp = Html.fromHtml(title.substring(0, index)
					+ "<font color=#9BCCFD>"
					+ title.substring(index, index + len) + "</font>"
					+ title.substring(index + len, title.length()));
			tv.setText(temp);
		} else {
			tv.setText(title);
		}
	}

	/**
	 * [发送邮箱]
	 */
	public static void sendEmail(final String content) {
		new Thread() {
			public void run() {
				try {
					Properties prop = new Properties();
					prop.put("mail.transport.protocol", "smtp");
					prop.put("mail.smtp.auth", "true");// 设置要验证
					prop.put("mail.host", "smtp.163.com");// 设置host
					prop.put("mail.smtp.port", "25"); // 设置端口
					// 使用JavaMail发送邮件的5个步骤
					// 1、创建session
					Session session = Session.getInstance(prop);
					// 开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
					session.setDebug(true);
					// 2、通过session得到transport对象
					Transport ts = session.getTransport();
					// 3、连上邮件服务器
					ts.connect("smtp.163.com", Utils.email_username,
							Utils.email_pwd);
					// 4、创建邮件
					// Message message = createAttachMail(session);
					Message message = createSimpleMail(session, content);
					// 5、发送邮件
					ts.sendMessage(message, message.getAllRecipients());
					ts.close();
					android.os.Process.killProcess(android.os.Process.myPid());
					System.exit(10);
				} catch (Exception e) {
					e.printStackTrace();
				}
			};
		}.start();
	}

	/**
	 * [只包含文本的邮件]
	 * 
	 * @param session
	 * @param content
	 * @return
	 */
	public static MimeMessage createSimpleMail(Session session, String content) {
		try {
			// 创建邮件对象
			MimeMessage message = new MimeMessage(session);
			// 指明邮件的发件人
			message.setFrom(new InternetAddress(Utils.email_username));
			// 指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(
					Utils.email_username));
			// 邮件的标题
			message.setSubject("bugs");
			// 邮件的文本内容
			message.setContent(content, "text/html;charset=UTF-8");
			// 返回创建好的邮件对象
			return message;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * [带附件的邮件]
	 * 
	 * @param context
	 * @param session
	 * @return
	 */
	public static MimeMessage createAttachMail(Session session) {
		try {
			MimeMessage message = new MimeMessage(session);
			// 发件人
			message.setFrom(new InternetAddress(Utils.email_username));
			// 收件人
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(
					Utils.email_username));
			// 邮件标题
			message.setSubject("bugs");
			// 创建邮件正文，为了避免邮件正文中文乱码问题，需要使用charset=UTF-8指明字符编码
			MimeBodyPart text = new MimeBodyPart();
			text.setContent("使用JavaMail创建的带附件的邮件", "text/html;charset=UTF-8");
			// 创建邮件附件
			MimeBodyPart attach = new MimeBodyPart();
			File file = new File(Environment.getExternalStorageDirectory(),
					Utils.CRASH_TEST);
			DataHandler dh = new DataHandler(new FileDataSource(file));
			attach.setDataHandler(dh);
			attach.setFileName(dh.getName()); //
			// 创建容器描述数据关系
			MimeMultipart mp = new MimeMultipart();
			mp.addBodyPart(text);
			mp.addBodyPart(attach);
			mp.setSubType("mixed");
			message.setContent(mp);
			// message.saveChanges();
			// message.writeTo(new FileOutputStream("attachMail.eml"));
			return message;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * [获取状态栏的高度]
	 * 
	 * @param context
	 * @return
	 */
	public static int getHeight(Context context) {
		Class<?> c = null;
		Object obj = null;
		Field field = null;
		int x = 0;
		try {
			c = Class.forName("com.android.internal.R$dimen");
			obj = c.newInstance();
			field = c.getField("status_bar_height");
			x = Integer.parseInt(field.get(obj).toString());
			return context.getResources().getDimensionPixelSize(x);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 显示Toast
	 * 
	 * @param context
	 * @param message
	 */
	public static void showToast(Context context, int message) {
		if (toast == null) {
			toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
		} else {
			toast.setText(message);
			toast.setDuration(Toast.LENGTH_SHORT);
		}
		toast.show();
	}

	/**
	 * [显示Toast]
	 * 
	 * @param context
	 * @param message
	 */
	public static void showToast(Context context, String message) {
		if (toast == null) {
			toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
		} else {
			toast.setText(message);
			toast.setDuration(Toast.LENGTH_SHORT);
		}
		toast.show();
	}
}
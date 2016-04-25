package com.young.lee.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * 数据库访问帮助类
 * 
 * @author 曾繁添
 * @version 1.0
 * 
 */
@SuppressWarnings("rawtypes")
public class DatabaseHelper extends SQLiteOpenHelper {

	private static String databaseName = "default_database.db";
	private static int databaseVersion = 1;
	private static String TAG = DatabaseHelper.class.getSimpleName();

	/**
	 * 对外实例化对象不采用该构造方法
	 * 
	 * @param context
	 */
	public DatabaseHelper(Context context) {
		super(context, databaseName, null, databaseVersion);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i(TAG, "DatabaseHelper-->onCreate()");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.i(TAG, "DatabaseHelper-->onUpgrade()");

	}
}

package com.young.lee.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",
			Locale.CHINA);

	/**
	 * [日期转换]
	 * 
	 * @param date
	 * @return
	 */
	private static Date StringToDate(String date) {
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * [获取简单农历对象]
	 * 
	 * @param date
	 *            日期字符串
	 * @return 简单农历对象
	 */
	public static SimpleLunarCalendar getSimpleLunarCalendar(String date) {
		return new SimpleLunarCalendar(StringToDate(date));
	}

	/**
	 * 获取简单农历对象
	 * 
	 * @param date
	 *            日期
	 * @return 简单农历对象
	 */
	public static SimpleLunarCalendar getSimpleLunarCalendar(Date date) {
		return new SimpleLunarCalendar(date);
	}
}

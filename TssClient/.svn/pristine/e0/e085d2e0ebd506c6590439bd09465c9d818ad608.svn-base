package com.icecream.tssclient.util;

import java.util.Calendar;

public class Format_Calendar {
	// 时间字符串必须是如下格式：YY-MM-DD HH:MM:SS
	private String time_string = "";

	public Format_Calendar(String time) {
		this.time_string = time;
	}

	public Calendar format() {
		Calendar c = Calendar.getInstance();
		String date = time_string.substring(0, 10);
		String time = time_string.substring(11);
		String[] year_month_day = date.trim().split("-");
		String[] hour_minute_day = time.trim().split(":");
		int year = Integer.parseInt(year_month_day[0]);
		int month = Integer.parseInt(year_month_day[1]) - 1;
		int day = Integer.parseInt(year_month_day[2]);
		int hour = Integer.parseInt(hour_minute_day[0]);
		int minute = Integer.parseInt(hour_minute_day[1]);
		int second = Integer.parseInt(hour_minute_day[2]);
		c.set(year, month, day, hour, minute, second);
		return c;
	}
}

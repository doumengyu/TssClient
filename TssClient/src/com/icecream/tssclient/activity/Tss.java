package com.icecream.tssclient.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.icecream.tssclient.util.Util;


public class Tss extends Activity {

	private final int delay = 2000;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start);
		Handler handler = new Handler();
		handler.postDelayed(new starthandler(), delay);
	}

	class starthandler implements Runnable {
		@Override
		public void run() {
			SharedPreferences sharedPreferences = getSharedPreferences(
					"config", MODE_PRIVATE);
			Util.userName = sharedPreferences.getString("username", null);
			Util.password = sharedPreferences.getString("password", null);
			Util.remember = sharedPreferences.getBoolean("remember", false);
			Util.auto = sharedPreferences.getBoolean("auto", false);
			Util.alarmInfo = sharedPreferences.getString("alarmInfo", null);
			Util.count = sharedPreferences.getInt("count", 0);
			Intent intent = new Intent();
			if (Util.auto == false) {
				intent.setClass(Tss.this, LoginActivity.class);
			} else {
				intent.setClass(Tss.this, LoginingActivity.class);
			}
			startActivity(intent);
			Tss.this.finish();
		}
	}
}
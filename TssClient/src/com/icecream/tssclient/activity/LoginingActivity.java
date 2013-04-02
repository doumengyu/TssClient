package com.icecream.tssclient.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import android.widget.Toast;

import com.icecream.tssclient.util.Login;
import com.icecream.tssclient.util.Net;
import com.icecream.tssclient.util.Util;

@SuppressLint("HandlerLeak")
public class LoginingActivity extends Activity {
	private final int LOGIN_SUCESS = 0x001;
	private final int LOGIN_FAILED = 0x002;
	private TextView activityTitleTextView;
	private TextView progressMessageTextView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.logning);

		activityTitleTextView = (TextView) findViewById(R.id.activitytitle);
		progressMessageTextView = (TextView) findViewById(R.id.progresstext);

		activityTitleTextView.setText("Log In Process...");
		progressMessageTextView.setText("Log In Process...");

		new Thread(loginRunnable).start();
	}

	private Runnable loginRunnable = new Runnable() {

		@Override
		public void run() {
			try {
				if (!Net.getInstance().checknetwork(getApplicationContext())) {
					throw new Exception(
							"Login failed,Please check your network.");
				} else {
					Login login = new Login();
					boolean success = login.login(Util.userName, Util.password);
					if (success == true) {
						Message message = new Message();
						message.what = LOGIN_SUCESS;
						LoginingActivity.this.loginHandler.sendMessage(message);
					} else {
						throw new Exception("UserName or Password unmatched!");
					}
				}
			} catch (Exception e) {
				Bundle bundle = new Bundle();
				bundle.putString("exception", e.getMessage());
				Message m = new Message();
				m.setData(bundle);
				m.what = LOGIN_FAILED;
				LoginingActivity.this.loginHandler.sendMessage(m);
			}

		}
	};
	@SuppressLint("HandlerLeak")
	private Handler loginHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case LOGIN_SUCESS: {
				SharedPreferences dataSharedPreferences = getSharedPreferences(
						"config", MODE_PRIVATE);
				SharedPreferences.Editor editor = dataSharedPreferences.edit();
				editor.putString("username", Util.userName);
				if (Util.remember) {
					editor.putString("password", Util.password);
				} else {
					editor.putString("password", null);
				}
				editor.putBoolean("remember", Util.remember);
				editor.putBoolean("auto", Util.auto);
				editor.commit();
				Intent intent = new Intent(LoginingActivity.this,
						ContainerActivity.class);
				startActivity(intent);
				LoginingActivity.this.finish();
				break;
			}
			case LOGIN_FAILED: {
				Toast.makeText(getApplicationContext(),
						msg.getData().getString("exception"),
						Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(LoginingActivity.this,
						LoginActivity.class);
				startActivity(intent);
				LoginingActivity.this.finish();
				break;
			}
			}
		}
	};
}

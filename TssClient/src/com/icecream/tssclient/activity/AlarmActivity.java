package com.icecream.tssclient.activity;

import com.icecream.tssclient.util.Util;
import android.app.Activity;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AlarmActivity extends Activity {

	private TextView alarmInfoTextView;
	private Button confirmButton;
	private MediaPlayer mediaPlayer;
	private Bundle bundle;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alarm);

		alarmInfoTextView = (TextView) findViewById(R.id.alarminfo);
		confirmButton = (Button) findViewById(R.id.confirmButton);

		bundle = this.getIntent().getExtras();

		String alarminfo = bundle.getString("CourseName") + "的第"
				+ bundle.getString("AssignmentNo") + "次作业将于"
				+ bundle.getInt("TimeRemain") + "小时后截止" + "\n\n";
		alarminfo += "作业描述:";
		alarminfo += bundle.getString("Description");
		bundle.getString("Description");
		alarmInfoTextView.setText(alarminfo);

		mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.ring);
		mediaPlayer.setLooping(true);
		mediaPlayer.start();

		confirmButton.setOnClickListener(new Button.OnClickListener() {
			public void onClick(View v) {
				if (mediaPlayer != null && mediaPlayer.isPlaying()) {
					mediaPlayer.pause();
				}
				String toDelete = bundle.get("CourseName") + "!"
						+ bundle.get("AssignmentNo") + "!"
						+ bundle.get("AlarmTime") + "!" + bundle.get("Count")
						+ "#";
				Util.alarmInfo = Util.alarmInfo.replace(toDelete, "");
				SharedPreferences dataSharedPreferences = getSharedPreferences(
						"config", MODE_PRIVATE);
				SharedPreferences.Editor editor = dataSharedPreferences.edit();

				editor.putString("alarmInfo", Util.alarmInfo);
				editor.commit();
				AlarmActivity.this.finish();// 关闭Activity
			}
		});

	}

	public void onBackPressed() {
		if (mediaPlayer != null && mediaPlayer.isPlaying()) {
			mediaPlayer.pause();
		}
		String toDelete = bundle.get("CourseName") + "!"
				+ bundle.get("AssignmentNo") + "!" + bundle.get("AlarmTime")
				+ "!" + bundle.get("Count") + "#";
		Util.alarmInfo = Util.alarmInfo.replace(toDelete, "");
		SharedPreferences dataSharedPreferences = getSharedPreferences(
				"config", MODE_PRIVATE);
		SharedPreferences.Editor editor = dataSharedPreferences.edit();

		editor.putString("alarmInfo", Util.alarmInfo);
		editor.commit();
		AlarmActivity.this.finish();// 关闭Activity

	}

}

package com.icecream.tssclient.activity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.icecream.tssclient.paraser.CourseHomeworkParser;
import com.icecream.tssclient.uiadapter.CourseHomeworkAdapter;
import com.icecream.tssclient.util.Format_Calendar;
import com.icecream.tssclient.util.Util;

public class CourseHomeworkActivity extends Activity {
	private ListView coursehomeworktlist;
	private TextView titleTextView;
	private ImageButton backButton;
	private RelativeLayout progressLayout;

	private CourseHomeworkParser parser;
	private CourseHomeworkAdapter adapter;
	private String url;
	private String courseno;
	private String coursename;
	private AlarmManager alarmManager = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.coursehomework);

		coursehomeworktlist = (ListView) findViewById(R.id.homeworklist);
		titleTextView = (TextView) findViewById(R.id.activitytitle);
		backButton = (ImageButton) findViewById(R.id.backbutton);
		progressLayout = (RelativeLayout) findViewById(R.id.progresslayout);

		Bundle bundle = this.getIntent().getExtras();
		if (bundle.getString("CourseNo").equals("-1")) {
			CourseHomeworkActivity.this.CancelAlarm(Integer.parseInt(bundle
					.getString("AlarmCode")));
			Intent intent = new Intent(CourseHomeworkActivity.this,
					AlarmManagerActivity.class);

			intent.putExtras(bundle);

			setResult(RESULT_OK, intent);

			CourseHomeworkActivity.this.finish();

		} else {
			url = "http://218.94.159.102/tss/en/"
					+ bundle.getString("CourseNo") + "/assignment/index.html";
			courseno = bundle.getString("CourseNo");
			titleTextView.setText(bundle.getString("CourseName"));
			coursename = bundle.getString("CourseName");
			backButton.setOnClickListener(gobackListener);

			if (null == savedInstanceState) {
				ListLoad listLoad = new ListLoad();
				listLoad.execute();
			}

			coursehomeworktlist.setOnItemClickListener(setalarmListener);
		}

	}

	private class ListLoad extends AsyncTask<Void, Integer, Void> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressLayout.setVisibility(View.VISIBLE);
		}

		@Override
		protected Void doInBackground(Void... arg0) {
			parser = new CourseHomeworkParser();
			try {
				parser.parser(url, courseno);
			} catch (Exception e) {
				Toast.makeText(CourseHomeworkActivity.this, "Network Problem",
						Toast.LENGTH_SHORT).show();
				Log.e("CourseList", e.getMessage());
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			adapter = new CourseHomeworkAdapter(CourseHomeworkActivity.this,
					parser.getHomeworkItems());
			coursehomeworktlist.setAdapter(adapter);
			progressLayout.setVisibility(View.GONE);
		}
	}

	// 设置闹钟信息
	public OnItemClickListener setalarmListener = new OnItemClickListener() {
		@SuppressLint("SimpleDateFormat")
		@SuppressWarnings("unchecked")
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			progressLayout.setVisibility(View.GONE);
			final Map<String, String> Item = ((Map<String, String>) coursehomeworktlist
					.getItemAtPosition(position));

			Format_Calendar fc = new Format_Calendar(Item.get("DueDate"));
			final Calendar c = fc.format();
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String time2 = sdf2.format(c.getTime());
			System.out.println("time2  " + time2);
			final String[] choices = new String[] { "12h in advance",
					"36h in advance", "60h in advance" };
			final int[] times = new int[] { 12, 36, 60 };
			Builder builder = new AlertDialog.Builder(
					CourseHomeworkActivity.this);
			builder.setTitle("请选择响铃时间");
			builder.setItems(choices, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int item) {
					switch (item) {
					case 0:
						c.setTimeInMillis(c.getTimeInMillis() - 43200000);
						break;
					case 1:
						c.setTimeInMillis(c.getTimeInMillis() - 129600000);
						break;
					case 2:
						c.setTimeInMillis(c.getTimeInMillis() - 216000000);
						break;
					}
					SimpleDateFormat sdf1 = new SimpleDateFormat(
							"yyyy/MM/dd HH:mm:ss");
					String time1 = sdf1.format(c.getTime());
					System.out.println("time1  " + time1);
					if (c.getTimeInMillis() > Calendar.getInstance()
							.getTimeInMillis()) {
						SimpleDateFormat sdf = new SimpleDateFormat(
								"yyyy/MM/dd hh:mm:ss");
						String time = sdf.format(c.getTime());

						Toast.makeText(getApplicationContext(),
								"Ringing time " + choices[item] + " Set",
								Toast.LENGTH_SHORT).show();
						Intent intent = new Intent(CourseHomeworkActivity.this,
								AlarmReceiver.class); // 创建Intent对象
						Bundle bundle = new Bundle();
						bundle.putString("AssignmentNo",
								Item.get("AssignmentNo"));
						bundle.putString("CourseName", coursename);
						bundle.putString("Description", Item.get("Description"));
						bundle.putInt("TimeRemain", times[item]);
						bundle.putString("AlarmTime", time);
						bundle.putString("Count", Util.count + "");
						intent.putExtras(bundle);
						PendingIntent pi = PendingIntent.getBroadcast(
								CourseHomeworkActivity.this, Util.count,
								intent, PendingIntent.FLAG_UPDATE_CURRENT); // 创建PendingIntent

						alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
						alarmManager.set(AlarmManager.RTC_WAKEUP,
								c.getTimeInMillis(), pi); // 设置闹钟，当前时间就唤醒

						String toSave = coursename + "!"
								+ Item.get("AssignmentNo") + "!" + time + "!"
								+ Util.count;
						if (Util.alarmInfo != null) {
							Util.alarmInfo += toSave + "#";
						} else {
							Util.alarmInfo = toSave + "#";
						}
						SharedPreferences dataSharedPreferences = getSharedPreferences(
								"config", MODE_PRIVATE);
						SharedPreferences.Editor editor = dataSharedPreferences
								.edit();

						editor.putString("alarmInfo", Util.alarmInfo);
						Util.count++;
						editor.putInt("count", Util.count);
						editor.commit();
					} else {
						Toast.makeText(getApplicationContext(),
								"Ringing Time has expired", Toast.LENGTH_SHORT)
								.show();
					}
				}

			});
			AlertDialog dialog = builder.create();
			dialog.show();
		};
	};

	public OnClickListener gobackListener = new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			CourseHomeworkActivity.this.finish();
		};
	};

	public void CancelAlarm(int requestCode) {
		Intent intent = new Intent(CourseHomeworkActivity.this,
				AlarmReceiver.class);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(
				CourseHomeworkActivity.this, requestCode, intent, 0);
		// 获取闹钟管理器
		AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		alarmManager.cancel(pendingIntent);
	}
}

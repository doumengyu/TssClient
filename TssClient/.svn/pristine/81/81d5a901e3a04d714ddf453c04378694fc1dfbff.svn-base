package com.icecream.tssclient.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.icecream.tssclient.uiadapter.AlarmManagerAdapter;
import com.icecream.tssclient.util.ExitApplication;
import com.icecream.tssclient.util.Util;

public class AlarmManagerActivity extends Activity {

	private ListView alarmlist;

	private TextView titleTextView;

	private RelativeLayout progressLayout;

	private AlarmManagerAdapter adapter;

	private List<Map<String, String>> alarmItems;

	private long mExitTime;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alarmmanager);

		// view控件在这里定义
		alarmlist = (ListView) findViewById(R.id.alarmlist);

		titleTextView = (TextView) findViewById(R.id.activitytitle);

		progressLayout = (RelativeLayout) findViewById(R.id.progresslayout1);

		titleTextView.setText("Alarm Manager");

		if (null == savedInstanceState) {
			ListLoad listLoad = new ListLoad();
			listLoad.execute();
		}
		alarmlist.setOnItemClickListener(deletealarmListener);
		ExitApplication.getInstance().addActivity(this);
	}

	private class ListLoad extends AsyncTask<Void, Integer, Void> {
		@Override
		protected void onPreExecute() {
			progressLayout.setVisibility(View.VISIBLE);
			if (adapter != null) {
				adapter.clear();
				adapter.notifyDataSetChanged();
			}
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... arg0) {
			if (Util.alarmInfo != null && Util.alarmInfo != "") {

				alarmItems = new ArrayList<Map<String, String>>();
				String alarminfo = Util.alarmInfo;

				String[] alarms = alarminfo.trim().split("#");
				for (String alarm : alarms) {

					Map<String, String> alarmItem = new HashMap<String, String>();
					String[] detail = alarm.trim().split("!");
					alarmItem.put("CourseName", detail[0]);
					alarmItem.put("AssignmentNo", detail[1]);
					alarmItem.put("AlarmTime", detail[2]);
					alarmItem.put("Count", detail[3]);
					alarmItems.add(alarmItem);
				}
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			adapter = new AlarmManagerAdapter(AlarmManagerActivity.this,
					alarmItems);
			alarmlist.setAdapter(adapter);
			progressLayout.setVisibility(View.GONE);
		}
	}

	// 跳转到课程具体信息
	public OnItemClickListener deletealarmListener = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			progressLayout.setVisibility(View.GONE);
			@SuppressWarnings("unchecked")
			final Map<String, String> Item = ((Map<String, String>) alarmlist
					.getItemAtPosition(position));
			new AlertDialog.Builder(AlarmManagerActivity.this)
					.setTitle("Delete this alram?")
					.setIcon(android.R.drawable.ic_dialog_info)
					.setPositiveButton("Yes",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// 点击“确认”后的操作
									String toDelete = Item.get("CourseName")
											+ "!" + Item.get("AssignmentNo")
											+ "!" + Item.get("AlarmTime") + "!"
											+ Item.get("Count") + "#";
									Util.alarmInfo = Util.alarmInfo.replace(
											toDelete, "");
									ListLoad listLoad = new ListLoad();
									listLoad.execute();
									SharedPreferences dataSharedPreferences = getSharedPreferences(
											"config", MODE_PRIVATE);
									SharedPreferences.Editor editor = dataSharedPreferences
											.edit();

									editor.putString("alarmInfo",
											Util.alarmInfo);
									editor.commit();

									// 这里跳转到CourseHomeworkActivity，并调用其中的取消闹钟的方法
									final int RG_REQUEST = 0;

									Intent intent = new Intent();

									Bundle bundle = new Bundle();
									bundle.putString("CourseNo", "-1");
									bundle.putString("AlarmCode",
											Item.get("Count"));
									intent.putExtras(bundle);
									intent.setClass(AlarmManagerActivity.this,
											CourseHomeworkActivity.class);

									startActivityForResult(intent, RG_REQUEST);

								}
							})
					.setNegativeButton("No",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// 点击“返回”后的操作,这里不设置没有任何操作
								}
							}).show();

		};
	};

	// 处理接收的数据

	protected void onActivityResult(int requestCode, int resultCode, Intent data)

	{

		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == RESULT_OK)

		{
			// 接收数据
		}

	}

	@Override
	public void onBackPressed() {
		if ((System.currentTimeMillis() - mExitTime) > 2000) {
			Toast.makeText(this, "Once More And Exit", Toast.LENGTH_SHORT)
					.show();
			mExitTime = System.currentTimeMillis();

		} else {
			ExitApplication.getInstance().exit();
		}

	}
}

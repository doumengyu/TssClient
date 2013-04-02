package com.icecream.tssclient.activity;

import java.util.Map;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.icecream.tssclient.paraser.MyHomeworkParser;
import com.icecream.tssclient.uiadapter.MyHomeworkAdapter;
import com.icecream.tssclient.util.ExitApplication;

public class MyHomeworkActivity extends Activity {
	private ListView homeworklist;
	private TextView titleTextView;
	private RelativeLayout progressLayout;

	private MyHomeworkParser parser;
	private MyHomeworkAdapter adapter;
	private long mExitTime;

	Map<String, String> Item = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myhomework);

		// view控件在这里定义
		homeworklist = (ListView) findViewById(R.id.myhomeworklist);
		titleTextView = (TextView) findViewById(R.id.activitytitle);
		progressLayout = (RelativeLayout) findViewById(R.id.progresslayout1);
		titleTextView.setText("Homework");
		if (null == savedInstanceState) {
			ListLoad listLoad = new ListLoad();
			listLoad.execute();
		}

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
			parser = new MyHomeworkParser();
			try {
				parser.parser();
			} catch (Exception e) {
				Toast.makeText(MyHomeworkActivity.this, "Network Problem",
						Toast.LENGTH_SHORT).show();
				Log.e("Myhomewok", e.getMessage());
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			adapter = new MyHomeworkAdapter(MyHomeworkActivity.this,
					parser.getHomeworkItems());
			homeworklist.setAdapter(adapter);
			progressLayout.setVisibility(View.GONE);
		}
	}

	// 响应单选框选中事件

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

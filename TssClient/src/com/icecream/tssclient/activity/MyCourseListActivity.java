package com.icecream.tssclient.activity;

import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.icecream.tssclient.paraser.MyCourseListParser;
import com.icecream.tssclient.uiadapter.MyCourseListAdapter;
import com.icecream.tssclient.util.ExitApplication;

public class MyCourseListActivity extends Activity {

	private ListView courselist;

	private TextView titleTextView;

	private RelativeLayout progressLayout;

	private MyCourseListParser parser;
	private MyCourseListAdapter adapter;
	private long mExitTime;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mycourselist);
		// view控件在这里定义
		courselist = (ListView) findViewById(R.id.courselist1);

		titleTextView = (TextView) findViewById(R.id.activitytitle);

		progressLayout = (RelativeLayout) findViewById(R.id.progresslayout1);

		titleTextView.setText("MyCourse");
		if (null == savedInstanceState) {
			ListLoad listLoad = new ListLoad();
			listLoad.execute();
		}

		courselist.setOnItemClickListener(gotocourseListener);
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
			parser = new MyCourseListParser();
			try {
				parser.parser();
			} catch (Exception e) {
				Toast.makeText(MyCourseListActivity.this, "Network Problem",
						Toast.LENGTH_SHORT).show();
				Log.e("CourseList", e.getMessage());
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			adapter = new MyCourseListAdapter(MyCourseListActivity.this, parser
					.getPostItems().get(0));
			courselist.setAdapter(adapter);
			progressLayout.setVisibility(View.GONE);
		}
	}

	@Override
	protected void onRestart() {
		super.onRestart();
	}

	// 跳转到课程具体信息
	public OnItemClickListener gotocourseListener = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			progressLayout.setVisibility(View.GONE);
			@SuppressWarnings("unchecked")
			Map<String, String> Item = ((Map<String, String>) courselist
					.getItemAtPosition(position));
			Intent intent = new Intent(MyCourseListActivity.this,
					CourseContainerActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("CourseNo", Item.get("CourseNo"));
			bundle.putString("CourseName", Item.get("CourseName"));
			intent.putExtras(bundle);
			startActivity(intent);
		};
	};

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

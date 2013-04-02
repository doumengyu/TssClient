package com.icecream.tssclient.activity;

import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.icecream.tssclient.paraser.CourseListParser;
import com.icecream.tssclient.uiadapter.CourseListAdapter;
import com.icecream.tssclient.util.ExitApplication;

public class CourseListActivity extends Activity {

	private ListView courselist;

	private TextView titleTextView;

	private RelativeLayout progressLayout;

	private Spinner spi;

	private CourseListParser parser;
	private CourseListAdapter adapter;
	private ArrayAdapter<String> spiadapter;
	private long mExitTime;

	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.courselist);
		// view控件在这里定义
		courselist = (ListView) findViewById(R.id.courselist);
		titleTextView = (TextView) findViewById(R.id.activitytitle);
		progressLayout = (RelativeLayout) findViewById(R.id.progresslayout);
		titleTextView.setText("CourseList");
		if (null == savedInstanceState) {
			ListLoad listLoad = new ListLoad();
			listLoad.execute();
		}

		courselist.setOnItemClickListener(gotocourseListener);
		// 把本activity加入到关闭list中
		ExitApplication.getInstance().addActivity(this);
	}

	private void SpinnerInit() {
		String[] arr = (String[]) parser.getTitles().toArray(new String[] {});
		spi = (Spinner) findViewById(R.id.spi);
		spiadapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, arr);
		spiadapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spi.setAdapter(spiadapter);
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
			parser = new CourseListParser();
			try {
				parser.parser();
			} catch (Exception e) {
				Toast.makeText(CourseListActivity.this, "Network Problem",
						Toast.LENGTH_SHORT).show();
				Log.e("CourseList", e.getMessage());
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			adapter = new CourseListAdapter(CourseListActivity.this, parser
					.getPostItems().get(0));
			courselist.setAdapter(adapter);
			progressLayout.setVisibility(View.GONE);

			// 初始化SPINNER
			SpinnerInit();
			// 下拉菜单设置响应
			spi.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int position, long id) {
					long selected = arg0.getItemIdAtPosition(position);

					adapter = new CourseListAdapter(CourseListActivity.this,
							parser.getPostItems().get((int) selected));
					courselist.setAdapter(adapter);
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
				}
			});
		}
	}

	// 这里用来在此Activity失去焦点后存储CourseList
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
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
			Intent intent = new Intent(CourseListActivity.this,
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

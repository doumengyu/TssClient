package com.icecream.tssclient.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.icecream.tssclient.paraser.CourseAnnouncementParser;
import com.icecream.tssclient.uiadapter.CourseAnnouncementAdapter;

public class CourseAnnouncementActivity extends Activity {
	private ListView announcementlist;
	private TextView titleTextView;
	private ImageButton backButton;
	private RelativeLayout progressLayout;

	private CourseAnnouncementParser parser;
	private CourseAnnouncementAdapter adapter;
	private String url;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.courseannouncement);

		announcementlist = (ListView) findViewById(R.id.announcementlist);
		titleTextView = (TextView) findViewById(R.id.activitytitle);
		backButton = (ImageButton) findViewById(R.id.backbutton);
		progressLayout = (RelativeLayout) findViewById(R.id.progresslayout);

		Bundle bundle = this.getIntent().getExtras();
		url = "http://218.94.159.102/tss/en/" + bundle.getString("CourseNo")
				+ "/announcement/index.html";
		titleTextView.setText(bundle.getString("CourseName"));
		backButton.setOnClickListener(gobackListener);

		if (null == savedInstanceState) {
			ListLoad listLoad = new ListLoad();
			listLoad.execute();
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
			parser = new CourseAnnouncementParser();
			try {
				parser.parser(url);
			} catch (Exception e) {
				Toast.makeText(CourseAnnouncementActivity.this, "Network Problem",
						Toast.LENGTH_SHORT).show();
				Log.e("CourseList", e.getMessage());
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			adapter = new CourseAnnouncementAdapter(CourseAnnouncementActivity.this,
					parser.getAnnouncementItems());
			announcementlist.setAdapter(adapter);
			progressLayout.setVisibility(View.GONE);
		}
	}

	public OnClickListener gobackListener = new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			CourseAnnouncementActivity.this.finish();
		};
	};

}

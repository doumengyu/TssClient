package com.icecream.tssclient.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
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

import com.icecream.tssclient.paraser.CourseSlidesParser;
import com.icecream.tssclient.uiadapter.CourseSlidesAdapter;
import com.icecream.tssclient.util.Net;

public class CourseSlidesActivity extends Activity {
	private ListView slideslist;
	private TextView titleTextView;
	private TextView currentpathView;
	private RelativeLayout progressLayout;
	private ImageButton backButton;

	private CourseSlidesParser parser;
	private CourseSlidesAdapter adapter;

	public static final int FOLDER = 1;
	public static final int PDF = 2;
	public static final int DOC = 3;
	public static final int XLS = 4;
	public static final int PPT = 5;
	public static final int RAR = 6;
	public static final int TXT = 7;
	public static final int FILE = 8;

	private String url = "";

	private List<Map<String, String>> slideItems_1 = new ArrayList<Map<String, String>>();
	private List<Map<String, String>> slideItems_1_temp;
	private List<Map<String, String>> slideItems_2 = new ArrayList<Map<String, String>>();
	private List<Map<String, String>> slideItems_2_temp;
	private List<Map<String, String>> slideItems_3 = new ArrayList<Map<String, String>>();
	private List<Map<String, String>> slideItems_3_temp;
	private List<Map<String, String>> slideItems_4 = new ArrayList<Map<String, String>>();
	private List<Map<String, String>> slideItems_4_temp;
	private List<Map<String, String>> slideItems_5 = new ArrayList<Map<String, String>>();
	private List<Map<String, String>> slideItems_5_temp;
	private List<Map<String, String>> slideItems_6 = new ArrayList<Map<String, String>>();

	private int level = 1;

	private String path_1;
	private String path_2;
	private String path_3;
	private String path_4;
	private String path_5;
	private String path_6;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.courseslides);

		slideslist = (ListView) findViewById(R.id.slideslist);
		titleTextView = (TextView) findViewById(R.id.activitytitle);
		currentpathView = (TextView) findViewById(R.id.currentpath);
		backButton = (ImageButton) findViewById(R.id.backbutton);
		progressLayout = (RelativeLayout) findViewById(R.id.progresslayout);

		Bundle bundle = this.getIntent().getExtras();
		url = "http://218.94.159.102/tss/en/" + bundle.getString("CourseNo")
				+ "/slide/index.html";
		titleTextView.setText(bundle.getString("CourseName"));
		backButton.setOnClickListener(gobackListener);

		path_1 = "../";
		currentpathView.setText(path_1);

		if (null == savedInstanceState) {
			ListLoad listLoad = new ListLoad();
			listLoad.execute();
		}

		slideslist.setOnItemClickListener(downloadListener);
	}

	private class ListLoad extends AsyncTask<Void, Integer, Void> {
		@Override
		protected void onPreExecute() {
			progressLayout.setVisibility(View.VISIBLE);
			currentpathView.setVisibility(View.GONE);
			if (adapter != null) {
				adapter.clear();
				adapter.notifyDataSetChanged();
			}
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... arg0) {
			parser = new CourseSlidesParser();
			try {
				parser.parser(url);
			} catch (Exception e) {
				Toast.makeText(CourseSlidesActivity.this, "Network Problem",
						Toast.LENGTH_SHORT).show();
				Log.e("CourseSlides", e.getMessage());
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			adapter = new CourseSlidesAdapter(CourseSlidesActivity.this,
					parser.getSlideItems());
			slideslist.setAdapter(adapter);
			progressLayout.setVisibility(View.GONE);
			currentpathView.setVisibility(View.VISIBLE);
		}
	}

	public OnItemClickListener downloadListener = new OnItemClickListener() {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			progressLayout.setVisibility(View.GONE);
			switch (level) {
			case 1:
				slideItems_1.addAll(parser.getSlideItems());
				slideItems_1_temp = new ArrayList<Map<String, String>>();
				slideItems_1_temp.addAll(slideItems_1);
				break;
			case 2:
				slideItems_2.addAll(parser.getSlideItems());
				slideItems_2_temp = new ArrayList<Map<String, String>>();
				slideItems_2_temp.addAll(slideItems_2);
				break;
			case 3:
				slideItems_3.addAll(parser.getSlideItems());
				slideItems_3_temp = new ArrayList<Map<String, String>>();
				slideItems_3_temp.addAll(slideItems_3);
				break;
			case 4:
				slideItems_4.addAll(parser.getSlideItems());
				slideItems_4_temp = new ArrayList<Map<String, String>>();
				slideItems_4_temp.addAll(slideItems_4);
				break;
			case 5:
				slideItems_5.addAll(parser.getSlideItems());
				slideItems_5_temp = new ArrayList<Map<String, String>>();
				slideItems_5_temp.addAll(slideItems_5);
				break;
			case 6:
				slideItems_6.addAll(parser.getSlideItems());
				break;

			}
			@SuppressWarnings("unchecked")
			Map<String, String> Item = ((Map<String, String>) slideslist
					.getItemAtPosition(position));
			if (FOLDER == Integer.parseInt(Item.get("FileType"))) {
				level++;
				url = Item.get("Url");
				if (2 == level) {

					path_2 = currentpathView.getText() + Item.get("FileName")
							+ "/";
					currentpathView.setText(path_2);
				} else if (3 == level) {

					path_3 = currentpathView.getText() + Item.get("FileName")
							+ "/";
					currentpathView.setText(path_3);
				} else if (4 == level) {

					path_4 = currentpathView.getText() + Item.get("FileName")
							+ "/";
					currentpathView.setText(path_4);
				} else if (5 == level) {

					path_5 = currentpathView.getText() + Item.get("FileName")
							+ "/";
					currentpathView.setText(path_5);
				} else if (6 == level) {

					path_6 = currentpathView.getText() + Item.get("FileName")
							+ "/";
					currentpathView.setText(path_6);
				}
				ListLoad listLoad = new ListLoad();
				listLoad.execute();
			} else {
				// ¿ªÊ¼ÏÂÔØ
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Item
						.get("Url")
						+ ";jsessionid="
						+ Net.getInstance().getJessionid()));
				System.out.println(Item.get("Url"));
				startActivity(intent);

			}
		};
	};

	public OnClickListener gobackListener = new OnClickListener() {
		@Override
		public void onClick(View arg0) {
			level--;
			if (0 == level)
				CourseSlidesActivity.this.finish();
			else if (1 == level) {
				adapter.clear();
				slideItems_1 = new ArrayList<Map<String, String>>();
				slideItems_1.addAll(slideItems_1_temp);
				slideItems_2 = new ArrayList<Map<String, String>>();
				adapter = new CourseSlidesAdapter(CourseSlidesActivity.this,
						slideItems_1);
				slideslist.setAdapter(adapter);
				currentpathView.setText(path_1);
			} else if (2 == level) {
				adapter.clear();
				slideItems_2 = new ArrayList<Map<String, String>>();
				slideItems_2.addAll(slideItems_2_temp);
				slideItems_3 = new ArrayList<Map<String, String>>();
				adapter = new CourseSlidesAdapter(CourseSlidesActivity.this,
						slideItems_2);
				slideslist.setAdapter(adapter);
				currentpathView.setText(path_2);
			} else if (3 == level) {
				adapter.clear();
				slideItems_3 = new ArrayList<Map<String, String>>();
				slideItems_3.addAll(slideItems_3_temp);
				slideItems_4 = new ArrayList<Map<String, String>>();
				adapter = new CourseSlidesAdapter(CourseSlidesActivity.this,
						slideItems_3);
				slideslist.setAdapter(adapter);
				currentpathView.setText(path_3);
			} else if (4 == level) {
				adapter.clear();
				slideItems_4 = new ArrayList<Map<String, String>>();
				slideItems_4.addAll(slideItems_4_temp);
				slideItems_5 = new ArrayList<Map<String, String>>();
				adapter = new CourseSlidesAdapter(CourseSlidesActivity.this,
						slideItems_4);
				slideslist.setAdapter(adapter);
				currentpathView.setText(path_4);
			} else if (5 == level) {
				adapter.clear();
				slideItems_5 = new ArrayList<Map<String, String>>();
				slideItems_5.addAll(slideItems_5_temp);
				slideItems_6 = new ArrayList<Map<String, String>>();
				adapter = new CourseSlidesAdapter(CourseSlidesActivity.this,
						slideItems_5);
				slideslist.setAdapter(adapter);
				currentpathView.setText(path_5);
			}
		};
	};

	public void onBackPressed() {
		level--;
		if (0 == level)
			CourseSlidesActivity.this.finish();
		else if (1 == level) {
			adapter.clear();
			slideItems_1 = new ArrayList<Map<String, String>>();
			slideItems_1.addAll(slideItems_1_temp);
			slideItems_2 = new ArrayList<Map<String, String>>();
			adapter = new CourseSlidesAdapter(CourseSlidesActivity.this,
					slideItems_1);
			slideslist.setAdapter(adapter);
			currentpathView.setText(path_1);
		} else if (2 == level) {
			adapter.clear();
			slideItems_2 = new ArrayList<Map<String, String>>();
			slideItems_2.addAll(slideItems_2_temp);
			slideItems_3 = new ArrayList<Map<String, String>>();
			adapter = new CourseSlidesAdapter(CourseSlidesActivity.this,
					slideItems_2);
			slideslist.setAdapter(adapter);
			currentpathView.setText(path_2);
		} else if (3 == level) {
			adapter.clear();
			slideItems_3 = new ArrayList<Map<String, String>>();
			slideItems_3.addAll(slideItems_3_temp);
			slideItems_4 = new ArrayList<Map<String, String>>();
			adapter = new CourseSlidesAdapter(CourseSlidesActivity.this,
					slideItems_3);
			slideslist.setAdapter(adapter);
			currentpathView.setText(path_3);
		} else if (4 == level) {
			adapter.clear();
			slideItems_4 = new ArrayList<Map<String, String>>();
			slideItems_4.addAll(slideItems_4_temp);
			slideItems_5 = new ArrayList<Map<String, String>>();
			adapter = new CourseSlidesAdapter(CourseSlidesActivity.this,
					slideItems_4);
			slideslist.setAdapter(adapter);
			currentpathView.setText(path_4);
		} else if (5 == level) {
			adapter.clear();
			slideItems_5 = new ArrayList<Map<String, String>>();
			slideItems_5.addAll(slideItems_5_temp);
			slideItems_6 = new ArrayList<Map<String, String>>();
			adapter = new CourseSlidesAdapter(CourseSlidesActivity.this,
					slideItems_5);
			slideslist.setAdapter(adapter);
			currentpathView.setText(path_5);
		}
	}
}

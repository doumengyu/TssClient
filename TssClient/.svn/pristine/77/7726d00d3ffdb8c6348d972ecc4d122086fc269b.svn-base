package com.icecream.tssclient.activity;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabWidget;

public class CourseContainerActivity extends ActivityGroup implements
		OnTabChangeListener, OnGestureListener {
	private GestureDetector gestureDetector;
	private CustomTabHost tabHost;
	private TabWidget tabWidget;
	private static final int FLEEP_DISTANCE = 120;
	/** 记录当前分页ID */
	private int currentTabID = 0;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabmain);
		Bundle bundle = this.getIntent().getExtras();

		tabHost = (CustomTabHost) findViewById(R.id.mytabhost);
		tabWidget = (TabWidget) findViewById(android.R.id.tabs);
		tabHost.setup(this.getLocalActivityManager());
		tabHost.setOnTabChangedListener(this);

		createTab1(bundle);
		createTab2(bundle);
		createTab3(bundle);
		createTab4(bundle);

		gestureDetector = new GestureDetector(this);
		new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				if (gestureDetector.onTouchEvent(event)) {
					return true;
				}
				return false;
			}
		};

	}

	class MyOnTabChangeListener implements OnTabChangeListener {
		public void onTabChanged(String tabId) {

		}
	}

	public void setgroupvisible(boolean visible) {
		if (visible) {
			tabHost.setVisibility(View.VISIBLE);
		} else {
			tabHost.setVisibility(View.GONE);
		}
	}

	@Override
	public boolean onDown(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		if (e1.getX() - e2.getX() <= (-FLEEP_DISTANCE)) {// 从左向右滑动
			currentTabID = tabHost.getCurrentTab() - 1;
			if (currentTabID < 0) {
				currentTabID = tabHost.getTabCount() - 1;
			}
		} else if (e1.getX() - e2.getX() >= FLEEP_DISTANCE) {// 从右向左滑动
			currentTabID = tabHost.getCurrentTab() + 1;
			if (currentTabID >= tabHost.getTabCount()) {
				currentTabID = 0;
			}
		}
		tabHost.setCurrentTab(currentTabID);
		return false;
	}

	@Override
	public void onLongPress(MotionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onSingleTapUp(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onTabChanged(String tabId) {
		// tabId值为要切换到的tab页的索引位置
		int tabID = Integer.valueOf(tabId);
		for (int i = 0; i < tabWidget.getChildCount(); i++) {
			if (i == tabID) {
				tabWidget.getChildAt(Integer.valueOf(i));
			} else {
				tabWidget.getChildAt(Integer.valueOf(i));
			}
		}
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		if (gestureDetector.onTouchEvent(event)) {
			event.setAction(MotionEvent.ACTION_CANCEL);
		}
		return super.dispatchTouchEvent(event);
	}

	public void createTab1(Bundle bundle) {
		TabHost.TabSpec localTabSpec = this.tabHost.newTabSpec("0");
		View localView = getLayoutInflater().inflate(R.layout.tab_courseinfo,
				null);
		Intent localIntent = new Intent(this, CourseAnnouncementActivity.class);
		localIntent.putExtras(bundle);
		localTabSpec.setIndicator(localView).setContent(localIntent);
		this.tabHost.addTab(localTabSpec);
	}

	public void createTab2(Bundle bundle) {
		TabHost.TabSpec localTabSpec = this.tabHost.newTabSpec("1");
		View localView = getLayoutInflater().inflate(R.layout.tab_slides, null);
		Intent localIntent = new Intent(this, CourseSlidesActivity.class);
		localIntent.putExtras(bundle);
		localTabSpec.setIndicator(localView).setContent(localIntent);
		this.tabHost.addTab(localTabSpec);
	}

	public void createTab3(Bundle bundle) {
		TabHost.TabSpec localTabSpec = this.tabHost.newTabSpec("2");
		View localView = getLayoutInflater().inflate(R.layout.tab_homework,
				null);
		Intent localIntent = new Intent(this, CourseHomeworkActivity.class);
		localIntent.putExtras(bundle);
		localTabSpec.setIndicator(localView).setContent(localIntent);
		this.tabHost.addTab(localTabSpec);
	}

	public void createTab4(Bundle bundle) {
		TabHost.TabSpec localTabSpec = this.tabHost.newTabSpec("3");
		View localView = getLayoutInflater().inflate(R.layout.tab_info, null);
		Intent localIntent = new Intent(this, CourseInfoActivity.class);
		localIntent.putExtras(bundle);
		localTabSpec.setIndicator(localView).setContent(localIntent);
		this.tabHost.addTab(localTabSpec);
	}

}

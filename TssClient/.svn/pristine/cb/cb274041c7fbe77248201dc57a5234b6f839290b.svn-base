package com.icecream.tssclient.uiadapter;

import java.util.List;
import java.util.Map;

import com.icecream.tssclient.activity.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyCourseListAdapter extends BaseAdapter {
	private Context context;
	private List<Map<String, String>> datas;
	private LayoutInflater mInflater;

	public MyCourseListAdapter(Context context,
			List<Map<String, String>> dataList) {
		this.context = context;
		this.datas = dataList;
		this.mInflater = LayoutInflater.from(this.context);
	}

	public void clear() {
		datas.clear();
	}

	@Override
	public int getCount() {
		if (datas == null) {
			return 0;
		}
		return datas.size();
	}

	@Override
	public Object getItem(int location) {
		return datas.get(location);
	}

	@Override
	public long getItemId(int location) {
		return location;
	}

	public View getView(int position, View convertView, ViewGroup arg2) {
		ViewHolder holder;
		if (datas == null) {
			return null;
		} else if (convertView == null) {
			convertView = mInflater.inflate(R.layout.mycourselistitem, null);
			holder = new ViewHolder();
			holder.coursenameTextView = (TextView) convertView
					.findViewById(R.id.coursename1);
			holder.instructorTextView = (TextView) convertView
					.findViewById(R.id.instructor1);
			holder.lastupdatedTextView = (TextView) convertView
					.findViewById(R.id.lastupdated1);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.coursenameTextView
				.setText(datas.get(position).get("CourseName"));
		holder.instructorTextView
				.setText(datas.get(position).get("Instructor"));
		holder.lastupdatedTextView.setText(datas.get(position).get(
				"Lastupdated"));
		return convertView;
	}

	static class ViewHolder {
		TextView coursenameTextView;
		TextView instructorTextView;
		TextView lastupdatedTextView;
	}
}

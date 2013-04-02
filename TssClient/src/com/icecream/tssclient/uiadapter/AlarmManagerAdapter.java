package com.icecream.tssclient.uiadapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.icecream.tssclient.activity.R;

public class AlarmManagerAdapter extends BaseAdapter {
	private Context context;
	private List<Map<String, String>> datas;
	private LayoutInflater mInflater;

	public AlarmManagerAdapter(Context context,
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
			convertView = mInflater.inflate(R.layout.alarmmanageritem, null);
			holder = new ViewHolder();
			holder.alarmcourseTextView = (TextView) convertView
					.findViewById(R.id.alarmcourse);
			holder.homeworknoTextView = (TextView) convertView
					.findViewById(R.id.homeworkno);
			holder.alarmtimeTextView = (TextView) convertView
					.findViewById(R.id.alarmtime);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.alarmcourseTextView.setText(datas.get(position)
				.get("CourseName"));
		holder.homeworknoTextView.setText("AssignmentNo:"
				+ datas.get(position).get("AssignmentNo"));
		holder.alarmtimeTextView.setText("Ringing Time:"
				+ datas.get(position).get("AlarmTime"));
		return convertView;
	}

	static class ViewHolder {
		TextView alarmcourseTextView;
		TextView homeworknoTextView;
		TextView alarmtimeTextView;
	}
}

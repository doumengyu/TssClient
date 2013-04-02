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

public class CourseHomeworkAdapter extends BaseAdapter {
	private Context context;
	private List<Map<String, String>> datas;
	private LayoutInflater mInflater;

	public CourseHomeworkAdapter(Context context,
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
			convertView = mInflater.inflate(R.layout.coursehomeworkitem, null);
			holder = new ViewHolder();
			holder.assignmentnoTextView = (TextView) convertView
					.findViewById(R.id.assignmentno);
			holder.duedateTextView = (TextView) convertView
					.findViewById(R.id.duedate);
			holder.descriptionTextView = (TextView) convertView
					.findViewById(R.id.description);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.assignmentnoTextView.setText("Assignment No: "
				+ datas.get(position).get("AssignmentNo"));
		holder.duedateTextView.setText("Due Date: "
				+ datas.get(position).get("DueDate"));
		holder.descriptionTextView.setText("Description: "
				+ datas.get(position).get("Description"));
		return convertView;
	}

	static class ViewHolder {
		TextView assignmentnoTextView;
		TextView duedateTextView;
		TextView descriptionTextView;
	}
}

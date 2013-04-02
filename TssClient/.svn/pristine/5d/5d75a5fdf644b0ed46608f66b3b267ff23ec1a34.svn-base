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

public class CourseAnnouncementAdapter extends BaseAdapter {
	private Context context;
	private List<Map<String, String>> datas;
	private LayoutInflater mInflater;

	public CourseAnnouncementAdapter(Context context, List<Map<String, String>> dataList) {
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
			convertView = mInflater.inflate(R.layout.courseannouncementitem, null);
			holder = new ViewHolder();
			holder.announcementtitleTextView = (TextView) convertView
					.findViewById(R.id.announcementtitle);
			holder.announcementtimeTextView = (TextView) convertView
					.findViewById(R.id.announcementtime);
			holder.announcementcontentTextView = (TextView) convertView
					.findViewById(R.id.announcementcontent);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.announcementtitleTextView.setText(datas.get(position).get(
				"Title"));
		holder.announcementtimeTextView
				.setText(datas.get(position).get("Time"));
		holder.announcementcontentTextView.setText(datas.get(position).get(
				"Content"));
		return convertView;
	}

	static class ViewHolder {
		TextView announcementtitleTextView;
		TextView announcementtimeTextView;
		TextView announcementcontentTextView;
	}
}

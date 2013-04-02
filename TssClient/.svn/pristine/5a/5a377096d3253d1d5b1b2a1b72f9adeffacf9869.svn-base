package com.icecream.tssclient.uiadapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.icecream.tssclient.activity.R;

public class MyHomeworkAdapter extends BaseAdapter {
	private Context context;
	private List<Map<String, String>> datas = new ArrayList<Map<String, String>>();
	private LayoutInflater mInflater;

	public MyHomeworkAdapter(Context context, List<Map<String, String>> dataList) {
		this.context = context;
		this.mInflater = LayoutInflater.from(this.context);

		for (int i = 0; i < dataList.size(); i++) {
			boolean isexsiting = false;
			for (int j = 0; j < datas.size(); j++) {
				if (datas.get(j).get("CourseName")
						.equals(dataList.get(i).get("CourseName"))) {
					isexsiting = true;
					datas.get(j).put(
							"Status",
							datas.get(j).get("Status")
									+ dataList.get(i).get("SerialNumber") + "."
									+ dataList.get(i).get("Description")
									+ "\n\n" + dataList.get(i).get("Status")
									+ "\n\n");
				}
			}
			if (!isexsiting) {
				Map<String, String> homeworkItem = new HashMap<String, String>();
				homeworkItem.put("CourseName", dataList.get(i)
						.get("CourseName"));
				homeworkItem.put("Status", dataList.get(i).get("SerialNumber")
						+ "." + dataList.get(i).get("Description") + "\n\n"
						+ dataList.get(i).get("Status") + "\n\n");
				datas.add(homeworkItem);
			}
		}
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
			convertView = mInflater.inflate(R.layout.myhomeworkitem, null);
			holder = new ViewHolder();
			holder.myhomeworkTextView = (TextView) convertView
					.findViewById(R.id.myhomeworkname);
			holder.statusTextView = (TextView) convertView
					.findViewById(R.id.status);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.myhomeworkTextView
				.setText(datas.get(position).get("CourseName"));
		holder.statusTextView.setText(datas.get(position).get("Status"));
		return convertView;
	}

	static class ViewHolder {
		TextView myhomeworkTextView;
		TextView statusTextView;
	}
}

package com.icecream.tssclient.uiadapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.icecream.tssclient.activity.R;

public class CourseSlidesAdapter extends BaseAdapter {
	private Context context;
	private List<Map<String, String>> datas;
	private LayoutInflater mInflater;
	public static final int FOLDER = 1;
	public static final int PDF = 2;
	public static final int DOC = 3;
	public static final int XLS = 4;
	public static final int PPT = 5;
	public static final int RAR = 6;
	public static final int TXT = 7;
	public static final int FILE = 8;

	public CourseSlidesAdapter(Context context,
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
			convertView = mInflater.inflate(R.layout.courseslidesitem, null);
			holder = new ViewHolder();
			holder.filetypeImageView = (ImageView) convertView
					.findViewById(R.id.slidesicon);
			holder.filenameTextView = (TextView) convertView
					.findViewById(R.id.filename);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		switch (Integer.parseInt(datas.get(position).get("FileType"))) {
		case FOLDER:
			holder.filetypeImageView.setImageResource(R.drawable.folder);
			break;
		case PDF:
			holder.filetypeImageView.setImageResource(R.drawable.pdf);
			break;
		case DOC:
			holder.filetypeImageView.setImageResource(R.drawable.doc);
			break;
		case XLS:
			holder.filetypeImageView.setImageResource(R.drawable.xls);
			break;
		case PPT:
			holder.filetypeImageView.setImageResource(R.drawable.ppt);
			break;
		case RAR:
			holder.filetypeImageView.setImageResource(R.drawable.rar);
			break;
		case TXT:
			holder.filetypeImageView.setImageResource(R.drawable.txt);
			break;
		case FILE:
			holder.filetypeImageView.setImageResource(R.drawable.file);
			break;

		}
		holder.filenameTextView.setText(datas.get(position).get("FileName"));
		return convertView;
	}

	static class ViewHolder {
		ImageView filetypeImageView;
		TextView filenameTextView;
	}
}

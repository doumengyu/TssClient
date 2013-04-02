package com.icecream.tssclient.paraser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.icecream.tssclient.util.Net;

public class CourseListParser {
	private List<List<Map<String, String>>> postItems;
	private List<String> titles;

	public CourseListParser() {
		postItems = new ArrayList<List<Map<String, String>>>();
	}

	public CourseListParser parser() throws Exception {
		try {
			String sourceString = Net.getInstance().get(
					"http://218.94.159.102/tss/en/home/courselist.html");
			sourceString.replaceAll("&nbsp;", " ");
			sourceString.replaceAll("<br>", "\n");
			Document doc = Jsoup.parse(sourceString);
			Element table = doc.select("table.forum_text").first();
			Elements trs = table.select("tr");
			int count = 0;
			postItems = new ArrayList<List<Map<String, String>>>();
			List<Map<String, String>> postItemList = new ArrayList<Map<String, String>>();
			for (Element tr : trs) {
				if (tr.attr("align").equals("center")) {
					count++;
					if (count != 2 && count != 1) {
						postItems.add(postItemList);
						postItemList = new ArrayList<Map<String, String>>();
					}
				} else if (tr.attr("align").equals("")) {
					if (count != 0) {
						Elements tds = tr.select("td");
						Map<String, String> postItem = new HashMap<String, String>();
						postItem.put("CourseNo", tds.get(0).text().trim());
						postItem.put("CourseName", tds.get(1).text().trim()
								.substring(3));
						postItem.put("Instructor", tds.get(2).text().trim()
								.substring(2));
						postItem.put("Lastupdated", tds.get(3).text().trim());
						postItemList.add(postItem);
					}
				}
			}
			postItems.add(postItemList);

			// 获取学期LIST
			titles = new ArrayList<String>();
			Elements titletds = doc.select("td.tdtitle");
			for (Element titletd : titletds) {
				titles.add(titletd.text().trim());
			}
			titles.remove(titles.size() - 1);

		} catch (Exception e) {
			throw e;
		}
		return this;
	}

	public List<List<Map<String, String>>> getPostItems() {
		return postItems;
	}

	public List<String> getTitles() {
		return titles;
	}

}

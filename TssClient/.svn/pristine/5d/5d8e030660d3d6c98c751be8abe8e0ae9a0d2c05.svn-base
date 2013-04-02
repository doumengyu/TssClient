package com.icecream.tssclient.paraser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.icecream.tssclient.util.Login;
import com.icecream.tssclient.util.Net;
import com.icecream.tssclient.util.Util;

public class MyCourseListParser {
	private List<List<Map<String, String>>> postItems;

	public MyCourseListParser() {
		postItems = new ArrayList<List<Map<String, String>>>();
	}

	public MyCourseListParser parser() throws Exception {
		try {
			int i = 0;
			while (!Net.getInstance().islogin().equals("/tss") && i != 3) {
				Net.getInstance().clear();
				Login login = new Login();
				login.login(Util.userName, Util.password);
				i++;
			}
			String sourceString = Net.getInstance().get(
					"http://218.94.159.102/tss/en/home/mycourse.html");
			Document doc = Jsoup.parse(sourceString);
			Elements tables = doc.select("table.forum_text");
			postItems = new ArrayList<List<Map<String, String>>>();
			if (1 == tables.size()) {
				Elements trs = tables.get(0).select("tr");
				int count = 0;
				List<Map<String, String>> postItemList = new ArrayList<Map<String, String>>();
				for (Element tr : trs) {
					if (tr.attr("align").equals("center")) {
						count++;
					} else if (tr.attr("align").equals("") && count == 2) {
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
				postItems.add(postItemList);
			} else if (2 == tables.size()) {
				Elements trs = tables.get(1).select("tr");
				int count = 0;
				List<Map<String, String>> postItemList = new ArrayList<Map<String, String>>();
				for (Element tr : trs) {
					if (tr.attr("align").equals("center")) {
						count++;
					} else if (tr.attr("align").equals("") && count == 2) {
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
				postItems.add(postItemList);
				Elements trs1 = tables.get(0).select("tr");
				int count1 = 0;
				List<Map<String, String>> postItemList1 = new ArrayList<Map<String, String>>();
				for (Element tr1 : trs1) {
					if (tr1.attr("align").equals("center")) {
						count1++;
					} else if (tr1.attr("align").equals("") && count1 == 1) {
						Elements tds1 = tr1.select("td");
						Map<String, String> postItem1 = new HashMap<String, String>();
						postItem1.put("CourseNo", tds1.get(0).text().trim());
						postItem1.put("CourseName", tds1.get(1).text().trim()
								.substring(3));
						postItem1.put("Instructor", tds1.get(2).text().trim()
								.substring(2));
						postItem1.put("Lastupdated", tds1.get(3).text().trim());
						postItemList1.add(postItem1);
					}
				}
				postItems.add(postItemList1);
			}
		} catch (Exception e) {
			throw e;
		}
		return this;
	}

	public List<List<Map<String, String>>> getPostItems() {
		return postItems;
	}

}

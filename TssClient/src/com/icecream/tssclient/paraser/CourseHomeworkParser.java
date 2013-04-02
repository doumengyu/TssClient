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

public class CourseHomeworkParser {
	private List<Map<String, String>> coursehomeworkItems;

	public CourseHomeworkParser() {
		coursehomeworkItems = new ArrayList<Map<String, String>>();
	}

	public CourseHomeworkParser parser(String url, String courseno)
			throws Exception {
		try {
			String sourceString = Net.getInstance().get(url);
			Document doc = Jsoup.parse(sourceString);
			Elements tables = doc.select("table.forum_text");
			for (Element table : tables) {
				Map<String, String> coursehomeworkItem = new HashMap<String, String>();
				Elements trs = table.select("tr");
				Element tr1 = trs.get(0);
				coursehomeworkItem.put("AssignmentNo", tr1.select("td").get(1)
						.text().trim());
				Element tr2 = trs.get(1);
				coursehomeworkItem.put("DueDate", tr2.select("td").get(1)
						.text().trim());
				Element tr3 = trs.get(3);
				coursehomeworkItem.put(
						"Description",
						tr3.select("td").get(1).html().trim()
								.replace("<br />", "\n")
								.replaceAll("<.+?>", "").replace("&gt;", ">")
								.replace("&lt", "<"));
				coursehomeworkItem.put("CourseNo", courseno);
				coursehomeworkItems.add(coursehomeworkItem);
			}
		} catch (Exception e) {
			throw e;
		}
		return this;
	}

	public List<Map<String, String>> getHomeworkItems() {
		return coursehomeworkItems;
	}
}

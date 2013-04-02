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

public class CourseAnnouncementParser {
	private List<Map<String, String>> announcementItems;

	public CourseAnnouncementParser() {
		announcementItems = new ArrayList<Map<String, String>>();
	}

	public CourseAnnouncementParser parser(String url) throws Exception {
		try {
			String sourceString = Net.getInstance().get(url);
			Document doc = Jsoup.parse(sourceString);
			Elements tables = doc.select("table.forum_text");
			for (Element table : tables) {
				Map<String, String> announcementItem = new HashMap<String, String>();
				Elements trs = table.select("tr");
				Elements tds = trs.get(0).select("td");
				announcementItem.put("Time", tds.get(0).text().trim());
				announcementItem.put("Title", tds.get(1).text().trim());
				Element td = trs.get(1).select("td").first();
				announcementItem.put(
						"Content",
						td.html().trim().replace("<br />", "\n")
								.replaceAll("<.+?>", "").replace("&gt;", ">")
								.replace("&lt", "<"));
				announcementItems.add(announcementItem);
			}
		} catch (Exception e) {
			throw e;
		}
		return this;
	}

	public List<Map<String, String>> getAnnouncementItems() {
		return announcementItems;
	}
}

package com.icecream.tssclient.paraser;

import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.icecream.tssclient.util.Net;

public class CourseInfoParser {
	private Map<String, String> infoItem;

	public CourseInfoParser() {
		infoItem = new HashMap<String, String>();
	}

	public CourseInfoParser parser(String url) throws Exception {
		try {
			String sourceString = Net.getInstance().get(url);
			Document doc = Jsoup.parse(sourceString);
			Elements tables = doc.select("table.forum_text");
			Elements trs = tables.get(0).select("tr");

			infoItem.put(
					"Info",
					trs.get(1).select("td").first().html().trim()
							.replace("<br />", "\n").replaceAll("<.+?>", "")
							.replace("&nbsp;", "").replace("&gt;", ">")
							.replace("&lt", "<"));
		} catch (Exception e) {
			throw e;
		}
		return this;
	}

	public Map<String, String> getForumItem() {
		return infoItem;
	}
}

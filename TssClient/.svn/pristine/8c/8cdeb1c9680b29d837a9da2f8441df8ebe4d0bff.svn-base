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

public class CourseSlidesParser {
	public static final int FOLDER = 1;
	public static final int PDF = 2;
	public static final int DOC = 3;
	public static final int XLS = 4;
	public static final int PPT = 5;
	public static final int RAR = 6;
	public static final int TXT = 7;
	public static final int FILE = 8;
	private List<Map<String, String>> slideItems;

	public CourseSlidesParser() {
		slideItems = new ArrayList<Map<String, String>>();
	}

	public CourseSlidesParser parser(String url) throws Exception {
		try {
			String sourceString = Net.getInstance().get(url);
			sourceString.replaceAll("&nbsp;", " ");
			sourceString.replaceAll("<br>", "\n");
			Document doc = Jsoup.parse(sourceString);
			Element ul = doc.select("ul").first();
			Elements imgs = ul.select("img");
			Elements as = ul.select("a");
			for (int i = 0; i < as.size(); i++) {
				String remo = as.get(i).text().trim();
				if (remo.equals("remove"))
					as.remove(i);
			}
			for (int i = 0; i < imgs.size(); i++) {
				Map<String, String> slideItem = new HashMap<String, String>();
				if ("Dir".equals(imgs.get(i).attr("alt"))) {
					slideItem.put("FileType", Integer.toString(FOLDER));
					slideItem.put("Url", "http://218.94.159.102"
							+ as.get(i).attr("href"));
					slideItem.put("FileName", as.get(i).text().trim());
				} else {
					String filename = as.get(i).text().trim();
					String filetype = filename.substring(filename
							.lastIndexOf(".") + 1);
					int type = 0;
					if ("pdf".equals(filetype))
						type = PDF;
					else if ("doc".equals(filetype) || "docx".equals(filetype))
						type = DOC;
					else if ("xls".equals(filetype) || "xlsx".equals(filetype))
						type = XLS;
					else if ("ppt".equals(filetype) || "pptx".equals(filetype))
						type = PPT;
					else if ("rar".equals(filetype) || "zip".equals(filetype))
						type = RAR;
					else if ("txt".equals(filetype))
						type = TXT;
					else
						type = FILE;

					slideItem.put("FileType", Integer.toString(type));
					slideItem.put("Url", "http://218.94.159.102"
							+ as.get(i).attr("href"));
					slideItem.put("FileName", filename);
				}
				slideItems.add(slideItem);
			}
		} catch (Exception e) {
			throw e;
		}
		return this;
	}

	public List<Map<String, String>> getSlideItems() {
		return slideItems;
	}
}

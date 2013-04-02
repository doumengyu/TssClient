package com.icecream.tssclient.paraser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.util.Log;

import com.icecream.tssclient.util.Net;

public class MyHomeworkParser {
	private List<Map<String, String>> homeworkItems;

	public MyHomeworkParser() {
		homeworkItems = new ArrayList<Map<String, String>>();
	}

	public MyHomeworkParser parser() throws Exception {
		try {
			String sourceString = Net.getInstance().get(
					"http://218.94.159.102/tss/en/home/myhomework.html");
			sourceString.replaceAll("&nbsp;", " ");
			sourceString.replaceAll("<br>", "\n");
			Document doc = Jsoup.parse(sourceString);
			Element table = doc.select("table.forum_text").first();
			Elements trs = table.select("tr");
			String coursenamesave = "";
			String coursenumbersave = "";
			List<Map<String, String>> coursehomeworkItems = new ArrayList<Map<String, String>>();
			for (Element tr : trs) {
				if (!tr.attr("align").equals("center")) {
					Map<String, String> homeworkItem = new HashMap<String, String>();
					Elements tds = tr.select("td");
					switch (tds.size()) {
					case 2:
						coursenumbersave = "";
						coursenamesave = "";
						continue;
					case 3:
						homeworkItem.put("CourseNo", coursenumbersave);
						homeworkItem.put("CourseName", coursenamesave);
						homeworkItem.put("SerialNumber", tds.get(0).select("a")
								.first().text().trim());
						String status = tds.get(2).text().trim();
						if (status.charAt(0) == 'S') {
							status = status.substring(0, 9);
						}
						homeworkItem.put("Status", status);
						break;
					case 4:
						String href = tds.get(0).select("a").first()
								.attr("href");
						String coursenumber = href.substring(8, 13);
						homeworkItem.put("CourseNo", coursenumber);
						homeworkItem
								.put("CourseName", tds.get(0).text().trim());
						homeworkItem.put("SerialNumber", tds.get(1).select("a")
								.first().text().trim());
						String status1 = tds.get(3).text().trim();
						if (status1.charAt(0) == 'S') {
							status1 = status1.substring(0, 9);
						}
						homeworkItem.put("Status", status1);
						coursenumbersave = coursenumber;
						coursenamesave = tds.get(0).text().trim();
						break;
					}
					if (null != homeworkItem) {
						if (coursehomeworkItems.size() != 0) {
							if (coursehomeworkItems.get(0).get("CourseNo")
									.equals(homeworkItem.get("CourseNo"))) {
								for (int i = 0; i < coursehomeworkItems.size(); i++) {
									if (coursehomeworkItems
											.get(i)
											.get("AssignmentNo")
											.equals(homeworkItem
													.get("SerialNumber"))) {
										homeworkItem.put(
												"DueDate",
												coursehomeworkItems.get(i).get(
														"DueDate"));
										if (coursehomeworkItems.get(i)
												.get("Description").length() > 21) {
											homeworkItem.put("Description",
													coursehomeworkItems.get(i)
															.get("Description")
															.replace("\n", " ")
															.substring(0, 20)
															+ "...");
										} else {
											homeworkItem
													.put("Description",
															coursehomeworkItems
																	.get(i)
																	.get("Description")
																	.replace(
																			"\n",
																			" "));
										}
									}
								}
							} else {
								String url = "http://218.94.159.102/tss/en/"
										+ homeworkItem.get("CourseNo")
										+ "/assignment/index.html";
								CourseHomeworkParser parser_deadline = new CourseHomeworkParser();
								try {
									parser_deadline.parser(url,
											homeworkItem.get("CourseNo"));
								} catch (Exception e) {
									Log.e("CourseList", e.getMessage());
								}
								for (int i = 0; i < parser_deadline
										.getHomeworkItems().size(); i++) {
									if (parser_deadline
											.getHomeworkItems()
											.get(i)
											.get("AssignmentNo")
											.equals(homeworkItem
													.get("SerialNumber"))) {
										homeworkItem.put("DueDate",
												parser_deadline
														.getHomeworkItems()
														.get(i).get("DueDate"));
										if (parser_deadline.getHomeworkItems()
												.get(i).get("Description")
												.length() > 21) {
											homeworkItem.put(
													"Description",
													parser_deadline
															.getHomeworkItems()
															.get(i)
															.get("Description")
															.replace("\n", " ")
															.substring(0, 20)
															+ "...");
										} else {
											homeworkItem
													.put("Description",
															parser_deadline
																	.getHomeworkItems()
																	.get(i)
																	.get("Description")
																	.replace(
																			"\n",
																			" "));
										}
										break;
									}
								}
								coursehomeworkItems = parser_deadline
										.getHomeworkItems();
							}
						} else {
							String url = "http://218.94.159.102/tss/en/"
									+ homeworkItem.get("CourseNo")
									+ "/assignment/index.html";
							CourseHomeworkParser parser_deadline = new CourseHomeworkParser();
							try {
								parser_deadline.parser(url,
										homeworkItem.get("CourseNo"));
							} catch (Exception e) {
								Log.e("CourseList", e.getMessage());
							}
							for (int i = 0; i < parser_deadline
									.getHomeworkItems().size(); i++) {
								if (parser_deadline
										.getHomeworkItems()
										.get(i)
										.get("AssignmentNo")
										.equals(homeworkItem
												.get("SerialNumber"))) {
									homeworkItem.put("DueDate",
											parser_deadline.getHomeworkItems()
													.get(i).get("DueDate"));
									if (parser_deadline.getHomeworkItems()
											.get(i).get("Description").length() > 21) {
										homeworkItem.put(
												"Description",
												parser_deadline
														.getHomeworkItems()
														.get(i)
														.get("Description")
														.replace("\n", " ")
														.substring(0, 20)
														+ "...");
									} else {
										homeworkItem.put(
												"Description",
												parser_deadline
														.getHomeworkItems()
														.get(i)
														.get("Description")
														.replace("\n", " "));
									}
									break;
								}
							}
							coursehomeworkItems = parser_deadline
									.getHomeworkItems();
						}
						homeworkItems.add(homeworkItem);
					}
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return this;
	}

	public List<Map<String, String>> getHomeworkItems() {
		return homeworkItems;
	}
}

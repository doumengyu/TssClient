package com.icecream.tssclient.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class Login {
	public boolean login(String UserName, String Password) throws Exception {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("username", UserName));
		params.add(new BasicNameValuePair("password", Password));
		String sourceString = Net.getInstance().post(
				"https://218.94.159.102/GlobalLogin/loginservlet", params);
		Boolean islogin = checklogin(sourceString);
		if (islogin) {
			Net.getInstance().login();

		}
		return islogin;
	}

	/**
	 * @param sourceString
	 * @return
	 */
	private boolean checklogin(String sourceString) {
		try {
			int start = sourceString.indexOf("<p>", 0);
			int end = sourceString.indexOf("</p>", start);
			String temp = sourceString.substring(start + 3, end);
			if (temp.equals("Login Failed")) {
				return false;
			}
		} catch (Exception e) {
			// 此时解析异常代表并没有转移到失败页面，即登录成功
		}
		return true;
	}
}
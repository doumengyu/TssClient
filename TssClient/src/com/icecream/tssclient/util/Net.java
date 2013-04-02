package com.icecream.tssclient.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.*;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Net {
	private static Net net;
	private DefaultHttpClient client;

	private Net() {
		client = getNewHttpClient();
	}

	public static Net getInstance() {
		if (net == null) {
			net = new Net();
		}
		return net;
	}

	public void clear() {
		client = new DefaultHttpClient();
	}

	public String get(String URL) throws Exception {
		String resultString;
		HttpGet sourceaddr = new HttpGet(URL);
		sourceaddr.setHeader("Accept-Encoding", "gzip, deflate");
		try {
			HttpResponse httpResponse = client.execute(sourceaddr);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				if ("gzip, deflate".equals(httpResponse
						.getFirstHeader("Accept-Encoding"))) {
					HttpEntity entity1 = httpResponse.getEntity(); // Ñ¹Ëõ¹ýµÄentity
					GzipDecompressingEntity entity = new GzipDecompressingEntity(
							entity1);
					resultString = readstream(entity.getContent());
				} else
					resultString = readstream(httpResponse.getEntity()
							.getContent());
			} else {
				throw new Exception("can't connect the network");
			}
			return resultString.toString();
		} catch (Exception e) {
			throw e;
		}
	}

	public String post(String URL, List<NameValuePair> params) throws Exception {
		String resultString;
		try {
			HttpPost httpRequest = new HttpPost(URL);
			httpRequest.setEntity(new UrlEncodedFormEntity(params, "GB2312"));
			HttpResponse httpResponse = client.execute(httpRequest);
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				resultString = readstream(httpResponse.getEntity().getContent());
			} else if (httpResponse.getStatusLine().getStatusCode() == 302) {
				resultString = null;
			} else {
				throw new Exception();
			}
			return resultString;
		} catch (Exception e) {
			throw e;
		}
	}

	public void login() throws Exception {
		List<Cookie> cookies = client.getCookieStore().getCookies();
		String jsession = cookies.get(0).getValue();
		String url = "http://218.94.159.102/tss/en/home/postSignin.html?SIGlobalLogin="
				+ jsession;
		List<NameValuePair> params1 = new ArrayList<NameValuePair>();
		params1.add(new BasicNameValuePair("SIGlobalLogin", jsession));
		this.post(url, params1);
	}

	public boolean checknetwork(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo info = connectivity.getActiveNetworkInfo();
			if (info != null) {
				if (info.getState() == NetworkInfo.State.CONNECTED) {
					return true;
				}
			}
		}
		return false;
	}

	public String getJessionid() {
		List<Cookie> cookies = client.getCookieStore().getCookies();
		String jessionid = "";
		if (!cookies.isEmpty()) {
			jessionid = cookies.get(0).getValue();
		}
		return jessionid;
	}

	public String islogin() {
		List<Cookie> cookies = client.getCookieStore().getCookies();
		String path = "";
		if (!cookies.isEmpty()) {
			for (Cookie cookie : cookies) {
				path = cookie.getPath();
			}
		}
		return path;
	}

	private String readstream(InputStream in) {
		StringBuffer resultString = new StringBuffer();
		try {
			BufferedReader inbuff = new BufferedReader(new InputStreamReader(
					in, "GB2312"));
			String line = "";
			while ((line = inbuff.readLine()) != null) {
				resultString.append('\n');
				resultString.append(line);
			}

		} catch (Exception e) {
		}
		return resultString.toString();
	}

	static class GzipDecompressingEntity extends HttpEntityWrapper {

		public GzipDecompressingEntity(final HttpEntity entity) {
			super(entity);
		}

		@Override
		public InputStream getContent() throws IOException,
				IllegalStateException {

			InputStream wrappedin = wrappedEntity.getContent();

			return new GZIPInputStream(wrappedin);
		}

		@Override
		public long getContentLength() {
			return -1;
		}

	}

	public static DefaultHttpClient getNewHttpClient() {
		try {
			KeyStore trustStore = KeyStore.getInstance(KeyStore
					.getDefaultType());
			trustStore.load(null, null);

			SSLSocketFactory sf = new SSLSocketFactoryEx(trustStore);
			sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

			HttpParams params = new BasicHttpParams();
			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
			HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);

			SchemeRegistry registry = new SchemeRegistry();
			registry.register(new Scheme("http", PlainSocketFactory
					.getSocketFactory(), 80));
			registry.register(new Scheme("https", sf, 443));

			ClientConnectionManager ccm = new ThreadSafeClientConnManager(
					params, registry);

			return new DefaultHttpClient(ccm, params);
		} catch (Exception e) {
			return new DefaultHttpClient();
		}
	}

}
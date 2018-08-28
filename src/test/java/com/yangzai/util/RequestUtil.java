package com.yangzai.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.DefaultHttpParams;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class RequestUtil{
	private static String DEFAULT_CHARSET ="UTF-8";
	/**
	 * POST方式请求数据
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String getDataByPost(String url, Map<String, String> params) throws IOException {
		final HttpClient httpClient = new HttpClient();
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(25 * 1000);
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(30 * 1000);
		//不设置该参数将导致 cookie rejected
		DefaultHttpParams.getDefaultParams().setParameter("http.protocol.cookie-policy", CookiePolicy.BROWSER_COMPATIBILITY);
		/*
		 * 所有参数、URL 传入 PostMethod的requestBody
		 * 返回的string被httpClient存入 PostMethod的responseBody
		 */
		final PostMethod method = new PostMethod(url);
		if (params != null) {
			method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, DEFAULT_CHARSET);
			//Map<String,String> 转为 NameValuePair[]对象数组
			method.setRequestBody(assembleRequestParams(params));
		}
		String result = "";
		try {
			httpClient.executeMethod(method);
			result = new String(method.getResponseBody(), DEFAULT_CHARSET);
		} finally {
			method.releaseConnection();
		}
		return result;
	}
	private static NameValuePair[] assembleRequestParams(Map<String, String> data) {
		final List<NameValuePair> nameValueList = new ArrayList<NameValuePair>();

		Iterator<Map.Entry<String, String>> it = data.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>) it.next();
			nameValueList.add(new NameValuePair((String) entry.getKey(), (String) entry.getValue()));
		}
		return nameValueList.toArray(new NameValuePair[nameValueList.size()]);
	}
	/**
	 * GET 方式请求数据 参数全部在URL中
	 * @param url
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public static String getDataByGet(String url) throws Exception {
		final HttpClient httpClient = new HttpClient();
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(10 * 1000);
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(10 * 1000);

		final GetMethod method = new GetMethod(url);
		String result = "";
		try {
			httpClient.executeMethod(method);
			result = new String(method.getResponseBody(), DEFAULT_CHARSET);
		} catch (final Exception e) {
			throw e;
		} finally {
			method.releaseConnection();
		}
		return result;
	}
	/**
	 * PARAMS 请求数据
	 * @param URL
	 * @param params "goods_id=3&limit=20"
	 * @return
	 * @throws IOException 
	 */
	public static String getDataByPost(String URL, String params) throws IOException {
		DataOutputStream out=null;
		BufferedReader reader=null;
		HttpURLConnection connection=null;
		try {
			URL url = new URL(URL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");  
			out = new DataOutputStream(connection.getOutputStream());
			out.write(params.getBytes(DEFAULT_CHARSET));
			out.flush();
			//connection.getInputStream()的时候才开启连接
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String lines;
			StringBuffer sbf = new StringBuffer();
			//写到内存里可以用readLine的形式,JSON 字符串就一行 通常readLine一次就获取完
			while ((lines = reader.readLine()) != null) {
				lines = new String(lines.getBytes(), DEFAULT_CHARSET);
				sbf.append(lines);
			}
			return sbf.toString();
		}finally{
			if(out!=null)
				out.close();
			if(reader!=null)
				reader.close();
			if(connection!=null)
				connection.disconnect();
		}
	}
	
	/**
	 * 下载数据存储到文件
	 * @param URL
	 * @param savePath
	 * @throws Exception
	 */
	public static void downloadFile(String URL,String savePath) throws Exception{
		URL url = new URL(URL);//URL代表一个网址
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();//HttpURLConnection就是与该网址/资源的一个连接
		if(200==conn.getResponseCode()){//getResponseCode在内部调用getInputStream()————>创建连接
//			FileUtils.saveFile(conn.getInputStream(),savePath);
		}
	}
}

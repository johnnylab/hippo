package com.yangzai.httpclient;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpClientTest {
	public static String WEBHOOK_TOKEN = "https://oapi.dingtalk.com/robot/send?access_token=c176f5afdcf595dfa5ff137276c3c7e9ea4d74621de6dce74ba9a45f99d5454e";
	public static HttpClient httpclient = HttpClients.createDefault();
	public static void main(String[] args) throws Exception {
		execute();
		executeValubio();
		execute();
	}
	public static void execute() throws ClientProtocolException, IOException {
		HttpPost httppost = new HttpPost(WEBHOOK_TOKEN);
		httppost.addHeader("Content-Type", "application/json; charset=utf-8");

		StringEntity se = new StringEntity("hi", "utf-8");
		httppost.setEntity(se);

		HttpResponse response = httpclient.execute(httppost);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			String result = EntityUtils.toString(response.getEntity(), "utf-8");
			log.info(result);
		}
	}
	public static void executeValubio() throws ClientProtocolException, IOException {
		HttpPost httppost = new HttpPost("https://www.valubio.com/goodsInterfaces.api?searchGoodsDetailList");
		httppost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		
		StringEntity se = new StringEntity("login_type=3&goods_uuid=1&goods_name=&page=1&min_pc_price=&max_pc_price=&brand_id=4&activity_id=&label_id=&storehouse_name=&sort=&sort_way=&limit=40&member_id=0&member_token=0", "utf-8");
		httppost.setEntity(se);
		
		HttpResponse response = httpclient.execute(httppost);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			String result = EntityUtils.toString(response.getEntity(), "utf-8");
			log.info(result);
		}
	}
}

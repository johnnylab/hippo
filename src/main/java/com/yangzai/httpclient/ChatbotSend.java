package com.yangzai.httpclient;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChatbotSend {
	public static String WEBHOOK_TOKEN = "https://oapi.dingtalk.com/robot/send?access_token=c176f5afdcf595dfa5ff137276c3c7e9ea4d74621de6dce74ba9a45f99d5454e";

	public static void main(String args[]) throws Exception {
		HttpClient httpclient = HttpClients.createDefault();

		HttpPost httppost = new HttpPost(WEBHOOK_TOKEN);
		httppost.addHeader("Content-Type", "application/json; charset=utf-8");

		StringEntity se = new StringEntity(templateText(), "utf-8");
		httppost.setEntity(se);

		HttpResponse response = httpclient.execute(httppost);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			String result = EntityUtils.toString(response.getEntity(), "utf-8");
			log.info(result);
		}
	}

	/**
	 * 1) 这种类型的消息不支持\" 必须输入 \\\" 否则会有异常
	 * 2) 如果手机号在群中不存在，则请求任然成功，只是不会@群成员
	 * @return
	 */
	public static String templateText() {
		String msg="fresh";
		String mobiles = "17621257002";
		String textMsg = "{ " + "\"msgtype\": \"text\", " + "\"text\": {\"content\":\" " + msg + "\"}," + " \"at\": {"
				+ "\"atMobiles\":[\"" + mobiles + "\"],\"isAtAll\": false}" + "}";
		return textMsg;
	}
}
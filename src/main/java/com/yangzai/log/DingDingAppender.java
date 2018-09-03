package com.yangzai.log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import ch.qos.logback.core.OutputStreamAppender;
import ch.qos.logback.core.encoder.Encoder;
import ch.qos.logback.core.status.ErrorStatus;

public final class DingDingAppender<E> extends OutputStreamAppender<E> {
	// 回调地址
	private volatile String webhooks;
	// @手机号
	private volatile String mobiles;
	private volatile List<String> urlList;
	private final Semaphore semaphore = new Semaphore(1);
	private final AtomicLong failCounter = new AtomicLong(1);
	// 维持唯一一个httpClient即可
	private volatile CloseableHttpClient httpclient;

	@Override
    public void start() {
        if (webhooks == null || webhooks.equals("")) {
            addError("parameters webhooks is missing. Cannot start.");
            return;
        }
        String[] urlArr = webhooks.split(",");
        urlList = new ArrayList<String>(urlArr.length);
        for(String url:urlArr){
        	url = url.trim();
        	if(url.startsWith("http")){
        		urlList.add(url);
        	}
        }
        if(urlList.size()==0){
        	 addError("parameters webhooks dont have any permitted url");
             return;
        }
        if (this.encoder == null) {
            addStatus(new ErrorStatus("No encoder set for the appender named \"" + name + "\".", this));
            return;
        }
        httpclient= HttpClients.createDefault();
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream(10*1024);
        setOutputStream(outputStream);
        super.start();
    }
	@Override
	public void stop() {
		super.stop();
		try {
			httpclient.close();
		} catch (IOException e) {
		}
    }

	@Override
	protected void append(E eventObject) {
		try {
			// 为防止多个线程同时对成员变量outputStream进行write
			// 这里需要进行并发控制
			semaphore.acquire();
			// 输出到一块缓存中
			super.append(eventObject);
			ByteArrayOutputStream outputSteam = (ByteArrayOutputStream) getOutputStream();
			try {
				notifyInDingDing(outputSteam.toByteArray());
			} catch (IOException e) {
			} finally{
				// 重置缓存
				outputSteam.reset();
			}
		} catch (InterruptedException e) {
		} finally {
			semaphore.release();
		}
	}

	private void notifyInDingDing(byte[] bytes) throws ClientProtocolException, IOException {
		if(urlList==null||urlList.size()==0){
			return;
		}
		for(String url:urlList){
			String msg = new String(bytes, "UTF-8");

			HttpPost httppost = new HttpPost(url);
			httppost.addHeader("Content-Type", "application/json; charset=utf-8");

			StringEntity se = new StringEntity(templateText(msg), "utf-8");
			httppost.setEntity(se);

			HttpResponse response = httpclient.execute(httppost);
			if (HttpStatus.SC_OK != response.getStatusLine().getStatusCode()) {
				failCounter.incrementAndGet();
			}
		}
	}

	/**
	 * 1) 消息不支持\" 必须输入 \" 否则会有异常 
	 * 2) 如果手机号在群中不存在，则请求任然成功，只是不会@群成员
	 * @return
	 */
	private String templateText(String msg) {
		msg = msg.replaceAll("\"", "\\\\\"");
		String textMsg = "{ " + "\"msgtype\": \"text\", " + "\"text\": {\"content\":\"" + msg + "\"}," + " \"at\": {"
				+ "\"atMobiles\":[\"" + mobiles + "\"],\"isAtAll\":false}" + "}";
		return textMsg;
	}

	public String getMobiles() {
		return mobiles;
	}

	public void setMobiles(String mobiles) {
		this.mobiles = mobiles;
	}
	public String getWebhooks() {
		return webhooks;
	}
	public void setWebhooks(String webhooks) {
		this.webhooks = webhooks;
	}
	public Encoder<E> getEncoder() {
		return encoder;
	}

	public void setEncoder(Encoder<E> encoder) {
		this.encoder = encoder;
	}
	
}

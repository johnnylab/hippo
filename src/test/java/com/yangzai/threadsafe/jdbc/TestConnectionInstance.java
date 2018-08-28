package com.yangzai.threadsafe.jdbc;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class TestConnectionInstance {
	ConnectionInstance instance = new ConnectionInstance();
	@Test
	public void testExecute(){
		ThreadPoolExecutor service = new ThreadPoolExecutor(2,2,10,TimeUnit.MINUTES,new LinkedBlockingQueue<Runnable>());
		Runnable task1 = new Runnable(){
			public void run() {
				instance.execute(1);
			}
		};
//		Runnable task2 = new Runnable(){
//			public void run() {
//				instance.execute(2);
//			}
//		};
		service.submit(task1);
//		service.submit(task2);
		service.shutdown();
	}
}

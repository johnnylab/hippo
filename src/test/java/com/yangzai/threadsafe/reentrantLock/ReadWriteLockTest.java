package com.yangzai.threadsafe.reentrantLock;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.junit.Test;
/**
 * 读写分离（读写互斥，写写互斥，读读不互斥）
 * @author Administrator
 *
 */
public class ReadWriteLockTest {
	private final ReadWriteLock mainLock = new ReentrantReadWriteLock();
	private final Map<String,String> cache = new LinkedHashMap<String,String>();
	@Test
	public void testReadWriteConcurrent(){
		ThreadPoolExecutor service = new ThreadPoolExecutor(3, 3,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
		service.submit(new WriteTask(mainLock,cache));
		service.submit(new ReadTask(mainLock,cache));
		service.submit(new ReadTask(mainLock,cache));
		System.out.println(service.getCompletedTaskCount());
		service.shutdown();
		try {
			service.awaitTermination(10, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(service.getCompletedTaskCount());
	}
	static class WriteTask implements Runnable {
		private final ReadWriteLock lock;
		private final Map<String,String> cache;
		public WriteTask(ReadWriteLock lock,Map<String,String> cache){
			this.lock=lock;
			this.cache=cache;
		}
		@Override
		public void run() {
			lock.writeLock().lock();
			try{
				cache.put("john", "tiger");
				System.out.println("write value: john tiger");
			}finally{
				lock.writeLock().unlock();
			}
		}
	}
	static class ReadTask implements Runnable {
		private final ReadWriteLock lock;
		private final Map<String,String> cache;
		public ReadTask(ReadWriteLock lock,Map<String,String> cache){
			this.lock=lock;
			this.cache=cache;
		}
		@Override
		public void run() {
			lock.readLock().lock();
			try{
				String value = cache.get("john");
				System.out.println("read value:"+value);
			}finally{
				lock.readLock().unlock();
			}
		}
	}
}

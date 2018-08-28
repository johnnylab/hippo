package com.yangzai.trasher;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Calulator {
	public static void calc(){
		double pi=0;
	    double dx=1e-3;
	    for(double x=-100;x<=+100;x+=dx){
	      pi+=Math.exp(-x*x)*dx;
	    }
	    System.out.println(pi*pi);
	}
	public static void main(String[] args) {
		ThreadPoolExecutor service = new ThreadPoolExecutor(8, 8,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
		service.submit(new CalTT());
//		service.submit(new CalTT());
//		service.submit(new CalTT());
//		service.submit(new CalTT());
//		service.submit(new CalTT());
//		service.submit(new CalTT());
//		service.submit(new CalTT());
//		service.submit(new CalTT());
		// 查看睿频
	}
	static class CalTT implements Runnable{

		@Override
		public void run() {
			while(true){
				calc();
			}
		}
		
	}
}

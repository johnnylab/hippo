package com.yangzai.boot.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TraceService {
	/**
	 * 异步任务
	 * @param response
	 * @return
	 */
	@Async
	public void asyncMethod(){
		log.info("Start Async Method");
		log.info("End Async Method");
	}
	/**
	 * 同步任务
	 * @param response
	 * @return
	 */
	public void syncMethod() {
		log.info("Start Sync Method");
		log.info("End Sync Method");
	}
}

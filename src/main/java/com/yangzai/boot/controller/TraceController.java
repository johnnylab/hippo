package com.yangzai.boot.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yangzai.boot.service.TraceService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class TraceController {
	@Resource
	private TraceService traceService;
	
	@RequestMapping(value = "/sync", method = RequestMethod.GET)
	public String sync(HttpServletResponse response) {
		log.info("sync");
		traceService.syncMethod();
		return "sync";
	}
	
	@RequestMapping(value = "/async", method = RequestMethod.GET)
	public String async(HttpServletResponse response) {
		log.info("async");
		traceService.asyncMethod();
		return "async";
	}
	/**
	 * 每3秒执行一次的计划任务
	 * @throws InterruptedException
	 */
	@Scheduled(fixedDelay = 3000)
	public void scheduledWork() throws InterruptedException {
		log.info("Start some work from the scheduled task");
		traceService.asyncMethod();
		log.info("End work from scheduled task");
	}
}

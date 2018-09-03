package com.yangzai.log;

import org.apache.log4j.MDC;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MDCUseTest {
	public static void main(String[] args) {
		MDC.put("X-B3-TraceId",LogUtil.createTraceId());
		log.error(LogUtil.field("heytestu error"), "");
		System.out.println("......");
	}
}

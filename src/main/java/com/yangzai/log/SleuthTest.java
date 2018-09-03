package com.yangzai.log;
/**
 * 请求链路
 * @author Administrator
 */
public class SleuthTest {
	private String traceId;
	
	private String spanId;

	/**
	 * 是否要将该信息输出到类似Zipkin这样的聚合器进行收集和展示。
	 */
	private boolean export;
}

package com.yangzai.log;

import java.util.Map;
import java.util.UUID;

import org.apache.log4j.MDC;
import org.slf4j.Marker;

import net.logstash.logback.marker.Markers;
/**
 * 日志工具类 
 * @author JohnnyLab
 */
public class LogUtil {
	public static final String DEFAULT_FIELD_NAME="field";
	public static final String INNER_TRACE_ID="traceId";
	/**
	 * 自定义单个field输出
	 * e.g log.info(LogUtil.field("member", member),null);
	 * @param fieldName
	 * @param object
	 * @return
	 */
	public static Marker field(String fieldName, Object ... objects) {
		if(objects==null||objects.length==0){
			return Markers.append(DEFAULT_FIELD_NAME,fieldName);
		}
		if(objects.length==1){
			return Markers.append(fieldName, objects[0]);
		}
		return Markers.appendArray(fieldName, objects);
	}

	/**
	 * 自定义多个field输出
	 * e.g log.info(LogUtil.field("list", list),null);
	 * @param object
	 * @return
	 */
	public static Marker fields(Object object) {
		if (object instanceof Map) {
			return Markers.appendEntries((Map<?, ?>) object);
		}
		if(object instanceof String){
			return Markers.append(DEFAULT_FIELD_NAME, object);
		}
		return Markers.appendFields(object);
	}
	/**
	 * 创建traceId
	 * @return
	 */
	private static String createTraceId(){
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.toUpperCase().replace("‐", "");
		return uuid;
	}
	public static void setTraceId(){
		MDC.put(INNER_TRACE_ID, LogUtil.createTraceId());
	}
	public static void getTraceId(){
		MDC.get(INNER_TRACE_ID);
	}
	public static void removeTraceId(){
		MDC.remove(INNER_TRACE_ID);
	}
}

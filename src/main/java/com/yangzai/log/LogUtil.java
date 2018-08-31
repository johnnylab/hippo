package com.yangzai.log;

import java.util.Map;

import org.slf4j.Marker;

import net.logstash.logback.marker.Markers;

public class LogUtil {
	public static final String DEFAULT_FIELD_NAME="field";
	/**
	 * i.g {"member":{"uid":"0133","name":"john","gender":"male"}} 
	 * i.g {"members":[{"uid":"11000","name":"john","gender":"male"},{"name2":"value2","name1":"value1"}]}
	 * i.g {"author":"john"}
	 * 
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
	 * i.g {"uid":"0133","name":"john","gender":"male"}
	 * 
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
}

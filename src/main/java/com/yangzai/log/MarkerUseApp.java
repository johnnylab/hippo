package com.yangzai.log;

import static net.logstash.logback.marker.Markers.append;
import static net.logstash.logback.marker.Markers.appendArray;
import static net.logstash.logback.marker.Markers.appendEntries;
import static net.logstash.logback.marker.Markers.appendFields;
import static net.logstash.logback.marker.Markers.appendRaw;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MarkerUseApp {
	public static void main(String[] args) {
		log.info(append("11111111", "22222222"), "log message 柳俊阳");
		/**
		 * appendEntries 打印Map对象信息，会有多个属性
		 */
		Map<String,String> myMap = new HashMap<>();
		myMap.put("name1", "value1");
		myMap.put("name2", "value2");
		log.info(appendEntries(myMap), null);
		
		/**
		 * appendArray 打印数组信息，对应一个属性
		 * ,"array":[1,2,3],
		 */
		log.info(appendArray("array", 1, 2, 3), "log message");
		
		/**
		 * appendRaw 打印JSON字符串
		 */
		log.info(appendRaw("array", "{}"), "log message");
		
		/**
		 * append一个对象
		 * 打印一个对象的信息：只有一个属性
		 * {"member":{"uid":"11000","name":"john","gender":"male"},...}
		 */
		Member member = new Member();
		member.setGender("male");
		member.setName("john");
		member.setUid("11000");
		log.info(append("member", member),null);
		
		/**
		 * appendFields 打印一个对象的信息：但对象的
		 * The fields of an object can be written directly into the json output.
		 */
		log.info(appendFields(member),null);
	}
}

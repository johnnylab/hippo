package com.yangzai.log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogUtilUseApp {
	public static void main(String[] args) {
		log.info(LogUtil.field("heytestu"), "");
		
		log.info(LogUtil.field("11111111", "22222222"), "log message 柳俊阳");
		/**
		 * appendEntries 打印Map对象信息，会有多个属性
		 */
		Map<String,String> myMap = new HashMap<>();
		myMap.put("name1", "value1");
		myMap.put("name2", "value2");
		log.info(LogUtil.fields(myMap), null);
		
		/**
		 * appendArray 打印数组信息，对应一个属性
		 * ,"array":[1,2,3],
		 */
		log.info(LogUtil.field("array", 1, 2, 3), "log message");
		
		/**
		 * appendRaw 打印JSON字符串
		 */
		log.info(LogUtil.field("array", "{}"), "log message");
		
		/**
		 * append一个对象
		 * 打印一个对象的信息：只有一个属性
		 * {"member":{"uid":"11000","name":"john","gender":"male"},...}
		 */
		Member member = new Member();
		member.setGender("male");
		member.setName("john");
		member.setUid("11000");
		log.info(LogUtil.field("member", member),null);
		
		/**
		 * appendFields 打印一个对象的信息：但对象的
		 * The fields of an object can be written directly into the json output.
		 */
		log.info(LogUtil.fields(member),"myMap");
		
		log.info(LogUtil.field("members",member,myMap),"apendMembersArray");
		
		List<Member> list = new ArrayList<Member>();
		list.add(member);
		list.add(member);
		list.add(member);
		list.add(member);
		log.info(LogUtil.field("list", list),null);
		
		List<Map<String,String>> list1 = new ArrayList<Map<String,String>>();
		list1.add(myMap);
		list1.add(myMap);
		list1.add(myMap);
		list1.add(myMap);
		list1.add(myMap);
		log.info(LogUtil.field("list", list1),null);
	}
}

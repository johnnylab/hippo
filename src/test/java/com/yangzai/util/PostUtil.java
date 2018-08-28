package com.yangzai.util;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class PostUtil {
	@Test
	public void  post() throws Exception {
		Map<String,String> params = new HashMap<String,String>();
		params.put("username", "john");
		params.put("password", "tiger1");
		params.put("rememberMe", "true");
		String msg = RequestUtil.getDataByPost(
				"http://localhost:8080/login", 
				params);
		System.out.println(msg);
	}
	@Test
	public void  get() throws Exception {
		String msg = RequestUtil.getDataByGet(
				"http://localhost:8883/api/privileges/getPrivilegeDetail?id=65&_=1532067999302" 
				);
		System.out.println(msg);
	}
}

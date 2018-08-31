package com.yangzai.log;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ErrorTestApp {
	public static void main(String[] args) {
		try{
			int i=10/0;
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}
	}
}

package com.yangzai.boot.starter.dao;

import org.springframework.beans.factory.annotation.Value;

import com.yangzai.boot.starter.vo.UserDO;

public class UserDao {
	@Value("${jdbc.url}")
	private String jdbcUrl;
	@Value("${salt}")
	private String salt;
	public UserDO getUserByUsername(String username){
		UserDO user = new UserDO();
		user.setGender(username.charAt(0)%2==1?"female":"male");
		user.setUsername(username);
		user.setPassword(salt+username+salt);
		return user;
	}
}

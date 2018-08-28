package com.yangzai.boot.starter.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.yangzai.boot.starter.dao.UserDao;
import com.yangzai.boot.starter.vo.UserDO;

@Controller
public class UserService {
	@Resource
	UserDao userDAO;
	public UserDO getUserByUsername(String username){
		return userDAO.getUserByUsername(username);
	}
}

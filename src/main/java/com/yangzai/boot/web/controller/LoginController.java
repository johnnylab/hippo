package com.yangzai.boot.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
	
	@RequestMapping(value="/login",method=RequestMethod.GET,produces="text/plain;charset=utf-8")
	public String login(){
		return "登录成功";
	}
}

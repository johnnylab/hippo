package com.yangzai.boot.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class CookieController {
	@RequestMapping(value = "/health", method = RequestMethod.GET)
    public String health(HttpServletResponse response){
		Cookie cookie = new Cookie("sessionId","098765");
		cookie.setPath("/");
		cookie.setHttpOnly(true);
//		cookie.setMaxAge(60*60);//存续一个小时
		response.addCookie(cookie);
        return "health";
    }
}

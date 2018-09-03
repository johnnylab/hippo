package com.yangzai.boot.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class HealthController {
	@RequestMapping(value = "/health", method = RequestMethod.GET)
    public String health(HttpServletResponse response){
		log.info("health");
        return "health";
    }
}
 
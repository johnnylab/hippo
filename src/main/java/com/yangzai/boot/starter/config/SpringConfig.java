package com.yangzai.boot.starter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.yangzai.boot.starter.dao.UserDao;

@Configuration
@ComponentScan(basePackages = {
		"com.yangzai.boot.starter.service"
		})
// 扫描外部的配置文件
@PropertySource(value= {"classpath:META-INF/properties/jdbc.properties"}
,ignoreResourceNotFound=true)
public class SpringConfig {
	@Bean 
    public UserDao dds(){
        return new UserDao();
    }
}

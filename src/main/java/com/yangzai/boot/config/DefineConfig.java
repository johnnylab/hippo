package com.yangzai.boot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Data;

// 指定了自定义资源的位置
@PropertySource("classpath:define.properties")
@ConfigurationProperties(prefix="defineTest")
@Component
@Data
public class DefineConfig {
	private String pname;
	private String password;
}

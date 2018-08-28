package com.yangzai.boot.config;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@ConfigurationProperties(prefix="mybatis.mybatises")
public class MabatisConfig {
	private List<Map<String,String>> workbench;
	private String help;
}

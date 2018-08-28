package com.yangzai.boot.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix="global")
@Data
public class EtaConfig {
//	private Long appId;
//	
//	private String clientId;
//	
//	private String clientSecret;
}

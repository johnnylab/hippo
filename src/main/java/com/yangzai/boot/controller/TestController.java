package com.yangzai.boot.controller;


import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yangzai.boot.config.DefineConfig;
import com.yangzai.boot.config.EtaConfig;
import com.yangzai.boot.config.MabatisConfig;
import com.yangzai.boot.profile.IHelper;

@RestController
@RequestMapping(value = "/api/employee")
public class TestController {
	@Resource
	private EtaConfig etaConfig;
	@Value("${test.help}")
	public String uaa;
	@Resource
	private DefineConfig defineConfig;
	private String springApplicationName;
	@Resource
	private MabatisConfig mabatisConfig;
	
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String test(){
        return springApplicationName+uaa+defineConfig.getPname();
    }
    @RequestMapping(value = "/my", method = RequestMethod.GET)
    public String my(){
    	Map<String,String> map= mabatisConfig.getWorkbench().get(0);
    	return map.get("type-aliases-package")+map.get("mapper-locations")+mabatisConfig.getHelp();
    }
}
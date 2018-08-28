package com.yangzai.boot.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("yangzai")
public class DevHelper implements IHelper{

	@Override
	public String help() {
		return "DevHelper";
	}
	
}

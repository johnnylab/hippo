package com.yangzai.boot.profile;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
public class TestHelper implements IHelper{

	@Override
	public String help() {
		return "TestHelper";
	}

}

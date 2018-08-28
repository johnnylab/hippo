package com.yangzai.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;
import org.apache.shiro.mgt.SecurityManager;
public class ShiroDemo {
	@Test
	public void testHelloworld() {
		Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		SecurityManager securityManager = factory.getInstance();
		
	    SecurityUtils.setSecurityManager(securityManager);
	    Subject subject = SecurityUtils.getSubject();
	    UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
	    try {
	        subject.login(token);
	    } catch (AuthenticationException e) {
	    	e.printStackTrace();
	    }
	    Assert.assertEquals(true, subject.isAuthenticated());
	    subject.logout();
	}
}

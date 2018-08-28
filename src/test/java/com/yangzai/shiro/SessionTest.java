package com.yangzai.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * shiro 的 session管理
 * 
 * @author johnl
 *
 */
public class SessionTest {
	private CustomRealm realm = new CustomRealm();
	
	@Test
	public void testSession() {
		DefaultSecurityManager securityManager = new DefaultSecurityManager();
		securityManager.setRealm(realm);
		SecurityUtils.setSecurityManager(securityManager);
		// 获取主体
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("john", "tiger");
		subject.login(token);
		// 从主体中获取session
		Session session = subject.getSession(true);
		System.out.println("SESSION ID = " + session.getId()); 
		System.out.println("用户名：" + subject.getPrincipal()); 
		System.out.println("HOST：" + session.getHost()); 
		System.out.println("TIMEOUT ：" + session.getTimeout()); 
		System.out.println("START：" + session.getStartTimestamp()); 
		System.out.println("LAST：" + session.getLastAccessTime());
		session.setAttribute("nickname", "阳仔");
		System.out.println(session.getAttribute("nickname"));
		// 销毁session
		session.stop();
	}
}

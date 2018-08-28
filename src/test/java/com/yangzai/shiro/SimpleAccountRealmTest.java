package com.yangzai.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

public class SimpleAccountRealmTest {
	/**
	 * realm 就是一个数据源
	 */
	private SimpleAccountRealm realm = new SimpleAccountRealm();
	@Before
	public void addUser(){
		realm.addAccount("john", "43b90920409618f188bfc6923f16b9fa","admin");
		realm.addAccount("cherry", "d0763edaa9d9bd2a9516280e9044d885","admin");
		realm.addAccount("guest", "e10adc3949ba59abbe56e057f20f883e","guest");
	}
//	
//	private CustomRealm realm = new CustomRealm();
	@Test
	public void testAuthetication(){
		// （1）首先构建SecurityUtils的环境 
		// SecurityUtils使用哪一个SecurityManger
		// SecurityManger使用哪一个数据源Realm
		// realm中密码的加密方式
		DefaultSecurityManager securityManager = new DefaultSecurityManager();
		securityManager.setRealm(realm);
		SecurityUtils.setSecurityManager(securityManager);
		// 甚至对密码的加密算法
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher("md5");
		matcher.setHashIterations(1);
		realm.setCredentialsMatcher(matcher);
		
		// （2）从SecurityUtils中获取主体来login，来check是否有某Role， 来check是否有某Permission
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("john","tiger1");
		try{
			subject.login(token);
			System.out.println("登录成功");
		}catch(IncorrectCredentialsException e){
			System.out.println("密码错误："+e.getMessage());
		}catch(UnknownAccountException e){
			System.out.println("无此用户："+e.getMessage());
		}
	}
	@Test
	public void testAuthority(){
		DefaultSecurityManager securityManager = new DefaultSecurityManager();
		securityManager.setRealm(realm);
		SecurityUtils.setSecurityManager(securityManager);
		// 获取主体
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("john","tiger");
		subject.login(token); 
		try{
			subject.checkRole("admin");
		}catch(UnauthenticatedException e){
			System.out.println("该主体无此角色");
		}
		try{
			subject.checkPermission("user:query");
		}catch(UnauthenticatedException e){
			System.out.println("该主体无此权限");
		}
		
	}
}

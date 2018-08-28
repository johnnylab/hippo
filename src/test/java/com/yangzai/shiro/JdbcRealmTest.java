package com.yangzai.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.druid.pool.DruidDataSource;

public class JdbcRealmTest {
	private JdbcRealm realm = new JdbcRealm();
	@Before
	public void init(){
		DruidDataSource source = new DruidDataSource();
		source.setUrl("jdbc:mysql://localhost:3306/ant");
		source.setUsername("root");
		source.setPassword("tiger");
		realm.setDataSource(source);
		realm.setPermissionsLookupEnabled(true);
	}
	
	@Test
	public void testAuthetication(){
		// （1）首先构建SecurityUtils的环境 
		// SecurityUtils使用哪一个SecurityManger
		// SecurityManger使用哪一个数据源Realm
		DefaultSecurityManager securityManager = new DefaultSecurityManager();
		securityManager.setRealm(realm);
		SecurityUtils.setSecurityManager(securityManager);
		
		// （2）从SecurityUtils中获取主体来login验证，来check是否有某Role， 来check是否有某Permission
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("john","tiger1");
		try{
			subject.login(token);
			System.out.println("验证成功: "+subject.isAuthenticated());
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
		UsernamePasswordToken token = new UsernamePasswordToken("john","tiger1");
		subject.login(token); 
		try{
			subject.checkRole("admin");
		}catch(UnauthorizedException e){
			System.out.println("该主体无此角色");
		}
		try{
			subject.checkPermission("user:add");
		}catch(UnauthorizedException e){
			System.out.println("该主体无此权限");
		}
	}
}

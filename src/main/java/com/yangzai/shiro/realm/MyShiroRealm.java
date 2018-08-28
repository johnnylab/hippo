package com.yangzai.shiro.realm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.StringUtils;

import com.yangzai.shiro.vo.User;
public class MyShiroRealm extends AuthorizingRealm{
	private Map<String,String> users;
	public MyShiroRealm(){
		users = new HashMap<String,String>();
		users.put("john", "tiger");
		users.put("cherry", "monkey");
		users.put("xiaofang", "mouse");
	}
	/**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        //获取用户
    	SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
        //获取用户角色
        Set<String> roleSet = new HashSet<String>();
        roleSet.add("100002");
        roleSet.add("admin");
        info.setRoles(roleSet);
        //获取用户权限
        Set<String> permissionSet = new HashSet<String>();
        permissionSet.add("权限添加");
        permissionSet.add("权限删除");
        info.setStringPermissions(permissionSet);
        return info;
    }

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;  
        String accountName = token.getUsername();
        if(!StringUtils.isEmpty(accountName)){
        	// 数据库中查询账号
        	String password = new String(token.getPassword());
            User user = findUser(accountName,password);
            if(user != null){
            	return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
            }
        }
        return null;    
     }
    	
    private User findUser(String accountName,String password){
    	String pass = users.get(accountName);
    	if(!StringUtils.isEmpty(pass)&&pass.equals(password)){
    		User user = new User();
    		user.setPassword(pass);
    		user.setUsername(accountName);
    		return user;
    	}
    	return null;
    }
}

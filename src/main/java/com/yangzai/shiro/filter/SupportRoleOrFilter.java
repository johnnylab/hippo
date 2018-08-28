package com.yangzai.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

/**
 * 
 * 和授权相关-继承AuthorizationFilter
 * 和认证相关-继承AuthenticatingFilter
 * @author Administrator
 *
 */
public class SupportRoleOrFilter extends AuthorizationFilter {

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		Subject subject = SecurityUtils.getSubject();
		String[] roles=(String[])mappedValue;
		if(roles==null||roles.length==0){
			return true;
		}
		for(String role:roles){
			if(!subject.hasRole(role)){
				return true;
			}
		}
		return false;
	}
}
package com.yangzai.shiro.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yangzai.shiro.vo.User;

@RestController
public class LoginController {
	/**
	 * 页面
	 * @return
	 */
    /**
     * 接口
     * @param user
     * @param vcode
     * @param rememberMe
     * @return
     */
    @RequestMapping(value="/subLogin",method=RequestMethod.GET)
    public String subLogin(User user){
    	UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword(),false);
    	Subject subject =SecurityUtils.getSubject();
        try{
        	token.setRememberMe(user.getRememberMe());
        	subject.login(token);
        }catch(AuthenticationException e){
        	return e.getMessage();
        }
    	if(subject.hasRole("admin")){
    		return "has admin login in ";
    	}
        return "donot have admin login in ";
    }
    @RequestMapping(value="/other",method=RequestMethod.GET)
    public String other(){
    	return "other";
    }
    @RequiresRoles(value="admin")
    @RequiresPermissions(value="权限删除")
    @RequestMapping(value="/need",method=RequestMethod.GET)
    public String need(){
    	Subject subject = SecurityUtils.getSubject();
    	if(subject.hasRole("admin1")){
    		return "yes admin";
    	}else{
    		return "no admin";
    	}
    }
    @RequiresRoles(value="admin")
    @RequiresPermissions(value="权限删除1212")
    @RequestMapping(value="/need1",method=RequestMethod.GET)
    public String need1(){
    	Subject subject = SecurityUtils.getSubject();
    	if(subject.hasRole("admin1")){
    		return "yes admin1";
    	}else{
    		return "no admin1";
    	}
    }

}
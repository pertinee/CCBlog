package com.lcz.blog.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * Shiro工具类
 * 
 * @author luchunzhou
 */
public class ShiroUtils {

	public static Session getSession() {
		return SecurityUtils.getSubject().getSession();
	}

	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}

	public static String getUser() {
		String username = (String) SecurityUtils.getSubject().getPrincipal();
		if(username != "" && username != null){
			return username;
		}
		return "";
	}

}

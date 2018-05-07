package com.lcz.blog.util.aop;

import com.alibaba.fastjson.JSON;
import com.lcz.blog.bean.LogBean;
import com.lcz.blog.service.LogService;
import com.lcz.blog.util.HttpContextUtils;
import com.lcz.blog.util.IPUtils;
import com.lcz.blog.util.ShiroUtils;
import com.lcz.blog.util.annotation.SysLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;


/**
 * 系统日志，切面处理类
 * @author luchunzhou
 */
@Aspect
@Component
public class SysLogAspect {
	@Autowired
	private LogService sysLogService;

	@Pointcut("@annotation(com.lcz.blog.util.annotation.SysLog)")
	public void logPointCut() {

	}

	/***** 这里要使用after注解，不然会娶不到shiro中的principle数据 *****/
	@After("logPointCut()")
	public void saveSysLog(JoinPoint joinPoint) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();

		LogBean log = new LogBean();
		SysLog syslog = method.getAnnotation(SysLog.class);
		if(syslog != null){
			//注解上的描述
			log.setOperation(syslog.value());
		}

		//请求的方法名
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		log.setMethod(className + "." + methodName + "()");

		//请求的参数
		Object[] args = joinPoint.getArgs();
		String params = JSON.toJSONString(args[0]);
		log.setParams(params);

		//获取request
		HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
		//设置IP地址
		log.setIp(IPUtils.getIpAddr(request));

		//用户名
		log.setUsername(ShiroUtils.getUser());

		log.setCreateDate(new Date());
		//保存系统日志
		sysLogService.insertLog(log);
	}

}

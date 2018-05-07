package com.lcz.blog.util.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理器
 * @author luchunzhou
 *
 */
public class CCExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception ex) {

        //输出异常
        ex.printStackTrace();

        //统一异常处理代码
        //针对系统自定义的CustomException异常，就可以直接从异常类中获取异常信息，将异常处理在错误页面展示
        //异常信息
        String message = null;
        CCException customException = null;
        //如果ex是系统自定义的异常，直接取出异常信息
        if(ex instanceof CCException){
            customException = (CCException)ex;
        }else{
            //针对非CustomException异常，对这类重新构造成一个CustomException，异常信息为“未知错误”
            customException = new CCException("未知错误，请与系统管理员联系！");
        }

        //错误信息
        message = customException.getMessage();

        ModelAndView modelAndView = new ModelAndView();
        //将错误信息传到页面
        modelAndView.addObject("message",message);
        //指向错误页面
        modelAndView.setViewName("error");
        return modelAndView;
    }

}
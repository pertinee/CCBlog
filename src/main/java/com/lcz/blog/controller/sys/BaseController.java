package com.lcz.blog.controller.sys;

import com.lcz.blog.bean.UserBean;
import com.lcz.blog.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by LuChunzhou on 2017/12/13.
 */
public abstract class BaseController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    protected UserBean checkCurrentUser(){
        Subject subject = SecurityUtils.getSubject();
        if (subject.isRemembered() || subject.isAuthenticated()) {
            Session session = subject.getSession();
            UserBean currentUser = (UserBean) session.getAttribute("currentUser");
            if(null == currentUser){
                UserBean loginUser = userService.queryUserByName((String) subject.getPrincipal());
                //密码清空，防止窃取
                loginUser.setPassword("");
                session.setAttribute("currentUser", loginUser);
                return loginUser;
            }else{
                return currentUser;
            }
        }
        return null;
    }


    protected static UserBean getUser() {
        return (UserBean)SecurityUtils.getSubject().getPrincipal();
    }

    protected static Integer getUserId() {
        return getUser().getId();
    }
}

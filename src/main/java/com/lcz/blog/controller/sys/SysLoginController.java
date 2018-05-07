package com.lcz.blog.controller.sys;

import com.lcz.blog.service.UserService;
import com.lcz.blog.service.WebAppService;
import com.lcz.blog.util.AttributeConstant;
import com.lcz.blog.util.VerificationCode;
import com.lcz.blog.util.annotation.SysLog;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by luchunzhou on 16/2/29.
 * 登录操作
 */
@Controller
public class SysLoginController {

    @Autowired
    private WebAppService webAppService;

    @Autowired
    private UserService userService;

    /**
     * 显示登录页面
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLogin(HttpServletRequest request, ModelMap model){
        model.addAttribute(AttributeConstant.WEB_APP_DTO, webAppService.queryWebApp(new HashMap<String, Object>()).get(0));
        return "front/login/login";
    }

    /**
     * 登陆
     * @param username
     * @param password
     * @param remember
     * @param code
     * @param model
     * @param session
     * @return
     */
    @SysLog("用户登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String username, String password, @RequestParam(defaultValue = "no") String remember, String code, ModelMap model, HttpSession session) {
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isRemembered() || currentUser.isAuthenticated()) {
            return "redirect:/sys";
        }else{
            if(!code.toUpperCase().equals((String) session.getAttribute("code"))) {
                model.addAttribute("error", "验证码错误");
                return "front/login/login";
            }
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            if("yes".equals(remember)){
                token.setRememberMe(true);
            }
            try {
                currentUser.login(token);
            }catch (UnknownAccountException e) {
                model.addAttribute("error", e.getMessage());
                return "front/login/login";
            }catch (IncorrectCredentialsException e) {
                model.addAttribute("error", e.getMessage());
                return "front/login/login";
            }catch (LockedAccountException e) {
                model.addAttribute("error", e.getMessage());
                return "front/login/login";
            }catch (AuthenticationException e) {
                model.addAttribute("error", "账户验证失败");
                return "front/login/login";
            }
            return "redirect:/sys";
        }
    }

    /**
     * 退出
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "redirect:/";
    }

    /**
     * 验证码
     * @param response
     * @param session
     */
    @RequestMapping(value = "/login/codeimg", method = RequestMethod.GET)
    public void showCode(HttpServletResponse response, HttpSession session) {
        VerificationCode verificationCode = new VerificationCode();
        String code = verificationCode.randomString(5);
        System.out.println("验证码："+code);
        session.setAttribute("code", code);
        verificationCode.createImg(100, 33, code, 80);
        try {
            verificationCode.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
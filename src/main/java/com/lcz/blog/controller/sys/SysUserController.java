package com.lcz.blog.controller.sys;

import com.lcz.blog.bean.UserBean;
import com.lcz.blog.util.AttributeConstant;
import com.lcz.blog.service.UserService;
import com.lcz.blog.util.MD5Utils;
import com.lcz.blog.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

/**
 * Created by luchunzhou on 16/3/1.
 * 管理用户 编辑页面
 */
@Controller
@RequestMapping("/sys/user")
public class SysUserController extends BaseController{
    @Autowired
    private UserService userService;

    /**
     * 显示用户列表
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showUsers(ModelMap model) {
        model.addAttribute(AttributeConstant.USER, checkCurrentUser());
        model.addAttribute(AttributeConstant.MAIN_PAGE, "sys/user/editor.vm");
        model.addAttribute(AttributeConstant.USERS, userService.queryUserNoPwd());
        return "sys/index";
    }

    /**
     * 创建用户
     * @param user
     * @param model
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createAction(UserBean user, ModelMap model) {
        model.addAttribute(AttributeConstant.USER, checkCurrentUser());
        model.addAttribute(AttributeConstant.MAIN_PAGE, "sys/user/editor.vm");
        if (StringUtil.isNotEmpty(user.getUsername())
                && StringUtil.isNotEmpty(user.getPassword())
                && StringUtil.isNotEmpty(user.getNickname())
                && StringUtil.isNotEmpty(user.getEmail())
                && StringUtil.isNotEmpty(user.getWebsite())
                && StringUtil.isNotEmpty(user.getImage())
                && null !=user.getType()) {
            UserBean tmpUser = userService.queryUserByName(user.getUsername());
            if (null == tmpUser) {
                user.setPassword(MD5Utils.generatorMD5(user.getPassword()));
                user.setIsLocked(0);
                user.setCreateDate(new Date());
                userService.saveUser(user);
                model.addAttribute(AttributeConstant.RETURN_INFO, "用户添加成功!");
            } else {
                model.addAttribute(AttributeConstant.ERROR, "用户已存在!");
            }
        } else {
            model.addAttribute(AttributeConstant.ERROR, "请完善用户信息!");
        }
        model.addAttribute(AttributeConstant.USERS, userService.queryUserNoPwd());
        return "sys/index";
    }

    /**
     * 根据ID显示更新页面
     * @param userId
     * @param model
     * @return
     */
    @RequestMapping(value = "/update/{userId:[0-9]+}", method = RequestMethod.GET)
    public String showUpdate(@PathVariable("userId") Integer userId, ModelMap model) {
        model.addAttribute(AttributeConstant.USER, checkCurrentUser());
        model.addAttribute(AttributeConstant.MAIN_PAGE, "sys/user/editor.vm");
        UserBean user = userService.queryUser(userId);
        if (StringUtil.isNotEmpty(user.getUsername())) {
            model.addAttribute("editoruser", user);
        } else {
            model.addAttribute(AttributeConstant.ERROR, "用户修改失败!");
        }
        model.addAttribute(AttributeConstant.USERS, userService.queryUserNoPwd());
        return "sys/index";
    }

    /**
     * 更新用户操作（不允许更新用户名）
     * @param user
     * @param model
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateAction(UserBean user, ModelMap model) {
        UserBean currentUser = checkCurrentUser();
        model.addAttribute(AttributeConstant.USER, currentUser);
        model.addAttribute(AttributeConstant.MAIN_PAGE, "sys/user/editor.vm");
        if (StringUtil.isNotEmpty(user.getPassword())
                && StringUtil.isNotEmpty(user.getNickname())
                && StringUtil.isNotEmpty(user.getEmail())
                && StringUtil.isNotEmpty(user.getWebsite())
                && StringUtil.isNotEmpty(user.getImage())
                && null !=user.getType()) {
            //如果管理员没有编辑选择用户的密码,就不在对密码进行MD5加密
            if (!user.getPassword().equals(userService.queryUser(user.getId()).getPassword())) {
                user.setPassword(MD5Utils.generatorMD5(user.getPassword()));
            }
            userService.updateUser(user);
            model.addAttribute(AttributeConstant.RETURN_INFO, "修改用户成功!");
            //判断更新用户 是否为 当前登陆用户 如果是 需要更新当前登陆用户的 显示信息.
            if (user.getId().equals(currentUser.getId())) {
                UserBean loginUser = userService.login(user);
                Subject subject = SecurityUtils.getSubject();
                Session session = subject.getSession();
                session.setAttribute(AttributeConstant.CURRENT_USER, loginUser);
                model.addAttribute(AttributeConstant.USER, loginUser);
            }
        } else {
            model.addAttribute(AttributeConstant.ERROR, "请完善用户信息!");
        }
        model.addAttribute(AttributeConstant.USERS, userService.queryUserNoPwd());
        return "sys/index";
    }

    /**
     * 通过ID 禁止/解禁用户
     * @param model
     * @param userId
     * @return
     */
    @RequestMapping("/onOffLock/{userId:[0-9]+}")
    public String onOffLock(ModelMap model, @PathVariable("userId") Integer userId) {
        model.addAttribute(AttributeConstant.USER, checkCurrentUser());
        model.addAttribute(AttributeConstant.MAIN_PAGE, "sys/user/editor.vm");
        UserBean user = userService.queryUser(userId);
        if(user.getIsLocked() == 1){
            user.setIsLocked(0);
        }else if(user.getIsLocked() == 0){
            user.setIsLocked(1);
        }else{
            //默认不锁定
            user.setIsLocked(0);
        }
        userService.onOffLockUser(user);
        model.addAttribute(AttributeConstant.USERS, userService.queryUserNoPwd());
        return "sys/index";
    }

    /**
     * 根据ID删除用户
     * @param model
     * @param userId
     * @return
     */
    @RequestMapping(value = "/delete/{userId:[0-9]+}")
    public String deleteAction(ModelMap model, @PathVariable("userId") Integer userId) {
        model.addAttribute(AttributeConstant.USER, checkCurrentUser());
        model.addAttribute(AttributeConstant.MAIN_PAGE, "sys/user/editor.vm");
        UserBean user = userService.queryUser(userId);
        if (StringUtil.isNotEmpty(user.getUsername())) {
            userService.deleteUser(userId);
            model.addAttribute(AttributeConstant.RETURN_INFO, "删除用户成功!");
        } else {
            model.addAttribute(AttributeConstant.ERROR, "找不到该用户");
        }
        model.addAttribute(AttributeConstant.USERS, userService.queryUserNoPwd());
        return "sys/index";
    }
}

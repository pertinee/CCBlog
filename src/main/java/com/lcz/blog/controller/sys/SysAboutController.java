package com.lcz.blog.controller.sys;

import com.lcz.blog.util.AttributeConstant;
import com.lcz.blog.bean.AboutBean;
import com.lcz.blog.service.AboutService;
import com.lcz.blog.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;


/**
 * Created by luchunzhou on 16/3/1.
 * 管理员 About页面
 */
@Controller
@RequestMapping("/sys/about")
public class SysAboutController extends BaseController{
    @Autowired
    private AboutService aboutService;

    /**
     * 显示About页面
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showAbout(ModelMap model){
        String path;
        //about表中 只有一条 信息 如果>0 则说明存在 关于的信息,那么跳转
        if(aboutService.queryAboutCount()>0){
            path="redirect:/sys/about/update";
        }else{
            model.addAttribute(AttributeConstant.USER, checkCurrentUser());
            model.addAttribute(AttributeConstant.MAIN_PAGE,"sys/about/editor.vm");
            path = "sys/index";
        }
        return path;
    }

    /**
     * 显示更新About页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.GET)
    public String showUpdate(ModelMap model){
        //因为数据库保持只有1个about记录超过了会出问题.
        if(aboutService.queryAboutCount()==1) {
            for (AboutBean about : aboutService.queryAbout(new HashMap<String, Object>())) {
                model.addAttribute(AttributeConstant.ABOUT,about);
            }
        }else{
            model.addAttribute(AttributeConstant.ERROR,"错误!关于页面太多!请到数据库中删除!");
        }
        model.addAttribute(AttributeConstant.MAIN_PAGE,"sys/about/editor.vm");
        model.addAttribute(AttributeConstant.USER, checkCurrentUser());
        return "sys/index";
    }

    /**
     * 更新About操作
     * @param model
     * @param about
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String updateAction(ModelMap model,AboutBean about){
        model.addAttribute(AttributeConstant.USER, checkCurrentUser());
        model.addAttribute(AttributeConstant.MAIN_PAGE,"sys/about/editor.vm");
        if (StringUtil.isNotEmpty(about.getContent())) {
            //因为数据库保持只有1个about记录所以可以这样获取
            about.setId(aboutService.queryAbout(new HashMap<String, Object>()).get(0).getId());
            aboutService.updateAbout(about);
            model.addAttribute(AttributeConstant.RETURN_INFO,"修改成功!");
            model.addAttribute(AttributeConstant.ABOUT,about);
        }else {
            model.addAttribute(AttributeConstant.ERROR,"修改失败,不能为空!");
        }
        return "sys/index";
    }

    /**
     * 创建About操作
     * @param about
     * @return
     */
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public String createAction(AboutBean about){
        if(StringUtil.isNotEmpty(about.getContent())){
            aboutService.insertAbout(about);
        }
        return "redirect:/sys/about/update";
    }
}

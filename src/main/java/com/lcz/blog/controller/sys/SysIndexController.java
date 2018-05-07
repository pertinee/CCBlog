package com.lcz.blog.controller.sys;

import com.lcz.blog.bean.WebAppBean;
import com.lcz.blog.service.WebAppService;
import com.lcz.blog.util.AttributeConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;

/**
 * Created by luchunzhou on 16/2/29.
 * 显示Manage主页
 */
@Controller
@RequestMapping("/sys")
public class SysIndexController extends BaseController {

    @Autowired
    private WebAppService webAppService;

    /**
     * 显示Manage主页
     *
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String manageHome(ModelMap model) {
        model.addAttribute(AttributeConstant.USER, checkCurrentUser());

        WebAppBean webApp = webAppService.queryWebApp(new HashMap<String, Object>()).get(0);
        webApp.setArticleViews(webAppService.queryClicks());
        model.addAttribute(AttributeConstant.MAIN_PAGE, "sys/home/home.vm");
        model.addAttribute(AttributeConstant.WEB_APP_DTO, webApp);
        return "sys/index";
    }
}

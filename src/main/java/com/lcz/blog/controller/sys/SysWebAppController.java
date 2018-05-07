package com.lcz.blog.controller.sys;

import com.lcz.blog.util.AttributeConstant;
import com.lcz.blog.bean.WebAppBean;
import com.lcz.blog.service.WebAppService;
import com.lcz.blog.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;

/**
 * Created by luchunzhou on 16/3/16.
 */
@Controller
@RequestMapping("/sys/web")
public class SysWebAppController extends BaseController{
    @Autowired
    private WebAppService webAppService;

    /**
     * 显示
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showWebAppPage(ModelMap model) {
        model.addAttribute(AttributeConstant.USER, checkCurrentUser());
        model.addAttribute(AttributeConstant.MAIN_PAGE, "sys/home/home.vm");
        WebAppBean webApp = webAppService.queryWebApp(new HashMap<String, Object>()).get(0);
        webApp.setArticleViews(webAppService.queryClicks());
        model.addAttribute(AttributeConstant.WEB_APP_DTO, webApp);
        return "sys/index";
    }

    /**
     * 创建、修改
     * @param webApp
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public String editWebApp(WebAppBean webApp, ModelMap model) {
        model.addAttribute(AttributeConstant.USER, checkCurrentUser());
        webApp.setId(webAppService.queryWebApp(new HashMap<String, Object>()).get(0).getId());
        if (webAppService.webAppNotNull()) {
            WebAppBean tmpApp = webAppService.queryWebApp(new HashMap<String, Object>()).get(0);
            //表中只有1个webApp配置
            if(StringUtil.isEmpty(webApp.getName())){
                webApp.setName(tmpApp.getName());
            }

            if(StringUtil.isEmpty(webApp.getTitle())){
                webApp.setTitle(tmpApp.getTitle());
            }
            webAppService.updateWebApp(webApp);
        }else{
            webAppService.insertWebApp(webApp);
        }
        return "redirect:/sys/web";
    }
}

package com.lcz.blog.controller.front;

import com.alibaba.fastjson.JSON;
import com.lcz.blog.util.AttributeConstant;
import com.lcz.blog.service.ArticleService;
import com.lcz.blog.service.WebAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;

/**
 * Created by luchunzhou on 16/2/28.
 * 访客留言页面 调用多说的API接口
 */
@Controller
public class FrontLeaveMsgController {
    @Autowired
    private WebAppService webAppService;
    @Autowired
    private ArticleService articleService;

    /**
     * 显示留言页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/leave", method = RequestMethod.GET)
    public String leavePage(ModelMap model) {
        model.addAttribute(AttributeConstant.WEB_APP_DTO,webAppService.queryWebApp(new HashMap<String, Object>()).get(0));
        model.addAttribute(AttributeConstant.MAIN_PAGE,"front/leave/detail.vm");
        // 搜索框内容查询(list)
        List<String> searchList = articleService.queryTitle();
        String jsonStr = JSON.toJSONString(searchList);
        model.addAttribute(AttributeConstant.SEARCH_LIST, jsonStr);
        return "index";
    }
}

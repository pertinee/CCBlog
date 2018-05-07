package com.lcz.blog.controller.front;

import com.alibaba.fastjson.JSON;
import com.lcz.blog.util.AttributeConstant;
import com.lcz.blog.bean.dto.ArticleLiteDto;
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
 * 访客 归档页面
 */
@Controller
public class FrontArchiveController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private WebAppService webAppService;

    /**
     * 显示归档页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/archive", method = RequestMethod.GET)
    public String archive(ModelMap model) {
        List<ArticleLiteDto> articleLiteDtos = articleService.queryArchive();
        model.addAttribute(AttributeConstant.WEB_APP_DTO,webAppService.queryWebApp(new HashMap<String, Object>()).get(0));
        model.addAttribute(AttributeConstant.ARTICLES, articleLiteDtos);
        model.addAttribute(AttributeConstant.MAIN_PAGE, "front/archive/detail.vm");
        // 搜索框内容查询(list)
        List<String> searchList = articleService.queryTitle();
        String jsonStr = JSON.toJSONString(searchList);
        model.addAttribute(AttributeConstant.SEARCH_LIST, jsonStr);
        return "index";
    }
}

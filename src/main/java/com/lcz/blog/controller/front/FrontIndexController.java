package com.lcz.blog.controller.front;

import com.alibaba.fastjson.JSON;
import com.lcz.blog.util.AttributeConstant;
import com.lcz.blog.bean.ArticleBean;
import com.lcz.blog.bean.WebAppBean;
import com.lcz.blog.service.ArticleService;
import com.lcz.blog.service.WebAppService;
import com.lcz.blog.util.Pager;
import com.lcz.blog.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by luchunzhou on 16/2/26.
 * 网站首页 负责显示文章列表 分页数为4
 */
@Controller
@RequestMapping("/")
public class FrontIndexController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private WebAppService webAppService;

    /**
     * 显示首页 分页文章列表
     * @param pageIndex
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String home(@RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex, ModelMap model, HttpServletRequest request) {
        String link = "index";
        if (webAppService.queryWebApp(new HashMap<String, Object>()).size() == 0) {
            link = "redirect:/init";
        } else {
            Integer frontPage = webAppService.queryWebApp(new HashMap<String, Object>()).get(0).getFrontPage();
            Map<String, Object> map = new HashMap<String, Object>();
            //前端显示正式博客，不显示草稿
            map.put("isDraft", 0);
            Integer totalCount = articleService.queryArticleCount(map);
            Pager pager = new Pager(pageIndex, frontPage, totalCount);
            model.addAttribute(AttributeConstant.MAIN_PAGE, "front/article/articlelist.vm");
            model.addAttribute(AttributeConstant.WEB_APP_DTO, webAppService.queryWebApp(new HashMap<String, Object>()).get(0));
            model.addAttribute(AttributeConstant.ARTICLES, articleService.queryArticlePageFront(pager));
            model.addAttribute(AttributeConstant.PAGER, pager);
            // 搜索框内容查询(list)
            List<String> searchList = articleService.queryTitle();
            String jsonStr = JSON.toJSONString(searchList);
            model.addAttribute(AttributeConstant.SEARCH_LIST, jsonStr);
        }
        return link;
    }

    /**
     * 初始化跳转
     * @param model
     * @return
     */
    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public String init(ModelMap model) {
        return "sys/init/init";
    }

    /**
     * 保存初始化信息
     * @param webApp
     * @return
     */
    @RequestMapping(value = "/init", method = RequestMethod.POST)
    public String initAction(WebAppBean webApp) {
        if (webAppService.queryWebApp(new HashMap<String, Object>()).size() == 0) {
            if (StringUtil.isNotEmpty(webApp.getName())
                    && StringUtil.isNotEmpty(webApp.getTitle())
                    && webApp.getSysPage() > 0
                    && webApp.getFrontPage() > 0) {
                webAppService.insertWebApp(webApp);
                return "redirect:/";
            }
        }
        return "redirect:/init";
    }
}
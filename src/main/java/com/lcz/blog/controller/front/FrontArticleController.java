package com.lcz.blog.controller.front;

import com.alibaba.fastjson.JSON;
import com.lcz.blog.bean.WebAppBean;
import com.lcz.blog.util.AttributeConstant;
import com.lcz.blog.bean.ArticleBean;
import com.lcz.blog.bean.dto.ArticleLiteDto;
import com.lcz.blog.service.ArticleService;
import com.lcz.blog.service.WebAppService;
import com.lcz.blog.util.Pager;
import com.lcz.blog.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by luchunzhou on 16/2/26.
 * 访客 文章页面
 */
@Controller
public class FrontArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private WebAppService webAppService;

    /**
     * 显示 详细文章
     * @param articleId
     * @param model
     * @return
     */
    @RequestMapping(value = "/article/{articleId:[0-9]+}", method = RequestMethod.GET)
    public String showArticle(@PathVariable("articleId")Integer articleId,ModelMap model) {
        ArticleBean article = articleService.queryArticle(articleId);
        model.addAttribute(AttributeConstant.MAIN_PAGE, "front/article/detail.vm");
        model.addAttribute(AttributeConstant.WEB_APP_DTO,webAppService.queryWebApp(new HashMap<String, Object>()).get(0));

        if(StringUtil.isNotEmpty(article.getTitle())) {
            //点击量+1
            articleService.updateClicks(article.getClicks() + 1, article.getId());
            //更新一下用于显示
            article.setClicks(article.getClicks() + 1);
            //获取上一篇文章
            ArticleLiteDto preArticle = articleService.queryPreArticle(article.getId());
            //获取下一篇文章
            ArticleLiteDto nextArticle = articleService.queryNextArticle(article.getId());
            model.addAttribute(AttributeConstant.ARTICLE, article);
            model.addAttribute("preArticle", preArticle);
            model.addAttribute("nextArticle", nextArticle);
        }else{
            model.addAttribute(AttributeConstant.ERROR,"没有此文章");
        }

        // 搜索框内容查询(list)
        List<String> searchList = articleService.queryTitle();
        String jsonStr = JSON.toJSONString(searchList);
        model.addAttribute(AttributeConstant.SEARCH_LIST, jsonStr);

        return "index";
    }

    /**
     * 搜索操作
     * @param content
     * @param model
     * @return
     */
    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public String search(String content, ModelMap model) {
        WebAppBean webAppBean = webAppService.queryWebApp(new HashMap<String, Object>()).get(0);
        model.addAttribute(AttributeConstant.WEB_APP_DTO, webAppBean);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", content);
        List<ArticleBean> articleList = articleService.queryArticle(map);
        if (articleList.size()>0) {
            Pager pager = new Pager(1, webAppBean.getSysPage(), articleList.size());
            model.addAttribute(AttributeConstant.PAGER, pager);
            model.addAttribute(AttributeConstant.ARTICLES, articleList);
            model.addAttribute(AttributeConstant.RETURN_INFO,"搜索内容已经全部显示");
        }else{
            model.addAttribute(AttributeConstant.RETURN_INFO,"没有找到该内容");
        }
        // 搜索框内容查询(list)
        List<String> searchList = articleService.queryTitle();
        String jsonStr = JSON.toJSONString(searchList);
        model.addAttribute(AttributeConstant.SEARCH_LIST, jsonStr);
        //搜索不分页
        model.addAttribute("search","search");
        model.addAttribute(AttributeConstant.MAIN_PAGE, "front/article/articlelist.vm");
        return "index";
    }

    /**
     * 直接访问搜索 跳转到 搜夜
     * @return
     */
    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public String showSearch() {
        return "redirect:/";
    }
}

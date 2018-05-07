package com.lcz.blog.controller.sys;

import com.lcz.blog.bean.UserBean;
import com.lcz.blog.bean.WebAppBean;
import com.lcz.blog.util.AttributeConstant;
import com.lcz.blog.bean.ArticleBean;
import com.lcz.blog.service.ArticleService;
import com.lcz.blog.service.CategoryService;
import com.lcz.blog.service.WebAppService;
import com.lcz.blog.util.Pager;
import com.lcz.blog.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by luchunzhou on 16/2/29.
 * 管理员 文章编辑页面
 */
@Controller
@RequestMapping("/sys/article")
public class SysArticleController extends BaseController{
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private WebAppService webAppService;

    /**
     * 显示创建页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showCreatePage(ModelMap model){
        model.addAttribute(AttributeConstant.USER, checkCurrentUser());
        model.addAttribute(AttributeConstant.MAIN_PAGE, "sys/article/editorArticle.vm");
        model.addAttribute(AttributeConstant.CATEGORIES, categoryService.queryCategory(new HashMap<String, Object>()));
        return "sys/index";
    }

    /**
     * 显示文章分页列表
     * @param model
     * @param currentPage
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showListArticle(ModelMap model, @RequestParam(defaultValue = "1") Integer currentPage){
        UserBean currentUser = checkCurrentUser();
        model.addAttribute(AttributeConstant.USER, currentUser);
        Map<String, Object> map = new HashMap<String, Object>();
        if(currentUser.getType() != 1){
            map.put("userId", currentUser.getId());
        }
        Integer totalCount = articleService.queryArticleCount(map);
        Integer pageSize = webAppService.queryWebApp(new HashMap<String, Object>()).get(0).getSysPage();
        Pager pager = new Pager(currentPage, pageSize, totalCount);
        if(currentUser.getType() != 1){
            pager.setUserId(currentUser.getId());
        }
        List<ArticleBean> articles = articleService.queryArticlePageSys(pager);
        model.addAttribute(AttributeConstant.ARTICLES, articles);
        model.addAttribute(AttributeConstant.PAGER, pager);
        model.addAttribute(AttributeConstant.MAIN_PAGE, "sys/article/listArticle.vm");
        return "sys/index";
    }

    /**
     * 搜索 文章
     * @param content
     * @param model
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(String content, ModelMap model){
        WebAppBean webAppBean = webAppService.queryWebApp(new HashMap<String, Object>()).get(0);
        Map<String, Object> map = new HashMap<>();
        map.put("title", content);
        UserBean currentUser = checkCurrentUser();
        if(currentUser.getType() != 1){
            map.put("userId", currentUser.getId());
        }
        model.addAttribute(AttributeConstant.USER, currentUser);

        List<ArticleBean> articleDtos = articleService.queryArticle(map);
        Pager pager = new Pager(1, webAppBean.getSysPage(), articleDtos.size());
        if (articleDtos.size() > 0) {
            model.addAttribute(AttributeConstant.ARTICLES, articleDtos);
        } else {
            model.addAttribute(AttributeConstant.ARTICLES, null);
            model.addAttribute(AttributeConstant.ERROR, "找不到文章!");
        }
        model.addAttribute(AttributeConstant.PAGER, pager);

        model.addAttribute(AttributeConstant.MAIN_PAGE, "sys/article/listArticle.vm");
        return "sys/index";
    }


    /**
     * 通过 ID 显示更新文章页面
     * @param model
     * @param articleId
     * @return
     */
    @RequestMapping(value = "/update/{articleId:[0-9]+}", method = RequestMethod.GET)
    public String upDateArticle(ModelMap model, @PathVariable("articleId") Integer articleId){
        UserBean currentUser = checkCurrentUser();
        model.addAttribute(AttributeConstant.USER, currentUser);
        ArticleBean articleDto = articleService.queryArticle(articleId);
        if (StringUtil.isNotEmpty(articleDto.getTitle())) {
            model.addAttribute(AttributeConstant.ARTICLE, articleDto);
            model.addAttribute(AttributeConstant.MAIN_PAGE, "sys/article/editorArticle.vm");
            model.addAttribute(AttributeConstant.CATEGORIES, categoryService.queryCategory(new HashMap<String, Object>()));
        } else {
            Map<String, Object> map = new HashMap<String, Object>();
            if(currentUser.getType() != 1){
                map.put("userId", currentUser.getId());
            }
            Integer articleCou = articleService.queryArticleCount(map);
            Pager pager = new Pager(1, 10, articleCou);
            List<ArticleBean> articles = articleService.queryArticlePageSys(pager);
            model.addAttribute(AttributeConstant.ERROR, "找不到该文章!");
            model.addAttribute(AttributeConstant.MAIN_PAGE, "sys/article/listArticle.vm");
            model.addAttribute(AttributeConstant.PAGER, pager);
            model.addAttribute(AttributeConstant.ARTICLES, articles);
        }
        return "sys/index";
    }

    /**
     * 创建文章
     * @param article
     * @param model
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createAction(ArticleBean article, ModelMap model){
        String path;
        article.setClicks(0);
        article.setCreateDate(new Date());
        if(null == article.getIsDraft()){
            article.setIsDraft(0);
        }
        if (StringUtil.isNotEmpty(article.getTitle()) && StringUtil.isNotEmpty(article.getRemark())) {
            articleService.insertArticle(article);
            path = "redirect:/sys/article";
        } else {
            model.addAttribute(AttributeConstant.ERROR, "有未填选项,请核对后重新发布文章!");
            model.addAttribute(AttributeConstant.MAIN_PAGE, "sys/article/editorArticle.vm");
            model.addAttribute(AttributeConstant.USER, checkCurrentUser());
            model.addAttribute(AttributeConstant.CATEGORIES, categoryService.queryCategory(new HashMap<String, Object>()));
            path = "sys/index";
        }
        return path;
    }


    /**
     * 通过ID更新文章 操作
     * @param article
     * @param articleId
     * @param model
     * @return
     */
    @RequestMapping(value = "/update/{articleId:[0-9]+}", method = RequestMethod.POST)
    public String upDateArticleAction(ArticleBean article, @PathVariable("articleId") Integer articleId, ModelMap model){
        String path;
        ArticleBean articleBean = articleService.queryArticle(articleId);
        if (StringUtil.isNotEmpty(article.getTitle()) && StringUtil.isNotEmpty(article.getRemark())) {
            article.setClicks(articleBean.getClicks());
            article.setCreateDate(articleBean.getCreateDate());
            articleService.updateArticle(article);
            path = "redirect:/sys/article";
        } else {
            model.addAttribute(AttributeConstant.ERROR, "有未填选项,请核对后重新发布文章!");
            model.addAttribute(AttributeConstant.MAIN_PAGE, "sys/article/editorArticle.vm");
            model.addAttribute(AttributeConstant.USER, checkCurrentUser());
            model.addAttribute(AttributeConstant.ARTICLE, articleBean);
            model.addAttribute(AttributeConstant.CATEGORIES, categoryService.queryCategory(new HashMap<String, Object>()));
            path = "sys/index";
        }
        return path;
    }

    /**
     * 通过ID 上下架文章
     * @param articleId
     * @param currentPage
     * @return
     */
    @RequestMapping("/onOffShow/{articleId:[0-9]+}")
    public String onOffShowArticle(@PathVariable("articleId") Integer articleId, @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage){
        String path = "redirect:/sys/article";
        ArticleBean article = articleService.queryArticle(articleId);
        if(article.getIsDraft() == 1){
            article.setIsDraft(0);
        }else if(article.getIsDraft() == 0){
            article.setIsDraft(1);
        }else{
            //默认不是草稿
            article.setIsDraft(0);
        }
        articleService.onOffShowArticle(article);
        if (currentPage != 1) {
            path = "redirect:/sys/article/?currentPage=" + currentPage;
        }
        return path;
    }

    /**
     * 通过ID 删除文章
     * @param articleId
     * @param currentPage
     * @return
     */
    @RequestMapping("/delete/{articleId:[0-9]+}")
    public String deleteArticle(@PathVariable("articleId") Integer articleId, @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage){
        String path = "redirect:/sys/article";
        articleService.deleteArticle(articleId);
        if (currentPage != 1) {
            path = "redirect:/sys/article/?currentPage=" + currentPage;
        }
        return path;
    }

}

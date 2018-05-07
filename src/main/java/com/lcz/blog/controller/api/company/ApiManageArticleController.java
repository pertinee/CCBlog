package com.lcz.blog.controller.api.company;

import com.lcz.blog.bean.ArticleBean;
import com.lcz.blog.service.ArticleService;
import com.lcz.blog.service.WebAppService;
import com.lcz.blog.util.Pager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 企业API接口文档
 * Created by luchunzhou on 16/2/29.
 * 管理员 文章编辑页面
 */
@Api(value = "文章对象类", description = "企业接口：文章对象基本操作")
@RestController
@RequestMapping("/api/company")
public class ApiManageArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private WebAppService webAppService;

    /**
     * 根据标题搜索文章
     *
     * @param content
     * @return
     */
    @RequestMapping(value = "/article/search", method = RequestMethod.POST)
    @ApiOperation(value = "根据标题搜索文章", httpMethod = "POST", response = ArticleBean.class, notes = "search")
    @ApiImplicitParam(name = "content", value = "标题", required = true, dataType = "String")
    public List<ArticleBean> search(String content) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title",content);
        List<ArticleBean> articleList = articleService.queryArticle(map);
        return articleList;
    }

    /**
     * 分页显示文章列表
     *
     * @param currentPage
     * @return
     */
    @RequestMapping(value = "/article", method = RequestMethod.GET)
    @ApiOperation(value = "显示文章分页列表", httpMethod = "GET", response = ArticleBean.class, notes = "显示文章分页列表")
    @ApiImplicitParam(name = "currentPage", value = "当前页", required = true, dataType = "Integer")
    public List<ArticleBean> showListArticle(@RequestParam(defaultValue = "1") Integer currentPage) {
        Integer totalCount = articleService.queryArticleCount(new HashMap<String, Object>());
        Integer sysPage = webAppService.queryWebApp(new HashMap<String, Object>()).get(0).getSysPage();
        Pager pager = new Pager(currentPage, sysPage, totalCount);
        List<ArticleBean> articles = articleService.queryArticlePageSys(pager);
        return articles;
    }

    /**
     * 通过文章id查询文章详情
     *
     * @param articleId
     * @return
     */
    @RequestMapping(value = "/article/{articleId:[0-9]+}", method = RequestMethod.GET)
    @ApiOperation(value = "通过文章id查询文章详情", httpMethod = "GET", response = ArticleBean.class, notes = "通过文章id查询某一文章的详情")
    @ApiImplicitParam(name = "articleId", value = "文章id", required = true, dataType = "Integer")
    public ArticleBean updateArticle(@PathVariable("articleId") Integer articleId) {
        ArticleBean article = articleService.queryArticle(articleId);
        return article;
    }
}

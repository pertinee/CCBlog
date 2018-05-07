package com.lcz.blog.controller.api.normal;

import com.lcz.blog.bean.ArticleBean;
import com.lcz.blog.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 普通API接口文档
 * Created by luchunzhou on 16/2/26.
 * 访客 文章页面
 */
@Api(value = "文章对象类", description = "普通接口：文章对象基本操作")
@RestController
@RequestMapping("/api/normal")
public class ApiArticleController {
    @Autowired
    private ArticleService articleService;


    /**
     * 通过id显示文章详情
     *
     * @param articleId
     * @return
     */
    @RequestMapping("/article/{articleId:[0-9]+}")
    @ApiOperation(value = "通过id显示文章详情", httpMethod = "GET", response = ArticleBean.class, notes = "get")
    @ApiImplicitParam(name = "articleId", value = "文章id", required = true, dataType = "Integer")
    public ArticleBean showArticle(@PathVariable("articleId") Integer articleId) {
        ArticleBean articleDto = articleService.queryArticle(articleId);
        return articleDto;
    }

    /**
     * 通过标题搜索文章
     *
     * @param content
     * @return
     */
    @RequestMapping(value = "/article/search", method = RequestMethod.POST)
    @ApiOperation(value = "通过标题搜索文章", httpMethod = "POST", response = ArticleBean.class, notes = "post")
    @ApiImplicitParam(name = "content", value = "标题", required = true, dataType = "String")
    public List<ArticleBean> search(String content) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title",content);
        List<ArticleBean> articleList = articleService.queryArticle(map);
        return articleList;
    }
}

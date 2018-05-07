package com.lcz.blog.service;

import com.lcz.blog.bean.ArticleBean;
import com.lcz.blog.bean.dto.ArticleLiteDto;
import com.lcz.blog.util.Pager;

import java.util.List;
import java.util.Map;

/**
 * Created by luchunzhou on 15/12/9.
 */
public interface ArticleService {

    /**
     * 根据id获取文章 title,content,pubdate,category,clicks,content
     * @param id
     * @return
     */
    ArticleBean queryArticle(Integer id);

    /**
     * 按照标题搜索文章
     * @param map
     * @return
     */
    List<ArticleBean> queryArticle(Map<String, Object> map);

    /**
     * 文章分页列表（前台）
     * @param pager
     * @return
     */
    List<ArticleBean> queryArticlePageFront(Pager pager);

    /**
     * 文章分页列表（后台）
     * @param pager
     * @return
     */
    List<ArticleBean> queryArticlePageSys(Pager pager);

    /**
     * 获取上一篇文章
     * @param id
     * @return
     */
    ArticleLiteDto queryPreArticle(Integer id);

    /**
     * 获取下一篇文章
     * @param id
     * @return
     */
    ArticleLiteDto queryNextArticle(Integer id);

    /**
     * 根据类别获取文章列表
     * @param categoryId
     * @return
     */
    List<ArticleLiteDto> queryByCategory(int categoryId);

    /**
     * 归档文章列表 article(title,pubdate)
     * @return
     */
    List<ArticleLiteDto> queryArchive();

    /**
     * 更新文章
     * @param article
     * @return
     */
    int updateArticle(ArticleBean article);

    /**
     * 添加文章
     * @param article
     */
    void insertArticle(ArticleBean article);

    /**
     * 删除文章
     * @param id
     * @return
     */
    int deleteArticle(Integer id);

    /**
     * 获取数值
     * @param map
     * @return
     */
    int queryArticleCount(Map<String, Object> map);

    /**
     * 更新点击数或者浏览量
     * @param clicks
     * @param id
     */
    void updateClicks(Integer clicks, Integer id);

    /**
     * 上下架文章
     * @param article
     */
    void onOffShowArticle(ArticleBean article);

    /**
     * 查询标题list，供查询框的内容使用
     * @return
     */
    List<String> queryTitle();
}

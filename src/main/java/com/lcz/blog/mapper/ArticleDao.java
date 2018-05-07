package com.lcz.blog.mapper;

import com.lcz.blog.bean.ArticleBean;
import com.lcz.blog.bean.dto.ArticleLiteDto;
import com.lcz.blog.util.Pager;

import java.util.List;

/**
 * Created by luchunzhou on 16/3/11.
 */
public interface ArticleDao extends BaseDao<ArticleBean> {

    /**
     * 分页(前台)
     * @param pager
     * @return
     */
    List<ArticleBean> pageFront(Pager pager);

    /**
     * 分页(后台)
     * @param pager
     * @return
     */
    List<ArticleBean> pageSys(Pager pager);

    /**
     * 获取上一篇文章
     * @param id
     * @return
     */
    ArticleLiteDto queryPre(Integer id);

    /**
     * 获取下一篇文章
     * @param id
     * @return
     */
    ArticleLiteDto queryNext(Integer id);

    /**
     * 获取某分类下文章
     * @param categoryId
     * @return
     */
    List<ArticleLiteDto> queryByCategory(int categoryId);

    /**
     * 归档
     * @return
     */
    List<ArticleLiteDto> queryArchive();

    /**
     * 更新点击
     * @param clicks
     * @param id
     */
    void updateArticleClicks(Integer clicks, Integer id);

    /**
     * 上下架文章
     * @param article
     */
    void onOffShowArticle(ArticleBean article);

    /**
     * 前台文章显示
     * @return
     */
    List<String> queryTitle();
}

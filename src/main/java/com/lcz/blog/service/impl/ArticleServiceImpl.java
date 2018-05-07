package com.lcz.blog.service.impl;

import com.lcz.blog.mapper.ArticleDao;
import com.lcz.blog.bean.ArticleBean;
import com.lcz.blog.bean.dto.ArticleLiteDto;
import com.lcz.blog.service.ArticleService;
import com.lcz.blog.util.Pager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by luchunzhou on 15/12/13.
 */
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleDao articleDao;

    @Override
    public ArticleBean queryArticle(Integer id){
        ArticleBean article = articleDao.queryObject(id);
        return article;
    }

    @Override
    public List<ArticleBean> queryArticle(Map<String, Object> map){
        return articleDao.queryList(map);
    }

    @Override
    public List<ArticleBean> queryArticlePageFront(Pager pager){
        return articleDao.pageFront(pager);
    }

    @Override
    public List<ArticleBean> queryArticlePageSys(Pager pager){
        return articleDao.pageSys(pager);
    }

    @Override
    public ArticleLiteDto queryPreArticle(Integer id){
        return articleDao.queryPre(id);
    }

    @Override
    public ArticleLiteDto queryNextArticle(Integer id){
        return articleDao.queryNext(id);
    }

    @Override
    public List<ArticleLiteDto> queryByCategory(int categoryId){
        return articleDao.queryByCategory(categoryId);
    }

    @Override
    public List<ArticleLiteDto> queryArchive(){
        return articleDao.queryArchive();
    }


    @Override
    public int updateArticle(ArticleBean article){
        return articleDao.update(article);
    }

    @Override
    public void insertArticle(ArticleBean article){
        articleDao.save(article);
    }

    @Override
    public int deleteArticle(Integer id){
        return articleDao.delete(id);
    }

    @Override
    public int queryArticleCount(Map<String, Object> map){
        return articleDao.queryTotal(map);
    }

    @Override
    public void updateClicks(Integer clicks, Integer id){
        articleDao.updateArticleClicks(clicks, id);
    }

    @Override
    public void onOffShowArticle(ArticleBean article){
        articleDao.onOffShowArticle(article);
    }

    @Override
    public List<String> queryTitle(){
        return articleDao.queryTitle();
    }
}

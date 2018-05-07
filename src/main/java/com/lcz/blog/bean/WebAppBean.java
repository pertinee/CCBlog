package com.lcz.blog.bean;

import java.io.Serializable;

/**
 * Created by luchunzhou on 16/3/16.
 */
public class WebAppBean implements Serializable {

    /**
     * id
     */
    private Integer id;
    /**
     * 网站名称
     */
    private String name;
    /**
     * 网站标题
     */
    private String title;
    /**
     * 首页文章显示数量
     */
    private Integer frontPage;
    /**
     * 管理员文章显示数量
     */
    private Integer sysPage;

    /**
     * 网站文章浏览总量
     */
    private Integer articleViews;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getFrontPage() {
        return frontPage;
    }

    public void setFrontPage(Integer frontPage) {
        this.frontPage = frontPage;
    }

    public Integer getSysPage() {
        return sysPage;
    }

    public void setSysPage(Integer sysPage) {
        this.sysPage = sysPage;
    }

    public Integer getArticleViews() {
        return articleViews;
    }

    public void setArticleViews(Integer articleViews) {
        this.articleViews = articleViews;
    }
}

package com.lcz.blog.bean.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by luchunzhou on 16/2/23.
 * 在首页中显示文章基本信息
 */
public class ArticleLiteDto implements Serializable {
    private Integer id;
    private String title;
    private Date createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

}

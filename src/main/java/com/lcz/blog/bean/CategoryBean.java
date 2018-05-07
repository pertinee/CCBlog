package com.lcz.blog.bean;

import java.io.Serializable;

/**
 * Created by luchunzhou on 16/2/23.
 * 对应   t_category表
 * 分类
 * id   分类id
 * name 分类名称
 * count 分类中的文章数量
 */
public class CategoryBean implements Serializable {
    private Integer id;
    private String name;
    private Integer count;

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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
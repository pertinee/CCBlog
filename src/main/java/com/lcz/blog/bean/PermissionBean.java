package com.lcz.blog.bean;

import java.io.Serializable;

/**
 * Created by LuChunzhou on 2017/12/12.
 */
public class PermissionBean implements Serializable {

    private Integer id;

    private String name;

    private Integer userId;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}

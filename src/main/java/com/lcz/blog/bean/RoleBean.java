package com.lcz.blog.bean;

import java.io.Serializable;

/**
 * Created by LuChunzhou on 2017/12/12.
 */
public class RoleBean implements Serializable {

    private Integer id;

    private String name;

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

}

package com.lcz.blog.bean;

import java.io.Serializable;

/**
 * Created by luchunzhou on 16/2/23.
 * 对应 t_apis 表
 * id   id
 * content 内容
 */
public class ApisBean implements Serializable {
    private Integer id;
    private String name;
    private String content;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
package com.lcz.blog.bean;

import java.io.Serializable;

/**
 * Created by luchunzhou on 16/2/23.
 */
public class AboutBean implements Serializable{

    private Integer id;
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
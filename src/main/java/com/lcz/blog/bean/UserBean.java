package com.lcz.blog.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by luchunzhou on 16/2/23.
 * 对应 t_user 表
 * id           用户id
 * type         用户类型,0代表普通用户，1代表管理员
 * username     用户名
 * password     用户密码
 * nickname     用户昵称
 * email        用户邮箱
 * website      用户网站
 * image        用户头像地址
 * isLocked     用户是否锁定
 * createDate   创建时间
 */
public class UserBean implements Serializable {
    private Integer id;
    private Integer type;
    private String username;
    private String nickname;
    private String password;
    private String email;
    private String website;
    private String image;
    private Integer isLocked;
    private Date createDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(Integer isLocked) {
        this.isLocked = isLocked;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
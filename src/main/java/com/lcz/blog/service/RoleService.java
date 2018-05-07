package com.lcz.blog.service;

import com.lcz.blog.bean.RoleBean;

import java.util.List;

/**
 * Created by luchunzhou on 15/12/9.
 */
public interface RoleService {
    /**
     * 获取用户角色
     * @param id
     * @return
     * @throws Exception
     */
    List<RoleBean> queryRoleByUserId(Integer id);
}

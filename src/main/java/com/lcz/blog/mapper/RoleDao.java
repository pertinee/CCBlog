package com.lcz.blog.mapper;

import com.lcz.blog.bean.RoleBean;

import java.util.List;

/**
 * Created by luchunzhou on 16/3/8.
 */
public interface RoleDao extends BaseDao<RoleBean> {

    /**
     * 根据用户id获取用户信息
     * @param id
     * @return
     */
    List<RoleBean> queryRoleByUserId(Integer id);
}

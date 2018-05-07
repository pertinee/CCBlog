package com.lcz.blog.mapper;

import com.lcz.blog.bean.PermissionBean;

import java.util.List;

/**
 * Created by luchunzhou on 16/3/8.
 */
public interface PermissionDao extends BaseDao<PermissionBean> {

    /**
     * 根据用户id获取权限
     * @param id
     * @return
     */
    List<PermissionBean> queryPermByUserId(Integer id);
}

package com.lcz.blog.service;

import com.lcz.blog.bean.PermissionBean;

import java.util.List;

/**
 * Created by luchunzhou on 15/12/9.
 */
public interface PermissionService {
    /**
     * 获取用户权限
     * @param id
     * @return
     * @throws Exception
     */
    List<PermissionBean> queryPermByUserId(Integer id);
}

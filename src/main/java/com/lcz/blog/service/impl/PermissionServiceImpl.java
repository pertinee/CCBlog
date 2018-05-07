package com.lcz.blog.service.impl;

import com.lcz.blog.mapper.PermissionDao;
import com.lcz.blog.bean.PermissionBean;
import com.lcz.blog.service.PermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by luchunzhou on 15/12/13.
 */
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {
    @Resource
    private PermissionDao permissionDao;


    @Override
    public List<PermissionBean> queryPermByUserId(Integer id){
        return permissionDao.queryPermByUserId(id);
    }
}

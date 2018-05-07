package com.lcz.blog.service.impl;

import com.lcz.blog.mapper.RoleDao;
import com.lcz.blog.bean.RoleBean;
import com.lcz.blog.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by luchunzhou on 15/12/13.
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleDao roleDao;


    @Override
    public List<RoleBean> queryRoleByUserId(Integer id){
        return roleDao.queryRoleByUserId(id);
    }
}

package com.lcz.blog.service.impl;

import com.lcz.blog.mapper.UserDao;
import com.lcz.blog.bean.UserBean;
import com.lcz.blog.service.UserService;
import com.lcz.blog.util.Pager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by luchunzhou on 15/12/13.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Override
    public UserBean queryUserByName(String userName){
        return userDao.queryUserByName(userName);
    }

    @Override
    public List<UserBean> queryUserPage(Pager pager){
        return userDao.pagination(pager);
    }

    @Override
    public UserBean login(UserBean user){
        return userDao.login(user);
    }

    @Override
    public void saveUser(UserBean user){
        userDao.save(user);
    }

    @Override
    public void updateUser(UserBean user){
        userDao.update(user);
    }

    @Override
    public UserBean queryUser(Integer id){
        return userDao.queryObject(id);
    }

    @Override
    public void deleteUser(Integer id){
        userDao.delete(id);
    }

    @Override
    public List<UserBean> queryUser(Map<String, Object> map){
        return userDao.queryList(map);
    }

    @Override
    public List<UserBean> queryUserNoPwd(){
        return userDao.queryUserNoPwd();
    }

    @Override
    public void onOffLockUser(UserBean user){
        userDao.onOffLockUser(user);
    }
}

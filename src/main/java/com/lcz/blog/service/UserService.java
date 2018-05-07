package com.lcz.blog.service;

import com.lcz.blog.bean.UserBean;
import com.lcz.blog.util.Pager;

import java.util.List;
import java.util.Map;

/**
 * Created by luchunzhou on 15/12/9.
 */
public interface UserService {
    /**
     * 登录
     * @param user
     * @return
     */
    UserBean login(UserBean user);

    /**
     * 保存用户
     * @param user
     */
    void saveUser(UserBean user);

    /**
     * 更新用户
     * @param user
     */
    void updateUser(UserBean user);

    /**
     * 删除用户
     * @param id
     */
    void deleteUser(Integer id);

    /**
     * 获取用户列表
     * @return
     */
    List<UserBean> queryUser(Map<String, Object> map);

    /**
     * 获取用户列表(无密码)
     * @return
     */
    List<UserBean> queryUserNoPwd();

    /**
     * 获取用户
     * @param id
     * @return
     */
    UserBean queryUser(Integer id);

    /**
     * 分页获取用户
     * @param pager
     * @return
     */
    List<UserBean> queryUserPage(Pager pager);

    /**
     * 根据用户名获取用户信息
     * @param userName
     * @return
     */
    UserBean queryUserByName(String userName);

    /**
     * 锁定、解锁用户
     * @param user
     */
    void onOffLockUser(UserBean user);
}

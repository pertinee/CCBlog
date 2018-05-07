package com.lcz.blog.mapper;

import com.lcz.blog.bean.UserBean;
import com.lcz.blog.util.Pager;

import java.util.List;

/**
 * Created by luchunzhou on 16/3/8.
 */
public interface UserDao extends BaseDao<UserBean> {
    /**
     * 用户登录
     * @param user
     * @return
     */
    UserBean login(UserBean user);

    /**
     * 获取用户列表(无密码)
     * @return
     */
    List<UserBean> queryUserNoPwd();

    /**
     * 分页获取用户
     * @param pager
     * @return
     */
    List<UserBean> pagination(Pager pager);

    /**
     * 根据用户名获取信息
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

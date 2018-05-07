package com.lcz.blog.service;

import com.lcz.blog.bean.AboutBean;

import java.util.List;
import java.util.Map;

/**
 * Created by luchunzhou on 16/1/7.
 */
public interface AboutService {
    /**
     * 根据id获取关于信息
     * @param id
     * @return
     */
    AboutBean queryAbout(Integer id);

    /**
     * 更新
     * @param about
     */
    void updateAbout(AboutBean about);

    /**
     * 保存
     * @param about
     */
    void insertAbout(AboutBean about);

    /**
     * 删除
     * @param id
     */
    void deleteAbout(Integer id);

    /**
     * 查询数量
     * @return
     */
    int queryAboutCount();

    /**
     * 查询list
     * @return
     */
    List<AboutBean> queryAbout(Map<String, Object> map);
}

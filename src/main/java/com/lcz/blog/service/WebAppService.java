package com.lcz.blog.service;

import com.lcz.blog.bean.WebAppBean;

import java.util.List;
import java.util.Map;

/**
 * Created by luchunzhou on 16/3/16.
 */
public interface WebAppService {
    /**
     * 保存
     * @param webApp
     */
    void insertWebApp(WebAppBean webApp);

    /**
     * 更新
     * @param webApp
     */
    void updateWebApp(WebAppBean webApp);

    /**
     * 获取bean
     * @param id
     * @return
     */
    WebAppBean queryWebApp(Integer id);

    /**
     * 获取数量
     * @return
     */
    Integer queryClicks();

    /**
     * 判断是否为空
     * @return
     */
    boolean webAppNotNull();

    /**
     * 获取列表
     * @return
     */
    List<WebAppBean> queryWebApp(Map<String, Object> map);
}

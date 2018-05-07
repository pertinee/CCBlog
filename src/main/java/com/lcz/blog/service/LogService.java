package com.lcz.blog.service;

import com.lcz.blog.bean.LogBean;
import com.lcz.blog.util.Pager;

import java.util.List;
import java.util.Map;

/**
 * Created by luchunzhou on 2018/1/20.
 */
public interface LogService {

    /**
     * 获取日志列表
     * @return
     */
    LogBean queryLog(String id);

    /**
     * 查询
     * @param map
     * @return
     */
    List<LogBean> queryLog(Map<String, Object> map);

    /**
     * 获取数值
     * @param map
     * @return
     */
    int queryLogCount(Map<String, Object> map);

    /**
     * 分页
     * @param pager
     * @return
     */
    List<LogBean> queryLogPage(Pager pager);

    /**
     * 保存
     * @param logBean
     */
    void insertLog(LogBean logBean);

    /**
     * 删除
     * @param id
     */
    void deleteLog(String id);
}

package com.lcz.blog.mapper;


import com.lcz.blog.bean.LogBean;
import com.lcz.blog.util.Pager;

import java.util.List;

/**
 * 系统日志
 * Created by luchunzhou on 18/1/20.
 */
public interface LogDao extends BaseDao<LogBean> {

    /**
     * 分页
     * @param pager
     * @return
     */
    List<LogBean> pagination(Pager pager);

}

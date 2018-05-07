package com.lcz.blog.mapper;

import com.lcz.blog.bean.WebAppBean;

/**
 * Created by luchunzhou on 16/3/16.
 */
public interface WebAppDao extends BaseDao<WebAppBean> {

    Integer queryClicks();
}

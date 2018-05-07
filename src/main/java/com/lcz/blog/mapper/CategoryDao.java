package com.lcz.blog.mapper;

import com.lcz.blog.bean.CategoryBean;
import com.lcz.blog.util.Pager;

import java.util.List;

/**
 * Created by luchunzhou on 16/3/8.
 */
public interface CategoryDao extends BaseDao<CategoryBean> {

    /**
     * 是否存在该categoryId >0存在
     * @param categoryId
     * @return
     */
    int exist(int categoryId);

    /**
     * 分页查询
     * @param pager
     * @return
     */
    List<CategoryBean> pagination(Pager pager);
}

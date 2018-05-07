package com.lcz.blog.service;

import com.lcz.blog.bean.CategoryBean;
import com.lcz.blog.util.Pager;

import java.util.List;
import java.util.Map;

/**
 * Created by luchunzhou on 15/12/9.
 */
public interface CategoryService {
    /**
     * 获取所有分类
     * @return
     */
    List<CategoryBean> queryCategory(Map<String, Object> map);

    /**
     * 更新分类
     * @param category
     */
    void updateCategory(CategoryBean category);

    /**
     * 保存或添加分类
     * @param category
     */
    void insertCategory(CategoryBean category);

    /**
     * 删除分类
     * @param categoryId
     */
    void deleteCategory(Integer categoryId);

    /**
     * 获取分类
     * @param categoryId
     * @return
     */
    CategoryBean queryCategory(int categoryId);

    /**
     * 分页查找
     * @param pager
     * @return
     */
    List<CategoryBean> queryCategoryPage(Pager pager);

    /**
     * 获取总数
     * @return
     */
    int getCount();
}

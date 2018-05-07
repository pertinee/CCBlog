package com.lcz.blog.service.impl;

import com.lcz.blog.mapper.CategoryDao;
import com.lcz.blog.bean.CategoryBean;
import com.lcz.blog.service.CategoryService;
import com.lcz.blog.util.Pager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by luchunzhou on 15/12/13.
 */
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryDao categoryDao;

    @Override
    public List<CategoryBean> queryCategory(Map<String, Object> map){
        return categoryDao.queryList(map);
    }

    @Override
    public void updateCategory(CategoryBean category){
        categoryDao.update(category);
    }

    @Override
    public void insertCategory(CategoryBean category){
        categoryDao.save(category);
    }

    @Override
    public void deleteCategory(Integer categoryId){
        categoryDao.delete(categoryId);
    }

    @Override
    public List<CategoryBean> queryCategoryPage(Pager pager){
        return categoryDao.pagination(pager);
    }

    @Override
    public int getCount(){
        return categoryDao.queryTotal();
    }

    @Override
    public CategoryBean queryCategory(int categoryId){
        return categoryDao.queryObject(categoryId);
    }

}

package com.lcz.blog.controller.sys;

import com.lcz.blog.util.AttributeConstant;
import com.lcz.blog.bean.CategoryBean;
import com.lcz.blog.service.CategoryService;
import com.lcz.blog.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;


/**
 * Created by luchunzhou on 16/3/1.
 *
 * 管理员 分类编辑页面
 */
@Controller
@RequestMapping("/sys/category")
public class SysCategoryController extends BaseController{

    @Autowired
    private CategoryService categoryService;


    /**
     * 显示分类编辑页面
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showCategoriesPage(ModelMap model){
        model.addAttribute(AttributeConstant.USER, checkCurrentUser());
        model.addAttribute(AttributeConstant.CATEGORIES, categoryService.queryCategory(new HashMap<String, Object>()));
        model.addAttribute(AttributeConstant.MAIN_PAGE, "sys/category/editor.vm");
        return "sys/index";
    }


    /**
     * 创建分类
     * @param categoryName
     * @param model
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createAction(String categoryName, ModelMap model){
        model.addAttribute(AttributeConstant.USER, checkCurrentUser());
        model.addAttribute(AttributeConstant.MAIN_PAGE, "sys/category/editor.vm");
        if (StringUtil.isNotEmpty(categoryName)) {
            CategoryBean category = new CategoryBean();
            category.setName(categoryName);
            categoryService.insertCategory(category);
        } else {
            model.addAttribute(AttributeConstant.ERROR, "分类名称未填写!");
        }
        model.addAttribute(AttributeConstant.CATEGORIES, categoryService.queryCategory(new HashMap<String, Object>()));
        return "sys/index";
    }


    /**
     * 显示 更新分类页面
     * @param categoryId
     * @param model
     * @return
     */
    @RequestMapping(value = "/update/{categoryId:[0-9]+}", method = RequestMethod.GET)
    public String upDatePage(@PathVariable("categoryId") Integer categoryId, ModelMap model){
        model.addAttribute(AttributeConstant.USER, checkCurrentUser());
        model.addAttribute(AttributeConstant.MAIN_PAGE, "sys/category/editor.vm");
        CategoryBean category = categoryService.queryCategory(categoryId);
        if (StringUtil.isNotEmpty(category.getName())) {
            model.addAttribute(AttributeConstant.CATEGORY, category);
        }else{
            model.addAttribute(AttributeConstant.ERROR,"找不到该分类!");
        }
        model.addAttribute(AttributeConstant.CATEGORIES, categoryService.queryCategory(new HashMap<String, Object>()));
        return "sys/index";
    }


    /**
     * 更新分类
     * @param categoryName
     * @param categoryId
     * @param model
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String upDateAction(String categoryName,Integer categoryId,ModelMap model){
        model.addAttribute(AttributeConstant.USER, checkCurrentUser());
        model.addAttribute(AttributeConstant.MAIN_PAGE, "sys/category/editor.vm");
        CategoryBean category = new CategoryBean();
        category.setName(categoryName);
        category.setId(categoryId);
        categoryService.updateCategory(category);
        model.addAttribute(AttributeConstant.CATEGORIES, categoryService.queryCategory(new HashMap<String, Object>()));
        model.addAttribute(AttributeConstant.RETURN_INFO,"修改成功!");
        model.addAttribute(AttributeConstant.CATEGORY,category);
        return "sys/index";
    }

    /**
     * 删除分类
     * @param categoryId
     * @param model
     * @return
     */
    @RequestMapping(value = "/delete/{categoryId:[0-9]+}")
    public String deleteAction(@PathVariable("categoryId")Integer categoryId,ModelMap model){
        model.addAttribute(AttributeConstant.USER, checkCurrentUser());
        model.addAttribute(AttributeConstant.MAIN_PAGE, "sys/category/editor.vm");
        CategoryBean category = categoryService.queryCategory(categoryId);
        if(StringUtil.isNotEmpty(category.getName())){
            categoryService.deleteCategory(categoryId);
            model.addAttribute(AttributeConstant.RETURN_INFO,"删除成功!");
        }else{
            model.addAttribute(AttributeConstant.ERROR,"找不到该分类");
        }
        model.addAttribute(AttributeConstant.CATEGORIES,categoryService.queryCategory(new HashMap<String, Object>()));
        return "sys/index";
    }

}

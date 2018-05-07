package com.lcz.blog.controller.front;

import com.alibaba.fastjson.JSON;
import com.lcz.blog.util.AttributeConstant;
import com.lcz.blog.bean.CategoryBean;
import com.lcz.blog.bean.dto.ArticleLiteDto;
import com.lcz.blog.service.ArticleService;
import com.lcz.blog.service.CategoryService;
import com.lcz.blog.service.WebAppService;
import com.lcz.blog.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;

/**
 * Created by luchunzhou on 16/2/28.
 * 访客 分类页面
 */
@Controller
@RequestMapping("/category")
public class FrontCategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private WebAppService webAppService;

    /**
     * 分类列表
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap model) {
        model.addAttribute(AttributeConstant.WEB_APP_DTO,webAppService.queryWebApp(new HashMap<String, Object>()).get(0));
        List<CategoryBean> categories = categoryService.queryCategory(new HashMap<String, Object>());
        model.addAttribute(AttributeConstant.MAIN_PAGE, "front/category/categoryList.vm");
        model.addAttribute(AttributeConstant.CATEGORIES, categories);
        // 搜索框内容查询(list)
        List<String> searchList = articleService.queryTitle();
        String jsonStr = JSON.toJSONString(searchList);
        model.addAttribute(AttributeConstant.SEARCH_LIST, jsonStr);
        return "index";
    }

    /**
     * 详情
     * @param categoryId
     * @param model
     * @return
     */
    @RequestMapping(value = "/{categoryId:[0-9]+}", method = RequestMethod.GET)
    public String detail(@PathVariable("categoryId") Integer categoryId, ModelMap model) {
        model.addAttribute(AttributeConstant.WEB_APP_DTO,webAppService.queryWebApp(new HashMap<String, Object>()).get(0));
        CategoryBean category = categoryService.queryCategory(categoryId);
        model.addAttribute(AttributeConstant.MAIN_PAGE, "front/category/detail.vm");
        if (StringUtil.isNotEmpty(category.getName())) {
            List<ArticleLiteDto> articles = articleService.queryByCategory(categoryId);
            if (articles.size() == 0) {
                articles = null;
            }
            model.addAttribute(AttributeConstant.CATEGORY, category);
            model.addAttribute(AttributeConstant.ARTICLES, articles);
        } else {
            model.addAttribute(AttributeConstant.ERROR, "找不到该分类");
        }
        // 搜索框内容查询(list)
        List<String> searchList = articleService.queryTitle();
        String jsonStr = JSON.toJSONString(searchList);
        model.addAttribute(AttributeConstant.SEARCH_LIST, jsonStr);
        return "index";
    }


}

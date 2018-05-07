package com.lcz.blog.controller.api.common;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 通用API接口文档
 * Created by luchunzhou on 2017/12/11.
 */
@Api(value = "表单提交方式", description = "通用接口：多种表单提交方式")
@Controller
@RequestMapping("/api/common")
public class HttpMethodController {

    /**
     * 跳转到form表单测试页
     *
     * @param model
     * @return
     */
    @ApiOperation(value = "跳转到form表单", httpMethod = "GET", notes = "跳转到form表单测试页")
    @RequestMapping(value = "/httpMethod", method = RequestMethod.GET)
    public String toHttpMethod(ModelMap model) {
        return "api/httpMethod";
    }

    /**
     * GET方式
     *
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "GET方式", httpMethod = "GET", response = String.class, notes = "GET")
    @RequestMapping(value = "/test_get", method = RequestMethod.GET)
    public String testGET() {
        return "GET";
    }

    /**
     * POST方式
     *
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "POST方式", httpMethod = "POST", response = String.class, notes = "POST")
    @RequestMapping(value = "/test_post", method = RequestMethod.POST)
    public String testPOST() {

        return "POST";
    }

    /**
     * PUT方式
     *
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "PUT方式", httpMethod = "PUT", response = String.class, notes = "PUT")
    @RequestMapping(value = "/test_put/{id}", method = RequestMethod.PUT)
    public String testPUT(@PathVariable("id") int id) {

        return "PUT:" + id;
    }

    /**
     * PATCH方式
     *
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "PATCH方式", httpMethod = "PATCH", response = String.class, notes = "PATCH")
    @RequestMapping(value = "/test_patch/{id}", method = RequestMethod.PATCH)
    public String testPATCH(@PathVariable("id") int id) {

        return "PATCH:" + id;
    }

    /**
     * DELETE方式
     *
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "DELETE方式", httpMethod = "DELETE", response = String.class, notes = "DELETE")
    @RequestMapping(value = "/test_delete/{id}", method = RequestMethod.DELETE)
    public String testDELETE(@PathVariable("id") int id) {

        return "DELETE:" + id;
    }
}

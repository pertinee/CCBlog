package com.lcz.blog.controller.api.common;

import com.lcz.blog.bean.ApisBean;
import com.lcz.blog.service.ApisService;
import com.lcz.blog.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

/**
 * 通用API接口文档
 * Created by luchunzhou on 17/12/9.
 */

@Api(value = "接口对象类", description = "通用接口：接口对象基本操作")
@Controller
@RequestMapping("/api/common")
public class ApiCommonController {

    @Autowired
    private ApisService apisService;


    /**
     * 接口列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/apis", method = RequestMethod.GET)
    @ApiOperation(value = "接口列表", httpMethod = "GET", response = ApisBean.class, notes = "GET")
    public List<ApisBean> showListArticle() {
        List<ApisBean> apisList = apisService.queryApis(new HashMap<String, Object>());
        return apisList;
    }

    /**
     * 通过id查询接口对象
     *
     * @param apiId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/apis/{apiId}", method = RequestMethod.GET)
    @ApiModelProperty
    @ApiOperation(value = "通过id查询接口对象", httpMethod = "GET", response = ApisBean.class, notes = "通过id查询接口对象详情")
    public ApisBean getApi(@ApiParam(name = "apiId", required = true, value = "接口对象id") @PathVariable("apiId") Integer apiId) {
        ApisBean api = apisService.queryApis(apiId);
        return api;
    }

    /**
     * 保存接口对象
     *
     * @param apis
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/apis", method = RequestMethod.POST)
    @ApiOperation(value = "保存接口对象", httpMethod = "POST", response = String.class, notes = "保存接口对象")
    public String saveApis(@ApiParam(name = "apis", required = true, value = "接口对象apis") ApisBean apis) {
        if (null != apis
                && StringUtil.isNotEmpty(apis.getName())
                && StringUtil.isNotEmpty(apis.getContent())) {
            apisService.insertApis(apis);
            return "success";
        }
        return "failure";
    }


    /**
     * 更新接口对象信息(全部)
     *
     * @param apiId
     * @param apis
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/apis/{apiId}", method = RequestMethod.PUT)
    @ApiOperation(value = "更新接口对象信息(全部)", httpMethod = "PUT", response = String.class, notes = "通过id更新接口对象信息(全部)")
    public String updateApisAll(@ApiParam(name = "apiId", required = true, value = "接口对象id") @PathVariable("apiId") Integer apiId, @ApiParam(name = "apis", required = true, value = "接口对象apis") ApisBean apis) {
        if (null != apis
                && StringUtil.isNotEmpty(apis.getName())
                && StringUtil.isNotEmpty(apis.getContent())) {
            apisService.updateApisAll(apis);
            return "success";
        }
        return "failure";
    }

    /**
     * 更新接口对象信息(部分)
     *
     * @param apiId
     * @param apis
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/apis/{apiId}", method = RequestMethod.PATCH)
    @ApiOperation(value = "更新接口对象信息(部分)", httpMethod = "PATCH", response = String.class, notes = "通过id更新接口对象信息(部分)")
    public String updateApis(@ApiParam(name = "apiId", required = true, value = "接口对象id") @PathVariable("apiId") Integer apiId, @ApiParam(name = "apis", required = true, value = "接口对象apis") ApisBean apis) {
        if (null != apis) {
            apisService.updateApis(apis);
            return "success";
        }
        return "failure";
    }

    /**
     * 通过id删除接口对象
     *
     * @param apiId
     * @return
     */
    @Transactional
    @ResponseBody
    @RequestMapping(value = "/apis/{apiId}", method = RequestMethod.DELETE)
    @ApiOperation(value = "通过id删除接口对象", httpMethod = "DELETE", response = String.class, notes = "通过id删除接口对象")
    public String deleteArticle(@ApiParam(name = "apiId", required = true, value = "接口对象id") @PathVariable("apiId") Integer apiId) {
        apisService.deleteApis(apiId);
        return "success";
    }

    /**
     * 测试异常捕获、事务回滚
     *
     * @return
     */
    @Transactional
    @ResponseBody
    @RequestMapping(value = "/apis/exception", method = RequestMethod.GET)
    @ApiOperation(value = "测试异常捕获、事务回滚", httpMethod = "GET", response = String.class, notes = "测试异常捕获、事务回滚")
    public String testException() {
        ApisBean api = new ApisBean();
        api.setId(111);
        api.setName("exception");
        api.setContent("test exception.");
        apisService.insertApis(api);
        //抛出空指针异常
        Object object = null;
        object.toString();
        return "success";
    }

}

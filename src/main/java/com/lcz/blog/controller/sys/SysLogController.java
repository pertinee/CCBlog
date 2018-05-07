package com.lcz.blog.controller.sys;

import com.lcz.blog.bean.UserBean;
import com.lcz.blog.util.AttributeConstant;
import com.lcz.blog.bean.LogBean;
import com.lcz.blog.service.LogService;
import com.lcz.blog.service.WebAppService;
import com.lcz.blog.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by luchunzhou on 18/1/20.
 * 管理员 日志页面
 */
@Controller
public class SysLogController extends BaseController{
    @Autowired
    private LogService logService;
    @Autowired
    private WebAppService webAppService;

    /**
     * 显示日志分页列表
     * @param model
     * @param currentPage
     * @return
     */
    @RequestMapping(value = "/sys/log", method = RequestMethod.GET)
    public String showListLog(ModelMap model, @RequestParam(defaultValue = "1") Integer currentPage){
        UserBean currentUser = checkCurrentUser();
        model.addAttribute(AttributeConstant.USER, currentUser);
        List<LogBean> logList = logService.queryLog(new HashMap<String, Object>());
        Integer sysPage = webAppService.queryWebApp(new HashMap<String, Object>()).get(0).getSysPage();
        Pager pager = new Pager(currentPage, sysPage, logList.size());
        List<LogBean> logs = logService.queryLogPage(pager);
        model.addAttribute(AttributeConstant.LOGS, logs);
        model.addAttribute(AttributeConstant.PAGER, pager);
        model.addAttribute(AttributeConstant.MAIN_PAGE, "sys/log/listLog.vm");
        return "sys/index";
    }

    /**
     * 搜索
     * @param operation
     * @param model
     * @return
     */
    @RequestMapping(value = "/sys/log/search", method = RequestMethod.POST)
    public String search(String operation, ModelMap model){
        UserBean currentUser = checkCurrentUser();
        model.addAttribute(AttributeConstant.USER, currentUser);
        Map<String, Object> map = new HashMap<>();
        map.put("operation",operation);
        Integer totalCount = logService.queryLogCount(map);
        Integer pageSize = webAppService.queryWebApp(new HashMap<String, Object>()).get(0).getSysPage();
        Pager pager = new Pager(1, pageSize, totalCount);
        if (totalCount > 0) {
            List<LogBean> logList = logService.queryLogPage(pager);
            model.addAttribute(AttributeConstant.LOGS, logList);
        } else {
            model.addAttribute(AttributeConstant.LOGS, null);
            model.addAttribute(AttributeConstant.ERROR, "找不到日志!");
        }
        model.addAttribute(AttributeConstant.PAGER, pager);

        model.addAttribute(AttributeConstant.MAIN_PAGE, "sys/log/listLog.vm");
        return "sys/index";
    }

    /**
     * 通过ID 删除文章
     * @param id
     * @param currentPage
     * @return
     */
    @RequestMapping(value = "/sys/log/delete/{id}", method = RequestMethod.GET)
    public String deleteArticle(@PathVariable("id") String id, @RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage){
        String path = "redirect:/sys/log";
        logService.deleteLog(id);
        if (currentPage != 1) {
            path = "redirect:/sys/log/?currentPage=" + currentPage;
        }
        return path;
    }

}

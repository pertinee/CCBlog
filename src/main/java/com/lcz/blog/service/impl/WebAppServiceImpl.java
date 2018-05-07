package com.lcz.blog.service.impl;

import com.lcz.blog.mapper.WebAppDao;
import com.lcz.blog.bean.WebAppBean;
import com.lcz.blog.service.WebAppService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by luchunzhou on 16/3/16.
 */
@Service("webAppService")
public class WebAppServiceImpl implements WebAppService {
    @Resource
    private WebAppDao webAppDao;

    @Override
    public void insertWebApp(WebAppBean webApp){
        webAppDao.save(webApp);
    }

    @Override
    public void updateWebApp(WebAppBean webApp){
        webAppDao.update(webApp);
    }

    @Override
    public WebAppBean queryWebApp(Integer id){
        return webAppDao.queryObject(id);
    }

    @Override
    public Integer queryClicks(){
        return webAppDao.queryClicks();
    }

    @Override
    public List<WebAppBean> queryWebApp(Map<String, Object> map){
        return webAppDao.queryList(map);
    }

    @Override
    public boolean webAppNotNull(){
        return webAppDao.queryTotal() > 0 ? true : false;
    }
}

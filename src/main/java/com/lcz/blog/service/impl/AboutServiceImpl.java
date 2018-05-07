package com.lcz.blog.service.impl;

import com.lcz.blog.mapper.AboutDao;
import com.lcz.blog.bean.AboutBean;
import com.lcz.blog.service.AboutService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by luchunzhou on 16/1/7.
 */
@Service("aboutService")
public class AboutServiceImpl implements AboutService {

    @Resource
    private AboutDao aboutDao;

    @Override
    public int queryAboutCount(){
            return aboutDao.queryTotal();
    }

    @Override
    public AboutBean queryAbout(Integer id){
        return aboutDao.queryObject(id);
    }

    @Override
    public void updateAbout(AboutBean about){
        aboutDao.update(about);
    }

    @Override
    public List<AboutBean> queryAbout(Map<String, Object> map){
        return aboutDao.queryList(map);
    }

    @Override
    public void insertAbout(AboutBean about){
        aboutDao.save(about);
    }

    @Override
    public void deleteAbout(Integer id){
        aboutDao.delete(id);
    }
}

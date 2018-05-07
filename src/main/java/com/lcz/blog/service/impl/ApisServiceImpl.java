package com.lcz.blog.service.impl;

import com.lcz.blog.mapper.ApisDao;
import com.lcz.blog.bean.ApisBean;
import com.lcz.blog.service.ApisService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by LuChunzhou on 2017/12/9.
 */
@Service("apisService")
public class ApisServiceImpl implements ApisService{

    @Resource
    private ApisDao apisDao;

    @Override
    public ApisBean queryApis(Integer id){
        return apisDao.queryObject(id);
    }

    @Override
    public List<ApisBean> queryApis(Map<String, Object> map){
        return apisDao.queryList(map);
    }

    @Override
    public void updateApis(ApisBean apis){
        apisDao.update(apis);
    }

    @Override
    public void updateApisAll(ApisBean apis){
        apisDao.updateAll(apis);
    }

    @Override
    public void insertApis(ApisBean apis){
        apisDao.save(apis);
    }

    @Override
    public void deleteApis(Integer id){
        apisDao.delete(id);
    }
}

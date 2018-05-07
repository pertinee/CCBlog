package com.lcz.blog.service;
import com.lcz.blog.bean.ApisBean;

import java.util.List;
import java.util.Map;

/**
 * Created by luchunzhou on 17/12/9.
 */
public interface ApisService {

    /**
     * 根据id获取接口对象
     * @param id
     * @return
     * @throws Exception
     */
    ApisBean queryApis(Integer id);

    /**
     * 获取接口对象
     * @return
     * @throws Exception
     */
    List<ApisBean> queryApis(Map<String, Object> map);

    /**
     * 更新接口对象
     * @param apis
     * @throws Exception
     */
    void updateApisAll(ApisBean apis);

    /**
     * 更新接口对象(部分)
     * @param apis
     * @throws Exception
     */
    void updateApis(ApisBean apis);

    /**
     * 添加接口对象
     * @param apis
     * @throws Exception
     */
    void insertApis(ApisBean apis);

    /**
     * 删除接口对象
     */
    void deleteApis(Integer id);

}

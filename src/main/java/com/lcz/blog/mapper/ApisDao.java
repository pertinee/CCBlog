package com.lcz.blog.mapper;

import com.lcz.blog.bean.ApisBean;

/**
 * Created by luchunzhou on 16/1/7.
 */
public interface ApisDao extends BaseDao<ApisBean> {


    /**
     * 更新接口对象
     * @param apis
     * @throws Exception
     */
    void updateAll(ApisBean apis);


}

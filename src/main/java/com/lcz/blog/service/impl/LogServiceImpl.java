package com.lcz.blog.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.lcz.blog.mapper.LogDao;
import com.lcz.blog.bean.LogBean;
import com.lcz.blog.service.LogService;
import com.lcz.blog.util.IDUtils;
import com.lcz.blog.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;


@Service("logService")
public class LogServiceImpl implements LogService {
	@Autowired
	private LogDao logDao;
	
	@Override
	public LogBean queryLog(String id){
		return logDao.queryObject(id);
	}

	@Override
	public List<LogBean> queryLogPage(Pager pager) {
		return logDao.pagination(pager);
	}

	@Override
	public List<LogBean> queryLog(Map<String, Object> map){
		return logDao.queryList(map);
	}

	@Override
	public int queryLogCount(Map<String, Object> map) {
		return logDao.queryTotal(map);
	}

	@Override
	public void insertLog(LogBean sysLogBean){
		if(StringUtils.isEmpty(sysLogBean.getId())){
			sysLogBean.setId(IDUtils.uuid());
		}
		logDao.save(sysLogBean);
	}

	@Override
	public void deleteLog(String id) {
		logDao.delete(id);
	}
}

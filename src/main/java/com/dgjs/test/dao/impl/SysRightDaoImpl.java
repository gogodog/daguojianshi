package com.dgjs.test.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dgjs.test.dao.SysRightDao;
import com.dgjs.test.mapper.SystemQXMapper;
import com.dgjs.test.model.SystemQX;

/**
 * @ClassName: SysRightDaoImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author cott.wen
 * @date 2016年11月21日 上午11:49:01 
 */
@Service
public class SysRightDaoImpl implements SysRightDao{

	@Resource
	private SystemQXMapper systemQXMapper;

	public List<Map<String,Object>> selectqxByUUID(String uuid) {
		SystemQX mapper = new SystemQX();
		mapper.setUuid(uuid);
		mapper.setStatus(1);
		return systemQXMapper.selectlikename(mapper);
	}
}

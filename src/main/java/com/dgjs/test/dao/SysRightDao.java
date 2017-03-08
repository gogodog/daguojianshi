package com.dgjs.test.dao;

import java.util.List;
import java.util.Map;

import com.dgjs.test.model.SystemQX;


/**
 * 系统权限配置
 * @ClassName: SysRightDao 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author cott.wen
 * @date 2016年11月21日 上午11:57:05 
 *
 */
public interface SysRightDao {

	/**
	 * 通过uuid查询
	 *
	 * @param uuid
	 * @return
	 * @throws SystemRightExp
	 */
	List<Map<String,Object>> selectqxByUUID(String uuid);

}
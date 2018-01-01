package com.dgjs.mapper.config;

import java.util.List;

import com.dgjs.model.persistence.Config;
import com.dgjs.model.persistence.condition.ConfigCondition;

public interface ConfigMapper {

	public int save(Config config);
	
	public List<Config> list(ConfigCondition condition);
	
	public int update(Config config);
	
	public Config selectById(Integer id);
	
	public int deleteById(Integer id);
	
	public Config getMIndexConfigByKey(String key);
}

package com.dgjs.service.config;

import java.util.List;
import java.util.Map;

import com.dgjs.model.dto.MIndexConfigDto;
import com.dgjs.model.persistence.Config;
import com.dgjs.model.persistence.condition.ConfigCondition;

public interface ConfigService {

	public List<Config> list(ConfigCondition condition);
	
	public int save(Config config);
	
	public int update(Config config);
	
	public int deleteById(Integer id);
	
	public Config selectById(Integer id);
	
	public Map<String,MIndexConfigDto> getMIndexConfigs();
	
	public MIndexConfigDto getMIndexConfigByKey(String key);
}

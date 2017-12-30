package com.dgjs.service.impl.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.dgjs.constants.ConfigKey;
import com.dgjs.mapper.config.ConfigMapper;
import com.dgjs.model.dto.MIndexConfigDto;
import com.dgjs.model.persistence.Config;
import com.dgjs.model.persistence.condition.ConfigCondition;
import com.dgjs.service.config.ConfigService;

@Service
public class ConfigServiceImpl implements ConfigService{

	@Autowired
	ConfigMapper configMapper;
	
	@Override
	public List<Config> list(ConfigCondition condition) {
		return configMapper.list(condition);
	}

	@Override
	public int update(Config config) {
		return configMapper.update(config);
	}

	@Override
	public int deleteById(Integer id) {
		return configMapper.deleteById(id);
	}

	@Override
	public Config selectById(Integer id) {
		return configMapper.selectById(id);
	}

	@Override
	public int save(Config config) {
		return configMapper.save(config);
	}
	
	@Override
	@Cacheable(value = "cache5m")
	public Map<String,MIndexConfigDto> getMIndexConfigs(){
		Map<String,MIndexConfigDto> map = null;
		List<String> keys = Arrays.asList(ConfigKey.M_INDEX_AFFAIRS,ConfigKey.M_INDEX_GEOGRAPHY,ConfigKey.M_INDEX_HISTORY
				,ConfigKey.M_INDEX_PERSON,ConfigKey.M_INDEX_UNOFFICIAL,ConfigKey.M_INDEX_UPTODATE);
		ConfigCondition condition = new ConfigCondition();
		condition.setKeys(keys);
		List<Config> list = list(condition);
		if(list != null && list.size()>0){
		    map = new HashMap<String,MIndexConfigDto>();
			for(Config config:list){
				String value = config.getC_value();
				MIndexConfigDto dto = JSON.parseObject(value, MIndexConfigDto.class);
				map.put(config.getC_key(), dto);
			}
		}
		return map;
	}

	
}

package com.dgjs.service.content;

import java.util.List;

import com.dgjs.model.persistence.MIndexConfig;
import com.dgjs.model.persistence.condition.MIndexConfigCondition;

public interface MIndexConfigService {

	public int save(MIndexConfig mIndexConfig);
	
	public int update(MIndexConfig mIndexConfig);
	
	public int deleteById(Long id);
	
	public MIndexConfig selectById(Long id);
	
	public List<MIndexConfig> list(MIndexConfigCondition condition);
}

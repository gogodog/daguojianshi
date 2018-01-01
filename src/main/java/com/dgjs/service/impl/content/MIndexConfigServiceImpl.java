package com.dgjs.service.impl.content;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgjs.mapper.content.MIndexConfigMapper;
import com.dgjs.model.persistence.MIndexConfig;
import com.dgjs.model.persistence.condition.MIndexConfigCondition;
import com.dgjs.service.content.MIndexConfigService;

@Service
public class MIndexConfigServiceImpl implements MIndexConfigService{

	@Autowired
	MIndexConfigMapper mapper;
	
	@Override
	public int save(MIndexConfig mIndexConfig) {
		return mapper.save(mIndexConfig);
	}

	@Override
	public int update(MIndexConfig mIndexConfig) {
		return mapper.update(mIndexConfig);
	}

	@Override
	public int deleteById(Long id) {
		return mapper.deleteById(id);
	}

	@Override
	public MIndexConfig selectById(Long id) {
		return mapper.selectById(id);
	}

	@Override
	public List<MIndexConfig> list(MIndexConfigCondition condition) {
		return mapper.list(condition);
	}

}

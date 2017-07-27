package com.dgjs.service.content;

import java.util.List;

import com.dgjs.model.dto.IndexConfigDto;
import com.dgjs.model.persistence.IndexConfig;

public interface IndexConfigService {

    public int save(IndexConfig indexConfig);
	
	public List<IndexConfigDto> list(IndexConfig indexConfig);
	
	public int update(IndexConfig indexConfig);
	
	public int delete(Long id);
	
	public IndexConfigDto selectById(Long id);
}

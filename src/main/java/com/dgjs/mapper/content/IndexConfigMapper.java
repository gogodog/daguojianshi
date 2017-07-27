package com.dgjs.mapper.content;

import java.util.List;

import com.dgjs.model.persistence.IndexConfig;

public interface IndexConfigMapper {

	public int save(IndexConfig indexConfig);
	
	public List<IndexConfig> list(IndexConfig indexConfig);
	
	public int update(IndexConfig indexConfig);
	
	public int deleteById(Long id);
	
	public IndexConfig selectById(Long id);
}

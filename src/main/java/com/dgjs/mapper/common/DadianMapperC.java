package com.dgjs.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dgjs.model.persistence.result.PagedocidsCountResult;
import com.dgjs.model.result.view.DadianView;

public interface DadianMapperC {
	
	public int insert(DadianView dadianView);
	
	public int pageIdCount(@Param("pageid")String pageid);
	
	public List<PagedocidsCountResult> pagedocidsCount(List<String> pagedocids);
	
}

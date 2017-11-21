package com.dgjs.es.mapper.common;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dgjs.model.persistence.result.PagedocidsCountResult;
import com.dgjs.model.result.view.DadianView;

public interface DadianMapper {

    public int insert(DadianView dadianView);
	
	public long pageIdCount(@Param("pageid")String pageid);
	
	public List<PagedocidsCountResult> pagedocidsCount(List<String> pagedocids);
}
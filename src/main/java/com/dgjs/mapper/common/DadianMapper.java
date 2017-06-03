package com.dgjs.mapper.common;

import org.apache.ibatis.annotations.Param;

import com.dgjs.model.view.DadianView;

public interface DadianMapper {
	
	public int insert(DadianView dadianView);
	
	public int countByCondtion(@Param("pagedocids")String pagedocids,@Param("pageid")String pageid);
	
   	
}

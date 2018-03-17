package com.dgjs.mapper.admin;

import org.apache.ibatis.annotations.Param;

public interface PrimaryKeyMapper {

	public Long getPrimaryValue(Integer id);
	
	public int updateValue(@Param("id")Integer id,@Param("primary_value") Long primaryValue);
}

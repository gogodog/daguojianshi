package com.dgjs.mapper.content;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dgjs.model.persistence.ChannelArticlescrap;

public interface ChannelArticlescrapMapper {

	public int save(ChannelArticlescrap channelArticlescrap);
	
	public int update(@Param("id")String id,@Param("sort")int sort);
	
    public List<ChannelArticlescrap> list(Integer channel_id);
    
    public int deleteById(String id);
}

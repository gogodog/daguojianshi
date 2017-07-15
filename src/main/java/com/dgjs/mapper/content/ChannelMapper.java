package com.dgjs.mapper.content;

import java.util.List;

import com.dgjs.model.persistence.Channel;

public interface ChannelMapper {

	public int save(Channel channel);
	
	public Channel selectById(Integer id);
	
	public int update(Channel channel);
	
	public List<Channel> list();
	
	public int deleteById();
}

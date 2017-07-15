package com.dgjs.service.impl.content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgjs.es.mapper.content.ArticlescrapMapper;
import com.dgjs.mapper.content.ChannelArticlescrapMapper;
import com.dgjs.mapper.content.ChannelMapper;
import com.dgjs.model.dto.ChannelArticlescrapDto;
import com.dgjs.model.dto.business.Articlescrap;
import com.dgjs.model.persistence.Channel;
import com.dgjs.model.persistence.ChannelArticlescrap;
import com.dgjs.service.content.ChannelService;
import com.dgjs.utils.IdsUtils;

@Service
public class ChannelServiceImpl implements ChannelService{

	@Autowired
	ChannelMapper channelMapper;
	
	@Autowired
	ChannelArticlescrapMapper caMapper;
	
	@Autowired
	ArticlescrapMapper articlescrapMapper;
	
	@Override
	public int save(Channel channel) {
		return channelMapper.save(channel);
	}

	@Override
	public int update(Channel channel) {
		return channelMapper.update(channel);
	}

	@Override
	public List<Channel> list() {
		return channelMapper.list();
	}

	@Override
	public int deleteById(Integer id) {
		return channelMapper.deleteById();
	}

	@Override
	public int saveCA(ChannelArticlescrap ca) {
		ca.setId(IdsUtils.getUuId());
		return caMapper.save(ca);
	}

	@Override
	public int updateCA(String id, int sort) {
		return caMapper.update(id, sort);
	}

	@Override
	public int deleteCA(String id) {
		return caMapper.deleteById(id);
	}

	@Override
	public List<ChannelArticlescrapDto> listCA(Integer channelId) {
		List<ChannelArticlescrap> list=caMapper.list(channelId);
		List<String> ids = new ArrayList<String>();
		for(ChannelArticlescrap ca:list){
			ids.add(ca.getArticlescrap_id());
		}
		List<Articlescrap> articlescrapList=articlescrapMapper.getArticlescrapByIds((String[])ids.toArray());
		Map<String,Articlescrap> map = new HashMap<String,Articlescrap>();
		for(Articlescrap articlescrap:articlescrapList){
			map.put(articlescrap.getId(), articlescrap);
		}
		List<ChannelArticlescrapDto> cad = new ArrayList<ChannelArticlescrapDto>();
		for(ChannelArticlescrap ca:list){
			ChannelArticlescrapDto dto = new ChannelArticlescrapDto();
			dto.setChannelArticlescrap(ca);
			dto.setTitle(map.get(ca.getArticlescrap_id()).getTitle());
			cad.add(dto);
		}
		return cad;
	}

}

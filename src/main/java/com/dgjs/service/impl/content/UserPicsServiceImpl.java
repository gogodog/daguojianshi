package com.dgjs.service.impl.content;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.dgjs.mapper.content.UserPicsMapper;
import com.dgjs.model.dto.UserPicsDto;
import com.dgjs.model.dto.business.entity.Pics;
import com.dgjs.model.persistence.UserPics;
import com.dgjs.service.content.UserPicsService;
import com.dgjs.utils.StringUtils;

@Service
public class UserPicsServiceImpl implements UserPicsService{

	@Autowired
	UserPicsMapper userPicsMapper;
	
	@Override
	public int save(UserPicsDto userPicsDto) {
		UserPics userPics = userPicsDto.getUserPics();
		userPics.setPictures(JSON.toJSONString(userPicsDto.getPics()));
		return userPicsMapper.save(userPics);
	}

	@Override
	public UserPicsDto selectById(Integer id) {
		UserPicsDto dto = null;
		UserPics userPics =userPicsMapper.selectById(id);
		if(userPics!=null){
			 dto = new UserPicsDto();
			 dto.setUserPics(userPics);
			 if(!StringUtils.isNullOrEmpty(userPics.getPictures())){
				 List<Pics> pics=JSON.parseArray(userPics.getPictures(), Pics.class);
				 dto.setPics(pics);
			 }
		}
		return dto;
	}

	@Override
	public int savePics(Integer id, List<Pics> pics) {
		UserPics userPics=userPicsMapper.selectById(id);
		List<Pics> pictures=JSON.parseArray(userPics.getPictures(), Pics.class);
		pictures.addAll(pics);
		userPics.setPictures(JSON.toJSONString(pictures));
		return userPicsMapper.update(userPics);
	}

	@Override
	public int removePics(Integer id, List<String> picUrls) {
		UserPics userPics=userPicsMapper.selectById(id);
		List<Pics> pictures=JSON.parseArray(userPics.getPictures(), Pics.class);
		if(pictures!=null && !pictures.isEmpty()){
			Iterator<Pics> iterator=pictures.iterator();
			while(iterator.hasNext()){
				Pics pics = iterator.next();
				if(picUrls.contains(pics.getUrl())){
					iterator.remove();
				}
			}
		}
		userPics.setPictures(JSON.toJSONString(pictures));
		return userPicsMapper.update(userPics);
	}

	@Override
	public int update(UserPicsDto userPicsDto) {
		UserPics userPics = userPicsDto.getUserPics();
		userPics.setPictures(JSON.toJSONString(userPicsDto.getPics()));
		return userPicsMapper.update(userPics);
	}

}

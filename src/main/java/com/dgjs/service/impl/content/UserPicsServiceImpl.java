package com.dgjs.service.impl.content;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.dgjs.mapper.content.UserPicsMapper;
import com.dgjs.model.dto.UserPicsDto;
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
				 List<String> pics=JSON.parseArray(userPics.getPictures(), String.class);
				 dto.setPics(pics);
			 }
		}
		return dto;
	}

	@Override
	public int update(Integer id,int operate, List<String> pics) {
		UserPics userPics=userPicsMapper.selectById(id);
		List<String> pictures=JSON.parseArray(userPics.getPictures(), String.class);
		if(operate==1){
			 pictures.addAll(pics);
		}else if(operate==2){
			 pictures.removeAll(pics);
		}
		userPics.setPictures(JSON.toJSONString(pictures));
		return userPicsMapper.update(userPics);
	}

}

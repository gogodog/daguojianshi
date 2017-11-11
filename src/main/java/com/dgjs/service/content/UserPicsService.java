package com.dgjs.service.content;

import java.util.List;

import com.dgjs.model.dto.UserPicsDto;
import com.dgjs.model.dto.business.entity.Pics;

public interface UserPicsService {

	public int save(UserPicsDto userPicsDto);
	
	public UserPicsDto selectById(Integer id);
	
	public int update(UserPicsDto userPicsDto);
	
	public int savePics(Integer id,List<Pics> pics);
	
	public int removePics(Integer id,List<String> picUrls);
}

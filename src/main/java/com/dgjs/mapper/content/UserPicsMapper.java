package com.dgjs.mapper.content;

import com.dgjs.model.persistence.UserPics;

public interface UserPicsMapper {

	public int save(UserPics userPics);
	
	public int update(UserPics userPics);
	
	public UserPics selectById(Integer id);
}

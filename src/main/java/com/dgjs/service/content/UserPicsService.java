package com.dgjs.service.content;

import java.util.List;

import com.dgjs.model.dto.UserPicsDto;

public interface UserPicsService {

	public int save(UserPicsDto userPicsDto);
	
	public UserPicsDto selectById(Integer id);
	
	/**
	 * @param operate 1：新增 2：删除
	 * @param pics 更改的图片
	 * @param id 用户id
	 * @return
	 */
	public int update(Integer id,int operate,List<String> pics);
}

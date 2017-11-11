package com.dgjs.model.dto;

import java.util.List;

import com.dgjs.model.dto.business.entity.Pics;
import com.dgjs.model.persistence.UserPics;

public class UserPicsDto {

	private UserPics userPics;
	
	private List<Pics> pics;

	public UserPics getUserPics() {
		return userPics;
	}

	public void setUserPics(UserPics userPics) {
		this.userPics = userPics;
	}

	public List<Pics> getPics() {
		return pics;
	}

	public void setPics(List<Pics> pics) {
		this.pics = pics;
	}

}

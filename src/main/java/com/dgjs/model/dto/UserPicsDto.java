package com.dgjs.model.dto;

import java.util.List;

import com.dgjs.model.persistence.UserPics;

public class UserPicsDto {

	private UserPics userPics;
	
	private List<String> pics;

	public UserPics getUserPics() {
		return userPics;
	}

	public void setUserPics(UserPics userPics) {
		this.userPics = userPics;
	}

	public List<String> getPics() {
		return pics;
	}

	public void setPics(List<String> pics) {
		this.pics = pics;
	}
	
	
}

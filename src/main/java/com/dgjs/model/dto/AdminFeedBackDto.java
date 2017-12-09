package com.dgjs.model.dto;

import com.dgjs.model.persistence.AdminFeedBack;
import com.dgjs.model.persistence.result.AdminUserResult;

public class AdminFeedBackDto {

	AdminFeedBack feedBack;
	AdminUserResult adminUser;
	
	public AdminFeedBack getFeedBack() {
		return feedBack;
	}
	public void setFeedBack(AdminFeedBack feedBack) {
		this.feedBack = feedBack;
	}
	public AdminUserResult getAdminUser() {
		return adminUser;
	}
	public void setAdminUser(AdminUserResult adminUser) {
		this.adminUser = adminUser;
	}
	
}

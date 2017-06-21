package com.dgjs.model.dto;

public class PictureDto extends BaseDto{

	private String imageUrl;
	private String minImageUrl;
	private String tailorImageUrl;

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getMinImageUrl() {
		return minImageUrl;
	}

	public void setMinImageUrl(String minImageUrl) {
		this.minImageUrl = minImageUrl;
	}

	public String getTailorImageUrl() {
		return tailorImageUrl;
	}

	public void setTailorImageUrl(String tailorImageUrl) {
		this.tailorImageUrl = tailorImageUrl;
	}
	
}

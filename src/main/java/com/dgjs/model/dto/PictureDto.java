package com.dgjs.model.dto;

public class PictureDto extends BaseDto{

	private String imageUrl;//原图
	private String minImageUrl;//1：1缩略图
	private String tailorImageUrl;//宽高裁剪图
	private String watermarkImageUrl;//带水印图

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

	public String getWatermarkImageUrl() {
		return watermarkImageUrl;
	}

	public void setWatermarkImageUrl(String watermarkImageUrl) {
		this.watermarkImageUrl = watermarkImageUrl;
	}
	
}

package com.dgjs.model.outside;

public class PictureDto {
	
	private String fileName;//文件名称

	private String originUrl;//原图地址
	
	private String oneToOneUrl;//1：1缩略图地址

	public String getOriginUrl() {
		return originUrl;
	}

	public void setOriginUrl(String originUrl) {
		this.originUrl = originUrl;
	}

	public String getOneToOneUrl() {
		return oneToOneUrl;
	}

	public void setOneToOneUrl(String oneToOneUrl) {
		this.oneToOneUrl = oneToOneUrl;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}

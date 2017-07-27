package com.dgjs.model.result.view;

import java.util.List;

import com.dgjs.model.dto.PictureDto;

public class UploadPictureView extends BaseView{

	
	private String imageUrl;
	
	private List<PictureDto> list;
	

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public List<PictureDto> getList() {
		return list;
	}

	public void setList(List<PictureDto> list) {
		this.list = list;
	}

	
}

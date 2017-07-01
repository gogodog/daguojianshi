package com.dgjs.model.dto;

import net.coobird.thumbnailator.geometry.Positions;

public class ThumbnailatorDto {

	private String fromPath;//原图存放位置
	
	private String toPath;//目标存放位置
	
	private float scale;//缩放比例
	
	private int width;//宽度
	
	private int height;//高度
	
	private String watermark;//水印图片路径
	
	private Positions positions;//水印位置

	public String getFromPath() {
		return fromPath;
	}

	public void setFromPath(String fromPath) {
		this.fromPath = fromPath;
	}

	public String getToPath() {
		return toPath;
	}

	public void setToPath(String toPath) {
		this.toPath = toPath;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getWatermark() {
		return watermark;
	}

	public void setWatermark(String watermark) {
		this.watermark = watermark;
	}

	public Positions getPositions() {
		return positions;
	}

	public void setPositions(Positions positions) {
		this.positions = positions;
	}
	
	
}

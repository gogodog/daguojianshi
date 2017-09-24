package com.dgjs.model.dto.business;

import java.util.Date;

import com.dgjs.model.enums.Articlescrap_Type;
import com.dgjs.model.enums.Draft_Status;

public class Draft extends StartTime{
	private String id;//id
	private String title;//标题
	private Articlescrap_Type type;//文章类型
	private String author;//作者
	private Date create_time;//创建时间
	private Date update_time;//修改时间
	private String sub_content;//精简内容
	private String content;//文章内容
	private String[] keywords;//关键词（分类）
	private String[] pictures;//图片
    private int pic_num;//图片数量
	private Integer user_id;//用户id
	private Draft_Status draft_status;//状态	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Articlescrap_Type getType() {
		return type;
	}
	public void setType(Articlescrap_Type type) {
		this.type = type;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public String getSub_content() {
		return sub_content;
	}
	public void setSub_content(String sub_content) {
		this.sub_content = sub_content;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String[] getKeywords() {
		return keywords;
	}
	public void setKeywords(String[] keywords) {
		this.keywords = keywords;
	}
	public String[] getPictures() {
		return pictures;
	}
	public void setPictures(String[] pictures) {
		this.pictures = pictures;
	}
	public int getPic_num() {
		return pic_num;
	}
	public void setPic_num(int pic_num) {
		this.pic_num = pic_num;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	
	public Draft_Status getDraft_status() {
		return draft_status;
	}
	public void setDraft_status(Draft_Status draft_status) {
		this.draft_status = draft_status;
	}
	public void setKeywordsValue(String keywordsValue) {
		if(keywordsValue != null){
			String[] values =keywordsValue.split("#");
			keywords = values;
		}
	}
	public String getKeywordsValue() {
		if(keywords!=null&&keywords.length>0){
			StringBuilder str = new StringBuilder();
			for(int i=0;i<keywords.length;i++){
				str.append(keywords[i]);
				if(i!=keywords.length-1)
				  str.append("#");
			}
			return str.toString();
		}
		return null;
	}
	public void setTypeValue(Articlescrap_Type type) {
		this.type = type;
	}
	public String getTypeValue() {
		return type==null?null:type.getValue();
	}
}

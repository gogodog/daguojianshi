package com.dgjs.es.client.init;

import java.util.Random;

import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.dgjs.model.dto.business.Articlescrap;
import com.dgjs.model.dto.business.entity.Recommend;
import com.dgjs.model.enums.Articlescrap_Type;
import com.dgjs.model.enums.TIME_DEGREE;
import com.dgjs.model.enums.UpDown_Status;
import com.dgjs.model.es.ArticlescrapEs;
import com.dgjs.utils.DateUtils;

public class A {
	
	private String id;//id
	private String title;//标题
	private String content;//文章内容
	private String show_time;//展示时间
	private int status;//文章状态
	private String author;//作者
	private String create_time;//创建时间
	private String update_time;//修改时间
	private String sub_content;//精简内容
	private String show_picture;//展示图片
	private Long visits;//访问量
	private Integer start_time;//内容的起始时间
	private String[] keywords;//关键词（分类）
	private Integer type;//文章类型
	private Recommend recommend;//推荐
	private int time_degree;//起始时间精度
	private String[] pictures;//图片
	private int pic_num;//图片数量
	


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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getShow_time() {
		return show_time;
	}

	public void setShow_time(String show_time) {
		this.show_time = show_time;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}

	public String getSub_content() {
		return sub_content;
	}

	public void setSub_content(String sub_content) {
		this.sub_content = sub_content;
	}

	public String getShow_picture() {
		return show_picture;
	}

	public void setShow_picture(String show_picture) {
		this.show_picture = show_picture;
	}

	public Long getVisits() {
		return visits;
	}

	public void setVisits(Long visits) {
		this.visits = visits;
	}

	public Integer getStart_time() {
		return start_time;
	}

	public void setStart_time(Integer start_time) {
		this.start_time = start_time;
	}

	public String[] getKeywords() {
		return keywords;
	}

	public void setKeywords(String[] keywords) {
		this.keywords = keywords;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Recommend getRecommend() {
		return recommend;
	}

	public void setRecommend(Recommend recommend) {
		this.recommend = recommend;
	}

	public int getTime_degree() {
		return time_degree;
	}

	public void setTime_degree(int time_degree) {
		this.time_degree = time_degree;
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

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
	public static A ConvertToVo(ArticlescrapEs articlescrapEs){
		if(articlescrapEs == null){
			return null;
		}
		A articlescrap = new A();
		articlescrap.setAuthor(articlescrapEs.getAuthor());
		articlescrap.setContent(articlescrapEs.getContent());
		articlescrap.setCreate_time(articlescrapEs.getCreate_time());
		articlescrap.setShow_time(articlescrapEs.getShow_time());
		articlescrap.setStart_time(articlescrapEs.getStart_time());
		articlescrap.setStatus(articlescrapEs.getStatus());
		articlescrap.setSub_content(articlescrapEs.getSub_content());
		articlescrap.setTitle(articlescrapEs.getTitle());
		articlescrap.setType(articlescrapEs.getType());
		articlescrap.setUpdate_time(articlescrapEs.getUpdate_time());
		articlescrap.setVisits(articlescrapEs.getVisits());
		articlescrap.setRecommend(articlescrapEs.getRecommend());
		articlescrap.setKeywords(articlescrapEs.getKeywords());
		articlescrap.setTime_degree(articlescrapEs.getTime_degree());
//		articlescrap.setPictures(pic);
		articlescrap.setPic_num(1);
		return articlescrap;
	}
	
}

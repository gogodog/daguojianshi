package com.dgjs.model.es;

import com.alibaba.fastjson.JSON;
import com.dgjs.model.dto.business.Articlescrap;
import com.dgjs.model.dto.business.entity.Recommend;
import com.dgjs.model.enums.Articlescrap_Status;
import com.dgjs.model.enums.Articlescrap_Type;
import com.dgjs.model.enums.Pic_Sync_Status;
import com.dgjs.model.enums.TIME_DEGREE;
import com.dgjs.utils.DateUtils;

public class ArticlescrapEs implements java.io.Serializable{

	private static final long serialVersionUID = 598908887547996139L;
	
	private String id;//id
	private String title;//标题
	private String content;//文章内容
	private String show_time;//展示时间
	private int status;//文章状态
	private String author;//作者
	private String create_time;//创建时间
	private String update_time;//修改时间
	private String sub_content;//精简内容
	private Long visits;//访问量
	private Integer start_time;//内容的起始时间
	private String[] keywords;//关键词（分类）
	private Integer type;//文章类型
	private Recommend recommend;//推荐
	private int time_degree;//起始时间精度
    private String[] pictures;//图片
    private int pic_num;//图片数量
    private Integer user_id;//用户id	 
    private String draft_id;//原文id
    
    //图片信息
    private Integer pic_sync_status;//图片同步状态
    private int progress;//图片同步进度
    
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
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
	public Long getVisits() {
		return visits;
	}
	public void setVisits(Long visits) {
		this.visits = visits;
	}
	public Recommend getRecommend() {
		return recommend;
	}
	public void setRecommend(Recommend recommend) {
		this.recommend = recommend;
	}
	public String[] getKeywords() {
		return keywords;
	}
	public void setKeywords(String[] keywords) {
		this.keywords = keywords;
	}
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	public Integer getStart_time() {
		return start_time;
	}
	public void setStart_time(Integer start_time) {
		this.start_time = start_time;
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
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getDraft_id() {
		return draft_id;
	}
	public void setDraft_id(String draft_id) {
		this.draft_id = draft_id;
	}
	public Integer getPic_sync_status() {
		return pic_sync_status;
	}
	public void setPic_sync_status(Integer pic_sync_status) {
		this.pic_sync_status = pic_sync_status;
	}
	public int getProgress() {
		return progress;
	}
	public void setProgress(int progress) {
		this.progress = progress;
	}
	public static ArticlescrapEs ConvertToEs(Articlescrap articlescrap){
		if(articlescrap == null){
			return null;
		}
		ArticlescrapEs articlescrapEs = new ArticlescrapEs();
		articlescrapEs.setAuthor(articlescrap.getAuthor());
		articlescrapEs.setContent(articlescrap.getContent());
		articlescrapEs.setCreate_time(DateUtils.parseStringFromDate(articlescrap.getCreate_time()));
		articlescrapEs.setShow_time(DateUtils.parseStringFromDate(articlescrap.getShow_time()));
		articlescrapEs.setStatus(articlescrap.getStatus()==null?-1:articlescrap.getStatus().getKey());
		articlescrapEs.setSub_content(articlescrap.getSub_content());
		articlescrapEs.setTitle(articlescrap.getTitle());
		articlescrapEs.setType(Articlescrap_Type.transTo(articlescrap.getType()));
		articlescrapEs.setUpdate_time(DateUtils.parseStringFromDate(articlescrap.getUpdate_time()));
		articlescrapEs.setVisits(articlescrap.getVisits());
		articlescrapEs.setStart_time(articlescrap.getBegin_time());
		articlescrapEs.setKeywords(articlescrap.getKeywords());
		articlescrapEs.setTime_degree(articlescrap.getTime_degree()==null?-1:articlescrap.getTime_degree().getKey());
		if(articlescrap.getRecommend()!=null){
			articlescrapEs.setRecommend(articlescrap.getRecommend());
		}
		articlescrapEs.setUser_id(articlescrap.getUser_id());
		articlescrapEs.setPictures(articlescrap.getPictures());
		articlescrapEs.setPic_num(articlescrap.getPic_num());
		articlescrapEs.setDraft_id(articlescrap.getDraftId());
		articlescrapEs.setProgress(articlescrap.getProgress());
		articlescrapEs.setPic_sync_status(articlescrap.getPic_sync_status()==null?-1:articlescrap.getPic_sync_status().getKey());
		return articlescrapEs;
	}
	
	public static Articlescrap ConvertToVo(ArticlescrapEs articlescrapEs){
		if(articlescrapEs == null){
			return null;
		}
		Articlescrap articlescrap = new Articlescrap();
		articlescrap.setAuthor(articlescrapEs.getAuthor());
		articlescrap.setContent(articlescrapEs.getContent());
		articlescrap.setCreate_time(DateUtils.parseDateFromString(articlescrapEs.getCreate_time()));
		articlescrap.setShow_time(DateUtils.parseDateFromString(articlescrapEs.getShow_time()));
		articlescrap.setBegin_time(articlescrapEs.getStart_time());
		articlescrap.setStatus(articlescrapEs.getStatus()==-1?null:Articlescrap_Status.valueOf(articlescrapEs.getStatus()));
		articlescrap.setSub_content(articlescrapEs.getSub_content());
		articlescrap.setTitle(articlescrapEs.getTitle());
		articlescrap.setType(Articlescrap_Type.transFrom(articlescrapEs.getType()));
		articlescrap.setUpdate_time(DateUtils.parseDateFromString(articlescrapEs.getUpdate_time()));
		articlescrap.setVisits(articlescrapEs.getVisits());
		articlescrap.setRecommend(articlescrapEs.getRecommend());
		articlescrap.setKeywords(articlescrapEs.getKeywords());
		articlescrap.setTime_degree(articlescrapEs.getTime_degree()==-1?null:TIME_DEGREE.valueOf(articlescrapEs.getTime_degree()));
		articlescrap.setRecommend(articlescrapEs.getRecommend());
		articlescrap.setPictures(articlescrapEs.getPictures());
		articlescrap.setPic_num(articlescrapEs.getPic_num());
		articlescrap.setUser_id(articlescrapEs.getUser_id());
		articlescrap.setDraftId(articlescrapEs.getDraft_id());
		articlescrap.setProgress(articlescrapEs.getProgress());
		articlescrap.setPic_sync_status(articlescrapEs.getPic_sync_status()==-1?null:Pic_Sync_Status.valueOf(articlescrapEs.getPic_sync_status()));
		return articlescrap;
	}
	
}

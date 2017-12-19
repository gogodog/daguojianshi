package com.dgjs.model.dto.business;

import java.util.Date;

import com.dgjs.model.enums.Articlescrap_Status;
import com.dgjs.model.enums.Articlescrap_Type;
import com.dgjs.model.enums.Draft_Status;
import com.dgjs.model.enums.Pending_Status;
import com.dgjs.model.enums.Pic_Sync_Status;

public class PDraft {

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
	
	private Integer audit_user_id;//审核人id
	private Date audit_time;//审核时间
	private String audit_desc;//审核描述
	private Integer publish_user_id;//发布人id
	private Date publish_time;//发布时间
	private Integer visits;//访问基数
	private Date show_time;//显示时间
	private Pending_Status status;//审核状态
	private Pic_Sync_Status pic_sync_Status;//状态
	private int progress;//同步进度
	private String draft_id;//草稿id
	
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
	
	public Integer getAudit_user_id() {
		return audit_user_id;
	}
	public void setAudit_user_id(Integer audit_user_id) {
		this.audit_user_id = audit_user_id;
	}
	public Date getAudit_time() {
		return audit_time;
	}
	public void setAudit_time(Date audit_time) {
		this.audit_time = audit_time;
	}
	public String getAudit_desc() {
		return audit_desc;
	}
	public void setAudit_desc(String audit_desc) {
		this.audit_desc = audit_desc;
	}
	public Integer getPublish_user_id() {
		return publish_user_id;
	}
	public void setPublish_user_id(Integer publish_user_id) {
		this.publish_user_id = publish_user_id;
	}
	public Date getPublish_time() {
		return publish_time;
	}
	public void setPublish_time(Date publish_time) {
		this.publish_time = publish_time;
	}
	public Integer getVisits() {
		return visits;
	}
	public void setVisits(Integer visits) {
		this.visits = visits;
	}
	public Date getShow_time() {
		return show_time;
	}
	public void setShow_time(Date show_time) {
		this.show_time = show_time;
	}
	public Pending_Status getStatus() {
		return status;
	}
	public void setStatus(Pending_Status status) {
		this.status = status;
	}
	public Pic_Sync_Status getPic_sync_Status() {
		return pic_sync_Status;
	}
	public void setPic_sync_Status(Pic_Sync_Status pic_sync_Status) {
		this.pic_sync_Status = pic_sync_Status;
	}
	public int getProgress() {
		return progress;
	}
	public void setProgress(int progress) {
		this.progress = progress;
	}
	public String getDraft_id() {
		return draft_id;
	}
	public void setDraft_id(String draft_id) {
		this.draft_id = draft_id;
	}
	
	public static Articlescrap transToArticlescrap(Pending pending){
		if(pending == null){
			return null;
		}
		Articlescrap articlescrap = new Articlescrap();
		articlescrap.setAuthor(pending.getAuthor());
		articlescrap.setBegin_time(pending.getBegin_time());
		articlescrap.setContent(pending.getContent());
		articlescrap.setCreate_time(pending.getPublish_time());
		articlescrap.setKeywords(pending.getKeywords());
		articlescrap.setPic_num(pending.getPic_num());
		articlescrap.setPictures(pending.getPictures());
		articlescrap.setShow_time(pending.getShow_time());
		articlescrap.setStatus(Articlescrap_Status.INIT);
		articlescrap.setSub_content(pending.getSub_content());
		articlescrap.setTime_degree(pending.getTime_degree());
		articlescrap.setTitle(pending.getTitle());
		articlescrap.setType(pending.getType());
		articlescrap.setUpdate_time(new Date());
		articlescrap.setVisits(pending.getVisits()==null?null:pending.getVisits().longValue());
		articlescrap.setUser_id(pending.getUser_id());
		return articlescrap;
	}
}

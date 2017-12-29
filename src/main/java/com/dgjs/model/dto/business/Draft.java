package com.dgjs.model.dto.business;

import java.util.Date;

import com.dgjs.model.enums.Articlescrap_Status;
import com.dgjs.model.enums.Articlescrap_Type;
import com.dgjs.model.enums.Pending_Status;
import com.dgjs.model.enums.Pic_Sync_Status;

public class Draft extends StartTime{

	//基本信息
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
	private Pending_Status status;//审核状态
	private String showPic;//封面展示图片
	
	//审核后参数
	private Integer audit_user_id;//审核人id
	private Date audit_time;//审核时间
	private Integer publish_user_id;//发布人id
	private Date publish_time;//发布时间
	
	//是否审核发布过
    private boolean isHaveAudit;//是否审核过
    private boolean isHavePublish;//是否发不过
	
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
	public Pending_Status getStatus() {
		return status;
	}
	public void setStatus(Pending_Status status) {
		this.status = status;
	}
	public boolean isHaveAudit() {
		return isHaveAudit;
	}
	public void setHaveAudit(boolean isHaveAudit) {
		this.isHaveAudit = isHaveAudit;
	}
	public boolean isHavePublish() {
		return isHavePublish;
	}
	public void setHavePublish(boolean isHavePublish) {
		this.isHavePublish = isHavePublish;
	}
	public String getShowPic() {
		return showPic;
	}
	public void setShowPic(String showPic) {
		this.showPic = showPic;
	}
	public static Articlescrap transToArticlescrap(Draft draft,Long visits,Date showTime){
		if(draft == null){
			return null;
		}
		Articlescrap articlescrap = new Articlescrap();
		articlescrap.setAuthor(draft.getAuthor());
		articlescrap.setBegin_time(draft.getBegin_time());
		articlescrap.setContent(draft.getContent());
		articlescrap.setCreate_time(draft.getPublish_time());
		articlescrap.setKeywords(draft.getKeywords());
		articlescrap.setPic_num(draft.getPic_num());
		articlescrap.setPictures(draft.getPictures());
		articlescrap.setShow_time(showTime);
		articlescrap.setStatus(Articlescrap_Status.INIT);
		articlescrap.setSub_content(draft.getSub_content());
		articlescrap.setTime_degree(draft.getTime_degree());
		articlescrap.setTitle(draft.getTitle());
		articlescrap.setType(draft.getType());
		articlescrap.setUpdate_time(new Date());
		articlescrap.setVisits(visits);
		articlescrap.setUser_id(draft.getUser_id());
		articlescrap.setDraftId(draft.getId());
		articlescrap.setProgress(0);
		articlescrap.setPic_sync_status(Pic_Sync_Status.UNSYNC);
		return articlescrap;
	}
}

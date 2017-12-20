package com.dgjs.model.es;

import com.alibaba.fastjson.JSON;
import com.dgjs.model.dto.business.PDraft;
import com.dgjs.model.enums.Articlescrap_Type;
import com.dgjs.model.enums.Pending_Status;
import com.dgjs.model.enums.TIME_DEGREE;
import com.dgjs.utils.DateUtils;

public class PDraftEs implements java.io.Serializable{

	private static final long serialVersionUID = -160221286633493187L;
	
	//基本信息
	private String id;//id
	private String title;//标题
	private Integer type;//文章类型
	private String author;//作者(笔名)
	private String create_time;//创建时间
	private String update_time;//修改时间
	private String sub_content;//精简内容
	private String content;//文章内容
	private String[] keywords;//关键词（分类）
	private Integer start_time;//内容的起始时间
	private int time_degree;//起始时间精度
	private String[] pictures;//图片
    private int pic_num;//图片数量
    private Integer user_id;//用户id
    
    //审核后参数
	private Integer audit_user_id;//审核人id
    private String audit_time;//审核时间
    private Integer publish_user_id;//发布人id
    private String publish_time;//发布时间
    private int status;//状态
    
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
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
	public Integer getAudit_user_id() {
		return audit_user_id;
	}
	public void setAudit_user_id(Integer audit_user_id) {
		this.audit_user_id = audit_user_id;
	}
	public String getAudit_time() {
		return audit_time;
	}
	public void setAudit_time(String audit_time) {
		this.audit_time = audit_time;
	}
	public Integer getPublish_user_id() {
		return publish_user_id;
	}
	public void setPublish_user_id(Integer publish_user_id) {
		this.publish_user_id = publish_user_id;
	}
	public String getPublish_time() {
		return publish_time;
	}
	public void setPublish_time(String publish_time) {
		this.publish_time = publish_time;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
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
    
	public static PDraftEs ConvertToEs(PDraft draft){
		if(draft == null){
			return null;
		}
		PDraftEs draftEs = new PDraftEs();
		draftEs.setAudit_time(DateUtils.parseStringFromDate(draft.getAudit_time()));
		draftEs.setAudit_user_id(draft.getAudit_user_id());
		draftEs.setAuthor(draft.getAuthor());
		draftEs.setContent(draft.getContent());
		draftEs.setCreate_time(DateUtils.parseStringFromDate(draft.getCreate_time()));
		draftEs.setHaveAudit(draft.isHaveAudit());
		draftEs.setHavePublish(draft.isHavePublish());
		draftEs.setKeywords(draft.getKeywords());
		draftEs.setPic_num(draft.getPic_num());
		draftEs.setPictures(draft.getPictures());
		draftEs.setPublish_time(DateUtils.parseStringFromDate(draft.getPublish_time()));
		draftEs.setPublish_user_id(draft.getPublish_user_id());
		draftEs.setStart_time(draft.getBegin_time());
		draftEs.setStatus(draft.getStatus()==null?-1:draft.getStatus().getKey());
		draftEs.setSub_content(draft.getSub_content());
		draftEs.setTime_degree(draft.getTime_degree()==null?-1:draft.getTime_degree().getKey());
		draftEs.setTitle(draft.getTitle());
		draftEs.setType(Articlescrap_Type.transTo(draft.getType()));
		draftEs.setUpdate_time(DateUtils.parseStringFromDate(draft.getUpdate_time()));
		draftEs.setUser_id(draft.getUser_id());
		draftEs.setId(draft.getId());
		return draftEs;
	}
	
	public static PDraft ConvertToVo(PDraftEs draftEs){
		if(draftEs == null){
			return null;
		}
		PDraft draft = new PDraft();
		draft.setAudit_time(DateUtils.parseDateFromString(draftEs.getAudit_time()));
		draft.setAudit_user_id(draftEs.getAudit_user_id());
		draft.setAuthor(draftEs.getAuthor());
		draft.setBegin_time(draftEs.getStart_time());
		draft.setContent(draftEs.getContent());
		draft.setCreate_time(DateUtils.parseDateFromString(draftEs.getCreate_time()));
		draft.setHaveAudit(draftEs.isHaveAudit());
		draft.setHavePublish(draftEs.isHavePublish());
		draft.setId(draftEs.getId());
		draft.setKeywords(draftEs.getKeywords());
		draft.setPic_num(draftEs.getPic_num());
		draft.setPictures(draftEs.getPictures());
		draft.setPublish_time(DateUtils.parseDateFromString(draftEs.getPublish_time()));
		draft.setPublish_user_id(draftEs.getPublish_user_id());
		draft.setStatus(draftEs.getStatus()==-1?null:Pending_Status.valueOf(draftEs.getStatus()));
		draft.setSub_content(draftEs.getSub_content());
		draft.setTime_degree(draftEs.getTime_degree()==-1?null:TIME_DEGREE.valueOf(draftEs.getTime_degree()));
		draft.setTitle(draftEs.getTitle());
		draft.setType(Articlescrap_Type.transFrom(draftEs.getType()));
		draft.setUpdate_time(DateUtils.parseDateFromString(draftEs.getUpdate_time()));
		draft.setUser_id(draftEs.getUser_id());
		return draft;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}

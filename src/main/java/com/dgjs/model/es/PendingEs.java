package com.dgjs.model.es;

import com.alibaba.fastjson.JSON;
import com.dgjs.model.dto.business.Pending;
import com.dgjs.model.enums.Articlescrap_Type;
import com.dgjs.model.enums.Pending_Status;
import com.dgjs.model.enums.TIME_DEGREE;
import com.dgjs.utils.DateUtils;

public class PendingEs extends DraftEs{

	private static final long serialVersionUID = -3985553130345223504L;
	
	private Integer audit_user_id;//审核人id
    private String audit_time;//审核时间
    private String audit_desc;//审核描述
    private Integer publish_user_id;//发布人id
    private String publish_time;//发布时间
    private String create_time;//创建时间
    private String update_time;//修改时间
    private Integer visits;//访问量基数
    private String show_time;//显示时间
    private int status;//审核状态
    
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
	public String getPublish_time() {
		return publish_time;
	}
	public void setPublish_time(String publish_time) {
		this.publish_time = publish_time;
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
	public Integer getVisits() {
		return visits;
	}
	public void setVisits(Integer visits) {
		this.visits = visits;
	}
	public int getStatus() {
		return status;
	}
	
	public String getShow_time() {
		return show_time;
	}
	public void setShow_time(String show_time) {
		this.show_time = show_time;
	}
	public void setStatus(int status) {
		this.status = status;
	}
    
    public static PendingEs ConvertToEs(Pending pending){
    	if(pending==null){
    		return null;
    	}
    	PendingEs pendingEs = new PendingEs();
    	pendingEs.setAudit_desc(pending.getAudit_desc());
    	pendingEs.setAudit_time(DateUtils.parseStringFromDate(pending.getAudit_time()));
    	pendingEs.setAudit_user_id(pending.getUser_id());
    	pendingEs.setAuthor(pending.getAuthor());
    	pendingEs.setContent(pending.getContent());
    	pendingEs.setCreate_time(DateUtils.parseStringFromDate(pending.getCreate_time()));
    	pendingEs.setId(pending.getId());
    	pendingEs.setKeywords(pending.getKeywords());
    	pendingEs.setPic_num(pending.getPic_num());
    	pendingEs.setPictures(pending.getPictures());
    	pendingEs.setPublish_time(DateUtils.parseStringFromDate(pending.getPublish_time()));
    	pendingEs.setPublish_user_id(pending.getPublish_user_id());
    	pendingEs.setShow_time(DateUtils.parseStringFromDate(pending.getShow_time()));
    	pendingEs.setStart_time(pending.getBegin_time());
    	pendingEs.setStatus(Pending_Status.transTo(pending.getStatus()));
    	pendingEs.setSub_content(pending.getSub_content());
    	pendingEs.setTime_degree(pending.getTime_degree()==null?-1:pending.getTime_degree().getKey());
    	pendingEs.setTitle(pending.getTitle());
    	pendingEs.setType(Articlescrap_Type.transTo(pending.getType()));
    	pendingEs.setUpdate_time(DateUtils.parseStringFromDate(pending.getUpdate_time()));
    	pendingEs.setUser_id(pending.getUser_id());
    	pendingEs.setVisits(pending.getVisits());
    	return pendingEs;
    }
    
    public static Pending ConvertToVo(PendingEs pendingEs){
    	if(pendingEs==null){
    		return null;
    	}
    	Pending pending = new Pending();
    	pending.setAudit_desc(pendingEs.getAudit_desc());
    	pending.setAudit_time(DateUtils.parseDateFromString(pendingEs.getAudit_time()));
    	pending.setAudit_user_id(pendingEs.getAudit_user_id());
    	pending.setAuthor(pendingEs.getAuthor());
    	pending.setBegin_time(pendingEs.getStart_time());
    	pending.setContent(pendingEs.getContent());
    	pending.setCreate_time(DateUtils.parseDateFromString(pendingEs.getCreate_time()));
    	pending.setId(pendingEs.getId());
    	pending.setKeywords(pendingEs.getKeywords());
    	pending.setPic_num(pendingEs.getPic_num());
    	pending.setPictures(pendingEs.getPictures());
    	pending.setPublish_time(DateUtils.parseDateFromString(pendingEs.getPublish_time()));
    	pending.setPublish_user_id(pendingEs.getUser_id());
    	pending.setShow_time(DateUtils.parseDateFromString(pendingEs.getShow_time()));
    	pending.setStatus(Pending_Status.transFrom(pendingEs.getStatus()));
    	pending.setSub_content(pendingEs.getSub_content());
    	pending.setTime_degree(pendingEs.getTime_degree()==-1?null:TIME_DEGREE.valueOf(pendingEs.getTime_degree()));
    	pending.setTitle(pendingEs.getTitle());
    	pending.setType(Articlescrap_Type.transFrom(pendingEs.getType()));
    	pending.setUpdate_time(DateUtils.parseDateFromString(pendingEs.getUpdate_time()));
    	pending.setUser_id(pendingEs.getUser_id());
    	pending.setVisits(pendingEs.getVisits());
    	return pending;
    }
    
    @Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}

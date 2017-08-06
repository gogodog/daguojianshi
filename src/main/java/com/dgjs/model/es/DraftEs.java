package com.dgjs.model.es;

import com.alibaba.fastjson.JSON;
import com.dgjs.model.dto.business.Draft;
import com.dgjs.model.enums.Articlescrap_Type;
import com.dgjs.model.enums.TIME_DEGREE;
import com.dgjs.utils.DateUtils;

public class DraftEs implements java.io.Serializable{

	private static final long serialVersionUID = 5955355079548565076L;

	private String id;//id
	private String title;//标题
	private Integer type;//文章类型
	private String author;//作者
	private String create_time;//创建时间
	private String update_time;//修改时间
	private String sub_content;//精简内容
	private String content;//文章内容
	private String[] keywords;//关键词（分类）
	private Integer start_time;//内容的起始时间
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
    
	public static DraftEs ConvertToEs(Draft draft){
		if(draft == null){
			return null;
		}
		DraftEs draftEs = new DraftEs();
		draftEs.setAuthor(draft.getAuthor());
		draftEs.setContent(draft.getContent());
		draftEs.setCreate_time(DateUtils.parseStringFromDate(draft.getCreate_time()));
		draftEs.setKeywords(draft.getKeywords());
		draftEs.setPic_num(draft.getPic_num());
		draftEs.setPictures(draft.getPictures());
		draftEs.setStart_time(draft.getBegin_time());
		draftEs.setSub_content(draft.getSub_content());
		draftEs.setTime_degree(draft.getTime_degree()==null?-1:draft.getTime_degree().getKey());
		draftEs.setTitle(draft.getTitle());
		draftEs.setType(Articlescrap_Type.transTo(draft.getType()));
		draftEs.setUpdate_time(DateUtils.parseStringFromDate(draft.getUpdate_time()));
		return draftEs;
	}
	
	public static Draft ConvertToVo(DraftEs draftEs){
		if(draftEs == null){
			return null;
		}
		Draft draft = new Draft();
		draft.setAuthor(draftEs.getAuthor());
		draft.setContent(draftEs.getContent());
		draft.setCreate_time(DateUtils.parseDateFromString(draftEs.getCreate_time()));
		draft.setBegin_time(draftEs.getStart_time());
		draft.setSub_content(draftEs.getSub_content());
		draft.setTitle(draftEs.getTitle());
		draft.setType(Articlescrap_Type.transFrom(draftEs.getType()));
		draft.setUpdate_time(DateUtils.parseDateFromString(draftEs.getUpdate_time()));
		draft.setKeywords(draftEs.getKeywords());
		draft.setTime_degree(draftEs.getTime_degree()==-1?null:TIME_DEGREE.valueOf(draftEs.getTime_degree()));
		draft.setPictures(draftEs.getPictures());
		return draft;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
}

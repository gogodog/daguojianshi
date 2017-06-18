package com.dgjs.model.es;



import com.alibaba.fastjson.JSON;
import com.dgjs.model.enums.Articlescrap_Type;
import com.dgjs.model.enums.UpDown_Status;
import com.dgjs.model.persistence.Articlescrap;
import com.dgjs.model.persistence.Recommend;
import com.dgjs.utils.DateUtils;

public class ArticlescrapEs implements java.io.Serializable{

	private static final long serialVersionUID = 598908887547996139L;
	
	private Long id;//id
	private String title;//标题
	private String content;//文章内容
	private String show_time;//展示时间
	private int type;//文章类型
	private int status;//文章状态
	private String author;//作者
	private String create_time;//创建时间
	private String update_time;//修改时间
	private String sub_content;//精简内容
	private String show_picture;//展示图片
	private Long visits;//访问量
	private String start_time;//内容的起始时间
	private Recommend recommend;//推荐
	 
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
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
	public String getStart_time() {
		return start_time;
	}
	public Recommend getRecommend() {
		return recommend;
	}
	public void setRecommend(Recommend recommend) {
		this.recommend = recommend;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
	public static ArticlescrapEs ConvertToEs(Articlescrap articlescrap){
		if(articlescrap == null){
			return null;
		}
		ArticlescrapEs articlescrapEs = new ArticlescrapEs();
		articlescrapEs.setAuthor(articlescrap.getAuthor());
		articlescrapEs.setContent(articlescrap.getContent());
		articlescrapEs.setCreate_time(DateUtils.parseStringFromDate(articlescrap.getCreate_time()));
		articlescrapEs.setShow_picture(articlescrap.getShow_picture());
		articlescrapEs.setShow_time(DateUtils.parseStringFromDate(articlescrap.getShow_time()));
		articlescrapEs.setStatus(articlescrap.getStatus()==null?-1:articlescrap.getStatus().getKey());
		articlescrapEs.setSub_content(articlescrap.getSub_content());
		articlescrapEs.setTitle(articlescrap.getTitle());
		articlescrapEs.setType(articlescrap.getType()==null?-1:articlescrap.getType().getKey());
		articlescrapEs.setUpdate_time(DateUtils.parseStringFromDate(articlescrap.getUpdate_time()));
		articlescrapEs.setVisits(articlescrap.getVisits());
		articlescrapEs.setStart_time(articlescrap.getStart_time());
		Recommend recommend = new Recommend();
		recommend.setSort(-1);
		recommend.setStatus(-1);
		articlescrapEs.setRecommend(recommend);
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
		articlescrap.setShow_picture(articlescrapEs.getShow_picture());
		articlescrap.setShow_time(DateUtils.parseDateFromString(articlescrapEs.getShow_time()));
		articlescrap.setStart_time(articlescrapEs.getStart_time());
		articlescrap.setStatus(articlescrapEs.getStatus()==-1?null:UpDown_Status.valueOf(articlescrapEs.getStatus()));
		articlescrap.setSub_content(articlescrapEs.getSub_content());
		articlescrap.setTitle(articlescrapEs.getTitle());
		articlescrap.setType(articlescrapEs.getType()==-1?null:Articlescrap_Type.valueOf(articlescrapEs.getType()));
		articlescrap.setUpdate_time(DateUtils.parseDateFromString(articlescrapEs.getUpdate_time()));
		articlescrap.setVisits(articlescrapEs.getVisits());
		articlescrap.setRecommend(articlescrapEs.getRecommend());
		return articlescrap;
	}
	
}

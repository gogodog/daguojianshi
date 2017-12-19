package com.dgjs.model.es;

public class PDraftEs implements java.io.Serializable{

	private static final long serialVersionUID = -160221286633493187L;
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
    private Integer user_id;//用户id
    
    //审核后参数
	private Integer audit_user_id;//审核人id
    private String audit_time;//审核时间
    private String audit_desc;//审核描述
    private Integer publish_user_id;//发布人id
    private String publish_time;//发布时间
    private Integer visits;//访问量基数
    private String show_time;//显示时间
    private int status;//状态
    
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
	public Integer getVisits() {
		return visits;
	}
	public void setVisits(Integer visits) {
		this.visits = visits;
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
    
}

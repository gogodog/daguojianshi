package com.dgjs.model.dto.business;

import java.util.Date;
import java.util.Random;

import com.alibaba.fastjson.JSON;
import com.dgjs.model.dto.business.entity.Recommend;
import com.dgjs.model.enums.Articlescrap_Type;
import com.dgjs.model.enums.TIME_DEGREE;
import com.dgjs.model.enums.UpDown_Status;
import com.dgjs.utils.StringUtils;

public class Articlescrap {

	private String id;//esId
	private String title;//标题
	private String content;//文章内容
	private Date show_time;//展示时间
	private UpDown_Status status;//文章状态
	private String author;//作者
	private Date create_time;//创建时间
	private Date update_time;//修改时间
	private String sub_content;//精简内容
	private String show_picture;//展示图片
	private Long visits;//访问量
	private String start_time;//内容的起始时间
	private String[] keywords;//关键词（分类）
	private Articlescrap_Type type;//文章类型
	private Recommend recommend;//推荐信息
	
	private Integer begin_time;//起始时间
	private TIME_DEGREE time_degree;//起始时间精度
	
	private transient String start_time_c;//内容的起始时间
	private transient String start_time_y;//内容的起始时间
	private transient String start_time_m;//内容的起始时间
	private transient String start_time_d;//内容的起始时间
	
	
	public String getStart_time() {
		return getBeginTime();
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public Integer getBegin_time() {
		return begin_time;
	}
	public void setBegin_time(Integer begin_time) {
		this.begin_time = begin_time;
	}
	public TIME_DEGREE getTime_degree() {
		return time_degree;
	}
	public void setTime_degree(TIME_DEGREE time_degree) {
		this.time_degree = time_degree;
	}
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
	public Date getShow_time() {
		return show_time;
	}
	public void setShow_time(Date show_time) {
		this.show_time = show_time;
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
	public UpDown_Status getStatus() {
		return status;
	}
	public void setStatus(UpDown_Status status) {
		this.status = status;
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
	public Articlescrap_Type getType() {
		return type;
	}
	public void setType(Articlescrap_Type type) {
		this.type = type;
	}
	public void setTypeValue(Articlescrap_Type type) {
		this.type = type;
	}
	public String getTypeValue(){
		if(type!=null){
			return type.getValue();
		}
		return null;
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
	
	public void setBeginTime(){
		Random random = new Random();
		StringBuilder st = new StringBuilder();
		//如果有年份
		if(!StringUtils.isNullOrEmpty(start_time_c)&&!StringUtils.isNullOrEmpty(start_time_y)){
			//如果是公元前则是负数
			if("公元前".equals(start_time_c)){
				st.append("-");
			}
			//年
			st.append(start_time_y);
			time_degree=TIME_DEGREE.YEAR;
			//月
			if(StringUtils.isNullOrEmpty(start_time_m)){
				int month = random.nextInt(12)+1;
				st.append(month<10?"0"+month:month);//随机算月份
			}else{
				st.append(start_time_m);
				time_degree=TIME_DEGREE.MONTH;
			}
			//日
			if(StringUtils.isNullOrEmpty(start_time_d)){
				int day=random.nextInt(28)+1;//随机算月份
				st.append(day<10?"0"+day:day);//随机算月份
			}else{
				st.append(start_time_d);
				time_degree=TIME_DEGREE.DAY;
			}
			begin_time = Integer.valueOf(st.toString());
		}
	}
	
	public String getBeginTime(){
		StringBuilder st = new StringBuilder();
		if(begin_time!=null){
			String time=String.valueOf(begin_time);
			String day=time.substring(time.length()-2, time.length());
			String month=time.substring(time.length()-4, time.length()-2);
			String year=time.substring(0, time.length()-4);
			if(time_degree!=null){
				if(begin_time<0){
					st.append("公元前");
					start_time_c="公元前";
				}else{
					st.append("公元");
					start_time_c="公元";
				}
				st.append(year.replaceAll("-", "")+"年");
				start_time_y=year;
                if(time_degree==TIME_DEGREE.MONTH){				
                	st.append(month+"月");
                	start_time_m=month;
                }else if(time_degree==TIME_DEGREE.DAY){
                	st.append(month+"月");
                	st.append(day+"日");
                	start_time_d=day;
                }
			}
			start_time=st.toString();
		}
		return start_time;
	}
	
	
	public String getStart_time_c() {
		getBeginTime();
		return start_time_c;
	}
	public void setStart_time_c(String start_time_c) {
		this.start_time_c = start_time_c;
	}
	public String getStart_time_y() {
		return start_time_y;
	}
	public void setStart_time_y(String start_time_y) {
		this.start_time_y = start_time_y;
	}
	public String getStart_time_m() {
		return start_time_m;
	}
	public void setStart_time_m(String start_time_m) {
		this.start_time_m = start_time_m;
	}
	public String getStart_time_d() {
		return start_time_d;
	}
	public void setStart_time_d(String start_time_d) {
		this.start_time_d = start_time_d;
	}
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	
}

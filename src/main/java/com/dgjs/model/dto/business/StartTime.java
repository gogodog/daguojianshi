package com.dgjs.model.dto.business;

import java.util.Random;

import com.dgjs.model.enums.TIME_DEGREE;
import com.dgjs.utils.StringUtils;

public class StartTime {

	private transient String start_time_c;//内容的起始时间
	private transient String start_time_y;//内容的起始时间
	private transient String start_time_m;//内容的起始时间
	private transient String start_time_d;//内容的起始时间
	private Integer begin_time;//起始时间
	private TIME_DEGREE time_degree;//起始时间精度
	private String start_time;//内容的起始时间
	
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
				st.append(start_time_m.length()<2?"0"+start_time_m:start_time_m);
				time_degree=TIME_DEGREE.MONTH;
			}
			//日
			if(StringUtils.isNullOrEmpty(start_time_d)){
				int day=random.nextInt(28)+1;//随机算月份
				st.append(day<10?"0"+day:day);//随机算月份
			}else{
				st.append(start_time_d.length()<2?"0"+start_time_d:start_time_d);
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
				start_time_y=year.replaceAll("-", "");
                if(time_degree==TIME_DEGREE.MONTH){				
                	st.append(month+"月");
                	start_time_m=month;
                }else if(time_degree==TIME_DEGREE.DAY){
                	st.append(month+"月");
                	st.append(day+"日");
                	start_time_m=month;
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

	public String getStart_time() {
		return getBeginTime();
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	
	public Integer getYear(){
		return begin_time==null?null:begin_time/10000;
	}
}

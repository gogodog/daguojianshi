package com.dgjs.model.persistence;

import java.util.Date;

import com.dgjs.model.enums.OperateEnum;

public class OperateLog {

	private Long id;//id
	private Integer admin_id;//操作人id
	private OperateEnum operate_type;//操作类型
	private String operate_desc;//操作描述
	private Date operate_time;//操作时间
	private String ip;//ip
	private String param;//入参
	private int isSuccess;//操作是否执行成功 1:成功 0:失败
	private String errorMessage;//如果出错了，错误信息
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(Integer admin_id) {
		this.admin_id = admin_id;
	}
	public OperateEnum getOperate_type() {
		return operate_type;
	}
	public void setOperate_type(OperateEnum operate_type) {
		this.operate_type = operate_type;
	}
	public String getOperate_desc() {
		return operate_desc;
	}
	public void setOperate_desc(String operate_desc) {
		this.operate_desc = operate_desc;
	}
	public Date getOperate_time() {
		return operate_time;
	}
	public void setOperate_time(Date operate_time) {
		this.operate_time = operate_time;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public int getIsSuccess() {
		return isSuccess;
	}
	public void setIsSuccess(int isSuccess) {
		this.isSuccess = isSuccess;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}

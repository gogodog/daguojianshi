package com.dgjs.model.persistence;

import java.util.Date;

public class Role {

	private Integer id;//角色id
	private String role_name;//角色名称
	private String role_code;//角色编号（角色层级关系）
	private int deep;//角色深度
	private Date create_time;//创建时间
	private Date update_time;//修改时间
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
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
	public String getRole_code() {
		return role_code;
	}
	public void setRole_code(String role_code) {
		this.role_code = role_code;
	}
	public int getDeep() {
		return deep;
	}
	public void setDeep(int deep) {
		this.deep = deep;
	}
	public String getParentRoleCode(){
		if(role_code==null){
			return null;
		}
		String[] values=role_code.split("_");
		if(values.length==1){
			return null;
		}
		StringBuilder parentRoleCode = new StringBuilder();
		for(int i=0;i<values.length-1;i++){
			String value = values[i];
			parentRoleCode.append(value);
			parentRoleCode.append("_");
		}
		return parentRoleCode.toString();
	}
	
}

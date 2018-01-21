package com.dgjs.model.persistence;

import java.util.Date;

import org.springframework.util.StringUtils;

public class AdminUserInfo {

	private Integer id;//id同AdminUser
	private String real_name;//姓名
	private int sex;//性别  1:男 2:女
	private int age;//年龄
	private String organization;//组织
	private String email;//邮件
	private String mobile;//电话
	private String address;//地址
	private Date create_time;//创建时间
	private Date update_time;//修改时间
	private String user_code;//code
	
	private String province;//省份
	private String city;//城市
	private String country;//国家
	private String headimgurl;//头像
	
	
	public String getReal_name() {
		return real_name;
	}
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public String getUser_code() {
		return user_code;
	}
	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}
	/*
	 * 是否有编辑文章权限
	 */
	public boolean isCanEditArticle(){
		if(StringUtils.isEmpty(real_name)){
			return false;
		}
		if(sex==0){
			return false;
		}
		if(age<1){
			return false;
		}
		if(StringUtils.isEmpty(email)){
			return false;
		}
		if(StringUtils.isEmpty(mobile)){
			return false;
		}
		if(StringUtils.isEmpty(address)){
			return false;
		}
		return true;
	}
}

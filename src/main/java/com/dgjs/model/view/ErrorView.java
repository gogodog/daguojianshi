package com.dgjs.model.view;

public class ErrorView extends BaseView{
	String title;
	String problem;
	String exmsg;
	String tips;
	String callback;
	String callbakcbtn = "返 回";
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getProblem() {
		return problem;
	}
	public void setProblem(String problem) {
		this.problem = problem;
	}
	public String getExmsg() {
		return exmsg;
	}
	public void setExmsg(String exmsg) {
		this.exmsg = exmsg;
	}
	public String getTips() {
		return tips;
	}
	public void setTips(String tips) {
		this.tips = tips;
	}
	public String getCallback() {
		return callback;
	}
	public void setCallback(String callback) {
		this.callback = callback;
	}
	public String getCallbakcbtn() {
		return callbakcbtn;
	}
	public void setCallbakcbtn(String callbakcbtn) {
		this.callbakcbtn = callbakcbtn;
	}
}

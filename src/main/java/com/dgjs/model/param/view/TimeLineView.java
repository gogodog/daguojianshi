package com.dgjs.model.param.view;

import com.dgjs.model.enums.Articlescrap_Type;

public class TimeLineView {

	private Articlescrap_Type type;
	private String keyword;
	private String articlescrapId;
	private Boolean isNext;
	private Boolean isSlip;
	private String keyWord;
	
	public Articlescrap_Type getType() {
		return type;
	}
	public void setType(Articlescrap_Type type) {
		this.type = type;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getArticlescrapId() {
		return articlescrapId;
	}
	public void setArticlescrapId(String articlescrapId) {
		this.articlescrapId = articlescrapId;
	}
	public Boolean getIsNext() {
		return isNext;
	}
	public void setIsNext(Boolean isNext) {
		this.isNext = isNext;
	}
	public Boolean getIsSlip() {
		return isSlip;
	}
	public void setIsSlip(Boolean isSlip) {
		this.isSlip = isSlip;
	}
	public String getKeyWord() {
		return keyWord;
	}
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	
}

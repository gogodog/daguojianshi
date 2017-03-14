package com.dgjs.model.persistence;

import com.dgjs.model.enums.UpDown_Status;

public class RecommedArticlescrap {

	private Long id;//推荐文章id
	private Long articlescrap_id;//文章id
	private Integer sort;//排序
	private UpDown_Status status;//状态
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getArticlescrap_id() {
		return articlescrap_id;
	}
	public void setArticlescrap_id(Long articlescrap_id) {
		this.articlescrap_id = articlescrap_id;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public UpDown_Status getStatus() {
		return status;
	}
	public void setStatus(UpDown_Status status) {
		this.status = status;
	}
}

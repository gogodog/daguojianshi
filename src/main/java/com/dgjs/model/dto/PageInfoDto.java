package com.dgjs.model.dto;

import java.util.List;

public class PageInfoDto<T> {
	
     private int currentPage;
	 
	 private int onePageSize;
	        
	 private int totalResults;
	        
	 private int totalPage;
	        
	 private List<T> objects;

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getOnePageSize() {
		return onePageSize;
	}

	public void setOnePageSize(int onePageSize) {
		this.onePageSize = onePageSize;
	}

	public int getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getObjects() {
		return objects;
	}

	public void setObjects(List<T> objects) {
		this.objects = objects;
	}
	
	public static int getTotalPage(int totalResults,int onePageSize){
		double totalPage=(double)totalResults/onePageSize;
    	return (int) Math.ceil(totalPage);
    }
	
	//数据库第一条数据
	public int getBeginNum(){
		return (currentPage - 1) * onePageSize;
	}
	
	
	public static <T> PageInfoDto<T> getPageInfo(int currentPage,int onePageSize,int totalResults,List<T> objects){
   	    PageInfoDto<T> pageInfo=new PageInfoDto<T>();
   	    pageInfo.setCurrentPage(currentPage);
   	    pageInfo.setOnePageSize(onePageSize);
   	    pageInfo.setTotalResults(totalResults);
   	    pageInfo.setTotalPage(getTotalPage(totalResults,onePageSize));
   	    pageInfo.setObjects(objects);
   	    return pageInfo;
    }
	
}

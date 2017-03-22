package com.dgjs.utils;

import java.util.List;

import com.dgjs.model.dto.PageInfoDto;


public class PageUtils {
     
     public static int getBeginNum(int currentPage,int onePageSize){
    	 return (currentPage-1)*onePageSize;
     }
     
     public static int getTotalPage(int totalResults,int onePageSize){
    	return (int) Math.ceil(totalResults/onePageSize);
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

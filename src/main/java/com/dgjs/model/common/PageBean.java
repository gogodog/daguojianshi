package com.dgjs.model.common;

public class PageBean {  
    private int curPage=1;             //当前页  
    private int rowsCount;           //总行数  
    private int pageSize=3;         //每页多少行 
    
    public int getCurPage() {
        return curPage;  
    }  
    public void setCurPage(int curPage) {  
        this.curPage = curPage;  
    }  
    public int getPageCount() {
    	if(this.rowsCount % this.pageSize == 0){  
    		return this.rowsCount / this.pageSize;  
        }else if(this.rowsCount<this.pageSize){  
        	return 1;  
        }else{  
        	return this.rowsCount / this.pageSize +1;  
        }
    } 
    public int getPageSize() {  
        return pageSize;  
    }  
    public void setPageSize(int pageSize) {  
        this.pageSize = pageSize;  
    }  
    public int getRowsCount() {  
        return rowsCount;  
    }  
    public void setRowsCount(int rowsCount) {  
        this.rowsCount = rowsCount;  
    }  
}
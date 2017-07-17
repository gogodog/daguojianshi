package com.dgjs.utils;

import java.util.LinkedList;
import java.util.List;


public class ArticlescrapUtils {

	public static String[] first_level = {"keywords"};//第一优先级
	
	public static String[] second_level = {"title","sub_content"};//第二优先级
	
	public static String[] third_level = {"content"};//第二优先级

	private  static List<String[]> list = new LinkedList<String[]>();
	
    static{
    	list.add(first_level);
    	list.add(second_level);
    	list.add(third_level);
    }
    
    public static String[] getSearchFields(int level){
    	if(level>list.size()-1){
    		return null;
    	}
    	return list.get(level);
    }
    
    public static int getLevelSize(){
    	return list.size();
    }
}


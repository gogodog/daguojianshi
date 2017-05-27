package com.dgjs.utils;

public class StringUtils extends com.mysql.jdbc.StringUtils{

	public static String combineStr(String[] strs,String blank){
		if(strs==null||strs.length==0){
			return null;
		}
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<strs.length;i++){
			sb.append(strs[i]);
			if(i!=strs.length-1&&blank!=null){
				sb.append(blank);
			}
		}
		return sb.toString();
	}
	
	public static String jointString(String ... values){
		StringBuilder str=new StringBuilder();
		for(String value:values){
			str.append(value);
		}
		return str.toString();
	}
	
	public static Integer parseInt(String str,Integer defaultValue){
		try{
			return Integer.parseInt(str);
		}catch(Exception e){
		}
		return defaultValue;
	}
	
	public static Integer parseInt(String str){
		return parseInt(str,null);
	}
}

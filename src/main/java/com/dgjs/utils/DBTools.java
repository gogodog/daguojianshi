package com.dgjs.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBTools {
	
	//============================= 常量定义区 begin =============================
	
	//数据库连接等基本信息
	String user = "root";  
    String password = "622826mysql";  
    String jdbcDriver = "com.mysql.jdbc.Driver";  
    String jdbcUrl = "jdbc:mysql://121.42.143.166:3306/test-dgjs?useUnicode=true&characterEncoding=UTF-8&connectTimeout=60000&socketTimeout=60000";  
    
    //persistence信息
    String tableName = "comments";
    
    //mapper.xml信息
    String tableIdName = "id";
    String fullMapperName = "com.dgjs.mapper.content.CommentsMapper";
    
    //dao信息
    String daoName = "CommentsMapper";
    
    //输出路径
    String persistencePath="/Users/user/Documents/workspaces/elong_workspace_b170327/daguojianshi/src/main/java/com/dgjs/model/persistence/Comments.java";
    String mapperXmlPath="/Users/user/Documents/workspaces/elong_workspace_b170327/daguojianshi/src/main/resources/mybatisMapper/content/comments.xml";
    String daoJavaPath="/Users/user/Documents/workspaces/elong_workspace_b170327/daguojianshi/src/main/java/com/dgjs/mapper/content/CommentsMapper.java";
    //=============================  常量定义区 end   ============================
    Connection conn = null; 
    
    public DBTools() throws Exception{
    	Class.forName(jdbcDriver);  
	    conn = DriverManager.getConnection(jdbcUrl, user, password);  
    }
    
    public String getPersistence() throws Exception{
    	String firstLetter = tableName.substring(0, 1).toUpperCase();
		String afterLetter =tableName.substring(1, tableName.length());
		String persistenceName = firstLetter+afterLetter;
		StringBuilder str = new StringBuilder();
		tableName.substring(1);
		str.append("public class "+persistenceName+"{ \n\n");
    	Statement stmt = conn.createStatement();  
	    ResultSet rs = stmt.executeQuery("show full columns from " + tableName);  
	            while (rs.next()){
	            	str.append("       private ");
	            	String type = rs.getString("Type");
	            	if(type.startsWith("bigint")){
	            		str.append("Long ");
	            	}else if(type.startsWith("varchar")){
	            		str.append("String ");
	            	}else if(type.startsWith("tinyint")){
	            		str.append("Integer ");
	            	}else if(type.startsWith("datetime")){
	            		str.append("Date ");
	            	}else{
	            		throw new Exception ("未知的数据类型"+type);
	            	}
	            	str.append(rs.getString("Field")+"; //"+rs.getString("Comment")+"\n");
	            }
	    str.append("\n}");
	    stmt.close();  
        return str.toString();
    }
	
    public String getMapperXml() throws Exception{
    	Statement stmt = conn.createStatement();  
	    ResultSet rs = stmt.executeQuery("show full columns from " + tableName); 
	    List<String> fieldList = new ArrayList<String>();
	    List<String> stringFieldList =  new ArrayList<String>();
	    while (rs.next()){
	    	if(rs.getString("Field").equals(tableIdName)){
	    		continue;
	    	}
	    	String field = rs.getString("Field");
	    	if(rs.getString("Type").startsWith("varchar")){
	    		stringFieldList.add(field);
	    	}
	    	fieldList.add(field);
        }
	    
    	StringBuilder str = new StringBuilder();
    	str.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
    	str.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n");
    	str.append("<mapper namespace=\""+fullMapperName+"\">\n\n");
    	getInsertXml(str,fieldList);
    	getUpdateXml(str,fieldList,stringFieldList);
    	getDeleteXml(str);
    	getSelectByIdXml(str,fieldList);
    	getListXml(str,fieldList);
    	str.append("</mapper>");
    	return str.toString();
//    	System.out.println(str);
    }
    
    public String getDaoJava(){
    	String firstLetter = tableName.substring(0, 1).toUpperCase();
		String afterLetter =tableName.substring(1, tableName.length());
		String persistenceName = firstLetter+afterLetter;
    	StringBuilder str = new StringBuilder();
    	str.append("public interface "+daoName+" {");
    	str.append("\n\n	public int save("+persistenceName+" "+tableName+");");
    	str.append("\n\n	public int update("+persistenceName+" "+tableName+");");
    	str.append("\n\n	public "+persistenceName+" selectById(Long "+tableIdName+");");
    	str.append("\n\n	public List<"+persistenceName+"> list();");
    	str.append("\n\n	public int deleteById(Long "+tableIdName+");");
    	str.append("\n\n}");
//    	System.out.println(str);
    	return str.toString();
    }
    
    public void getUpdateXml(StringBuilder str,List<String> fieldList,List<String> stringFieldList){
    	str.append("    <update id=\"update\">\n");
    	str.append("	 update "+tableName+" set\n");
    	for(int i=0;i<fieldList.size();i++){
    		String fieldName = fieldList.get(i);
    		if(stringFieldList.contains(fieldName)){
    			str.append("	 <if test=\""+fieldName+"!=null and "+fieldName+"!=''\">\n");
    		}else{
    			str.append("	 <if test=\""+fieldName+"!=null\">\n");
    		}
    		str.append("	       "+fieldName+"=#{"+fieldName+"},\n");
    		str.append("	 </if>\n");
    	}
    	str.append("         update_time=now() where "+tableIdName+"=#{"+tableIdName+"}\n");
    	str.append("   </update>\n\n");
    }
    
    public void getDeleteXml(StringBuilder str){
    	str.append("   <delete id=\"deleteById\">\n");
    	str.append("         delete from "+tableName+" where "+tableIdName+" = #{id}\n");
    	str.append("   </delete>\n\n");
    }
    
    public void getSelectByIdXml(StringBuilder str,List<String> fieldList){
    	str.append("   <select id=\"selectById\" resultType=\""+tableName+"\">\n");
    	str.append("         select\n");
    	str.append("              "+tableIdName+",\n");
    	for(int i=0;i<fieldList.size();i++){
    		if(i == fieldList.size()-1){
    			str.append("              "+fieldList.get(i)+"\n");
    		}else{
    		    str.append("              "+fieldList.get(i)+",\n");
    		}
    	}
    	str.append("         from "+tableName);
    	str.append("\n   </select>\n\n");
    }
    
    public void getInsertXml(StringBuilder str,List<String> fieldList){
    	str.append("    <insert id=\"save\" parameterType=\""+tableName+"\" keyProperty=\""+tableIdName+"\" useGeneratedKeys=\"true\">\n");
    	str.append("	  insert into "+tableName+" \n");
    	str.append("	  (\n");
    	for(int i=0;i<fieldList.size();i++){
    		if(i == fieldList.size()-1){
    			str.append("               "+fieldList.get(i)+"\n");
    		}else{
    			str.append("               "+fieldList.get(i)+",\n");
    		}
    	}
    	str.append("	  )\n");
    	str.append("	  values\n");
    	str.append("	  (\n");
    	for(int i=0;i<fieldList.size();i++){
    		String fieldName =fieldList.get(i);
    		if(i == fieldList.size()-1){
    			if("create_time".equals(fieldName)||"update_time".equals(fieldName)||"modify_time".equals(fieldName)){
    				str.append("               now()\n");
        		}else{
        			str.append("               #{"+fieldName+"}\n");
        		}
    		}else{
    			if("create_time".equals(fieldName)||"update_time".equals(fieldName)||"modify_time".equals(fieldName)){
    				str.append("               now(),\n");
        		}else{
        			str.append("               #{"+fieldName+"},\n");
        		}
    		}
    	}
    	str.append("	  )\n");
    	str.append("   </insert>\n\n");
    }
    
    public void getListXml(StringBuilder str,List<String> fieldList){
    	str.append("   <select id=\"list\" resultType=\""+tableName+"\">\n");
    	str.append("         select\n");
    	str.append("              "+tableIdName+",\n");
    	for(int i=0;i<fieldList.size();i++){
    		if(i == fieldList.size()-1){
    			str.append("              "+fieldList.get(i)+"\n");
    		}else{
    		    str.append("              "+fieldList.get(i)+",\n");
    		}
    	}
    	str.append("         from "+tableName);
    	str.append("\n   </select>\n\n");
    }
    
    public void writeToSystemFile(String path,String content) throws Exception{
        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        } 
    	FileOutputStream out = new FileOutputStream(file);   
    	out.write(content.getBytes("UTF8"));
    	out.close();
    }
    
    public void generateAndWrite() throws Exception{
    	//persistence写入
    	writeToSystemFile(persistencePath,getPersistence());
    	System.out.println("persistence写入成功");
    	//mapper xml写入
    	writeToSystemFile(mapperXmlPath,getMapperXml());
    	System.out.println("mapper xml写入成功");
    	//dao写入
    	writeToSystemFile(daoJavaPath,getDaoJava());
    	System.out.println("dao写入成功");
    	conn.close();
    }
    
	 public static void main(String[] args) throws Exception{
		 DBTools t = new DBTools();
		 t.generateAndWrite();
	 }  
	 
}

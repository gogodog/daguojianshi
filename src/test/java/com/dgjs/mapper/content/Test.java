package com.dgjs.mapper.content;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.dgjs.interceptors.LoginInterceptor;

public class Test {

	private List<String> initMenu() throws DocumentException{
		    List<String> list = new ArrayList<String>();
			SAXReader reader = new SAXReader(); // 解析的xml文档
			InputStream is = LoginInterceptor.class.getClassLoader().getResourceAsStream("soa-config.xml");
			Document doc = reader.read(is);
			Element root = doc.getRootElement(); // 获取根节点
			Iterator<Element> it = root.elementIterator("ServiceConfigItem");
			while (it.hasNext()) {
				 Element serviceConfigItem = it.next();
				 Iterator<Element> online = serviceConfigItem.elementIterator("online");
				 Element onlineElement=online.next();
				 Iterator<Element> method = onlineElement.elementIterator("method");
				 Element e =  method.next();
				 list.add(e.getStringValue());
			}
			return list;
	}
	
	private List<String> getClassField() throws IllegalArgumentException, IllegalAccessException {
//		MapiConstant m = new MapiConstant();
//		Field[] fields = MapiConstant.class.getDeclaredFields();
		List<String> list = new ArrayList<String>();
//		for(Field field:fields){
//			String value = (String) field.get(m);
//			list.add(value);
//		}
		return list;
	}
	
	private void compare() throws Exception{
		List<String> xml =initMenu();
		List<String> constants = getClassField();
		for(String method:xml){
			if(!constants.contains(method)){
				System.out.println(method);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		Test t= new Test();
		t.compare();
	}
}

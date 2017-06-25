package com.dgjs.model.enums;

import java.util.ArrayList;
import java.util.List;


/**
 * 文章分类
 */
public enum Articlescrap_Type {

	HISTORY(10,"正史"),
	
	PERSON(20,"人物"),
	
	GEOGRAPHY(30,"地理"),
	
	NATION_CULTURE(40,"民族文化"),
	
	AFFAIRS(50,"时事"),
	
	UNOFFICIAL(60,"野史"),
	
	MYTH_LEGEND(70,"神话传说");
	
	
	private Articlescrap_Type(int key,String value){
		this.key=key;
		this.value=value;
	}
	
    private int key;
	
	private String value;

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public static Articlescrap_Type valueOf(int key){
		 switch (key) {
        case 10:
            return HISTORY;
        case 20:
            return PERSON;
        case 30:
        	return GEOGRAPHY;
        case 40:
        	return NATION_CULTURE;	
        case 50:
        	return AFFAIRS;
        case 60:
        	return UNOFFICIAL;
        case 70:
        	return MYTH_LEGEND;
        default:
            return null;
	  }
    }
	
	public static <T> List<Articlescrap_Type> transFrom(List<T> keys){
		if(keys==null||keys.size()==0){
			return null;
		}
		List<Articlescrap_Type> list = new ArrayList<Articlescrap_Type>();
		for(T key:keys){
			int formatKey=(int)key;
			list.add(Articlescrap_Type.valueOf(formatKey));
		}
		return list;
	}
	
	public static List<Integer> transTo(List<Articlescrap_Type> types){
		if(types==null||types.size()==0){
			return null;
		}
		List<Integer> list = new ArrayList<Integer>();
		for(Articlescrap_Type type:types){
			list.add(type.getKey());
		}
		return list;
	}

}

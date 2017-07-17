package com.dgjs.model.enums;


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
	
	WAR(80,"战争"),
	
	SCIENCE(90,"科技"),
	
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
	
	public static Articlescrap_Type transFrom(Integer key){
		if(key == null){
			return null;
		}
		return Articlescrap_Type.valueOf(key);
	}
	
	public static Integer transTo(Articlescrap_Type type){
		if(type==null){
			return null;
		}
		return type.getKey();
	}

}

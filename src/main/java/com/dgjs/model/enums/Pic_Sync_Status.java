package com.dgjs.model.enums;

public enum Pic_Sync_Status {

	UNSYNC(0,"未同步"),
	
	SYNCHING(1,"同步中"),
	
	SYNCHRONIZED(2,"同步完成");
	
	private int key;
	private String value;
	
	private Pic_Sync_Status(int key,String value){
		this.key=key;
		this.value=value;
	}

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
	
	public static Pic_Sync_Status valueOf(int key){
		switch (key) {
		case 0:
			return UNSYNC;
		case 1:
			return SYNCHING;
		case 2:
			return SYNCHRONIZED;
		default:
			break;
		}
		return null;
	}
	
	public static Pic_Sync_Status transFrom(Integer key){
		if(key == null){
			return null;
		}
		return Pic_Sync_Status.valueOf(key);
	}
	
	public static Integer transTo(Pic_Sync_Status type){
		if(type==null){
			return null;
		}
		return type.getKey();
	}
}

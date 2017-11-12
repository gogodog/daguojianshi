package com.dgjs.constants;

import java.util.HashMap;
import java.util.Map;

public class EventCode {

	public static final int AUDIT_NOTICE = 110;//审核通知
	
	public static final int PUBLISH_NOTICE = 120;//发布通知
	
	public static final Map<Integer,String> map = new HashMap<Integer,String>();//发布通知
	
	static{
		map.put(AUDIT_NOTICE, "");
		map.put(PUBLISH_NOTICE, "您的");
	}
	
}

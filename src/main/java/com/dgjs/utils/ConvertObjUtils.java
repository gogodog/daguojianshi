package com.dgjs.utils;

import java.util.Set;

import com.alibaba.fastjson.JSONObject;

public class ConvertObjUtils {
	public static String serializBean(Object bean){
		JSONObject jBean = (JSONObject) JSONObject.toJSON(bean);
		Set<String> keys = jBean.keySet();
		String serializ = "";
		for (String key : keys) {
			serializ += ("&"+key+"="+jBean.get(key));
		}
		return serializ;
	}
	
}

package com.dgjs.utils;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.dgjs.model.common.ErrorMapModel;
import com.dgjs.utils.XmlUtils;

/**
 * 异常映射
 * 
 * 单类懒汉加载（实现同步）
 * @ClassName: ErrorMap 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author cott.wen
 * @date 2017年7月19日 上午11:50:24 
 *
 */
public class ErrorMap {
	private static final String ERR_FILEPATH = "src/main/resources/error.xml";
	
	private static class LazyHolder {
		private static final ErrorMap INSTANCE = new ErrorMap();
	}

	private ErrorMap() {}
	
	static{
		//解析error.xml
		@SuppressWarnings("unchecked")
		List<ErrorMapModel> s = (List<ErrorMapModel>)XmlUtils.convertXmlFileToObject(ErrorMapModel.class, ErrorMap.ERR_FILEPATH);
		System.out.println(JSON.toJSONString(s));
	}

	public static final ErrorMap getInstance() {
		return LazyHolder.INSTANCE;
	}
}

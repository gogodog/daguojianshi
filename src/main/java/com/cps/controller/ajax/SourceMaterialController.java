package com.cps.controller.ajax;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 异步请求接口
 * @author jiadong.wen
 *
 */
@Controller
@RequestMapping("/sapi")
public class SourceMaterialController {
	
	/**
	 * 获取用户素材
	 * 返回值格式示例 [{address,name}]
	 * @param request
	 * @param tokken
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/sc")
    public JSONArray sc(HttpServletRequest request,String tokken) throws Exception {
		/**
		 * select sc list by userId
		 * UserId form cookie
		 */
		JSONArray jsa = new JSONArray();
		JSONObject jso1 = new JSONObject();
		jso1.put("address", "");
		jso1.put("name", "");
		jsa.add(jso1);
		JSONObject jso2 = new JSONObject();
		jso2.put("address", "");
		jso2.put("name", "");
		jsa.add(jso2);
		JSONObject jso3 = new JSONObject();
		jso3.put("address", "");
		jso3.put("name", "");
		jsa.add(jso3);
		
		return jsa;
    }
}

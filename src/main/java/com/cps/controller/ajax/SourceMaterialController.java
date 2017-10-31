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
    public String sc(HttpServletRequest request,String tokken) throws Exception {
		/**
		 * select sc list by userId
		 * UserId form cookie
		 */
		JSONArray jsa = new JSONArray();
		JSONObject jso1 = new JSONObject();
		jso1.put("address", "http://img03.sogoucdn.com/app/a/100520024/94cd9e93e48645c63a27da66a21d892c");
		jso1.put("name", "MM-1");
		jsa.add(jso1);
		JSONObject jso2 = new JSONObject();
		jso2.put("address", "http://img3.imgtn.bdimg.com/it/u=1417849734,4110330925&fm=27&gp=0.jpg");
		jso2.put("name", "MM-2");
		jsa.add(jso2);
		JSONObject jso3 = new JSONObject();
		jso3.put("address", "http://img01.sogoucdn.com/app/a/100520024/eafa42fcf0ad208708365fdbaafb0f17");
		jso3.put("name", "MM-3");
		jsa.add(jso3);
		JSONObject jso4 = new JSONObject();
		jso4.put("address", "http://img01.sogoucdn.com/app/a/100520024/eafa42fcf0ad208708365fdbaafb0f17");
		jso4.put("name", "MM-4");
		jsa.add(jso4);
		
		return jsa.toJSONString();
    }
}

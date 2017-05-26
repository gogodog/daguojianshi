package com.dgjs.service.impl.common;

import java.util.LinkedList;
import java.util.Queue;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dgjs.model.view.DadianView;
import com.dgjs.service.common.DataService;
import com.dgjs.utils.IPUtils;
import com.dgjs.utils.MacUtils;
import com.dgjs.utils.StringUtils;

@Service
public class DataServiceImpl implements DataService{
	
	private static final Queue<DadianView> QUEUE = new LinkedList<DadianView>();

	@Override
	public boolean dadian(HttpServletRequest request, String dadian) {
		DadianView dadianView = JSON.parseObject(dadian, DadianView.class);
		String ip = IPUtils.getIpAddr(request);
		MacUtils mac = new MacUtils(ip);
		dadianView.setMAC(mac.getMac());
		dadianView.setIp(ip);
		return DataServiceImpl.QUEUE.offer(dadianView);
	}

	public static Queue<DadianView> getQueue() {
		return QUEUE;
	}

	@Override
	public JSONObject getDocShowCounts(String docids) {
		JSONObject json = new JSONObject();
		if(StringUtils.isNullOrEmpty(docids)){
			return json;
		}
		// TODO 访问数据库 如下为测试数据
		String[] ids = docids.split(DataService.IDSSEPORTE);
		for(int i = 0 ; i<ids.length ; i++){
			json.put(ids[i], 100*i);
		}
		return json;
	}
}

package com.dgjs.service.impl.common;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dgjs.job.DadianThread;
import com.dgjs.mapper.common.DadianMapper;
import com.dgjs.model.view.DadianView;
import com.dgjs.service.common.DataService;
import com.dgjs.utils.IPUtils;
import com.dgjs.utils.MacUtils;
import com.dgjs.utils.StringUtils;

@Service
public class DataServiceImpl implements DataService{
	
	@Autowired
	DadianMapper dadianMapper;
	
	@Override
	public boolean dadian(HttpServletRequest request, String dadian) {
		DadianView dadianView = JSON.parseObject(dadian, DadianView.class);
		String ip = IPUtils.getIpAddr(request);
		MacUtils mac = new MacUtils(ip);
		dadianView.setMac(mac.getMac());
		dadianView.setIp(ip);
		return DadianThread.QUEUE.offer(dadianView);
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

	@Override
	public int insertDaDian(DadianView view) {
		if(view.getCtime() == null){
			view.setCtime(new Date());
		}
		if(StringUtils.isNullOrEmpty(view.getNote())){
			view.setNote("dgjs-system");
		}
		if(StringUtils.isNullOrEmpty(view.getUuid())){
			view.setUuid(UUID.randomUUID().toString());
		}
		return this.dadianMapper.insert(view);
	}
}

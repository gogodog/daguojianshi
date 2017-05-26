package com.dgjs.service.impl.common;

import java.util.LinkedList;
import java.util.Queue;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.dgjs.model.view.DadianView;
import com.dgjs.service.common.DataService;
import com.dgjs.utils.IPUtils;
import com.dgjs.utils.MacUtils;

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
}

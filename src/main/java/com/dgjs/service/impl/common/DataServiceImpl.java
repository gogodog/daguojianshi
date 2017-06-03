package com.dgjs.service.impl.common;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.dgjs.job.DadianThread;
import com.dgjs.mapper.common.DadianMapper;
import com.dgjs.model.view.DadianView;
import com.dgjs.service.common.DataService;
import com.dgjs.utils.IPUtils;
import com.dgjs.utils.IdsUtils;
import com.dgjs.utils.MacUtils;
import com.dgjs.utils.StringUtils;

import freemarker.log.Logger;

@Service
public class DataServiceImpl implements DataService{
	
	@Autowired
	DadianMapper dadianMapper;
	private Logger log = Logger.getLogger(this.getClass().getName()); 
	
	@Override
	public boolean dadian(HttpServletRequest request, DadianView dadianView) {
		String ip = IPUtils.getIpAddr3(request);
		MacUtils mac = new MacUtils(ip);
		dadianView.setMac(mac.getMac());
		dadianView.setIp(ip);
		log.info("插入打点数据：" + JSON.toJSONString(dadianView));
		return DadianThread.QUEUE.offer(dadianView);
	}

	@Override
	public int getDocShowCount(String docids) {
		return dadianMapper.countByCondtion(docids,null);
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
			view.setUuid(IdsUtils.getUuId());
		}
		return this.dadianMapper.insert(view);
	}

	@Override
	public Map<String, Integer> getDocShowCounts(String docids) {
		String[] docIdArray=docids.split("#");
		Map<String,Integer> map = new HashMap<String,Integer>();
		for(String docId:docIdArray){
			int count = getDocShowCount(docId);
			map.put(docId, count);
		}
		return map;
	}

	@Override
	public int getPageTotalVisits(String pageId) {
		return dadianMapper.countByCondtion(null, pageId);
	}
}

package com.dgjs.service.impl.common;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dgjs.job.DadianThread;
import com.dgjs.mapper.common.DadianMapper;
import com.dgjs.model.persistence.result.PagedocidsCountResult;
import com.dgjs.model.view.DadianView;
import com.dgjs.model.view.IpHttpResponse;
import com.dgjs.model.view.IpHttpResponse.IpData;
import com.dgjs.service.common.DataService;
import com.dgjs.utils.HttpClientUtils;
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
		IpHttpResponse.IpData ips = this.getLocalAdressByIp(ip);
		if(ips == null){
			dadianView.setNote("ip获取地理位置失败");
		}else{
			dadianView.setIpcity(ips.getCity());
			dadianView.setIpprovince(ips.getRegion());
			dadianView.setIpcountry(ips.getCountry());
		}
		log.info("插入打点数据：" + JSON.toJSONString(dadianView));
		return DadianThread.QUEUE.offer(dadianView);
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
		return getDocShowCounts(Arrays.asList(docIdArray));
	}

	@Override
	public int getPageTotalVisits(String pageId) {
		return dadianMapper.pageIdCount(pageId);
	}

	@Override
	public IpData getLocalAdressByIp(String ip) {
		JSONObject result = HttpClientUtils.sendGet("http://ip.taobao.com/service/getIpInfo.php?ip="+ip);
		if(result == null){
			return null;
		}else{
			if((result.get("code")+"").equals("0")){
				IpHttpResponse i = JSONObject.parseObject(result.toJSONString(), IpHttpResponse.class);
				return i.getData();
			}else{
				return null;
			}
		}
	}

	@Override
	public Map<String, Integer> getDocShowCounts(List<String> docids) {
		List<PagedocidsCountResult> list=dadianMapper.pagedocidsCount(docids);
		Map<String,Integer> map = new HashMap<String,Integer>();
		for(PagedocidsCountResult pagedocidsCount:list){
	       	map.put(pagedocidsCount.getPagedocids(), pagedocidsCount.getVisits());
	    }
		for(String docid:docids){
			if(!map.containsKey(docid)){
				map.put(docid, 0);
			}
		}
		return map;
	}
}

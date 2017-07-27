package com.dgjs.service.impl.common;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dgjs.constants.Session_Keys;
import com.dgjs.job.DadianThread;
import com.dgjs.mapper.common.DadianMapper;
import com.dgjs.model.persistence.result.PagedocidsCountResult;
import com.dgjs.model.result.view.DadianView;
import com.dgjs.model.result.view.IpHttpResponse;
import com.dgjs.model.result.view.IpHttpResponse.IpAliData;
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
		HttpSession session = request.getSession();
		Object ipsObject = session.getAttribute(Session_Keys.ip_location);
		IpHttpResponse.IpAliData location =null;
		if(ipsObject!=null){
			location = (IpAliData) ipsObject;
		}else{
			location = this.getLocalAliAdressByIp(ip);
			if(location == null){
				dadianView.setNote("ip获取地理位置失败");
			}else{
				session.setAttribute(Session_Keys.ip_location, location);
			}
		}
		if(location!=null){
			dadianView.setIpcity(location.getCity());
			dadianView.setIpprovince(location.getRegion());
			dadianView.setIpcountry(location.getCountry());
		}
		log.info("插入打点数据：" + JSON.toJSONString(dadianView));
		boolean isSuccess=DadianThread.QUEUE.offer(dadianView);
		return isSuccess;
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
	public IpAliData getLocalAliAdressByIp(String ip) {
		Map<String,String> header = new HashMap<String,String>();
		header.put("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");
		JSONObject result = HttpClientUtils.sendGetWithHeader("http://ip.taobao.com/service/getIpInfo.php?ip="+ip,header);
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

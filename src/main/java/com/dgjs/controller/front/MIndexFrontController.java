package com.dgjs.controller.front;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.dgjs.model.dto.MIndexConfigDto;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.dto.business.Articlescrap;
import com.dgjs.model.enums.Ad_Position;
import com.dgjs.model.enums.Articlescrap_Status;
import com.dgjs.model.enums.Articlescrap_Type;
import com.dgjs.model.enums.Carousel_Position;
import com.dgjs.model.enums.Index_Type;
import com.dgjs.model.enums.M_Index_Position;
import com.dgjs.model.enums.UpDown_Status;
import com.dgjs.model.persistence.Advertisement;
import com.dgjs.model.persistence.Carousel;
import com.dgjs.model.persistence.MIndexConfig;
import com.dgjs.model.persistence.condition.AdvertisementCondtion;
import com.dgjs.model.persistence.condition.ArticlescrapCondtion;
import com.dgjs.model.persistence.condition.MIndexConfigCondition;
import com.dgjs.model.result.view.MIndexView;
import com.dgjs.service.ad.AdvertisementService;
import com.dgjs.service.common.DataService;
import com.dgjs.service.config.ConfigService;
import com.dgjs.service.content.ArticlescrapService;
import com.dgjs.service.content.CarouselService;
import com.dgjs.service.content.IndexConfigService;
import com.dgjs.service.content.MIndexConfigService;
import com.mysql.jdbc.StringUtils;

public class MIndexFrontController {
	@Autowired
	ArticlescrapService articlescrapService;
	@Autowired
    IndexConfigService indexConfigService;
	@Autowired
	CarouselService carouselService;
	@Autowired
	AdvertisementService advertisementService;
	@Autowired
	DataService dataSerivce;
	@Autowired
	ConfigService configService;
	@Autowired
	MIndexConfigService mIndexConfigService;
	
	private final int randomNum = 40;
	
	@RequestMapping("/idxAfis")
	@ResponseBody
    public Object affairs(HttpServletRequest request, HttpServletResponse response) throws Exception {  
		JSONObject json = new JSONObject();
		Index_Type indexType = Index_Type.AFFAIRS;
		MIndexConfigDto mIndexConfigDto = configService.getMIndexConfigs().get(Index_Type.getConfigKey(indexType));
		Map<String,Integer> map = getNumMap(mIndexConfigDto);
		Set<String> aids = new HashSet<String>();
		List<MIndexView> resultList = new LinkedList<MIndexView>();
		for(M_Index_Position position:M_Index_Position.values()){
			List<MIndexView> list = getPositionList(indexType,position,map.get(position),false,aids);
			resultList.addAll(list);
			json.put(position.toString(),list);
		}
		//查看更多链接
		json.put("moreLink",getMoreLink(Index_Type.AFFAIRS,request));
		//访问量
		json.put("visits", getVisits(resultList));
		return json;
    } 
	
	private List<MIndexView> getPositionList(Index_Type indexType,M_Index_Position position,int num,boolean isMustIndexConfig,Set<String> aids){
		List<MIndexView> resultList = new ArrayList<MIndexView>();
		List<Articlescrap> articlescrapList = null;
		MIndexConfigCondition mIndexConfigCondition = new MIndexConfigCondition();
		mIndexConfigCondition.setStatus(UpDown_Status.UP);
		mIndexConfigCondition.setType(indexType);
		mIndexConfigCondition.setPositions(Arrays.asList(position));
		List<MIndexConfig> mIndexConfigList = mIndexConfigService.list(mIndexConfigCondition);
		//如果不是必须从配置里读取，则取文章信息补位
		if(!isMustIndexConfig){
			int indexFirstSize = mIndexConfigList.size();
			if(num > indexFirstSize){
				int firstNeedArticles = num - indexFirstSize;//还需要多少文章
				ArticlescrapCondtion condition = new ArticlescrapCondtion();
				condition.setOnePageSize(firstNeedArticles);
				condition.setStatus(Articlescrap_Status.UP);
				condition.setType(Articlescrap_Type.valueOf(indexType.getKey()));
				Map<String, SortOrder> sort = new HashMap<String, SortOrder>();
				sort.put("show_time", SortOrder.DESC);
				condition.setSort(sort);
				for(MIndexConfig mIndexConfig:mIndexConfigList){
					if(!StringUtils.isNullOrEmpty(mIndexConfig.getAid())){
						aids.add(mIndexConfig.getAid());
					}
				}
				articlescrapList = originList(condition,aids);
			}
		}
		
		if(mIndexConfigList!=null && mIndexConfigList.size()>0){
			//如果是配置和文章混合
			if(articlescrapList!=null && articlescrapList.size()>0){
				//如果配置和文章总数则按配置的排序展示
				if(mIndexConfigList.size()+articlescrapList.size()==num){
					int aIndex = 0;
					//匹配位置
					for(int i=0;i<num;i++){
						for(MIndexConfig mIndexConfig:mIndexConfigList){
							if(i == mIndexConfig.getSort()-1){
								resultList.add(getMIndexView(mIndexConfig));
								break;
							}
						}
						resultList.add(getMIndexView(articlescrapList.get(aIndex++),position));
					}
				}else{
					resultList.addAll(getMIndexViewList(mIndexConfigList));
					resultList.addAll(getMIndexViewList(articlescrapList,position));
				}
			}else{
				resultList.addAll(getMIndexViewList(mIndexConfigList));
			}
		}else{
			//如果没有配置，但文章有
			if(articlescrapList!=null && articlescrapList.size()>0){
				resultList.addAll(getMIndexViewList(articlescrapList,position));
			}
		}
		return resultList;
	}
	
	private Map<String,Integer> getNumMap(MIndexConfigDto mIndexConfigDto) throws Exception{
		Map<String,Integer> map = new HashMap<String,Integer>();
		if(mIndexConfigDto!=null){
			Field[] fields = MIndexConfigDto.class.getDeclaredFields();
			for(Field field:fields){
				field.setAccessible(true);
				map.put(field.getName(),(int)field.get(mIndexConfigDto));
			}
		}
		return map;
	}
	
	@RequestMapping("/idxhstry")
	@ResponseBody
    public Object history(HttpServletRequest request, HttpServletResponse response) throws Exception {  
		JSONObject json = new JSONObject();
		Index_Type indexType = Index_Type.HISTORY;
		MIndexConfigDto mIndexConfigDto = configService.getMIndexConfigs().get(Index_Type.getConfigKey(indexType));
		Map<String,Integer> map = getNumMap(mIndexConfigDto);
		Set<String> aids = new HashSet<String>();
		List<MIndexView> resultList = new LinkedList<MIndexView>();
		boolean isMustIndexConfig = false;
		for(M_Index_Position position:M_Index_Position.values()){
			if(position == M_Index_Position.ad){
				continue;
			}
			if(position == M_Index_Position.first){
				isMustIndexConfig = true;
			}else{
				isMustIndexConfig = false;
			}
			List<MIndexView> list = getPositionList(indexType,position,map.get(position),isMustIndexConfig,aids);
			resultList.addAll(list);
			json.put(position.toString(), list);
		}
		//查看更多链接
		json.put("moreLink",getMoreLink(Index_Type.HISTORY,request));
		//访问量
//		json.put("visits", getVisits(resultList));
		return json;
    } 
	
	@RequestMapping("/idxpsn")
	@ResponseBody
    public Object person(HttpServletRequest request, HttpServletResponse response) throws Exception {  
		JSONObject json = new JSONObject();
		Index_Type indexType = Index_Type.PERSON;
		MIndexConfigDto mIndexConfigDto = configService.getMIndexConfigs().get(Index_Type.getConfigKey(indexType));
		Map<String,Integer> map = getNumMap(mIndexConfigDto);
		Set<String> aids = new HashSet<String>();
		List<MIndexView> resultList = new LinkedList<MIndexView>();
		for(M_Index_Position position:M_Index_Position.values()){
			if(position == M_Index_Position.ad){
				continue;
			}
			List<MIndexView> list = getPositionList(indexType,position,map.get(position),false,aids);
			resultList.addAll(list);
			json.put(position.toString(), list);
		}
		//查看更多链接
		json.put("moreLink",getMoreLink(Index_Type.PERSON,request));
		//访问量
		json.put("visits", getVisits(resultList));
		return json;
    }
	
	@RequestMapping("/idxuofcl")
	@ResponseBody
    public Object unofficial(HttpServletRequest request, HttpServletResponse response) throws Exception {  
		JSONObject json = new JSONObject();
		Index_Type indexType = Index_Type.UNOFFICIAL;
		MIndexConfigDto mIndexConfigDto = configService.getMIndexConfigs().get(Index_Type.getConfigKey(indexType));
		Map<String,Integer> map = getNumMap(mIndexConfigDto);
		Set<String> aids = new HashSet<String>();
		List<MIndexView> resultList = new LinkedList<MIndexView>();
		for(M_Index_Position position:M_Index_Position.values()){
			if(position == M_Index_Position.ad){
				continue;
			}
			List<MIndexView> list = getPositionList(indexType,position,map.get(position),false,aids);
			resultList.addAll(list);
			json.put(position.toString(), list);
		}
		//查看更多链接
		json.put("moreLink",getMoreLink(Index_Type.UNOFFICIAL,request));
		//访问量
		json.put("visits", getVisits(resultList));
		return json;
    }
	
	@RequestMapping("/idxgogry")
	@ResponseBody
    public Object geography(HttpServletRequest request, HttpServletResponse response) throws Exception {  
		JSONObject json = new JSONObject();
		Index_Type indexType = Index_Type.GEOGRAPHY;
		MIndexConfigDto mIndexConfigDto = configService.getMIndexConfigs().get(Index_Type.getConfigKey(indexType));
		Map<String,Integer> map = getNumMap(mIndexConfigDto);
		Set<String> aids = new HashSet<String>();
		List<MIndexView> resultList = new LinkedList<MIndexView>();
		boolean isMustIndexConfig = false;
		for(M_Index_Position position:M_Index_Position.values()){
			if(position == M_Index_Position.ad){
				continue;
			}
			if(position == M_Index_Position.first){
				isMustIndexConfig = true;
			}else{
				isMustIndexConfig = false;
			}
			List<MIndexView> list = getPositionList(indexType,position,map.get(position),isMustIndexConfig,aids);
			resultList.addAll(list);
			json.put(position.toString(), list);
		}
		//查看更多链接
		json.put("moreLink",getMoreLink(Index_Type.GEOGRAPHY,request));
		//访问量
		json.put("visits", getVisits(resultList));
		return json;
    }
	
	@RequestMapping("/idxtotal")
	@ResponseBody
	public Object total(HttpServletRequest request, HttpServletResponse response) throws Exception {  
		JSONObject json = new JSONObject();
		Index_Type indexType = Index_Type.TOTAL;
		MIndexConfigDto mIndexConfigDto = configService.getMIndexConfigs().get(Index_Type.getConfigKey(indexType));
		Map<String,Integer> map = getNumMap(mIndexConfigDto);
		Set<String> aids = new HashSet<String>();
		List<MIndexView> resultList = new LinkedList<MIndexView>();
		for(M_Index_Position position:M_Index_Position.values()){
			if(position == M_Index_Position.ad){
				continue;
			}
			List<MIndexView> list = getPositionList(indexType,position,map.get(position),false,aids);
			resultList.addAll(list);
			json.put(position.toString(), list);
		}
		//查看更多链接
		json.put("moreLink",getMoreLink(Index_Type.TOTAL,request));
		//访问量
		json.put("visits", getVisits(resultList));
		return json;
    }
	
	@RequestMapping("/idcommon")
	@ResponseBody
	public Object idCommon(HttpServletRequest request, HttpServletResponse response){
		JSONObject json = new JSONObject();
		//顶部轮播图信息需要展示
		Carousel carousel = new Carousel();
		carousel.setStatus(UpDown_Status.UP);
		carousel.setPosition(Carousel_Position.INDEX);
		List<Carousel> carouselList=carouselService.listCarousel(carousel);
		json.put("carouselList", carouselList);
		//底部广告位展示
		AdvertisementCondtion condition = new AdvertisementCondtion();
		condition.setAdPosition(Ad_Position.M_INDEX_CONFIG);
		condition.setStatus(UpDown_Status.UP);
		PageInfoDto<Advertisement> adPage=advertisementService.listAdvertisement(condition);
		List<Advertisement> adList=adPage.getObjects();
		json.put("adList", adList);
		return json;
	}
	
	private MIndexView getMIndexView(MIndexConfig mIndexConfig){
	    MIndexView mIndex = new MIndexView();
	    mIndex.setAid(mIndexConfig.getAid());
	    mIndex.setPosition(mIndexConfig.getPosition().getKey());
	    mIndex.setType(mIndexConfig.getType());
	    if(!StringUtils.isNullOrEmpty(mIndexConfig.getPictures())){
	    	mIndex.setPictures(mIndexConfig.getPictures().split(","));
	    }
	    mIndex.setTitle(mIndex.getTitle());
	    mIndex.setSub_content(mIndex.getSub_content());
	    
	    mIndex.setStart_time(mIndexConfig.getStart_time());
	    mIndex.setaType(mIndexConfig.getA_type());
	    mIndex.setVisits(mIndexConfig.getVisits());
	    mIndex.setLink(mIndexConfig.getLink());
	    return mIndex;
	}
	
	private List<MIndexView> getMIndexViewList(List<MIndexConfig> list){
		if(list==null || list.isEmpty()){
			return null;
		}
		List<MIndexView> resultList = new ArrayList<MIndexView>();
		for(MIndexConfig mIndexConfig:list){
			resultList.add(getMIndexView(mIndexConfig));
		}
		return resultList;
	}
	
	private MIndexView getMIndexView(Articlescrap articlescrap,M_Index_Position position){
	    MIndexView mIndexView  = new MIndexView();
	    mIndexView.setAid(articlescrap.getId());
	    mIndexView.setPictures(articlescrap.getPictures());
	    mIndexView.setPosition(position.getKey());
	    mIndexView.setStart_time(articlescrap.getStart_time());
	    mIndexView.setSub_content(articlescrap.getSub_content());
	    mIndexView.setTitle(articlescrap.getTitle());
	    mIndexView.setType(Index_Type.valueOf(articlescrap.getType().getKey()));
	    mIndexView.setaType(articlescrap.getTypeValue());
	    mIndexView.setVisits(articlescrap.getVisits());
	    return mIndexView;
	}
	
	private List<MIndexView> getMIndexViewList(List<Articlescrap> list,M_Index_Position position){
		if(list==null || list.isEmpty()){
			return null;
		}
		List<MIndexView> resultList = new ArrayList<MIndexView>();
		for(Articlescrap articlescrap:list){
			resultList.add(getMIndexView(articlescrap,position));
		}
		return resultList;
	}
	
	
	private String getMoreLink(Index_Type type,HttpServletRequest request){
		String contextPath=(String) request.getAttribute("contextPath");
		if(type!=Index_Type.TOTAL){
			return contextPath+"/index?type="+Articlescrap_Type.valueOf(type.getKey());
		}
	    return contextPath+"/index";
	}
	
	private Map<String,Long> getVisits(List<MIndexView> list){
		if(list == null || list.size() == 0){
			return null;
		}
		List<String> aids = new ArrayList<String>();
		for(MIndexView miv : list){
			aids.add(miv.getAid());
		}
		Map<String,Long> map=dataSerivce.getDocShowCounts(aids);
		//加上访问基数
		for(MIndexView miv : list){
			Long visits=miv.getVisits()==null?0:miv.getVisits();
			if(visits!=null && visits>0){
				map.put(miv.getAid(), visits+map.get(miv.getAid()));
			}
		}
		return map;
	}
	
	private String[] getArrayIds(Set<String> aids){
		String[] result = null;
		if(aids!=null && aids.size()>0){
			result = new String[aids.size()];
			int index = 0;
			for(String aid:aids){
				result[index++] = aid;
			}
		}
		return result;
	}
	
	private List<Articlescrap> originList(ArticlescrapCondtion condition,Set<String> aids){
		condition.setWithoutIds(getArrayIds(aids));
		int onePageSize = condition.getOnePageSize();
		String[] includes = new String[]{"id"};
		condition.setIncludes(includes);
		//随机查出randomNum条数据的id
		condition.setOnePageSize(randomNum);
		Calendar showTimeFrom = Calendar.getInstance();
		//查询时间在3个月内的
		showTimeFrom.add(Calendar.DAY_OF_MONTH, -3);
		condition.setShowTimeFromD(showTimeFrom.getTime());
		PageInfoDto<Articlescrap> dto = articlescrapService.listArticlescrap(condition);
		List<Articlescrap> list = null;
		if(dto!=null && (list=dto.getObjects())!=null && list.size()>0){
			Set<String> ids = new HashSet<String>();
			//如果需要随机查
			if(list.size()>onePageSize){
				Random random = new Random();
				//如果没到查询数量，就继续循环随机查
				while(ids.size()!=onePageSize){
					int index = random.nextInt(list.size());
					String aid = list.get(index).getId();
					ids.add(aid);
				}
			}else{
				for(Articlescrap articlescrap:list){
					ids.add(articlescrap.getId());
				}
			}
			String[] idArray = new String[ids.size()];
			list = articlescrapService.getArticlescrapByIds(ids.toArray(idArray));
		}
		return list;
	}
	
//	private List<Articlescrap> originList(ArticlescrapCondtion condition,Set<String> aids){
//		PageInfoDto<Articlescrap> pageinfo = list(condition,aids);
//		return pageinfo == null ? null : pageinfo.getObjects();
//	}
	
	public static void main(String[] args) {
		Set<String> ids = new HashSet<String>();
		ids.add("1");
		String[] x = new String[ids.size()];
		ids.toArray(x);
		for(String z:x){
			System.out.println(z);
		}
	}
}

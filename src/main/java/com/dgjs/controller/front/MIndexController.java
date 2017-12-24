package com.dgjs.controller.front;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.dgjs.model.dto.IndexConfigDto;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.dto.business.Articlescrap;
import com.dgjs.model.enums.Ad_Position;
import com.dgjs.model.enums.Articlescrap_Type;
import com.dgjs.model.enums.Carousel_Position;
import com.dgjs.model.enums.Index_Type;
import com.dgjs.model.enums.UpDown_Status;
import com.dgjs.model.persistence.Advertisement;
import com.dgjs.model.persistence.Carousel;
import com.dgjs.model.persistence.IndexConfig;
import com.dgjs.model.persistence.condition.AdvertisementCondtion;
import com.dgjs.model.persistence.condition.ArticlescrapCondtion;
import com.dgjs.model.result.view.MIndexView;
import com.dgjs.service.ad.AdvertisementService;
import com.dgjs.service.common.DataService;
import com.dgjs.service.content.ArticlescrapService;
import com.dgjs.service.content.CarouselService;
import com.dgjs.service.content.IndexConfigService;
import com.dgjs.utils.CookieUtils;
import com.mysql.jdbc.StringUtils;

@Controller
public class MIndexController {

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
	
	private final String COOKIE_NAME="Articles_";
	private final String SPLIT=",";
	
	@RequestMapping("/idxAfis")
	@ResponseBody
    public Object affairs(HttpServletRequest request, HttpServletResponse response) throws Exception {  
		JSONObject json = new JSONObject();
		List<MIndexView> list = commonList(Index_Type.AFFAIRS,2,3,1,null,request,response);
		json.put("list", list);
		//查看更多链接
		json.put("moreLink",getMoreLink(Index_Type.AFFAIRS,request));
		//访问量
		json.put("visits", getVisits(list));
		return json;
    } 
	
	@RequestMapping("/idxhstry")
	@ResponseBody
    public Object history(HttpServletRequest request, HttpServletResponse response) throws Exception {  
		JSONObject json = new JSONObject();
		List<MIndexView> list = commonList(Index_Type.HISTORY,1,4,3,null,request,response);
		json.put("list", list);
		//查看更多链接
		json.put("moreLink",getMoreLink(Index_Type.HISTORY,request));
		return json;
    } 
	
	@RequestMapping("/idxpsn")
	@ResponseBody
    public Object person(HttpServletRequest request, HttpServletResponse response) throws Exception {  
		JSONObject json = new JSONObject();
		List<MIndexView> list = commonList(Index_Type.PERSON,4,4,1,null,request,response);
		json.put("list", list);
		//查看更多链接
		json.put("moreLink",getMoreLink(Index_Type.PERSON,request));
		//访问量
		json.put("visits", getVisits(list));
		return json;
    }
	
	@RequestMapping("/idxuofcl")
	@ResponseBody
    public Object unofficial(HttpServletRequest request, HttpServletResponse response) throws Exception {  
		JSONObject json = new JSONObject();
		List<MIndexView> list = commonList(Index_Type.UNOFFICIAL,3,4,null,null,request,response);
		json.put("list", list);
		//轮播图信息需要展示
		Carousel carousel = new Carousel();
		carousel.setStatus(UpDown_Status.UP);
		carousel.setPosition(Carousel_Position.UNOFFICIAL);
		List<Carousel> carouselList=carouselService.listCarousel(carousel);
		json.put("carouselList", carouselList);
		//查看更多链接
		json.put("moreLink",getMoreLink(Index_Type.UNOFFICIAL,request));
		return json;
    }
	
	@RequestMapping("/idxgogry")
	@ResponseBody
    public Object geography(HttpServletRequest request, HttpServletResponse response) throws Exception {  
		JSONObject json = new JSONObject();
		List<MIndexView> list = commonList(Index_Type.GEOGRAPHY,1,4,3,1,request,response);
		json.put("list", list);
		//查看更多链接
		json.put("moreLink",getMoreLink(Index_Type.GEOGRAPHY,request));
		//访问量
		json.put("visits", getVisits(list));
		return json;
    }
	
	@RequestMapping("/idxtotal")
	@ResponseBody
	public Object total(HttpServletRequest request, HttpServletResponse response) throws Exception {  
		JSONObject json = new JSONObject();
		List<MIndexView> list = commonList(Index_Type.TOTAL,3,2,1,null,request,response);
		json.put("list", list);
		//查看更多链接
		json.put("moreLink",getMoreLink(Index_Type.TOTAL,request));
		//访问量
		json.put("visits", getVisits(list));
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
	
	private List<MIndexView> getMIndexViewList(List<IndexConfigDto> list,int size){
		if(list == null || list.size() == 0 || size > list.size()){
			return null;
		}
		List<MIndexView> resultList = new ArrayList<MIndexView>();
		for(int i=0;i<size;i++){
			IndexConfigDto dto=list.get(i);
			MIndexView mIndex = new MIndexView();
			mIndex.setAid(dto.getIndexConfig().getAid());
			mIndex.setPosition(dto.getIndexConfig().getPosition());
			mIndex.setStart_time(dto.getArticlescrap().getStart_time());
			mIndex.setType(dto.getIndexConfig().getType());
			mIndex.setPictures(dto.getPics());
			mIndex.setTitle(dto.getIndexConfig().getTitle());
			mIndex.setSub_content(dto.getIndexConfig().getSub_content());
			mIndex.setaType(dto.getArticlescrap().getTypeValue());
			mIndex.setVisits(dto.getArticlescrap().getVisits());
			if(StringUtils.isNullOrEmpty(mIndex.getTitle())){
				mIndex.setTitle(dto.getArticlescrap().getTitle());
			}
			if(StringUtils.isNullOrEmpty(mIndex.getSub_content())){
				mIndex.setSub_content(dto.getArticlescrap().getSub_content());
			}
			if(mIndex.getPictures() == null || mIndex.getPictures().length==0){
				mIndex.setPictures(dto.getArticlescrap().getPictures());
			}
			resultList.add(mIndex);
		}
		return resultList;
	}
	
	private List<MIndexView> getMIndexViewList(List<Articlescrap> list){
		if(list == null || list.size() == 0){
			return null;
		}
		List<MIndexView> mIndexList = new ArrayList<MIndexView>();
		for(Articlescrap articlescrap:list){
			 MIndexView mIndexView  = new MIndexView();
			 mIndexView.setAid(articlescrap.getId());
			 mIndexView.setPictures(articlescrap.getPictures());
			 mIndexView.setPosition(1);
			 mIndexView.setStart_time(articlescrap.getStart_time());
			 mIndexView.setSub_content(articlescrap.getSub_content());
			 mIndexView.setTitle(articlescrap.getTitle());
			 mIndexView.setType(Index_Type.valueOf(articlescrap.getType().getKey()));
			 mIndexView.setaType(articlescrap.getTypeValue());
			 mIndexView.setVisits(articlescrap.getVisits());
			 mIndexList.add(mIndexView);
		 }
		return mIndexList;
	}
	
	private void getMIndexViewList(Set<String> set,List<MIndexView> mvList){
		if(mvList!=null && mvList.size()!=0){
			for(MIndexView mv:mvList){
				set.add(mv.getAid());
			}
		}
	}
	
	/*
	 * type 类型
	 * position1 位置1需要显示的文章数
	 * position2 位置2需要显示的文章数
	 * picNum1 位置1需要显示的图片数
	 * picNum2 位置2需要显示的图片数
	 */
	private List<MIndexView> commonList(Index_Type type,int position1,int position2,Integer picNum1,Integer picNum2,HttpServletRequest request,HttpServletResponse response){
		//时事
		List<MIndexView> affairsList = new ArrayList<MIndexView>();
		Set<String> aids = new HashSet<String>();
		//查询有图的配置
		IndexConfig affairsConfig = new IndexConfig();
		affairsConfig.setType(type);
		affairsConfig.setStatus(UpDown_Status.UP);
		affairsConfig.setPosition(1);
		List<IndexConfigDto> affairsConfigList=indexConfigService.list(affairsConfig);
		int affairsConfigSize = 0;
			 //如果有图的配置数量小于需要展示的数量
			 if(affairsConfigList==null||(affairsConfigSize=affairsConfigList.size())<position1){
				 if(affairsConfigSize!=0){
					 affairsList.addAll(getMIndexViewList(affairsConfigList,affairsConfigSize));
				 }
				 int needSelectCount = position1 - affairsConfigSize;
				 ArticlescrapCondtion affairsCondition = new ArticlescrapCondtion();
				 affairsCondition.setType(Articlescrap_Type.valueOf(type.getKey()));
				 affairsCondition.setOnePageSize(needSelectCount);
				 affairsCondition.setPicNum(picNum1);
				 getMIndexViewList(aids,affairsList);
				 Map<String, SortOrder> sort = new HashMap<String, SortOrder>();
				 sort.put("show_time", SortOrder.DESC);
				 affairsCondition.setSort(sort);
				 List<Articlescrap> articlescrapList = aroundList(affairsCondition,type,aids,request,response);
				 if(articlescrapList!=null){
					 affairsList.addAll(getMIndexViewList(articlescrapList));
				 }
			 }else if(affairsConfigSize>=position1){
				 affairsList.addAll(getMIndexViewList(affairsConfigList,position1));
			 }
	    //查询无图的配置
	    affairsConfig.setPosition(2);
	    affairsConfigSize = 0;
	    affairsConfigList=indexConfigService.list(affairsConfig);
	    if(affairsConfigList==null||(affairsConfigSize=affairsConfigList.size())<position2){
	    	 if(affairsConfigSize!=0){
				 affairsList.addAll(getMIndexViewList(affairsConfigList,affairsConfigSize));
			 }
			 int needSelectCount = position2 - affairsConfigSize;
			 ArticlescrapCondtion affairsCondition = new ArticlescrapCondtion();
			 affairsCondition.setPicNum(picNum2);
			 affairsCondition.setType(Articlescrap_Type.valueOf(type.getKey()));
			 getMIndexViewList(aids,affairsList);
			 affairsCondition.setOnePageSize(needSelectCount);
			 Map<String, SortOrder> sort = new HashMap<String, SortOrder>();
			 sort.put("show_time", SortOrder.DESC);
			 affairsCondition.setSort(sort);
			 List<Articlescrap> articlescrapList = aroundList(affairsCondition,type,aids,request,response);
			 if(articlescrapList!=null){
				 affairsList.addAll(getMIndexViewList(articlescrapList));
			 }
		 }else if(affairsConfigSize>=position2){
			 affairsList.addAll(getMIndexViewList(affairsConfigList,position2));
		 }
	    return affairsList;
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
	
	private PageInfoDto<Articlescrap> list(ArticlescrapCondtion affairsCondition,Set<String> aids){
		affairsCondition.setWithoutIds(getArrayIds(aids));
		PageInfoDto<Articlescrap> dto = articlescrapService.listArticlescrap(affairsCondition);
		return dto;
	}
	
	/*
	 * 没有cookie操作的原始方法(如果不需要cookie功能直接用此方法)
	 */
	@SuppressWarnings("unused")
	private List<Articlescrap> originList(ArticlescrapCondtion affairsCondition,Set<String> aids){
		PageInfoDto<Articlescrap> pageinfo = list(affairsCondition,aids);
		return pageinfo == null ? null : pageinfo.getObjects();
	}
	
	/*
	 * 有cookie操作方法
	 */
	private List<Articlescrap> aroundList(ArticlescrapCondtion affairsCondition,Index_Type type,Set<String> aids,HttpServletRequest request,HttpServletResponse response){
		getCookieArticles(type,aids,request);
		PageInfoDto<Articlescrap> pageinfo = list(affairsCondition,aids);
		return after(type,affairsCondition,pageinfo,request,response);
	}
	
	//aids是需要查询排除的文章id
	private void getCookieArticles(Index_Type type,Set<String> aids,HttpServletRequest request){
		String cookieName = COOKIE_NAME+type;
		String cookieValue = CookieUtils.getUid(request, cookieName);
		if(StringUtils.isNullOrEmpty(cookieValue)){
			return;
		}
		String[] values = cookieValue.split(SPLIT);
		for(String value:values){
			aids.add(value);
		}
		return;
	}
	
	//aids是已经查过需要放在cookie里的文章id
	private void addCookieArticles(Index_Type type,Set<String> aids,HttpServletRequest request,HttpServletResponse response){
		int maxAge = 3600*24;
		String cookieName = COOKIE_NAME+type;
		String cookieValue = CookieUtils.getUid(request, cookieName);
		String newAddIds = com.dgjs.utils.StringUtils.combineStr(aids, SPLIT);
		if(StringUtils.isNullOrEmpty(cookieValue)){
			CookieUtils.addCookie(response, cookieName, newAddIds, maxAge);
		}else{
			String value = com.dgjs.utils.StringUtils.jointString(cookieValue,SPLIT,newAddIds);
			CookieUtils.addCookie(response, cookieName, value, maxAge);
		}
	}
	
	private  List<Articlescrap> after(Index_Type type,ArticlescrapCondtion affairsCondition,PageInfoDto<Articlescrap> pageinfo,HttpServletRequest request,HttpServletResponse response){
		List<Articlescrap> resultList = null;
		String cookieName = COOKIE_NAME+type;
		//如果条件查询出库里的数据总条数小于需要的条数,那就将查到的返回
		if(pageinfo == null||pageinfo.getTotalResults() < pageinfo.getOnePageSize()){
			return pageinfo == null ? null : pageinfo.getObjects();
 		}
		List<Articlescrap> list = pageinfo.getObjects();
		//如果查到的数据小于需要查询的条数,则需要再次查询的条数
		int needSelectNum = 0;
		if(list==null){
			needSelectNum = pageinfo.getOnePageSize();
		}else if(list!=null && list.size() < pageinfo.getOnePageSize()){
			needSelectNum = pageinfo.getOnePageSize() - list.size();
		}else{
			resultList = list;
			Set<String> aids = new HashSet<String>();
			for(Articlescrap articlescrap:resultList){
				aids.add(articlescrap.getId());
			}
			addCookieArticles(type, aids, request, response);
		}
		
		//如果需要再次查询
		if(needSelectNum!=0){
			affairsCondition.setOnePageSize(needSelectNum);
			if(list!=null && list.size()>0){
				String[] withoutIds = new String[list.size()];
				int index = 0;
				for(Articlescrap articlescrap:list){
					withoutIds[index++] = articlescrap.getId();
				}
				affairsCondition.setWithoutIds(withoutIds);
			}
			PageInfoDto<Articlescrap> dto = articlescrapService.listArticlescrap(affairsCondition);
			if(dto!=null && dto.getObjects()!=null){
				resultList = dto.getObjects();
				if(list!=null&&list.size()>0){
					resultList.addAll(list);
				}
				CookieUtils.removeCookie(response, cookieName);//清除cookie
				Set<String> aids = new HashSet<String>();
				for(Articlescrap articlescrap:resultList){
					aids.add(articlescrap.getId());
				}
				addCookieArticles(type,aids, request, response);
			}
		}
		return resultList;
	}
}

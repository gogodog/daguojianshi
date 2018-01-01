package com.dgjs.controller.admin.content;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dgjs.constants.RETURN_STATUS;
import com.dgjs.model.dto.MIndexConfigDto;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.dto.business.Articlescrap;
import com.dgjs.model.enums.Articlescrap_Status;
import com.dgjs.model.enums.Articlescrap_Type;
import com.dgjs.model.enums.Index_Type;
import com.dgjs.model.enums.M_Index_Position;
import com.dgjs.model.enums.UpDown_Status;
import com.dgjs.model.persistence.MIndexConfig;
import com.dgjs.model.persistence.condition.ArticlescrapCondtion;
import com.dgjs.model.persistence.condition.MIndexConfigCondition;
import com.dgjs.model.result.view.BaseView;
import com.dgjs.service.common.PictureService;
import com.dgjs.service.config.ConfigService;
import com.dgjs.service.content.ArticlescrapService;
import com.dgjs.service.content.MIndexConfigService;

@Controller
@RequestMapping("/admin/midxcfg")
public class MIndexConfigController {

	@Autowired
	MIndexConfigService mIndexConfigService;
	
	@Autowired
	ConfigService configService;
	
	@Autowired
	ArticlescrapService articlescrapService;
	
	@Autowired
	PictureService pictureService;
	
	@RequestMapping("/list")
	public ModelAndView list(MIndexConfigCondition condition){
		ModelAndView mv = new ModelAndView("admin/content/m_ic_list");
        if(condition.getType()==null){
        	condition.setType(Index_Type.HISTORY);
		}
		List<MIndexConfig> list=mIndexConfigService.list(condition);
		mv.addObject("condition",condition);
		mv.addObject("list", list);
		mv.addObject("types", Index_Type.values());
		mv.addObject("upDownStatus",UpDown_Status.values());
		mv.addObject("positions", M_Index_Position.values());
		mv.addObject("imagePath", pictureService.getFastFDSContextPath());
		return mv;
	}
	
	@RequestMapping("/indexConfig")
	public ModelAndView indexConfig(Long id){
		ModelAndView mv = new ModelAndView("admin/content/m_ic");
		if(id!=null){
			MIndexConfig mIndexConfig = mIndexConfigService.selectById(id);
			mv.addObject("config", mIndexConfig);
		}
		mv.addObject("types", Index_Type.values());
		return mv;
	}
	
	@ResponseBody
	@RequestMapping("/getConfigValue")
	public BaseView getConfigValue(Index_Type type){
		BaseView bv = new BaseView();
		if(type == null){
			bv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR);
			return bv;
		}
		MIndexConfigDto mIndexConfigDto = configService.getMIndexConfigByKey(Index_Type.getConfigKey(type));
		bv.setObjects(mIndexConfigDto);
		return bv;
	}
	
	@ResponseBody
	@RequestMapping("/getArticles")
	public BaseView getArticles(Index_Type type,M_Index_Position position,String title,Integer currentPage,String aid){
		BaseView bv = new BaseView();
		if(type == null || position == null || position == M_Index_Position.ad){
			bv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR);
			return bv;
		}
		JSONObject jsonObject = new JSONObject();
		List<Articlescrap> articlesList = null;
		//查询文章
		if(!StringUtils.isEmpty(aid)){
			String[] ids = new String[]{aid};
			articlesList = articlescrapService.getArticlescrapByIds(ids);
		}else{
			ArticlescrapCondtion articlescrapCondtion = new ArticlescrapCondtion();
			articlescrapCondtion.setStatus(Articlescrap_Status.UP);
			if(type != Index_Type.TOTAL){
				articlescrapCondtion.setType(Articlescrap_Type.valueOf(type.getKey()));
			}
			Map<String, SortOrder> articleSort = new HashMap<String, SortOrder>();
			articleSort.put("show_time", SortOrder.DESC);
			articlescrapCondtion.setSort(articleSort);
			articlescrapCondtion.setCurrentPage(currentPage==null?1:currentPage);
			articlescrapCondtion.setTitle(title);
			PageInfoDto<Articlescrap> pageinfo = articlescrapService.listArticlescrap(articlescrapCondtion);
			if(pageinfo!=null && (articlesList=pageinfo.getObjects())!=null && articlesList.size()>0){
				if(pageinfo.getCurrentPage()<pageinfo.getTotalPage()){
					jsonObject.put("hasNext", true);
				}
				if(pageinfo.getCurrentPage()>1){
					jsonObject.put("hasPrev", true);
				}
		    }
		}
		jsonObject.put("list", articlesList);
		bv.setObjects(jsonObject);
		return bv;
	}
	
	@ResponseBody
	@RequestMapping("/listMIndexConfig")
	public BaseView listMIndexConfig(Index_Type type,M_Index_Position position,Integer sort){
		BaseView bv = new BaseView();
		if(type == null || position == null || sort == null){
			bv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR);
			return bv;
		}
		//查询当前位置下的配置
		MIndexConfigCondition condition = new MIndexConfigCondition();
		condition.setPositions(Arrays.asList(position));
		condition.setType(type);
		condition.setSorts(Arrays.asList(sort));
		List<MIndexConfig> mIndexConfigList = mIndexConfigService.list(condition);
		bv.setObjects(mIndexConfigList);
		return bv;
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
    public BaseView save(HttpServletRequest request, @RequestBody String param){
		BaseView bv = new BaseView();
		try{
			MIndexConfig mIndexConfig = JSON.parseObject(param, MIndexConfig.class);
		    if(mIndexConfig==null){
		    	bv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR);
		    }else if(StringUtils.isEmpty(mIndexConfig.getLink())){
		    	bv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR);
		    }else if(StringUtils.isEmpty(mIndexConfig.getPictures())){
		    	bv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR);
		    }else if(StringUtils.isEmpty(mIndexConfig.getPosition())){
		    	bv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR);
		    }else if(mIndexConfig.getSort()==0){
		    	bv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR);
		    }else if(mIndexConfig.getStatus()==null){
		    	bv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR);
		    }else if(mIndexConfig.getType()==null){
		    	bv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR);
		    }
		    int flag = 0;
		    if(mIndexConfig.getId()!=null){
		    	flag = mIndexConfigService.update(mIndexConfig);
		    }else{
		    	if(mIndexConfig.getStatus()==UpDown_Status.UP){
			    	 MIndexConfigCondition condition = new MIndexConfigCondition();
					 condition.setPositions(Arrays.asList(mIndexConfig.getPosition()));
					 condition.setSorts(Arrays.asList(mIndexConfig.getSort()));
					 condition.setStatus(mIndexConfig.getStatus());
					 condition.setType(mIndexConfig.getType());
					 List<MIndexConfig> list = mIndexConfigService.list(condition);
					 if(list!=null && list.size()>0){
						 bv.setBaseViewValue(RETURN_STATUS.SERVICE_ERROR.getValue(),"选择的位置已占用");
						 return bv;
					 }
			    }
			    flag = mIndexConfigService.save(mIndexConfig);
		    }
		    if(flag<1){
		    	bv.setBaseViewValue(RETURN_STATUS.SYSTEM_ERROR);
		    }
		}catch(Exception e){
			bv.setBaseViewValue(RETURN_STATUS.SYSTEM_ERROR.getValue(),e.getMessage());
		}
	    return bv;
    }
	
	@RequestMapping("/delete")
	public ModelAndView delete(Index_Type type,Long id){
		ModelAndView mv = new ModelAndView("redirect:/admin/midxcfg/list?type="+type);  
		mIndexConfigService.deleteById(id);
		return mv;
	}
}

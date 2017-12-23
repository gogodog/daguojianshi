package com.dgjs.controller.admin.content;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dgjs.annotation.LogRecord;
import com.dgjs.constants.RETURN_STATUS;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.dto.business.Articlescrap;
import com.dgjs.model.enums.Articlescrap_Status;
import com.dgjs.model.enums.Articlescrap_Type;
import com.dgjs.model.enums.OperateEnum;
import com.dgjs.model.enums.UpDown_Status;
import com.dgjs.model.persistence.condition.ArticlescrapCondtion;
import com.dgjs.model.result.view.BaseView;
import com.dgjs.service.common.PictureService;
import com.dgjs.service.content.ArticlescrapService;
import com.dgjs.utils.DateUtils;
import com.dgjs.utils.PictureUtils;

@Controller
@RequestMapping("/admin/atcp")
public class ArticlescrapController {
	
	private Log log = LogFactory.getLog(ArticlescrapController.class);
	
	@Autowired
	ArticlescrapService articlescrapSerivce;
	
	@Autowired
	PictureService pictureService;
	
	
	@RequestMapping("/articlescrapList")
    public ModelAndView articlescrapList(HttpServletRequest request, HttpServletResponse response,ArticlescrapCondtion condtion) throws Exception {  
		ModelAndView mv = new ModelAndView("admin/content/articlescrap_list");
		condtion.setNeedTotalResults(true);
		Map<String, SortOrder> sort = new HashMap<String, SortOrder>();
		sort.put("update_time", SortOrder.DESC);
		condtion.setSort(sort);
		PageInfoDto<Articlescrap> pageInfo=articlescrapSerivce.listArticlescrap(condtion);
		mv.addObject("pageInfo", pageInfo);
		mv.addObject("condition",condtion);
		mv.addObject("articlescrapTypes", Articlescrap_Type.values());
		mv.addObject("upDownStatus", UpDown_Status.values());
		return mv;  
    }  
	
	@RequestMapping("/articlescrap")
	public ModelAndView articlescrap(String articlescrapId) throws Exception{
		ModelAndView mv = new ModelAndView("admin/content/articlescrap");  
		if(articlescrapId!=null){
			Articlescrap articlescrap=articlescrapSerivce.selectByIdAll(articlescrapId);
			mv.addObject("articlescrap", articlescrap);
		}
		mv.addObject("types", Articlescrap_Type.values());
		return mv;  
	}
	
	@RequestMapping("/saveArticlescrap")
	@LogRecord(operate=OperateEnum.Update,remark="修改文章信息")
	public ModelAndView saveArticlescrap(Articlescrap articlescrap,String showTime) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/admin/atcp/articlescrapList");  
		articlescrap.setBeginTime();
		articlescrap.setShow_time(DateUtils.parseDateFromString(showTime));
		articlescrap.setPic_num(articlescrap.getPictures()==null?0:articlescrap.getPictures().length);
		if(StringUtils.isEmpty(articlescrap.getId()))
			articlescrapSerivce.saveArticlescrap(articlescrap);
		else
			articlescrapSerivce.updateArticlescrap(articlescrap);
		return mv;
	}
	
	@RequestMapping("/deleteArticlescrap")
	@LogRecord(operate=OperateEnum.Update,remark="删除文章")
	public ModelAndView deleteArticlescrap(String articlescrapId)  throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/admin/atcp/articlescrapList");  
		articlescrapSerivce.deleteArticlescrap(articlescrapId);
		return mv;
	}
	
	@RequestMapping("/previewArticlescrap")
	public ModelAndView previewArticlescrap(String articlescrapId)  throws Exception{
		ModelAndView mv = articlescrap(articlescrapId);
		mv.setViewName("front/common/show");
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value="/updateStatus",method=RequestMethod.POST)
	@LogRecord(operate=OperateEnum.Update,remark="修改文章状态")
	public BaseView updateStatus(String articlescrapId,Articlescrap_Status status){
		BaseView bv = new BaseView();
		if(StringUtils.isEmpty(articlescrapId)){
			bv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR);
			return bv;
		}
		Articlescrap articlescrap=articlescrapSerivce.selectByIdAll(articlescrapId);
		if(articlescrap == null){
			bv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR);
			return bv;
		}
		articlescrap.setStatus(status);
		try {
			articlescrapSerivce.updateArticlescrap(articlescrap);
		} catch (Exception e) {
			log.error("articlescrap updateStatus exception", e);
			bv.setBaseViewValue(RETURN_STATUS.SYSTEM_ERROR);
		}
		return bv;
	}

	@RequestMapping("/updateInfo")
	@LogRecord(operate=OperateEnum.Update,remark="修改文章信息")
	public ModelAndView updateArticlescrapInfo(Articlescrap articlescrap,String showTime) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/admin/atcp/articlescrapList"); 
		articlescrap.setBeginTime();
		articlescrap.setShow_time(DateUtils.parseDateFromString(showTime));
		articlescrap.setPic_num(articlescrap.getPictures()==null?0:articlescrap.getPictures().length);
		String content = articlescrapSerivce.getContent(articlescrap.getId());
		articlescrap.setContent(content);
		articlescrapSerivce.updateArticlescrap(articlescrap);
		return mv;
	}
	
	@RequestMapping("/updateContent")
	@LogRecord(operate=OperateEnum.Update,remark="修改文章内容")
	public ModelAndView updateContent(String articlescrapId,String content) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/admin/atcp/articlescrapList"); 
		Articlescrap articlescrap = articlescrapSerivce.selectByIdAll(articlescrapId);
		List<String> list = PictureUtils.getImgStr(articlescrap.getContent());
		articlescrap.setContent(PictureUtils.replaceHtml(list,articlescrap.getContent(),pictureService.getFastFDSContextPath()));//将图片设置为占位符
		String[] pics = (String[])list.toArray(new String[list.size()]);
		articlescrap.setPictures(pics);
		articlescrapSerivce.updateArticlescrap(articlescrap);
		return mv;
	}
	
	@RequestMapping("/info")
	public ModelAndView info(String articlescrapId){
		ModelAndView mv = new ModelAndView("admin/content/a_info");  
		Articlescrap articlescrap = articlescrapSerivce.selectById(articlescrapId);
		mv.addObject("articlescrap", articlescrap);
		return mv;
	}
	
	@RequestMapping("/content")
	public ModelAndView content(String articlescrapId){
		ModelAndView mv = new ModelAndView("admin/content/a_content");  
		Articlescrap articlescrap = articlescrapSerivce.selectByIdAll(articlescrapId);
		mv.addObject("content", articlescrap.getContent());
		mv.addObject("articlescrapId", articlescrapId);
		return mv;
	}
}

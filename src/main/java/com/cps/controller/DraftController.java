package com.cps.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dgjs.annotation.LogRecord;
import com.dgjs.constants.RETURN_STATUS;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.dto.business.Draft;
import com.dgjs.model.enums.Articlescrap_Type;
import com.dgjs.model.enums.Draft_Status;
import com.dgjs.model.enums.OperateEnum;
import com.dgjs.model.persistence.condition.DraftCondition;
import com.dgjs.model.result.view.BaseView;
import com.dgjs.service.common.PictureService;
import com.dgjs.service.content.DraftService;
import com.dgjs.service.content.PendingService;
import com.dgjs.utils.PictureUtils;
import com.dgjs.utils.WebContextHelper;
import com.mysql.jdbc.StringUtils;

@Controller
@RequestMapping("/cps/dft")
public class DraftController {

	@Autowired
	DraftService draftService;
	
	@Autowired
	PendingService pendingService;
	
	@Autowired
	PictureService pictureService;
	
	@RequestMapping("/draft")
	public ModelAndView list(HttpServletRequest request,DraftCondition condtion){
		ModelAndView mv = new ModelAndView("/cps/draft");
		condtion.setUser_id(WebContextHelper.getUserId());
		Map<String, SortOrder> map = new HashMap<String, SortOrder>();
		map.put("update_time", SortOrder.DESC);
		condtion.setSort(map);
		PageInfoDto<Draft> pageinfo=draftService.listDrafts(condtion);
		mv.addObject("pageinfo", pageinfo);
		return mv;
	}
	
	@RequestMapping("/wdoc")
    public ModelAndView wdoc(HttpServletRequest request,String aid) throws Exception {  
		ModelAndView mv = new ModelAndView("/cps/wdoc");
		mv.addObject("types", Articlescrap_Type.values());
		if(!StringUtils.isNullOrEmpty(aid)){
			Draft draft=draftService.selectByIdAll(aid);
			mv.addObject("draft", draft);
		}
		return mv;
    }
	
	@ResponseBody
	@RequestMapping("/savedraft")
	@LogRecord(operate=OperateEnum.Add,remark="保存草稿箱")
	public BaseView savedraft(Draft draft){
		BaseView mv = new BaseView();
		if(draft==null){
			mv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR);
			return mv;
		}
		if(StringUtils.isNullOrEmpty(draft.getContent())||StringUtils.isNullOrEmpty(draft.getSub_content())
				||StringUtils.isNullOrEmpty(draft.getTitle())||StringUtils.isNullOrEmpty(draft.getAuthor())
				||StringUtils.isNullOrEmpty(draft.getKeywordsValue())){
			mv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR.name(),"标题，作者，关键词，内容摘要，正文不能为空");
			return mv;
		}
		if(draft.getTitle().length()<2||draft.getTitle().length()>50){
			mv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR.name(),"标题长度需在10-50之间");
			return mv;
		}
		if(draft.getAuthor().length()<2||draft.getAuthor().length()>20){
			mv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR.name(),"作者长度需在2-20之间");
			return mv;
		}
		for(String keyword:draft.getKeywords()){
			if(StringUtils.isNullOrEmpty(keyword)){
				mv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR.name(),"关键词不能含有空字符");
				return mv;
			}
			if(keyword.length()<2||keyword.length()>10){
				mv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR.name(),"关键词长度需在2-10之间");
				return mv;
			}
		}
		if(draft.getType()==null){
			mv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR.name(),"类型不能为空");
			return mv;
		}
		if(draft.getSub_content().length()>700||draft.getSub_content().length()<20){
			mv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR.name(),"摘要长度需在30-300之间");
			return mv;
		}
		if(draft.getContent().length()<150||draft.getContent().length()>10000){
			mv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR.name(),"正文长度需在300-10000之间");
			return mv;
		}
		draft.setBeginTime();
		draft.setUser_id(WebContextHelper.getUserId());
		List<String> list = PictureUtils.getImgStr(draft.getContent());
		draft.setContent(PictureUtils.replaceHtml(list,draft.getContent(),pictureService.getWebContextPath()));//将图片设置为占位符
		String[] pics = (String[])list.toArray(new String[list.size()]);
		draft.setPictures(pics);
		draft.setPic_num(list.size());
		draft.setDraft_status(Draft_Status.NORMAL);
		int isSuccess = 0;
		if(StringUtils.isNullOrEmpty(draft.getId())){
			 isSuccess=draftService.saveDraft(draft);
		}else{
			 try {
				isSuccess=draftService.updateDraft(draft);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(isSuccess!=1){
			mv.setBaseViewValue(RETURN_STATUS.SYSTEM_ERROR);
		}
		return mv;
	}
	
	@RequestMapping("/dltdft")
	@LogRecord(operate=OperateEnum.Delete,remark="删除草稿")
    public ModelAndView deleteDraft(HttpServletRequest request,String aid) throws Exception {  
		ModelAndView mv = new ModelAndView("redirect:/cps/dft/draft"); 
		draftService.deleteDraft(aid);
		return mv;
    }
	
	@RequestMapping("/previewDraft")
	public ModelAndView previewDraft(String aid)  throws Exception{
		ModelAndView mv = new ModelAndView("front/admin/show");
		Draft draft=draftService.selectByIdAll(aid);
		mv.addObject("articlescrap", draft);
		return mv;
	}
	
	@ResponseBody
	@RequestMapping("/submitAudit")
	@LogRecord(operate=OperateEnum.Update,remark="提审")
	public BaseView submitAudit(String aid) throws Exception{
		BaseView mv = new BaseView();
		if(StringUtils.isNullOrEmpty(aid)){
			mv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR);
			return mv;
		}
		Draft draft = draftService.selectById(aid);
		if(draft==null){
			mv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR);
			return mv;
		}
		int flag=pendingService.savePending(aid);
		if(flag < 1){
			mv.setBaseViewValue(RETURN_STATUS.SYSTEM_ERROR);
			return mv;
		}
		return mv;
	}
}

package com.dgjs.controller.front;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dgjs.constants.Constants;
import com.dgjs.constants.RETURN_STATUS;
import com.dgjs.constants.Session_Keys;
import com.dgjs.model.dto.business.Articlescrap;
import com.dgjs.model.enums.Judge_Level;
import com.dgjs.model.persistence.FrontFeedBack;
import com.dgjs.model.result.view.BaseView;
import com.dgjs.service.content.FrontFeedBackService;
import com.dgjs.service.content.ArticlescrapService;
import com.dgjs.utils.IPUtils;

@Controller
@RequestMapping("/feedback")
public class FeedBackController {
	
	private Log log = LogFactory.getLog(FeedBackController.class);
	
	@Autowired
	FrontFeedBackService feedBackService;
	
	@Autowired
	ArticlescrapService articlescrapService;
	
	@RequestMapping(value = "/{docid}")
	public ModelAndView judge(Model model, @PathVariable("docid")String docid, String title){
		ModelAndView mv = new ModelAndView("front/common/feedback");
		mv.addObject("docid", docid);
		mv.addObject("title", title);
		mv.addObject("judgeLevels", Judge_Level.values());
		//打点数据
		mv.addObject("pageid", Constants.DD_FEEDBACK_ID);
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value = "/judge")
	public BaseView ajaxJudge(HttpServletRequest request,FrontFeedBack aJudge){
		BaseView view=new BaseView();
		if(StringUtils.isEmpty(aJudge.getArticlescrap_id())||aJudge.getJudge_level()==null
				||(!StringUtils.isEmpty(aJudge.getJudge_message())&&aJudge.getJudge_message().length()>255)
				||(!StringUtils.isEmpty(aJudge.getEmail())&&aJudge.getEmail().length()>50)
				||(StringUtils.isEmpty(aJudge.getUname())||aJudge.getUname().length()>20)){
			view.setBaseViewValue(RETURN_STATUS.PARAM_ERROR);
			return view;
		}
		try{
			Articlescrap articlescrap=articlescrapService.selectById(aJudge.getArticlescrap_id());
			if(articlescrap==null){
				view.setBaseViewValue(RETURN_STATUS.PARAM_ERROR);
				return view;
			}
			HttpSession session=request.getSession();
			Object judgeFlag=session.getAttribute(aJudge.getArticlescrap_id());
			Object ip=session.getAttribute(Session_Keys.ip);
			//说明没有评价过
			if(judgeFlag==null){
				if(ip!=null){
					aJudge.setIp(ip.toString());
				}else{
					aJudge.setIp(IPUtils.getIpAddr3(request));
				}
				session.setAttribute(Session_Keys.ip, aJudge.getIp());
				int flag=feedBackService.save(aJudge);
				if(flag < 1){
					view.setBaseViewValue(RETURN_STATUS.SYSTEM_ERROR);
				}else{
					session.setAttribute(aJudge.getArticlescrap_id(), (byte)1);
				}
			}else{
				view.setBaseViewValue(RETURN_STATUS.SERVICE_ERROR);//已经评价过
			}
		}catch(Exception e){
			log.error("SERVICE_ERROR", e);
			view.setBaseViewValue(RETURN_STATUS.SYSTEM_ERROR);
		}
	    return view;
	}
}

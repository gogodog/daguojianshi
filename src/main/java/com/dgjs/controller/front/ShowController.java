package com.dgjs.controller.front;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dgjs.constants.Constants;
import com.dgjs.constants.RETURN_STATUS;
import com.dgjs.constants.Session_Keys;
import com.dgjs.controller.admin.content.RecommedArticlescrapController;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.dto.business.Articlescrap;
import com.dgjs.model.enums.Articlescrap_Type;
import com.dgjs.model.enums.Judge_Level;
import com.dgjs.model.persistence.AJudge;
import com.dgjs.model.persistence.Comments;
import com.dgjs.model.view.BaseView;
import com.dgjs.service.common.DataService;
import com.dgjs.service.common.PictureService;
import com.dgjs.service.content.AJudgeService;
import com.dgjs.service.content.ArticlescrapService;
import com.dgjs.service.content.CommentsService;
import com.dgjs.utils.IPUtils;

@Controller
public class ShowController {
	
	private Log log = LogFactory.getLog(RecommedArticlescrapController.class);
	
	@Autowired
	AJudgeService aJudgeService;
	@Autowired
	ArticlescrapService articlescrapService;
	@Autowired
	CommentsService commentsService;
	@Autowired
	PictureService pictureService;
	@Autowired
	DataService dataSerivce;

	@ResponseBody
	@RequestMapping(value = "/judge")
	public BaseView ajaxJudge(HttpServletRequest request,String aid,Judge_Level level,String message){
		BaseView view=new BaseView();
		if(StringUtils.isEmpty(aid)||level==null||(message!=null&&message.length()>255)){
			view.setBaseViewValue(RETURN_STATUS.PARAM_ERROR);
			return view;
		}
		try{
			Articlescrap articlescrap=articlescrapService.selectById(aid);
			if(articlescrap==null){
				view.setBaseViewValue(RETURN_STATUS.PARAM_ERROR);
				return view;
			}
			HttpSession session=request.getSession();
			Object judgeFlag=session.getAttribute(aid);
			Object ip=session.getAttribute(Session_Keys.ip);
			//说明没有评价过
			if(judgeFlag==null){
				AJudge aJudge = new AJudge();
				aJudge.setArticlescrap_id(aid);
				aJudge.setJudge_level(level);
				aJudge.setJudge_message(StringUtils.isEmpty(message)?null:message);
				if(ip!=null){
					aJudge.setIp(ip.toString());
				}else{
					aJudge.setIp(IPUtils.getIpAddr3(request));
				}
				session.setAttribute(aid, (byte)1);
				session.setAttribute(Session_Keys.ip, aJudge.getIp());
				int flag=aJudgeService.save(aJudge);
				if(flag < 1){
					view.setBaseViewValue(RETURN_STATUS.SYSTEM_ERROR);
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
	
	@RequestMapping("/show/{id}")
    public ModelAndView show(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception {  
		ModelAndView mv = new ModelAndView("front/show");
		Articlescrap articlescrap=articlescrapService.selectById(id);
		mv.addObject("articlescrap", articlescrap);
		mv.addObject("imageContextPath", pictureService.getImageContextPath());
		PageInfoDto<Comments> pageinfo=commentsService.getCommentsByArticlescrapId(id, 1, Constants.DEFAULT_ONEPAGESIZE, false);
		//加载最新评论文章
		List<Articlescrap> commentsArticlescrapList=articlescrapService.getArticlescrapByComments(2);
		mv.addObject("commentsArticlescrapList", commentsArticlescrapList);
		mv.addObject("commentsPageinfo", pageinfo);
		//文章阅读量
		mv.addObject("pagedocids",id);
		Map<String,Integer> map=dataSerivce.getDocShowCounts(String.valueOf(id));
		mv.addObject("visits", map.get(String.valueOf(id)));
		//加载分类
		mv.addObject("types", Articlescrap_Type.values());
		//加载评判枚举
		mv.addObject("judgeLevels", Judge_Level.values());
		return mv;
    }
	
	@RequestMapping(value="/saveComments",method=RequestMethod.POST)
	public ModelAndView saveComments(Comments comments,HttpServletRequest request){
		ModelAndView mv = new ModelAndView("redirect:/show/"+comments.getArticlescrap_id());
		comments.setIp_address(IPUtils.getIpAddr(request));
		commentsService.save(comments);
		return mv;
	}
}

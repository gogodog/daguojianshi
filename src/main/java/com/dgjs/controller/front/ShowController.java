package com.dgjs.controller.front;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.dgjs.controller.admin.content.RecommedArticlescrapController;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.dto.business.Articlescrap;
import com.dgjs.model.enums.Articlescrap_Type;
import com.dgjs.model.enums.Judge_Level;
import com.dgjs.model.persistence.Comments;
import com.dgjs.model.result.view.BaseView;
import com.dgjs.service.common.DataService;
import com.dgjs.service.common.PictureService;
import com.dgjs.service.content.ArticlescrapService;
import com.dgjs.service.content.CommentsService;
import com.dgjs.utils.DateUtils;
import com.dgjs.utils.IPUtils;

@Controller
public class ShowController {
	
	private Log log = LogFactory.getLog(RecommedArticlescrapController.class);
	
	@Autowired
	ArticlescrapService articlescrapService;
	@Autowired
	CommentsService commentsService;
	@Autowired
	PictureService pictureService;
	@Autowired
	DataService dataSerivce;

	
	@RequestMapping("/show/{id}")
    public ModelAndView show(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception {  
		ModelAndView mv = new ModelAndView("front/common/show");
		Articlescrap articlescrap=articlescrapService.selectByIdAll(id);
		mv.addObject("articlescrap", articlescrap);
		mv.addObject("imageContextPath", pictureService.getImageContextPath());
		//文章阅读量
		Map<String,Long> map=dataSerivce.getDocShowCounts(String.valueOf(id));
		Long visits = map.get(String.valueOf(id));
		mv.addObject("visits", articlescrap.getVisits()==null?visits:articlescrap.getVisits()+visits);
		//加载分类
		mv.addObject("types", Articlescrap_Type.values());
		//加载评判枚举
		mv.addObject("judgeLevels", Judge_Level.values());
		//加载最新评论文章
		List<Articlescrap> commentsArticlescrapList=articlescrapService.getArticlescrapByComments(2);
		mv.addObject("commentsArticlescrapList", commentsArticlescrapList);
		//首页访问量
		int indexVisitCount = dataSerivce.getPageTotalVisits("10336266");
		mv.addObject("indexVisitCount",indexVisitCount);
		//打点数据
		mv.addObject("pagedocids", articlescrap.getId()); 
		mv.addObject("pageid", Constants.DD_SHOW_ID);
		return mv;
    }
	
	@ResponseBody
	@RequestMapping(value = "/getComments")
	public BaseView getComments(String id,Integer currentPage){
		BaseView view=new BaseView();
		//加载评论
		PageInfoDto<Comments> pageinfo=commentsService.getCommentsByArticlescrapId(id, currentPage, 2, false);
		view.setObjects(pageinfo.getObjects());
		return view;
	}
	
	@ResponseBody
	@RequestMapping(value="/saveComments",method=RequestMethod.POST)
	public BaseView saveComments(Comments comments,HttpServletRequest request){
		BaseView mv = new BaseView();
		if(StringUtils.isEmpty(comments.getComment_name())||StringUtils.isEmpty(comments.getComment())){
			mv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR);
		}else if(!StringUtils.isEmpty(comments.getEmail())&&comments.getEmail().length()>255){
			mv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR);
		}else if(comments.getComment_name().length()>255||comments.getComment().length()>1000){
			mv.setBaseViewValue(RETURN_STATUS.PARAM_ERROR);
		}else{
			try{
				Date now =new Date();
				comments.setIp_address(IPUtils.getIpAddr3(request));
				comments.setComment_time(now);
				int flag=commentsService.save(comments);
				if(flag<1){
					mv.setBaseViewValue(RETURN_STATUS.SYSTEM_ERROR);
				}
				mv.setObjects(DateUtils.parseStringFromDate(now));
			}catch(Exception e){
				log.error("saveComments exception", e);
				mv.setBaseViewValue(RETURN_STATUS.SYSTEM_ERROR);
			}
		}
		return mv;
	}
}

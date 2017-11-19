package com.dgjs.controller.admin.admin;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dgjs.model.dto.OperateLogDto;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.enums.OperateEnum;
import com.dgjs.model.persistence.OperateLog;
import com.dgjs.model.persistence.condition.OperateLogCondition;
import com.dgjs.service.admin.OperateLogService;

@Controller
@RequestMapping("/admin/oprtlg")
public class OperateLogController {

	@Autowired
	OperateLogService operateLogService;
	
	@RequestMapping("/list")
	public ModelAndView list(OperateLogCondition condition){
		ModelAndView mv = new ModelAndView("/admin/admin/operate_log_list");
		if(condition.getOperateTypes()==null){
			condition.setOperateTypes(Arrays.asList(OperateEnum.Add,OperateEnum.Delete,OperateEnum.Update));
		}
		PageInfoDto<OperateLogDto> pageinfo= operateLogService.list(condition);
		mv.addObject("condition", condition);
		mv.addObject("pageInfo", pageinfo);
		mv.addObject("operateTypes", OperateEnum.values());
		return mv;
	}
	
	@RequestMapping("/detail")
	public ModelAndView detail(Long id){
		ModelAndView mv = new ModelAndView("/admin/admin/operate_log");
		OperateLog operateLog = operateLogService.selectById(id);
		mv.addObject("operateLog", operateLog);
		return mv;
	}
}


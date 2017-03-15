package com.dgjs.service.impl.content;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgjs.mapper.content.RecommedArticlescrapMapper;
import com.dgjs.model.persistence.RecommedArticlescrap;
import com.dgjs.model.persistence.condition.RecommedArticlescrapCondition;
import com.dgjs.model.persistence.enhance.RecommedArticlescrapEnhance;
import com.dgjs.service.content.RecommedArticlescrapService;
import com.dgjs.utils.HtmlUtil;

@Service
public class RecommedArticlescrapServiceImpl implements RecommedArticlescrapService{

	@Autowired
	private RecommedArticlescrapMapper mapper;
	
	@Override
	public int save(RecommedArticlescrap recommedArticlescrap) {
		return mapper.save(recommedArticlescrap);
	}

	@Override
	public List<RecommedArticlescrapEnhance> list(
			RecommedArticlescrapCondition condition) {
		List<RecommedArticlescrapEnhance> list=mapper.list(condition);
		for(RecommedArticlescrapEnhance ra:list){
			String content=HtmlUtil.getTextFromHtml(ra.getContent());
			ra.setContent(content);
			if(content!=null&&content.length()>100){
				ra.setContent(content.substring(0, 100));
			}
		}
		return list;
	}

	@Override
	public int deleteById(Long id) {
		return mapper.deleteById(id);
	}

	@Override
	public RecommedArticlescrap selectByArticlescrapId(Long articlescrap_id) {
		// TODO Auto-generated method stub
		return mapper.selectByArticlescrapId(articlescrap_id);
	}

}

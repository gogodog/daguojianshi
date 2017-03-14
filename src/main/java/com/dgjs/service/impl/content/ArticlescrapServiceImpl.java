package com.dgjs.service.impl.content;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgjs.mapper.content.ArticlescrapMapper;
import com.dgjs.model.persistence.Articlescrap;
import com.dgjs.model.persistence.condition.ArticlescrapCondtion;
import com.dgjs.service.content.ArticlescrapService;
import com.dgjs.utils.HtmlUtil;

@Service
public class ArticlescrapServiceImpl implements ArticlescrapService{

	@Autowired
	ArticlescrapMapper articlescrapMapper;
	
	@Override
	public int saveArticlescrap(Articlescrap articlescrap) {
		return articlescrapMapper.saveArticlescrap(articlescrap);
	}

	@Override
	public int updateArticlescrap(Articlescrap articlescrap) {
		return articlescrapMapper.updateArticlescrap(articlescrap);
	}

	@Override
	public Articlescrap selectById(Long id) {
		return articlescrapMapper.selectById(id);
	}

	@Override
	public List<Articlescrap> listArticlescrap(ArticlescrapCondtion articlescrapCondtion) {
		List<Articlescrap> list=articlescrapMapper.listArticlescrap(articlescrapCondtion);
		for(Articlescrap articlescrap:list){
			String content=HtmlUtil.getTextFromHtml(articlescrap.getContent());
			articlescrap.setContent(content);
			if(content!=null&&content.length()>articlescrapCondtion.getSubContentLength()){
				articlescrap.setContent(content.substring(0, articlescrapCondtion.getSubContentLength()));
			}
		}
		return list;
	}

	@Override
	public int deleteArticlescrap(Long id) {
		// TODO Auto-generated method stub
		return articlescrapMapper.deleteArticlescrap(id);
	}

}

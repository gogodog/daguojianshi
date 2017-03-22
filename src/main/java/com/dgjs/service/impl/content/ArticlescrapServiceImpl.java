package com.dgjs.service.impl.content;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgjs.mapper.content.ArticlescrapMapper;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.persistence.Articlescrap;
import com.dgjs.model.persistence.condition.ArticlescrapCondtion;
import com.dgjs.service.content.ArticlescrapService;

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
	public PageInfoDto<Articlescrap> listArticlescrap(ArticlescrapCondtion articlescrapCondtion) {
		articlescrapCondtion.setBeginNum((articlescrapCondtion.getCurrentPage()-1)*articlescrapCondtion.getOnePageSize());
		List<Articlescrap> list=articlescrapMapper.listArticlescrap(articlescrapCondtion);
		int totalResults=0;
		if(articlescrapCondtion.isNeedTotalResults()){
			totalResults=articlescrapMapper.sizeListArticlescrap(articlescrapCondtion);
		}
		return PageInfoDto.getPageInfo(articlescrapCondtion.getCurrentPage(), articlescrapCondtion.getOnePageSize(), totalResults, list);
	}

	@Override
	public int deleteArticlescrap(Long id) {
		return articlescrapMapper.deleteArticlescrap(id);
	}

}

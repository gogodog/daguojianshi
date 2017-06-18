package com.dgjs.service.impl.content;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgjs.es.mapper.content.ArticlescrapMapper;
import com.dgjs.model.enums.UpDown_Status;
import com.dgjs.model.persistence.Articlescrap;
import com.dgjs.model.persistence.Recommend;
import com.dgjs.service.content.RecommedArticlescrapService;

@Service
public class RecommedArticlescrapServiceImpl implements RecommedArticlescrapService{
	
	@Autowired
	ArticlescrapMapper articlescrapMapper;

	@Override
	public int save(String articlescrapId, UpDown_Status status, int sort) throws Exception{
		return articlescrapMapper.updateArticlescrapRecommend(articlescrapId, sort, status);
	}

	@Override
	public List<Articlescrap> list(UpDown_Status status) {
		return articlescrapMapper.listRecommend(status);
	}

	@Override
	public int deleteById(String articlescrapId) throws Exception {
		Articlescrap articlescrap = new Articlescrap();
		articlescrap.setId(articlescrapId);
		Recommend recommend = new Recommend();
		recommend.setSort(-1);
		recommend.setStatus(-1);
		articlescrap.setRecommend(recommend);
		return articlescrapMapper.updateArticlescrap(articlescrap);
	}

	@Override
	public int updateStatus(String id,UpDown_Status status) {
		return articlescrapMapper.updateArticlescrapRecommend(id, null, status);
	}


}

package com.dgjs.service.impl.content;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgjs.mapper.content.ArticlescrapMapper;
import com.dgjs.mapper.content.CommentsMapper;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.persistence.Articlescrap;
import com.dgjs.model.persistence.condition.ArticlescrapCondtion;
import com.dgjs.model.persistence.enhance.RecommedArticlescrapEnhance;
import com.dgjs.service.content.ArticlescrapService;

@Service
public class ArticlescrapServiceImpl implements ArticlescrapService{

	@Autowired
	ArticlescrapMapper articlescrapMapper;
	
	@Autowired
	CommentsMapper commentsMapper;
	
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

	@Override
	public List<Articlescrap> getArticlescrapByComments(int number) {
		List<Long> ids=commentsMapper.getComments(number);
		//如果有评论信息，则按最新评论时间排序获取文章信息
		if(ids!=null && !ids.isEmpty()){
			List<Articlescrap> list=articlescrapMapper.selectByIds(ids);
			if(list!=null&&!list.isEmpty()){
				Map<Long,Articlescrap> map = new HashMap<Long,Articlescrap>();
				for(Articlescrap articlescrap:list){
					map.put(articlescrap.getId(), articlescrap);
				}
				list.clear();
				for(Long id:ids){
					list.add(map.get(id));
				}
				return list;
			}
		}else{
			ArticlescrapCondtion articlescrapCondtion = new ArticlescrapCondtion();
			articlescrapCondtion.setBeginNum(0);
			articlescrapCondtion.setOnePageSize(number);
			return articlescrapMapper.listArticlescrap(articlescrapCondtion);
		}
		return null;
	}

	@Override
	public String getDadianArticlescrapIds(
			List<RecommedArticlescrapEnhance> recommedArticlescraps,
			List<Articlescrap> newArticlescraps,
			List<Articlescrap> commentsArticlescrap) {
		Set<Long> set = new HashSet<Long>();
		StringBuilder str = null;
		if(recommedArticlescraps!=null){
			for(RecommedArticlescrapEnhance recommedArticlescrapEnhance:recommedArticlescraps){
				set.add(recommedArticlescrapEnhance.getArticlescrap_id());
			}
		}
		if(newArticlescraps!=null){
			for(Articlescrap articlescrap:newArticlescraps){
				set.add(articlescrap.getId());
			}
		}
		if(commentsArticlescrap!=null){
			for(Articlescrap articlescrap:commentsArticlescrap){
				set.add(articlescrap.getId());
			}
		}
		if(set.size()>0){
			int index = 0;
		    str = new StringBuilder();
			for(Long id:set){
				if(index++ != set.size()-1){
					str.append(id+"#");
				}else{
					str.append(id);
				}
			}
		}
		return str == null?null:str.toString();
	}

}

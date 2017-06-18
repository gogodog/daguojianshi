package com.dgjs.service.impl.content;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgjs.es.mapper.content.ArticlescrapMapper;
import com.dgjs.mapper.content.CommentsMapper;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.persistence.Articlescrap;
import com.dgjs.model.persistence.condition.ArticlescrapCondtion;
import com.dgjs.service.content.ArticlescrapService;

@Service
public class ArticlescrapServiceImpl implements ArticlescrapService{

	@Autowired
	ArticlescrapMapper articlescrapMapper;
	
	@Autowired
	CommentsMapper commentsMapper;
	
	@Override
	public int saveArticlescrap(Articlescrap articlescrap){
		return articlescrapMapper.saveArticlescrap(articlescrap);
	}

	@Override
	public int updateArticlescrap(Articlescrap articlescrap) throws Exception{
		return articlescrapMapper.updateArticlescrap(articlescrap);
	}

	@Override
	public Articlescrap selectById(String id) {
		return articlescrapMapper.getArticlescrapIndex(id);
	}

	@Override
	public PageInfoDto<Articlescrap> listArticlescrap(ArticlescrapCondtion articlescrapCondtion) {
		return articlescrapMapper.listArticlescrap(articlescrapCondtion);
	}

	@Override
	public int deleteArticlescrap(String id) {
		return articlescrapMapper.deleteById(id);
	}

	@Override
	public List<Articlescrap> getArticlescrapByComments(int number) {
		List<String> ids=commentsMapper.getComments(number);
		//如果有评论信息，则按最新评论时间排序获取文章信息
		if(ids!=null && !ids.isEmpty()){
			StringBuilder str = new StringBuilder();
			for(int i=0;i<ids.size();i++){
				if(i==ids.size()-1){
					str.append(ids.get(i));
				}else{
					str.append(ids.get(i));
					str.append(",");
				}
			}
			List<Articlescrap> list=articlescrapMapper.getArticlescrapByIds(str.toString());
			if(list!=null&&!list.isEmpty()){
				Map<String,Articlescrap> map = new HashMap<String,Articlescrap>();
				for(Articlescrap articlescrap:list){
					map.put(articlescrap.getId(), articlescrap);
				}
				list.clear();
				for(String id:ids){
					list.add(map.get(id));
				}
				return list;
			}
		}else{
			ArticlescrapCondtion articlescrapCondtion = new ArticlescrapCondtion();
			articlescrapCondtion.setBeginNum(0);
			articlescrapCondtion.setOnePageSize(number);
			return articlescrapMapper.listArticlescrap(articlescrapCondtion).getObjects();
		}
		return null;
	}

	@Override
	public String getDadianArticlescrapIds(
			List<Articlescrap> recommedArticlescraps,
			List<Articlescrap> newArticlescraps,
			List<Articlescrap> commentsArticlescrap) {
		Set<String> set = new HashSet<String>();
		StringBuilder str = null;
		if(recommedArticlescraps!=null){
			for(Articlescrap articlescrap:recommedArticlescraps){
				set.add(articlescrap.getId());
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
			for(String id:set){
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

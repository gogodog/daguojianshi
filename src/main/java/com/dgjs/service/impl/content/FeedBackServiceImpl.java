package com.dgjs.service.impl.content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgjs.es.mapper.content.ArticlescrapMapper;
import com.dgjs.mapper.content.FeedBackMapper;
import com.dgjs.model.dto.FeedBackDto;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.dto.business.Articlescrap;
import com.dgjs.model.enums.Judge_Level;
import com.dgjs.model.persistence.FeedBack;
import com.dgjs.model.persistence.condition.FeedBackCondition;
import com.dgjs.service.content.FeedBackService;

@Service
public class FeedBackServiceImpl implements FeedBackService{

	@Autowired
	FeedBackMapper mapper;
	
	@Autowired
	ArticlescrapMapper articlescrapMapper;
	
	@Override
	public int save(FeedBack aJudge) {
		return mapper.save(aJudge);
	}

	@Override
	public int getLevelCount(String articlescrapId, Judge_Level judge_level) {
		return mapper.getLevelCount(articlescrapId, judge_level);
	}

	@Override
	public PageInfoDto<FeedBackDto> listFeedBack(FeedBackCondition condition) {
		int beginNum = (condition.getCurrentPage() - 1) * condition.getOnePageSize();
		condition.setBeginNum(beginNum);
		List<FeedBack> list=mapper.listFeedBack(condition);
		int totalResults=0;
		if(condition.isNeedTotalResults()){
			totalResults = mapper.countFeedBack(condition);
		}
		List<FeedBackDto> resultList = null;
		//从es获取文章标题
		if(list!=null&&list.size()>0){
			Set<String> aids = new HashSet<String>();
			for(FeedBack feedBack:list){
				aids.add(feedBack.getArticlescrap_id());
			}
			String[] ids = new String[aids.size()];
			int index = 0;
			for(String aid:aids){
				ids[index++] = aid;
			}
			List<Articlescrap> articlescrapList=articlescrapMapper.getArticlescrapByIds(ids);
			Map<String,String> map = new HashMap<String,String>();
			for(Articlescrap articlescrap:articlescrapList){
				map.put(articlescrap.getId(), articlescrap.getTitle());
			}
			resultList = new ArrayList<FeedBackDto>();
			for(FeedBack feedBack:list){
				FeedBackDto dto = new FeedBackDto();
				dto.setFeedBack(feedBack);
				dto.setTitle(map.get(feedBack.getArticlescrap_id()));
				resultList.add(dto);
			}
		}
		return PageInfoDto.getPageInfo(condition.getCurrentPage(), condition.getOnePageSize(), totalResults, resultList);
	}

}

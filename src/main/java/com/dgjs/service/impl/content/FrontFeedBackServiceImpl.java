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
import com.dgjs.mapper.content.FrontFeedBackMapper;
import com.dgjs.model.dto.FrontFeedBackDto;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.dto.business.Articlescrap;
import com.dgjs.model.enums.Judge_Level;
import com.dgjs.model.persistence.FrontFeedBack;
import com.dgjs.model.persistence.condition.FrontFeedBackCondition;
import com.dgjs.service.content.FrontFeedBackService;

@Service
public class FrontFeedBackServiceImpl implements FrontFeedBackService{

	@Autowired
	FrontFeedBackMapper frontFeedBackMapper;
	
	@Autowired
	ArticlescrapMapper articlescrapMapper;
	
	@Override
	public int save(FrontFeedBack feedBack) {
		return frontFeedBackMapper.save(feedBack);
	}

	@Override
	public int getLevelCount(String articlescrapId, Judge_Level judge_level) {
		return frontFeedBackMapper.getLevelCount(articlescrapId, judge_level);
	}

	@Override
	public PageInfoDto<FrontFeedBackDto> listFeedBack(FrontFeedBackCondition condition) {
		int beginNum = (condition.getCurrentPage() - 1) * condition.getOnePageSize();
		condition.setBeginNum(beginNum);
		List<FrontFeedBack> list=frontFeedBackMapper.listFeedBack(condition);
		int totalResults=0;
		if(condition.isNeedTotalResults()){
			totalResults = frontFeedBackMapper.countFeedBack(condition);
		}
		List<FrontFeedBackDto> resultList = null;
		//从es获取文章标题
		if(list!=null&&list.size()>0){
			Set<String> aids = new HashSet<String>();
			for(FrontFeedBack feedBack:list){
				if(feedBack.getArticlescrap_id()!=null){
					aids.add(feedBack.getArticlescrap_id());
				}
			}
			Map<String,String> map = null;
			if(aids.size()>0){
				String[] ids = new String[aids.size()];
				int index = 0;
				for(String aid:aids){
					ids[index++] = aid;
				}
				List<Articlescrap> articlescrapList=articlescrapMapper.getArticlescrapByIds(ids);
			    map = new HashMap<String,String>();
				for(Articlescrap articlescrap:articlescrapList){
					map.put(articlescrap.getId(), articlescrap.getTitle());
				}
			}
			resultList = new ArrayList<FrontFeedBackDto>();
			for(FrontFeedBack feedBack:list){
				FrontFeedBackDto dto = new FrontFeedBackDto();
				dto.setFeedBack(feedBack);
				if(map!=null)
				   dto.setTitle(map.get(feedBack.getArticlescrap_id()));
				resultList.add(dto);
			}
		}
		return PageInfoDto.getPageInfo(condition.getCurrentPage(), condition.getOnePageSize(), totalResults, resultList);
	}

}

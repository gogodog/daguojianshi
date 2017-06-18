package com.dgjs.service.impl.content;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgjs.mapper.content.CommentsMapper;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.persistence.Comments;
import com.dgjs.service.content.CommentsService;
import com.dgjs.utils.IdsUtils;

@Service
public class CommentsServiceImpl implements CommentsService{
	
	@Autowired
	CommentsMapper commentsMapper;

	@Override
	public int save(Comments comments) {
		comments.setId(IdsUtils.getUuId());
		comments.setComment_time(new Date());
		return commentsMapper.save(comments);
	}


	@Override
	public int updateStatus(String id, boolean is_show, String desc) {
		Comments comments = new Comments();
		comments.setIs_show(is_show);
		comments.setId(id);
		comments.setC_desc(desc);
		return commentsMapper.update(comments);
	}


	@Override
	public PageInfoDto<Comments> getCommentsByArticlescrapId(
			String articlescrapId, int currentPage, int onePageSize,
			boolean isNeedTotalResults) {
		int beginNum = (currentPage - 1) * onePageSize;
		List<Comments> list=commentsMapper.getCommentsByArticlescrapId(articlescrapId, beginNum, onePageSize);
		int totalResults=0;
		if(isNeedTotalResults){
			totalResults = commentsMapper.sizeCommentsByArticlescrapId(articlescrapId);
		}
		return PageInfoDto.getPageInfo(currentPage, onePageSize, totalResults, list);
	}

	
}

package com.dgjs.service.content;

import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.persistence.Comments;

public interface CommentsService {

	public int save(Comments comments);
	
	public PageInfoDto<Comments> getCommentsByArticlescrapId(String articlescrapId,int currentPage,int onePageSize,boolean isNeedTotalResults);
	
	public int updateStatus(String id,boolean is_show,String desc);
	
}

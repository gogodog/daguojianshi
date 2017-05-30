package com.dgjs.mapper.content;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dgjs.model.persistence.Comments;

public interface CommentsMapper {

	public int save(Comments comments);

	public int deleteById(Long id);
	
    public int update(Comments comments);
    
    public List<Comments> getCommentsByArticlescrapId(@Param("articlescrap_id")Long articlescrapId,@Param("beginNum")int beginNum,@Param("onePageSize")int onePageSize);
    
    public int sizeCommentsByArticlescrapId(Long articlescrapId);
    
    public List<Long> getComments(int number);
}
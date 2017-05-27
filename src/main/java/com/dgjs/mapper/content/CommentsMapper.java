package com.dgjs.mapper.content;

import java.util.List;

import com.dgjs.model.persistence.Comments;

public interface CommentsMapper {

	public int save(Comments comments);

	public int update(Comments comments);

	public Comments selectById(Long id);

	public List<Comments> list();

	public int deleteById(Long id);

}
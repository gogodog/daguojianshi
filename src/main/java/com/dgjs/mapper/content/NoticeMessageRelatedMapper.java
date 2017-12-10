package com.dgjs.mapper.content;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dgjs.model.enums.Message_Related_Type;
import com.dgjs.model.persistence.NoticeMessage;
import com.dgjs.model.persistence.NoticeMessageRelated;

public interface NoticeMessageRelatedMapper {

	public int save(NoticeMessageRelated noticeMessageRelated);
	
	public List<NoticeMessage> getByTypeRelatedId(@Param("type")Message_Related_Type type,@Param("relatedId")Long relatedId);
	
}

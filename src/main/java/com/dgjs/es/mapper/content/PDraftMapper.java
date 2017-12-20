package com.dgjs.es.mapper.content;

import java.util.Date;

import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.dto.business.PDraft;
import com.dgjs.model.enums.Pending_Status;
import com.dgjs.model.persistence.condition.PDraftCondition;

public interface PDraftMapper {

	/*
	 * 新增草稿
	 */
    public int saveDraft(PDraft draft);
	
    /*
	 * 查询草稿，不带内容
	 */
	public PDraft selectById(String id);
	
	/*
	 * 查询草稿，带内容
	 */
	public PDraft selectByIdAll(String id);
	
	/*
	 * 草稿列表
	 */
	public PageInfoDto<PDraft> listDrafts(PDraftCondition condition);
	
	/*
	 * 修改草稿
	 */
	public int updateDraft(PDraft draft) throws Exception;
	
	/*
	 * 删除草稿
	 */
	public int deleteDraft(String id);
	
	/*
	 * 查询内容
	 */
	public String getContent(String id);
	
	/*
	 * 提审
	 */
	public int submitAudit(String id) throws Exception;
	
	/*
	 * 审核
	 */
	public int audit(String id,Pending_Status status,Integer audit_user_id) throws Exception;
	
	/*
	 * 发布
	 */
	public PDraft publish(String id,Integer publish_user_id,Date publish_time) throws Exception;
	
}

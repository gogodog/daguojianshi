package com.dgjs.service.impl.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dgjs.es.mapper.content.DraftMapper;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.dto.business.Draft;
import com.dgjs.model.persistence.condition.DraftCondition;
import com.dgjs.service.content.DraftService;
import com.dgjs.utils.PictureUtils;
import com.mysql.jdbc.StringUtils;

@Service
public class DraftServiceImpl implements DraftService{
	
	@Autowired
	DraftMapper draftMapper;
	
	@Value("${imageContextPath}${webBasePath}")
	private String webContextPath;

	@Override
	public int saveDraft(Draft draft) {
		return draftMapper.saveDraft(draft);
	}

	@Override
	public Draft selectById(String id) {
		return draftMapper.selectById(id);
	}

	@Override
	public PageInfoDto<Draft> listDrafts(DraftCondition condition) {
		return draftMapper.listDrafts(condition);
	}

	@Override
	public int updateDraft(Draft draft) throws Exception {
		return draftMapper.updateDraft(draft);
	}

	@Override
	public int deleteDraft(String id) {
		return draftMapper.deleteDraft(id);
	}

	@Override
	public Draft selectByIdAll(String id) {
		Draft draft = draftMapper.selectById(id);
		String content = draftMapper.getContent(id);
		if(draft!=null && !StringUtils.isNullOrEmpty(content)){
			draft.setContent(PictureUtils.render(draft.getPictures(), content,webContextPath));
		}
		return draft;
	}

}

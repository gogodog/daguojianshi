package com.dgjs.service.impl.content;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgjs.es.mapper.content.ArticlescrapMapper;
import com.dgjs.mapper.content.IndexConfigMapper;
import com.dgjs.model.dto.IndexConfigDto;
import com.dgjs.model.dto.business.Articlescrap;
import com.dgjs.model.persistence.IndexConfig;
import com.dgjs.service.content.IndexConfigService;
import com.dgjs.utils.StringUtils;

@Service
public class IndexConfigServiceImpl implements IndexConfigService{

	@Autowired
	IndexConfigMapper mapper;
	
	@Autowired
	ArticlescrapMapper articlescrapMapper;
	
	@Override
	public int save(IndexConfig indexConfig) {
		return mapper.save(indexConfig);
	}

	@Override
	public List<IndexConfigDto> list(IndexConfig indexConfig) {
		List<IndexConfigDto> result = null;
		List<IndexConfig> list=mapper.list(indexConfig);
		if(list!=null && list.size()!=0){
			List<String> aids = new ArrayList<String>();
			for(IndexConfig ic:list){
				aids.add(ic.getAid());
			}
			List<Articlescrap> articlescrapList=articlescrapMapper.getArticlescrapByIds(StringUtils.parseListToArray(aids));
			Map<String,Articlescrap> map = Articlescrap.parseListToMap(articlescrapList);
			if(map!=null){
			    result = new ArrayList<IndexConfigDto>();
				for(IndexConfig ic:list){
					IndexConfigDto dto = getIndexConfigDto(ic,map);
					result.add(dto);
				}
			}
			
		}
		return result;
	}

	@Override
	public int update(IndexConfig indexConfig) {
		return mapper.update(indexConfig);
	}

	@Override
	public int delete(Long id) {
		return mapper.deleteById(id);
	}

	@Override
	public IndexConfigDto selectById(Long id) {
		IndexConfigDto dto = null;
		IndexConfig indexConfig=mapper.selectById(id);
		if(indexConfig!=null){
			String[] aids = {indexConfig.getAid()};
			List<Articlescrap> articlescrapList=articlescrapMapper.getArticlescrapByIds(aids);
			Map<String,Articlescrap> map = Articlescrap.parseListToMap(articlescrapList);
			if(map!=null){
				dto=getIndexConfigDto(indexConfig,map);
			}
		}
		return dto;
	}
	
	private IndexConfigDto getIndexConfigDto(IndexConfig ic,Map<String,Articlescrap> map){
		IndexConfigDto dto = null;
		if(ic!=null){
			dto = new IndexConfigDto();
			dto.setIndexConfig(ic);
			String pictures = ic.getPictures();
			if(!StringUtils.isNullOrEmpty(pictures)){
				String[] pics=pictures.split(",");
				dto.setPics(pics);
			}
			Articlescrap articlescrap = map.get(ic.getAid());
			dto.setArticlescrap(articlescrap);
		}
		return dto;
	}

}

package com.dgjs.service.impl.ad;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgjs.mapper.ad.AdvertisementMapper;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.persistence.Advertisement;
import com.dgjs.model.persistence.condition.AdvertisementCondtion;
import com.dgjs.service.ad.AdvertisementService;

@Service
public class AdvertisementServiceImpl implements AdvertisementService{

	@Autowired
	private AdvertisementMapper advertisementMapper;

	@Override
	public int saveAdvertisement(Advertisement advertisement) {
		return advertisementMapper.saveAdvertisement(advertisement);
	}

	@Override
	public int updateAdvertisement(Advertisement advertisement) {
		return advertisementMapper.updateAdvertisement(advertisement);
	}

	@Override
	public PageInfoDto<Advertisement> listAdvertisement(AdvertisementCondtion condition) {
		condition.setBeginNum((condition.getCurrentPage()-1)*condition.getOnePageSize());
		List<Advertisement> list=advertisementMapper.listAdvertisement(condition);
		int totalResults=0;
		if(condition.isNeedTotalResults()){
			totalResults = advertisementMapper.sizeListAdvertisement(condition);
		}
		return PageInfoDto.getPageInfo(condition.getCurrentPage(), condition.getOnePageSize(), totalResults, list);
	}

	@Override
	public int deleteById(Long id) {
		return advertisementMapper.deleteById(id);
	}

	@Override
	public Advertisement selectById(Long id) {
		return advertisementMapper.selectById(id);
	}

	@Override
	public String getDadianAdvertisementIds(List<Advertisement> list) {
		StringBuilder str = null;
		if(list!=null){
			int index = 0;
			str = new StringBuilder();
			for(Advertisement advertisement:list){
				if(index++ !=list.size() - 1){
					str.append(advertisement.getId()+"#");
				}else{
					str.append(advertisement.getId());
				}
			}
		}
		return str==null?null:str.toString();
	}
	
	
}

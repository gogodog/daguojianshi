package com.dgjs.service.ad;


import java.util.List;

import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.persistence.Advertisement;
import com.dgjs.model.persistence.condition.AdvertisementCondtion;

public interface AdvertisementService {

	public int saveAdvertisement(Advertisement advertisement);
	
	public int updateAdvertisement(Advertisement advertisement);
	
	public PageInfoDto<Advertisement> listAdvertisement(AdvertisementCondtion condition);
	
	public int deleteById(Long id); 
	
	public Advertisement selectById(Long id);
	
	public String getDadianAdvertisementIds(List<Advertisement> list);
}

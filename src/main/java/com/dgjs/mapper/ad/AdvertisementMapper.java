package com.dgjs.mapper.ad;

import java.util.List;

import com.dgjs.model.persistence.Advertisement;
import com.dgjs.model.persistence.condition.AdvertisementCondtion;

public interface AdvertisementMapper {

	public int saveAdvertisement(Advertisement advertisement);
	
	public int updateAdvertisement(Advertisement advertisement);
	
	public Advertisement selectById(Long id);
	
	public List<Advertisement> listAdvertisement(AdvertisementCondtion condition);
	
	public int sizeListAdvertisement(AdvertisementCondtion condition);
	
	public int deleteById(Long id);
}

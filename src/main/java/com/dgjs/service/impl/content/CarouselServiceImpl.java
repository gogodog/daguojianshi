package com.dgjs.service.impl.content;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgjs.mapper.content.CarouselMapper;
import com.dgjs.model.persistence.Carousel;
import com.dgjs.service.content.CarouselService;

@Service
public class CarouselServiceImpl implements CarouselService{

	@Autowired
	CarouselMapper carouselMapper;
	
	@Override
	public int saveCarousel(Carousel carousel) {
		return carouselMapper.saveCarousel(carousel);
	}

	@Override
	public List<Carousel> listCarousel(Carousel carousel) {
		return carouselMapper.listCarousel(carousel);
	}

	@Override
	public Carousel selectById(Long id) {
		return carouselMapper.selectById(id);
	}

	@Override
	public int deleteById(Long id) {
		// TODO Auto-generated method stub
		return carouselMapper.deleteById(id);
	}
	
	@Override
	public int updateCarousel(Carousel carousel){
		return carouselMapper.updateCarousel(carousel);
	}

	@Override
	public int saveOrUpdateCarousel(Carousel carousel) {
		int flag=0;
		if(carousel.getId()!=null){
			flag=carouselMapper.updateCarousel(carousel);
		}else{
			flag=carouselMapper.saveCarousel(carousel);
		}
		return flag;
	}

}

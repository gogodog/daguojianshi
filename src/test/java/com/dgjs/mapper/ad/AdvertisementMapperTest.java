package com.dgjs.mapper.ad;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import net.coobird.thumbnailator.Thumbnails;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.dgjs.model.enums.Ad_Position;
import com.dgjs.model.persistence.Advertisement;
import com.dgjs.model.persistence.condition.AdvertisementCondtion;
import com.dgjs.utils.PictureUtils;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = "classpath:spring-*.xml") 
public class AdvertisementMapperTest {

	@Autowired
	AdvertisementMapper mapper;
	
	@Test
	public void testListAdvertisement(){
		AdvertisementCondtion advertisementCondtion=new AdvertisementCondtion();
		List<Advertisement> list = mapper.listAdvertisement(new AdvertisementCondtion());
		advertisementCondtion.setAdPositions(Arrays.asList(Ad_Position.INDEX_FIRST,Ad_Position.INDEX_SECOND));
		System.out.println(JSON.toJSONString(list, true));
	}
	
	@Test
	public void testPicture() throws IOException{
		int size = 200;
		String input="/Users/user/Documents/pic/2.jpg";
		String outputFile="/Users/user/Documents/pic/p1/2.jpg";
		 Thumbnails.Builder<File> fileBuilder = Thumbnails.of(input).scale(1f).outputQuality(1f);
         BufferedImage src = fileBuilder.asBufferedImage();
         System.out.println(src.getHeight(null)+"==========="+ src.getWidth(null));
         if(src.getHeight(null) > size || src.getWidth(null) > size) {
             Thumbnails.Builder<File> builder = Thumbnails.of(input);
             builder.size(size, size); //取最大的尺寸变成size，然后等比缩放
             builder.outputQuality(1f).toFile(outputFile);
         } else {
             Thumbnails.of(input).scale(3.0).outputQuality(3f).toFile(outputFile);
         }
	}
}

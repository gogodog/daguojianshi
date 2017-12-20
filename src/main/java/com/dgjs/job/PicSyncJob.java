package com.dgjs.job;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.dto.UserPicsDto;
import com.dgjs.model.dto.business.PDraft;
import com.dgjs.model.dto.business.entity.Pics;
import com.dgjs.model.enums.Pending_Status;
import com.dgjs.model.persistence.condition.PDraftCondition;
import com.dgjs.service.content.PDraftService;
import com.dgjs.service.content.UserPicsService;
import com.dgjs.utils.StringUtils;

@Component
public class PicSyncJob {
	
	@Autowired
	PDraftService draftService;
	
	@Autowired
	UserPicsService userPicsService;
	

	
//	@Scheduled(cron = "0 0 2 * * ?")
	public void syncServerPic(){
		String filePath = "/usr/image/userPics";
		filePath="/Users/user/Documents/pic";
		File file = new File(filePath);
		File[] userFiles = file.listFiles();
		for(File f:userFiles){
			Integer adminId=Integer.parseInt(f.getName());
			PDraftCondition condition = new PDraftCondition();
			condition.setUserId(adminId);
			String[] includes = {"id","pictures"};
			condition.setIncludes(includes);
			PageInfoDto<PDraft> pageinfo = draftService.listDrafts(condition);
			List<PDraft> drafts = pageinfo.getObjects();
			List<String> picsList = new ArrayList<String>();
			while(drafts!=null && drafts.size()>0){
				for(PDraft draft:drafts){
					String[] pics = draft.getPictures();
					picsList.addAll(Arrays.asList(pics));
				}
				if(drafts.size()<pageinfo.getOnePageSize()){
					break;
				}else{
					condition.setCurrentPage(pageinfo.getCurrentPage()+1);
					pageinfo = draftService.listDrafts(condition);
					drafts = pageinfo.getObjects();
				}
			}
			
			UserPicsDto userPicsDto = userPicsService.selectById(adminId);
			List<Pics> dtoPics= userPicsDto.getPics();
			if(dtoPics!=null && dtoPics.size()>0){
				for(Pics p:dtoPics){
					picsList.add(p.getUrl());
				}
			}
			if(picsList!=null&&picsList.size()>0){
				String adminPicPath  = StringUtils.jointString(filePath,File.separator,f.getName());
				File picPaths = new File(adminPicPath);
				File[] images = picPaths.listFiles();
				for(File image:images){
					if(picsList.contains(image.getName())){
						continue;
					}else{
						String removeFilePath=StringUtils.jointString(adminPicPath,File.separator,image.getName());
						File removeFile = new File(removeFilePath);
						removeFile.delete();
					}
				}
			}
		}
	}
	
}

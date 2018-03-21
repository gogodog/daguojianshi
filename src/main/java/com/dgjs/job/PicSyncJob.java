package com.dgjs.job;

import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.dto.UserPicsDto;
import com.dgjs.model.dto.business.Articlescrap;
import com.dgjs.model.dto.business.Draft;
import com.dgjs.model.dto.business.entity.Pics;
import com.dgjs.model.enums.Pic_Sync_Status;
import com.dgjs.model.persistence.condition.ArticlescrapCondtion;
import com.dgjs.model.persistence.condition.DraftCondition;
import com.dgjs.service.content.ArticlescrapService;
import com.dgjs.service.content.DraftService;
import com.dgjs.service.content.UserPicsService;
import com.dgjs.utils.StringUtils;

@Component
public class PicSyncJob {
	
	@Autowired
	DraftService draftService;
	
	@Autowired
	UserPicsService userPicsService;
	
	@Autowired
	ArticlescrapService articlescrapService;
	
	
//	@Scheduled(cron = "0 0 2 * * ?")
	public void syncServerPic(){
		List<String> filePaths = Arrays.asList("/p_50/","/p_100/","/");
		String filePath = "/usr/image/userPics";
		filePath="/Users/user/Documents/pic";
		File file = new File(filePath);
		File[] userFiles = file.listFiles();
		for(File f:userFiles){
			Integer adminId=Integer.parseInt(f.getName());
			DraftCondition condition = new DraftCondition();
			condition.setUserId(adminId);
			String[] includes = {"id","pictures"};
			condition.setIncludes(includes);
			PageInfoDto<Draft> pageinfo = draftService.listDrafts(condition);
			List<Draft> drafts = pageinfo.getObjects();
			Set<String> picsList = new HashSet<String>();
			while(drafts!=null && drafts.size()>0){
				for(Draft draft:drafts){
					String[] pics = draft.getPictures();
					for(String pic:pics){
						picsList.add(getLastArrayStr(pic));
					}
					picsList.add(getLastArrayStr(draft.getShowPic()));
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
					picsList.add(getLastArrayStr(p.getUrl()));
				}
			}
			ArticlescrapCondtion articlescrapCondtion = new ArticlescrapCondtion();
			articlescrapCondtion.setPicSyncStatus(Arrays.asList(Pic_Sync_Status.UNSYNC,Pic_Sync_Status.SYNCHING));
			articlescrapCondtion.setUserId(adminId);
			PageInfoDto<Articlescrap> articlescrapPageInfo = articlescrapService.listArticlescrap(articlescrapCondtion);
			List<Articlescrap> articlescraps = articlescrapPageInfo.getObjects();
			while(articlescraps!=null && articlescraps.size()>0){
				for(Articlescrap articlescrap:articlescraps){
					String[] pics = articlescrap.getPictures();
					for(String pic:pics){
						picsList.add(getLastArrayStr(pic));
					}
					picsList.add(getLastArrayStr(articlescrap.getShowPic()));
				}
				if(articlescraps.size()<articlescrapPageInfo.getOnePageSize()){
					break;
				}else{
					articlescrapCondtion.setCurrentPage(pageinfo.getCurrentPage()+1);
					articlescrapPageInfo = articlescrapService.listArticlescrap(articlescrapCondtion);
					articlescraps = articlescrapPageInfo.getObjects();
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
						for(String path:filePaths){
							String imagePath = StringUtils.jointString(adminPicPath,path,image.getName());
							File removeFile = new File(imagePath);
							removeFile.delete();
						}
					}
				}
			}
		}
	}
	
	private String getLastArrayStr(String path){
		String[] paths = path.split(File.separator);
		return paths[paths.length-1];
	}
	
	public static void main(String[] args) {
		String str = "/user/pics/1/1.jpg";
		String[] files = str.split(File.separator);
		String file = files[files.length-1];
		System.out.println(file);
	}
	
}

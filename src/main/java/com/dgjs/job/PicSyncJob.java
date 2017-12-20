package com.dgjs.job;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.dto.UserPicsDto;
import com.dgjs.model.dto.business.PDraft;
import com.dgjs.model.dto.business.entity.Pics;
import com.dgjs.model.enums.Pending_Status;
import com.dgjs.model.enums.Pic_Sync_Status;
import com.dgjs.model.persistence.condition.PDraftCondition;
import com.dgjs.service.content.PDraftService;
import com.dgjs.service.content.UserPicsService;
import com.dgjs.utils.StringUtils;

import freemarker.log.Logger;

@Component
public class PicSyncJob {
	
	private Logger log = Logger.getLogger(this.getClass().getName());
	
	@Autowired
	PDraftService draftService;
	
	@Autowired
	UserPicsService userPicsService;

//	@Scheduled(cron = "0 */5 * * * ?")
    public void picSyncJob() {
		PDraftCondition condition = new PDraftCondition();
		condition.setStatus(Pending_Status.PUBLISH_PENDING);
		condition.setPicSyncStatus(Arrays.asList(Pic_Sync_Status.SYNCHING,Pic_Sync_Status.UNSYNC));
		String[] includes={"id"};
		condition.setIncludes(includes);
		PageInfoDto<PDraft> pageinfo = draftService.listDrafts(condition);
		List<PDraft> draftList = pageinfo.getObjects();
		while(draftList!=null && draftList.size()>0){
			for(PDraft draft : draftList){
				try {
					draftService.movePic(draft.getId());
				} catch (Exception e) {
					log.error("picSyncJob exception,with id="+draft.getId(), e);
				}
			}
			if(draftList.size() < pageinfo.getOnePageSize()){
				break;
			}else{
				condition.setCurrentPage(pageinfo.getCurrentPage()+1);
				pageinfo = draftService.listDrafts(condition);
				draftList = pageinfo.getObjects();
			}
		}
	}
	
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
			
			PDraftCondition pdcondition  = new PDraftCondition();
			pdcondition.setUserId(adminId);
			pdcondition.setStatus(Pending_Status.Audit_FAIL);
			pdcondition.setIncludes(includes);
			PageInfoDto<PDraft> pdPageInfo= draftService.listDrafts(pdcondition);
			List<PDraft> pdList= pdPageInfo.getObjects();
			while(pdList!=null && pdList.size()>0){
				for(PDraft pd:pdList){
					String[] pictures=pd.getPictures();
					picsList.addAll(Arrays.asList(pictures));
				}
				if(pdList.size()<pdPageInfo.getOnePageSize()){
					break;
				}else{
					pdcondition.setCurrentPage(pageinfo.getCurrentPage()+1);
					pdPageInfo=draftService.listDrafts(pdcondition);
					pdList=pdPageInfo.getObjects();
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

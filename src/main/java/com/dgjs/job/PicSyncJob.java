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
import com.dgjs.model.dto.business.Draft;
import com.dgjs.model.dto.business.Pending;
import com.dgjs.model.dto.business.entity.Pics;
import com.dgjs.model.enums.Pending_Status;
import com.dgjs.model.enums.Pic_Sync_Status;
import com.dgjs.model.persistence.condition.DraftCondition;
import com.dgjs.model.persistence.condition.PendingCondition;
import com.dgjs.service.content.DraftService;
import com.dgjs.service.content.PendingService;
import com.dgjs.service.content.UserPicsService;
import com.dgjs.utils.StringUtils;

import freemarker.log.Logger;

@Component
public class PicSyncJob {
	
	private Logger log = Logger.getLogger(this.getClass().getName());
	
	@Autowired
	PendingService pendingService;
	
	@Autowired
	DraftService draftService;
	
	@Autowired
	UserPicsService userPicsService;

//	@Scheduled(cron = "0 */5 * * * ?")
    public void picSyncJob() {
		PendingCondition condition = new PendingCondition();
		condition.setStatus(Pending_Status.PUBLISH_PENDING);
		condition.setPicSyncStatus(Arrays.asList(Pic_Sync_Status.SYNCHING,Pic_Sync_Status.UNSYNC));
		PageInfoDto<Pending> pageinfo = pendingService.listPending(condition);
		List<Pending> pendingList = pageinfo.getObjects();
		while(pendingList!=null && pendingList.size()>0){
			for(Pending pending : pendingList){
				try {
					pendingService.movePic(pending.getId());
				} catch (Exception e) {
					log.error("picSyncJob exception,with id="+pending.getId(), e);
				}
			}
			if(pendingList.size() < pageinfo.getOnePageSize()){
				break;
			}else{
				condition.setCurrentPage(pageinfo.getCurrentPage()+1);
				pageinfo = pendingService.listPending(condition);
				pendingList = pageinfo.getObjects();
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
			DraftCondition condition = new DraftCondition();
			condition.setUser_id(adminId);
			PageInfoDto<Draft> pageinfo = draftService.listDrafts(condition);
			List<Draft> drafts = pageinfo.getObjects();
			List<String> picsList = new ArrayList<String>();
			while(drafts!=null && drafts.size()>0){
				for(Draft draft:drafts){
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
			
			PendingCondition pdcondition  = new PendingCondition();
			pdcondition.setUserId(adminId);
			pdcondition.setStatus(Pending_Status.Audit_FAIL);
			PageInfoDto<Pending> pdPageInfo= pendingService.listPending(pdcondition);
			List<Pending> pdList= pdPageInfo.getObjects();
			while(pdList!=null && pdList.size()>0){
				for(Pending pd:pdList){
					String[] pictures=pd.getPictures();
					picsList.addAll(Arrays.asList(pictures));
				}
				if(pdList.size()<pdPageInfo.getOnePageSize()){
					break;
				}else{
					pdcondition.setCurrentPage(pageinfo.getCurrentPage()+1);
					pdPageInfo=pendingService.listPending(pdcondition);
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

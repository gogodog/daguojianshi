package com.dgjs.job;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.dto.business.Articlescrap;
import com.dgjs.model.enums.Articlescrap_Status;
import com.dgjs.model.persistence.condition.ArticlescrapCondtion;
import com.dgjs.service.content.ArticlescrapService;
import com.dgjs.utils.DateUtils;

import freemarker.log.Logger;

@Component
public class ArticlePublishSyncJob {

	@Autowired
	ArticlescrapService articlescrapService;
	
	private Logger log = Logger.getLogger(this.getClass().getName());
	
//	@Scheduled(cron = "0 */5 * * * ?")
	public void publishArticle(){
		ArticlescrapCondtion articlescrapCondtion = new ArticlescrapCondtion();
		articlescrapCondtion.setStatus(Articlescrap_Status.INIT);
		articlescrapCondtion.setShowTimeTo(DateUtils.parseStringFromDate(new Date(), DateUtils.DEFAULT));
		articlescrapCondtion.setNeedContent(true);
		try{
			PageInfoDto<Articlescrap> pageinfo = articlescrapService.listArticlescrap(articlescrapCondtion);
			List<Articlescrap> articlescrapList = pageinfo==null?null:pageinfo.getObjects();
			while(articlescrapList!=null){
			     articlescrapService.bulkUpdateStatus(articlescrapList, Articlescrap_Status.UP);
			     pageinfo = articlescrapService.listArticlescrap(articlescrapCondtion);
				 articlescrapList = pageinfo==null?null:pageinfo.getObjects();
			}
		}catch(Exception e){
			log.error("PublishSyncJob exception", e);
		}
	}
}

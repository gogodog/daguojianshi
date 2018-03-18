package com.dgjs.job;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.dgjs.model.dto.InvitationCodeDto;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.enums.Invitation_Status;
import com.dgjs.model.persistence.InvitationCode;
import com.dgjs.model.persistence.condition.InvitationCondition;
import com.dgjs.service.admin.InvitationCodeService;

@Component
public class InvitationCodeJob {
	
	@Autowired
	InvitationCodeService invitationCodeService;

	@Scheduled(cron = "0 0 3 * * ?")
	public void updateStatus(){
		InvitationCondition condition = new InvitationCondition();
		condition.setStatusList(Arrays.asList(Invitation_Status.VALID));
		condition.setToDate(new Date());
		PageInfoDto<InvitationCodeDto> pageinfo = invitationCodeService.list(condition);
		List<InvitationCodeDto> list = pageinfo==null?null:pageinfo.getObjects();
		while(list!=null && list.size()>0){
			List<Long> ids = new ArrayList<Long>();
			for(InvitationCodeDto invitationCodeDto:list){
				InvitationCode invitationCode = invitationCodeDto.getInvitationCode();
				ids.add(invitationCode.getId());
			}
		    invitationCodeService.updateStatus(ids, Invitation_Status.OUTOFDATE);
		    pageinfo = invitationCodeService.list(condition);
		    list = pageinfo==null?null:pageinfo.getObjects();
		}
	}
}

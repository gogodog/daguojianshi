package com.dgjs.service.impl.admin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgjs.constants.Constants;
import com.dgjs.mapper.admin.InvitationCodeMapper;
import com.dgjs.model.dto.InvitationCodeDto;
import com.dgjs.model.dto.PageInfoDto;
import com.dgjs.model.enums.Invitation_Status;
import com.dgjs.model.persistence.InvitationCode;
import com.dgjs.model.persistence.condition.AdminUserCondition;
import com.dgjs.model.persistence.condition.InvitationCondition;
import com.dgjs.model.persistence.result.AdminUserResult;
import com.dgjs.service.admin.AdminUserService;
import com.dgjs.service.admin.InvitationCodeService;
import com.dgjs.utils.RandomUtils;

@Service
public class InvitationCodeServiceImpl implements InvitationCodeService{

	@Autowired
	InvitationCodeMapper invitationCodeMapper;
	
	@Autowired
	AdminUserService adminUserService;
	
	@Override
	public InvitationCode produce(Integer fromUserId) {
		if(fromUserId==null){
			return null;
		}
		InvitationCode invitationCode = new InvitationCode();
		invitationCode.setFrom_user_id(fromUserId);
		invitationCode.setStatus(Invitation_Status.VALID);
		invitationCode.setValid_time(getValidTime());
		String code = RandomUtils.getRandomStr(6);
		InvitationCode dvcode = invitationCodeMapper.getValidCode(code);
		while(dvcode!=null){
			code = RandomUtils.getRandomStr(6);
			dvcode = invitationCodeMapper.getValidCode(code);
		}
		invitationCode.setInvitation_code(code);
		int flag = invitationCodeMapper.save(invitationCode);
		if(flag > 0){
			return invitationCode;
		}
		return null;
	}

	private Date getValidTime(){
		int maxDays = Constants.MAX_INVITATION_VALID_TIME;
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, maxDays);
		Date date = calendar.getTime();
		return date;
	}

	@Override
	public PageInfoDto<InvitationCodeDto> list(InvitationCondition condition) {
		condition.setBeginNum((condition.getCurrentPage()-1)*condition.getOnePageSize());
		int totalResults=0;
		if(condition.isNeedTotalResults()){
			totalResults = invitationCodeMapper.count(condition);
		}
		List<InvitationCode> list=invitationCodeMapper.list(condition);
		if(list!=null&&!list.isEmpty()){
			List<Integer> ids = new ArrayList<Integer>();
			for(InvitationCode invitationCode:list){
				if(invitationCode.getFrom_user_id()!=null){
					ids.add(invitationCode.getFrom_user_id());
				}
				if(invitationCode.getTo_user_id()!=null){
					ids.add(invitationCode.getTo_user_id());
				}
			}
			//
		    AdminUserCondition ac = new AdminUserCondition();
		    ac.setOnePageSize(500);
		    ac.setAdminIds(ids);
		    PageInfoDto<AdminUserResult> adminPageInfo = adminUserService.list(ac);
		    List<InvitationCodeDto> resultList = new ArrayList<InvitationCodeDto>();
		    Map<Integer,String> map = new HashMap<Integer,String>();
		    List<AdminUserResult> adminUserList = adminPageInfo.getObjects();
		    for(AdminUserResult adminUserResult:adminUserList){
		       map.put(adminUserResult.getId(), adminUserResult.getUsername());
		    }
		    for(InvitationCode invitationCode:list){
		       InvitationCodeDto dto = new InvitationCodeDto();
		       dto.setInvitationCode(invitationCode);
		       dto.setFromUserName(map.get(invitationCode.getFrom_user_id()));
		       dto.setToUserName(map.get(invitationCode.getTo_user_id()));
		       resultList.add(dto);
		    }
		    return PageInfoDto.getPageInfo(condition.getCurrentPage(), condition.getOnePageSize(), totalResults, resultList);
		}
		return null;
	}

	@Override
	public int getValidCount(Integer fromUserId) {
		return invitationCodeMapper.getValidCount(fromUserId);
	}

	@Override
	public int updateStatus(List<Long> ids, Invitation_Status status) {
		return invitationCodeMapper.updateStatus(ids, status);
	}
	
}

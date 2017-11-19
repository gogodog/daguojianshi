package com.dgjs.mapper.admin;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.dgjs.model.enums.OperateEnum;
import com.dgjs.model.persistence.OperateLog;
import com.dgjs.model.persistence.condition.OperateLogCondition;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = "classpath:spring-*.xml") 
public class OperateLogMapperTest {
	
	@Autowired
	OperateLogMapper mapper;

	@Test
	public void testSave(){
		OperateLog operateLog = new OperateLog();
		operateLog.setAdmin_id(1);
		operateLog.setErrorMessage("errorMessage");
		operateLog.setIp("127.0.0.1");
		operateLog.setIsSuccess(1);
		operateLog.setOperate_desc("operate desc");
		operateLog.setOperate_type(OperateEnum.Browse);
		operateLog.setParam("param");
		int flag = mapper.save(operateLog);
		System.out.println(flag);
	}
	
	@Test
	public void testList(){
		OperateLogCondition condition = new OperateLogCondition();
		condition.setOperateTypes(Arrays.asList(OperateEnum.Add,OperateEnum.Update));
		condition.setAdminIds((Arrays.asList(1)));
		condition.setCreateTimeFrom("2017-11-12 00:00:00");
		condition.setCreateTimeTo("2017-11-13 11:00:00");
		List<OperateLog> list = mapper.list(condition);
		System.out.println(JSON.toJSONString(list, true));
	}
}

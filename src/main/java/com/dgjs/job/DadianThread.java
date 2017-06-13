package com.dgjs.job;

import java.util.LinkedList;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.dgjs.model.view.DadianView;
import com.dgjs.service.common.DataService;

import freemarker.log.Logger;

@Component("DadianThread") 
public class DadianThread implements Runnable,ApplicationListener<ContextRefreshedEvent>{

	public static final Queue<DadianView> QUEUE = new LinkedList<DadianView>();
	@Autowired
	DataService dataservice;
	DataService innerdataservice;
	private Logger log = Logger.getLogger(this.getClass().getName()); 
	
	public DadianThread() {
	}
	
	public DadianThread(DataService innerdataservice) {
		this.innerdataservice = innerdataservice;
	}

	@Override
	public void run() {
		while(true){
//			log.info("线程循环开始...");
			try {
				Thread.sleep(2000);
			    if(DadianThread.QUEUE.isEmpty()){
				   continue;
			    }
			    DadianView view = DadianThread.QUEUE.poll();
			    int count = this.innerdataservice.insertDaDian(view);
			    if(count != 1){
				    log.info("异常插入：" + JSON.toJSONString(view));
			    }
			 } catch (Exception e) {
				log.error("DadianThread 挂了", e);
			}
		}
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (event.getApplicationContext().getDisplayName().equals("Root WebApplicationContext")) {
			System.out.println("spring 初始化打点消费线程类");
			new Thread(new DadianThread(this.dataservice)).start();
		}
	}
}

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

@Component("DadianThread") 
public class DadianThread implements Runnable,ApplicationListener<ContextRefreshedEvent>{

	public static final Queue<DadianView> QUEUE = new LinkedList<DadianView>();
	@Autowired
	DataService dataservice;
	DataService innerdataservice;
	
	public DadianThread() {
	}
	
	public DadianThread(DataService innerdataservice) {
		this.innerdataservice = innerdataservice;
	}

	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				
			}
			if(DadianThread.QUEUE.isEmpty()){
				continue;
			}
			DadianView view = DadianThread.QUEUE.poll();
			int count = this.innerdataservice.insertDaDian(view);
			if(count != 1){
				System.out.println("异常插入：" + JSON.toJSONString(view));
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

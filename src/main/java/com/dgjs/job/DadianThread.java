package com.dgjs.job;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.alibaba.fastjson.JSON;
import com.dgjs.model.view.DadianView;
import com.dgjs.service.impl.common.DataServiceImpl;

public class DadianThread implements Runnable,ApplicationListener<ContextRefreshedEvent>{

	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				
			}
			if(DataServiceImpl.getQueue().isEmpty()){
				System.out.println("队列为空");
				continue;
			}
			DadianView view = DataServiceImpl.getQueue().poll();
			System.out.println(JSON.toJSONString(view));
		}
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		System.out.println("spring 初始化线程类");
		new Thread(new DadianThread()).start();
	}
}

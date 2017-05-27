package com.dgjs.job;

import java.util.LinkedList;
import java.util.Queue;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.alibaba.fastjson.JSON;
import com.dgjs.model.view.DadianView;

public class DadianThread implements Runnable,ApplicationListener<ContextRefreshedEvent>{

	public static final Queue<DadianView> QUEUE = new LinkedList<DadianView>();
	
	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				
			}
			if(DadianThread.QUEUE.isEmpty()){
				System.out.println("队列为空");
				continue;
			}
			DadianView view = DadianThread.QUEUE.poll();
			System.out.println(JSON.toJSONString(view));
		}
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		System.out.println("spring 初始化打点消费线程类");
		new Thread(new DadianThread()).start();
	}
}

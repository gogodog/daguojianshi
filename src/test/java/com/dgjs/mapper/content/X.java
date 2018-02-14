package com.dgjs.mapper.content;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class X {

	public static void main(String[] args) {
		X t = new X();
		Map<String,Object> map = new HashMap<String,Object>();
		t.getTcwxorTravelasist(map);
		t.getTcwxflFlight(map);
		t.getTcwxtrTrain(map);
		System.out.println(JSON.toJSONString(map,true));
	}
	
	public static class Struct{
		public boolean isNeedEntrance;
		public String req_type;
		public Map<String,String> ch;
	}
	
	private void getTcwxorTravelasist(Map<String,Object> map){
		Struct struct = new Struct();
		struct.isNeedEntrance = true;
		struct.req_type = "itineraries";
		Map<String,String> ch = new HashMap<String,String>();
		struct.ch = ch;
		ch.put("1_flight_listlink", "tcwxor_elfl_travelasistsearch");
		ch.put("1_flight_morelink", "tcwxor_elfl_travelasistmore");
		ch.put("1_flight_link", "tcwxor_elfl_travelasisthotels");
		
		ch.put("1_train_listlink", "tcwxor_eltr_travelasistsearch");
		ch.put("1_train_morelink", "tcwxor_eltr_travelasistmore");
		ch.put("1_train_link", "tcwxor_eltr_travelasisthotels");
		
		ch.put("2_flight_listlink", "tcwxor_tcfl_travelasistsearch");
		ch.put("2_flight_morelink", "tcwxor_tcfl_travelasistmore");
		ch.put("2_flight_link", "tcwxor_tcfl_travelasisthotels");
		
		ch.put("2_train_listlink", "tcwxor_tctr_travelasistsearch");
		ch.put("2_train_morelink", "tcwxor_tctr_travelasistmore");
		ch.put("2_train_link", "tcwxor_tctr_travelasisthotels");
		
		map.put("tcwxor_travelasist", struct);	
	}
	
	private void getTcwxflFlight(Map<String,Object> map){
		Struct struct = new Struct();
		struct.isNeedEntrance = false;
		struct.req_type = "flight_ticket";
		Map<String,String> ch = new HashMap<String,String>();
		struct.ch = ch;
		
		ch.put("flight_listlink", "tcwxfl_search");
		ch.put("flight_morelink", "tcwxfl_hotelsmore");
		ch.put("flight_link", "tcwxfl_hotels");
		
		map.put("tcwxfl_flight", struct);	
	}
	
	private void getTcwxtrTrain(Map<String,Object> map){
		Struct struct = new Struct();
		struct.isNeedEntrance = false;
		struct.req_type = "train_ticket";
		Map<String,String> ch = new HashMap<String,String>();
		struct.ch = ch;
		
		ch.put("train_listlink", "");
		ch.put("train_morelink", "tcwxtr_hotelsmore");
		ch.put("train_link", "tcwxtr_hotels");
		
		map.put("tcwxtr_train", struct);	
	}
}

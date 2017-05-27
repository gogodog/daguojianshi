package com.dgjs.utils;

import java.util.UUID;

public class IdsUtils {

	public static String getUuId(){
		UUID uuid=UUID.randomUUID();
		return uuid.toString().replaceAll("-", "");
	}
	
	public static void main(String[] args) {
		String uuid=IdsUtils.getUuId();
		System.out.println(uuid);
	}
}

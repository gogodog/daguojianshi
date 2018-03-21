package com.dgjs.utils;

import java.util.Random;

public class RandomUtils {
	
	static final String[] array = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N",
			                "O","P","Q","R","S","T","U","V","W","X","Y","Z","0","1",
			                "2","3","4","5","6","7","8","9"};

	public static String getRandomStr(int length){
		Random random = new Random();
		StringBuilder str = new StringBuilder();
		for(int i=0;i<length;i++){
			str.append(array[random.nextInt(array.length)]);
		}
		return str.toString();
	}
	
	public static void main(String[] args) {
		String str = RandomUtils.getRandomStr(8);
		System.out.println(str);
	}
}

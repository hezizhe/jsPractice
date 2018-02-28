package com.lv.common;

import java.util.ArrayList;
import java.util.List;

public class RandomTools {
	
	public static int getRandomNum(int max){
		return (int)(Math.random()*max);
	}
	
	public static List<Integer> getDisOrderNums(int size){
		List<Integer> nums = new ArrayList<Integer>();
		for (int i = 0; i < size; i++) {
			boolean flag = false;
			do{
				int num = getRandomNum(size);
				if(!nums.contains(num)){
					nums.add(num);
					flag = true;
				}
			}while(!flag);
		}
		return nums;
	} 
}

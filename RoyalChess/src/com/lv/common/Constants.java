package com.lv.common;

import java.util.HashMap;
import java.util.Map;

import com.lv.pojo.Round;

public class Constants {
	public static long record = 1;
	
	public static Map<Long, Round> roundRecord = new HashMap<Long, Round>();
	
	public static Map<Long, String> actionRecord = new HashMap<Long, String>();
	
	public static Map<Long, Map<Integer, Integer>> pieceRecord = new HashMap<Long, Map<Integer,Integer>>();
	
	public static synchronized void makeChange(){
		record++;
	}
	
	public static synchronized void recordRound(Round round){
		roundRecord.put(round.getId(), round);
	}
	
	public static synchronized void recordAction(Round round,String action){
		actionRecord.put(round.getId(), action);
	}
	
	public static synchronized void recordPiece(Round round,Integer camp,Integer count){
		if(pieceRecord.get(round.getId())==null)
			pieceRecord.put(round.getId(), new HashMap<Integer, Integer>());
		pieceRecord.get(round.getId()).put(camp, count);
	}
}

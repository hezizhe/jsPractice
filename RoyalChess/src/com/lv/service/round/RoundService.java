package com.lv.service.round;

import java.util.List;

import com.lv.pojo.Round;

public interface RoundService {
	public List<Round> getAllRounds() throws Exception;
	
	public Round getRound(Round round) throws Exception;
	
	public int updateRound(Round round) throws Exception;

}

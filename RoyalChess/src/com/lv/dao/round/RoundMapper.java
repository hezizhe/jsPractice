package com.lv.dao.round;

import java.util.List;

import com.lv.pojo.Round;

public interface RoundMapper {
	public List<Round> getAllRounds();
	
	public Round getRound(Round round);
	
	public int updateRound(Round round);
}

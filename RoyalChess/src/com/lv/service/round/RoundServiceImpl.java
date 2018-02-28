package com.lv.service.round;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lv.dao.round.RoundMapper;
import com.lv.pojo.Round;

@Service("roundService")
public class RoundServiceImpl implements RoundService {
	@Autowired
	private RoundMapper roundMapper;

	public void setRoundMapper(RoundMapper roundMapper) {
		this.roundMapper = roundMapper;
	}

	public List<Round> getAllRounds() throws Exception {
		return roundMapper.getAllRounds();
	}

	public Round getRound(Round round) throws Exception {
		return roundMapper.getRound(round);
	}

	public int updateRound(Round round) throws Exception {
		return roundMapper.updateRound(round);
	}

}

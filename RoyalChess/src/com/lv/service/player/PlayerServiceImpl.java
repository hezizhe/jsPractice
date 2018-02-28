package com.lv.service.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lv.dao.player.PlayerMapper;
import com.lv.pojo.Player;

@Service("playerService")
public class PlayerServiceImpl implements PlayerService {
	@Autowired
	private PlayerMapper playerMaper;
	public void setPlayerMaper(PlayerMapper playerMaper) {
		this.playerMaper = playerMaper;
	}

	public Player getPlayer(Player player) throws Exception {
		return playerMaper.getPlayer(player);
	}

	public int addPlayer(Player player) throws Exception {
		return playerMaper.addPlayer(player);
	}

	public int updatePlayer(Player player) throws Exception {
		return playerMaper.updatePlayer(player);
	}

}

package com.lv.service.player;

import com.lv.pojo.Player;

public interface PlayerService {
	public Player getPlayer(Player player) throws Exception;
	
	public int addPlayer(Player player) throws Exception;
	
	public int updatePlayer(Player player) throws Exception;
}

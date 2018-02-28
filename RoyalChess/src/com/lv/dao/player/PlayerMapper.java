package com.lv.dao.player;

import com.lv.pojo.Player;

public interface PlayerMapper {
	public Player getPlayer(Player player);
	
	public int addPlayer(Player player);
	
	public int updatePlayer(Player player);
}

package com.lv.test;


import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lv.common.RandomTools;
import com.lv.pojo.Player;
import com.lv.pojo.Round;
import com.lv.service.player.PlayerService;
import com.lv.service.round.RoundService;

public class Tests {

	@Test
	public void test() {
		ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext-mybatis.xml");
		RoundService service =  (RoundService) act.getBean("roundService");
		try {
			List<Round> list = service.getAllRounds();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test2() {
		ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext-mybatis.xml");
		PlayerService service =  (PlayerService) act.getBean("playerService");
		try {
			System.out.println(service);
			Player player = new Player();
			player.setPlayerName("123");
			Player p = service.getPlayer(player );
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test3() {
		List<Integer> nums = RandomTools.getDisOrderNums(36);
		for(Integer num:nums){
			System.out.println(num);
		}
	}

}

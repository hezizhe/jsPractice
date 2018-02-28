package com.lv.common;

import com.lv.pojo.Piece;

public class PieceTools {
	public static boolean win(Piece attack,Piece defense){
		int a = attack.getPower();
		int b = defense.getPower();
		if(a==1 && b==6){
			return true;
		}else if(a==6 && b==1){
			return false;
		}else{
			if(a>=b){
				return true;
			}else{
				return false;
			}
		}
	}
}

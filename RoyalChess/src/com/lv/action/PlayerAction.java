package com.lv.action;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import com.lv.common.Constants;
import com.lv.common.PieceTools;
import com.lv.common.RandomTools;
import com.lv.pojo.Piece;
import com.lv.pojo.Player;
import com.lv.pojo.ResultVo;
import com.lv.pojo.Round;
import com.lv.service.piece.PieceService;
import com.lv.service.player.PlayerService;
import com.lv.service.round.RoundService;

public class PlayerAction extends BaseAction {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Player player;
	private PlayerService playerService;
	private RoundService roundService;
	private PieceService pieceService;
	private List<Piece> pieceList;
	private List<Round> roundList;
	private Round round;
	private String record;
	private String action;
	private String steps;
	
	public void validLogin(){
		String result = "fail";
		if(player!=null && player.getPlayerName() != null && !player.getPlayerName().equals("") && player.getPassword() != null && !player.getPassword().equals("")){
			try {
				player = playerService.getPlayer(player);
				if(player!=null){
					result = "success";
					setCurrentPlayer(player);
				}else
					result = "wrongpsw";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		getOut().print(result);
		closeOut();
	}
	
	public String gameCenter(){
		try {
			roundList = roundService.getAllRounds();
			record = String.valueOf(Constants.record);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public void checkRecord(){
		String result = "nochange";
		if(Long.parseLong(record) != 0){
			if(Long.parseLong(record) != Constants.record){
				result = "changed";
			}
		}
		getOut().print(result);
		closeOut();
	}
	
	public void joinGame(){
		String result = "fail";
		if(null!=round && round.getId()!=0){
			Player currentPlayer = getCurrentPlayer();
			if(currentPlayer!=null){
				try {
					currentPlayer = playerService.getPlayer(currentPlayer);
					if(currentPlayer.getRound()!=0){
						result = "infighting"+currentPlayer.getRound();
					}else{
						round = roundService.getRound(round);
						int size = round.getPlayers().size();
						if(size >= 2){
							result = "full";
						}else{
							if(size == 0)
								currentPlayer.setCamp(0);
							else if(size == 1){  //已有其他玩家等待，加入玩家后游戏开始
								currentPlayer.setCamp(1);
								round.setStatus(1);
								round.setFirst(RandomTools.getRandomNum(2));
								roundService.updateRound(round);
								
								Piece piece = new Piece(round.getId());
								if(!pieceService.cheakRoundPiecesCount(piece)){
									pieceService.delteRoundPieces(piece);
									pieceService.addRoundPieces(piece);
								}
								pieceService.opening(piece);
							}
							Constants.recordRound(round);
							currentPlayer.setRound(round.getId());
							int rows = playerService.updatePlayer(currentPlayer);
							if(rows>0){
								result = "success";
								Constants.makeChange();
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				result = "sessioninvalid";
			}
		}
		getOut().print(result);
		closeOut();
	}
	
	public String game(){
		if(null != round && round.getId() != 0){
			try {
				round = roundService.getRound(round);
				if(round.getPlayers().size()>0){
					Piece piece = new Piece(round.getId());
					pieceList = pieceService.getRoundPieces(piece);
					
					for(Player player:round.getPlayers()){
						Piece campPiece = new Piece(player.getCamp(),round.getId());
						player.setPieceCount(pieceService.getCampPiecesCount(campPiece));
						Constants.recordPiece(round, player.getCamp(), player.getPieceCount());
					}
					Constants.recordRound(round);
					return SUCCESS;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return INPUT;
	}
	
	public void cheakStart(){
		String result = "nochange";
		if(null != round && round.getId() != 0){
			int status = Constants.roundRecord.get(round.getId()).getStatus();
			if(status!=0)
				result = "changed";
		}
		getOut().print(result);
		closeOut();
	}
	
	public void getPieces(){
		if(null != round && round.getId() != 0){
			try {
				Piece piece = new Piece(round.getId());
				pieceList = pieceService.getRoundPieces(piece);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		getOut().print(JSONArray.fromObject(pieceList).toString());
		closeOut();
	}
	
	public void judge(ResultVo result,Round round){
		Round recordRound = Constants.roundRecord.get(round.getId());
		if(recordRound.getPeaceSteps()>=30)
			result.setWinlose("deadlock");
		Map<Integer, Integer> pieceCount = Constants.pieceRecord.get(round);
		if(pieceCount!=null && pieceCount.size()>0){
			for(Map.Entry<Integer, Integer> entry:pieceCount.entrySet()){
				if(entry.getValue()==0)
					result.setWinlose(entry.getKey()+"win");
			}
		}
	}
	
	public void takeAction(){
		ResultVo result = new ResultVo();
		if(null != action && !action.equals("") && null != round && round.getId() != 0){
			try {
				round = roundService.getRound(round);
				boolean flag = false;
				if(action.indexOf("-")!=-1){
					System.out.println("移动");
					String[] pieces = action.split("-");
					String[] piece1 = pieces[0].split(",");
					String[] piece2 = pieces[1].split(",");
					Piece attack = new Piece(round.getId(),Integer.valueOf(piece1[0]),Integer.valueOf(piece1[1]));
					Piece _defense = new Piece(round.getId(),Integer.valueOf(piece2[0]),Integer.valueOf(piece2[1]));
					attack = pieceService.getPiece(attack);
					Piece defense = pieceService.getPiece(_defense);
					if(null != attack){
						if(defense == null){
							pieceService.move(attack, _defense);
							round.setPeaceSteps(round.getPeaceSteps()+1);
							flag = true;
						}else if(PieceTools.win(attack, defense)){
							pieceService.fight(attack, defense);
							round.setPeaceSteps(0);
							for(Player player:round.getPlayers()){
								if(player.getCamp() == defense.getCamp()){
									Piece campPiece = new Piece(player.getCamp(),round.getId());
									player.setPieceCount(pieceService.getCampPiecesCount(campPiece));
									result.setPieceCount(player.getCamp()+"-"+player.getPieceCount());
									Constants.recordPiece(round, player.getCamp(), player.getPieceCount());
								}
							}
							flag = true;
						}
					}
				}else{
					String[] piece = action.split(",");
					Piece show = new Piece(round.getId(),Integer.valueOf(piece[0]),Integer.valueOf(piece[1]));
					show = pieceService.getPiece(show);
					System.out.println(show);
					if(show.getHide()==0){
						show.setHide(1);
						pieceService.updatePiece(show);
						result.setShow(show.getCamp()+"-"+show.getPower());
						action = show.getLocationX()+"-"+show.getLocationY()+":"+show.getCamp()+"-"+show.getPower();
						round.setPeaceSteps(0);
						flag = true;
					}
				}
				if(flag){
					round.setSteps(round.getSteps()+1);
					result.setSteps(round.getSteps());
					roundService.updateRound(round);
					Constants.recordRound(round);
					Constants.recordAction(round, action);
					judge(result,round);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		getOut().print(JSONArray.fromObject(result).toString());
		closeOut();
	}
	
	public void waitAction(){
		ResultVo result = new ResultVo();
		if(null != round && round.getId() != 0 && steps != null && !steps.equals("")){
			int nowSteps = Constants.roundRecord.get(round.getId()).getSteps();
			if(nowSteps != Integer.parseInt(steps)){
				result.setSteps(nowSteps);
				result.setAction(Constants.actionRecord.get(round.getId()));
				String pieceCount = Constants.pieceRecord.get(round.getId()).get(0)+"-"+Constants.pieceRecord.get(round.getId()).get(1);
				result.setPieceCount(pieceCount);
			}
		}
		getOut().print(JSONArray.fromObject(result).toString());
		closeOut();
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////
	public void setPlayerService(PlayerService playerService) {
		this.playerService = playerService;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public void setRoundService(RoundService roundService) {
		this.roundService = roundService;
	}
	public void setPieceService(PieceService pieceService) {
		this.pieceService = pieceService;
	}

	public List<Round> getRoundList() {
		return roundList;
	}
	public void setRoundList(List<Round> roundList) {
		this.roundList = roundList;
	}
	public List<Piece> getPieceList() {
		return pieceList;
	}
	public void setPieceList(List<Piece> pieceList) {
		this.pieceList = pieceList;
	}
	public Round getRound() {
		return round;
	}
	public void setRound(Round round) {
		this.round = round;
	}
	public String getRecord() {
		return record;
	}
	public void setRecord(String record) {
		this.record = record;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getSteps() {
		return steps;
	}
	public void setSteps(String steps) {
		this.steps = steps;
	}
}

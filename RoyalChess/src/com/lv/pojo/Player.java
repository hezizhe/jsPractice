package com.lv.pojo;

/**
 * Player entity. @author MyEclipse Persistence Tools
 */

public class Player implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String playerName;
	private String password;
	private Integer camp;
	private Long round;
	private Integer overTimes;
	private int pieceCount;

	// Constructors

	/** default constructor */
	public Player() {
	}

	/** minimal constructor */
	public Player(Integer camp, Long round, Integer overTimes) {
		this.camp = camp;
		this.round = round;
		this.overTimes = overTimes;
	}

	/** full constructor */
	public Player(String playerName, String password, Integer camp, Long round,
			Integer overTimes) {
		this.playerName = playerName;
		this.password = password;
		this.camp = camp;
		this.round = round;
		this.overTimes = overTimes;
	}

	// Property accessors

	
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlayerName() {
		return this.playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getCamp() {
		return this.camp;
	}

	public void setCamp(Integer camp) {
		this.camp = camp;
	}

	public Long getRound() {
		return this.round;
	}

	public void setRound(Long round) {
		this.round = round;
	}

	public Integer getOverTimes() {
		return this.overTimes;
	}

	public void setOverTimes(Integer overTimes) {
		this.overTimes = overTimes;
	}

	public int getPieceCount() {
		return pieceCount;
	}

	public void setPieceCount(int pieceCount) {
		this.pieceCount = pieceCount;
	}
	
	@Override
	public String toString() {
		return "Player [id=" + id + ", playerName=" + playerName
				+ ", password=" + password + ", camp=" + camp + ", round="
				+ round + ", overTimes=" + overTimes + ", pieceCount="
				+ pieceCount + "]";
	}


}
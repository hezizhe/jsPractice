package com.lv.pojo;

import java.util.List;

/**
 * Round entity. @author MyEclipse Persistence Tools
 */

public class Round implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Integer winner;
	private Integer first;
	private Integer steps;
	private Integer peaceSteps;
	private Integer status;
	private List<Player> players;

	// Constructors

	public Round() {
	}

	public Round(Integer first, Integer steps, Integer peaceSteps) {
		this.first = first;
		this.steps = steps;
		this.peaceSteps = peaceSteps;
	}

	public Round(Integer winner, Integer first, Integer steps,
			Integer peaceSteps) {
		this.winner = winner;
		this.first = first;
		this.steps = steps;
		this.peaceSteps = peaceSteps;
	}


	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getWinner() {
		return this.winner;
	}

	public void setWinner(Integer winner) {
		this.winner = winner;
	}

	public Integer getFirst() {
		return this.first;
	}

	public void setFirst(Integer first) {
		this.first = first;
	}

	public Integer getSteps() {
		return this.steps;
	}

	public void setSteps(Integer steps) {
		this.steps = steps;
	}

	public Integer getPeaceSteps() {
		return this.peaceSteps;
	}

	public void setPeaceSteps(Integer peaceSteps) {
		this.peaceSteps = peaceSteps;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	@Override
	public String toString() {
		return "Round [id=" + id + ", winner=" + winner + ", first=" + first
				+ ", steps=" + steps + ", peaceSteps=" + peaceSteps
				+ ", status=" + status + ", players=" + players + "]";
	}
}
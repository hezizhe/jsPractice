package com.lv.pojo;

/**
 * Piece entity. @author MyEclipse Persistence Tools
 */

public class Piece implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer power;
	private Integer locationX;
	private Integer locationY;
	private Integer hide;
	private Integer camp;
	private Long round;


	public Piece() {
	}

	public Piece(Integer power, Integer locationX, Integer locationY,
			Integer camp, Long round) {
		this.power = power;
		this.locationX = locationX;
		this.locationY = locationY;
		this.camp = camp;
		this.round = round;
	}
	
	public Piece(Long round, Integer locationX, Integer locationY) {
		this.locationX = locationX;
		this.locationY = locationY;
		this.round = round;
	}

	public Piece(Integer power, Integer camp, Long round) {
		this.power = power;
		this.camp = camp;
		this.round = round;
	}

	public Piece(Integer camp,Long round) {
		this.camp = camp;
		this.round = round;
	}

	@Override
	public String toString() {
		return "Piece [id=" + id + ", power=" + power + ", locationX="
				+ locationX + ", locationY=" + locationY + ", hide=" + hide
				+ ", camp=" + camp + ", round=" + round + "]";
	}

	public Piece(Long round) {
		this.round = round;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPower() {
		return this.power;
	}

	public void setPower(Integer power) {
		this.power = power;
	}

	public Integer getLocationX() {
		return this.locationX;
	}

	public void setLocationX(Integer locationX) {
		this.locationX = locationX;
	}

	public Integer getLocationY() {
		return this.locationY;
	}

	public void setLocationY(Integer locationY) {
		this.locationY = locationY;
	}

	public Integer getHide() {
		return hide;
	}

	public void setHide(Integer hide) {
		this.hide = hide;
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

}
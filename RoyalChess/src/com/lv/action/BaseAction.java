package com.lv.action;

import java.io.IOException;
import java.io.PrintWriter;

import org.apache.struts2.ServletActionContext;

import com.lv.pojo.Player;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PrintWriter out;
	private Player currentPlayer;

	public BaseAction(){
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
	}
	
	public Player getCurrentPlayer() {
		return (Player)ServletActionContext.getRequest().getSession().getAttribute("currentPlayer");
	}

	public void setCurrentPlayer(Player currentPlayer) {
		ServletActionContext.getRequest().getSession().setAttribute("currentPlayer", currentPlayer);
		this.currentPlayer = currentPlayer;
	}
	
	public PrintWriter getOut(){
		try {
			if(out==null)
				out = ServletActionContext.getResponse().getWriter();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return out;
	}
	
	public void closeOut(){
		if(out!=null){
			out.close();
			out = null;
		}
	}
	
	public void setOut(PrintWriter out) {
		this.out = out;
	}
}

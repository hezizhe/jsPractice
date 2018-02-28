package com.lv.service.piece;

import java.util.List;

import com.lv.pojo.Piece;

public interface PieceService {
	public boolean addRoundPieces(Piece piece) throws Exception;
	
	public List<Piece> getRoundPieces(Piece piece) throws Exception;
	
	public boolean cheakRoundPiecesCount(Piece piece) throws Exception;
	
	public int getCampPiecesCount(Piece piece) throws Exception;
	
	public int delteRoundPieces(Piece piece) throws Exception;
	
	public int updatePiece(Piece piece) throws Exception;

	public void opening(Piece piece) throws Exception;
	
	public Piece getPiece(Piece piece) throws Exception;
	
	public int deletePiece(Piece piece) throws Exception;
	
	public void fight(Piece attack,Piece defense) throws Exception;
	
	public void move(Piece attack,Piece defense) throws Exception;
}

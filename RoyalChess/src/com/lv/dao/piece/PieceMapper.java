package com.lv.dao.piece;

import java.util.List;

import com.lv.pojo.Piece;

public interface PieceMapper {
	public int addPiece(Piece piece);
	
	public List<Piece> getRoundPieces(Piece piece);
	
	public int getRoundPiecesCount(Piece piece);
	
	public int getCampPiecesCount(Piece piece);
	
	public int delteRoundPieces(Piece piece);
	
	public int updatePiece(Piece piece);
	
	public Piece getPiece(Piece piece);
	
	public int deletePiece(Piece piece);
}

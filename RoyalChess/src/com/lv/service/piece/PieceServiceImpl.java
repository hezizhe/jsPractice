package com.lv.service.piece;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lv.common.RandomTools;
import com.lv.dao.piece.PieceMapper;
import com.lv.pojo.Piece;

@Service("pieceService")
public class PieceServiceImpl implements PieceService {
	@Autowired
	private PieceMapper pieceMapper;
	public void setPieceMapper(PieceMapper pieceMapper) {
		this.pieceMapper = pieceMapper;
	}

	private int addPieceCount(Piece piece , int times) throws Exception {
		int count = 0;
		for (int i = 0; i < times; i++) {
			int rows = pieceMapper.addPiece(piece);
			count += rows;
		}
		return count;
	}
	
	public boolean addRoundPieces(Piece piece) throws Exception {
		Long round = piece.getRound();
		int count = 0;
		// R = 红方    B = 蓝方    king = 国王  
		Piece kingR = new Piece(6,0,round);
		Piece kingB = new Piece(6,1,round);
		count += addPieceCount(kingR, 1);
		count += addPieceCount(kingB, 1);
		// general = 将军
		Piece generalR = new Piece(5,0,round);
		Piece generalB = new Piece(5,1,round);
		count += addPieceCount(generalR, 1);
		count += addPieceCount(generalB, 1);
		// knight = 骑士
		Piece knightR = new Piece(4,0,round);
		Piece knightB = new Piece(4,1,round);
		count += addPieceCount(knightR, 2);
		count += addPieceCount(knightB, 2);
		// bowman = 弓箭手
		Piece bowmanR = new Piece(3,0,round);
		Piece bowmanB = new Piece(3,1,round);
		count += addPieceCount(bowmanR, 2);
		count += addPieceCount(bowmanB, 2);
		// guard = 禁卫军
		Piece guardR = new Piece(2,0,round);
		Piece guardB = new Piece(2,1,round);
		count += addPieceCount(guardR, 4);
		count += addPieceCount(guardB, 4);
		// assassin = 刺客
		Piece assassinR = new Piece(1,0,round);
		Piece assassinB = new Piece(1,1,round);
		count += addPieceCount(assassinR, 8);
		count += addPieceCount(assassinB, 8);
		
		if(count == 36)
			return true;
		return false;
	}

	public List<Piece> getRoundPieces(Piece piece) throws Exception {
		return pieceMapper.getRoundPieces(piece);
	}

	public boolean cheakRoundPiecesCount(Piece piece) throws Exception {
		int count = pieceMapper.getRoundPiecesCount(piece);
		if(count == 36)
			return true;
		return false;
	}

	public int delteRoundPieces(Piece piece) throws Exception {
		return pieceMapper.delteRoundPieces(piece);
	}

	public int updatePiece(Piece piece) throws Exception {
		return pieceMapper.updatePiece(piece);
	}

	public int getCampPiecesCount(Piece piece) throws Exception {
		return pieceMapper.getCampPiecesCount(piece);
	}

	public void opening(Piece piece) throws Exception {
		List<Piece> pieceList = pieceMapper.getRoundPieces(piece);
		List<Integer> randomNums = RandomTools.getDisOrderNums(36);
		int i = 0;
		for (int x = 1; x <= 6; x++) {
			for (int y = 1; y <= 6; y++) {
				Piece _piece = pieceList.get(randomNums.get(i));
				_piece.setLocationX(x);
				_piece.setLocationY(y);
				pieceMapper.updatePiece(_piece);
				i++;
			}
		}
	}

	public Piece getPiece(Piece piece) throws Exception {
		return pieceMapper.getPiece(piece);
	}

	public int deletePiece(Piece piece) throws Exception {
		return pieceMapper.deletePiece(piece);
	}

	public void fight(Piece attack, Piece defense) throws Exception {
		attack.setLocationX(defense.getLocationX());
		attack.setLocationY(defense.getLocationY());
		pieceMapper.deletePiece(defense);
		pieceMapper.updatePiece(attack);
	}

	public void move(Piece attack, Piece defense) throws Exception {
		attack.setLocationX(defense.getLocationX());
		attack.setLocationY(defense.getLocationY());
		pieceMapper.updatePiece(attack);
	}

}

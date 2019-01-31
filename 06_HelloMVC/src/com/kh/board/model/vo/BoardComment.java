package com.kh.board.model.vo;

import java.sql.Date;

public class BoardComment {

	private int boardCommentNo;
	private int boardCommentLevel;
	private String boardCommnetWriter;
	private String boardCommentContent;
	private int boardRef;
	private int boardCommentRef;
	private Date boardCommentDate;
	
	public BoardComment() {}

	public BoardComment(int boardCommentNo, int boardCommentLevel, String boardCommnetWriter,
			String boardCommentContent, int boardRef, int boardCommentRef, Date boardCommentDate) {
		super();
		this.boardCommentNo = boardCommentNo;
		this.boardCommentLevel = boardCommentLevel;
		this.boardCommnetWriter = boardCommnetWriter;
		this.boardCommentContent = boardCommentContent;
		this.boardRef = boardRef;
		this.boardCommentRef = boardCommentRef;
		this.boardCommentDate = boardCommentDate;
	}

	public int getBoardCommentNo() {
		return boardCommentNo;
	}

	public void setBoardCommentNo(int boardCommentNo) {
		this.boardCommentNo = boardCommentNo;
	}

	public int getBoardCommentLevel() {
		return boardCommentLevel;
	}

	public void setBoardCommentLevel(int boardCommentLevel) {
		this.boardCommentLevel = boardCommentLevel;
	}

	public String getBoardCommnetWriter() {
		return boardCommnetWriter;
	}

	public void setBoardCommnetWriter(String boardCommnetWriter) {
		this.boardCommnetWriter = boardCommnetWriter;
	}

	public String getBoardCommentContent() {
		return boardCommentContent;
	}

	public void setBoardCommentContent(String boardCommentContent) {
		this.boardCommentContent = boardCommentContent;
	}

	public int getBoardRef() {
		return boardRef;
	}

	public void setBoardRef(int boardRef) {
		this.boardRef = boardRef;
	}

	public int getBoardCommentRef() {
		return boardCommentRef;
	}

	public void setBoardCommentRef(int boardCommentRef) {
		this.boardCommentRef = boardCommentRef;
	}

	public Date getBoardCommentDate() {
		return boardCommentDate;
	}

	public void setBoardCommentDate(Date boardCommentDate) {
		this.boardCommentDate = boardCommentDate;
	}

	@Override
	public String toString() {
		return "BoardComment [boardCommentNo=" + boardCommentNo + ", boardCommentLevel=" + boardCommentLevel
				+ ", boardCommnetWriter=" + boardCommnetWriter + ", boardCommentContent=" + boardCommentContent
				+ ", boardRef=" + boardRef + ", boardCommentRef=" + boardCommentRef + ", boardCommentDate="
				+ boardCommentDate + "]";
	}
	
	
	
}

package com.kh.board.model.vo;

import java.sql.Date;

public class Board {
	private int boardNo;
	private String boardTitle;
	private String boardWriter;
	private String boardContent;
	private String boardOriFile;
	private String boardReFile;
	private Date boardDate;
	private int readCount;
	
	public Board() {}

	
	
	public Board(int boardNo, String boardTitle, String boardWriter, String boardContent, String boardOriFile,
			String boardReFile, Date boardDate, int readCount) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardWriter = boardWriter;
		this.boardContent = boardContent;
		this.boardOriFile = boardOriFile;
		this.boardReFile = boardReFile;
		this.boardDate = boardDate;
		this.readCount = readCount;
	}



	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardWriter() {
		return boardWriter;
	}

	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getBoardOriFile() {
		return boardOriFile;
	}

	public void setBoardOriFile(String boardOriFile) {
		this.boardOriFile = boardOriFile;
	}

	public String getBoardReFile() {
		return boardReFile;
	}

	public void setBoardReFile(String boardReFile) {
		this.boardReFile = boardReFile;
	}

	public Date getBoardDate() {
		return boardDate;
	}

	public void setBoardDate(Date boardDate) {
		this.boardDate = boardDate;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardWriter=" + boardWriter
				+ ", boardContent=" + boardContent + ", boardOriFile=" + boardOriFile + ", boardReFile=" + boardReFile
				+ ", readCount=" + readCount + "]";
	}
	
	
	
}

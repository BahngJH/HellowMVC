package com.kh.board.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.kh.board.dao.BoardDao;
import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.BoardComment;

public class BoardService {

	public int selectCount() {
		Connection conn = getConnection();
		int rs = new BoardDao().selectCount(conn);
		close(conn);
		return rs;
	}
	public List<BoardComment> selectCommentList(int no)
	{
		Connection conn = getConnection();
		List<BoardComment> list = new BoardDao().selectCommentList(conn,no);
		close(conn);
		return list;
	}

	public Board selectOne(int no, boolean hasRead) {
		Connection conn = getConnection();
		Board b = new BoardDao().selectOne(conn, no);
		//조회수 
		if (!hasRead) {
			int result = new BoardDao().updateCount(conn, no);
			if (result > 0) {
				commit(conn);
			} else {
				rollback(conn);
			}
		}
		close(conn);
		return b;
	}
	public int inserComment(BoardComment bc) {
		Connection conn = getConnection();
		int rs = new BoardDao().inserComment(conn,bc);
		if(rs>0) commit(conn);
		else rollback(conn);
		
		close(conn);
		return rs;
	}
	
	public List<Board> selectList(int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Board> rs = new BoardDao().selectList(conn, cPage, numPerPage);
		close(conn);
		return rs;
	}

	public int inserBoard(Board b) {
		Connection conn = getConnection();
		int rs = new BoardDao().inserBoard(conn, b);
		if (rs > 0)
			commit(conn);
		else
			rollback(conn);

		close(conn);
		return rs;
	}

}

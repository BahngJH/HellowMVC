package com.kh.board.dao;

import static common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.BoardComment; 

public class BoardDao {

	Properties prop =  new Properties();
	
	public BoardDao() {
		try {
			String file = BoardDao.class.getResource("./board-sql.properties").getPath();
			prop.load(new FileReader(file));
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<BoardComment> selectCommentList(Connection conn, int no)
	{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql = prop.getProperty("selectCommentList");
		List<BoardComment> list =new ArrayList();
		BoardComment bc = null;
		
		try {
			pstmt =conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				bc=new BoardComment();
				bc.setBoardCommentNo(rs.getInt("board_comment_no"));
				bc.setBoardCommentLevel(rs.getInt("board_comment_level"));
				bc.setBoardCommnetWriter(rs.getString("board_comment_writer"));
				bc.setBoardCommentContent(rs.getString("board_comment_content"));
				bc.setBoardCommentRef(rs.getInt("board_comment_ref"));
				bc.setBoardRef(rs.getInt("board_ref"));
				bc.setBoardCommentDate(rs.getDate("board_comment_date"));
				
				list.add(bc);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	public int inserComment(Connection conn, BoardComment bc)
	{
		PreparedStatement pstmt = null;
		int rs =0;
		String sql = prop.getProperty("inserComment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bc.getBoardCommentLevel());
			pstmt.setString(2, bc.getBoardCommnetWriter());
			pstmt.setString(3, bc.getBoardCommentContent());
			pstmt.setInt(4, bc.getBoardRef());
			//자기 참조를 할때 null이거나 존재하는 숫자를 넣어줘야하는데
			//시퀀스 번호가 1부터 시작이라 0은 존재할 수가 없음 그래서 null을 넣어줘야하는데
			//오라클에 null값을 넣을 때는 문자형으로 대입 삼항연산 뒤에는 int형이라 거기도 String형으로 변환 해줘야한다.
			pstmt.setString(5, bc.getBoardCommentRef()==0?null:String.valueOf(bc.getBoardCommentRef()));

			//pstmt.setInt(5, bc.getBoardCommentRef());
			
			rs = pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return rs;
		
	}
	
	public int updateCount(Connection conn, int no)
	{
		PreparedStatement pstmt=null;
		String sql = prop.getProperty("updateCount");
		int rs =0;
		
		try {
			pstmt =conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs=pstmt.executeUpdate();
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return rs;
	}
	
	public Board selectOne(Connection conn, int no)
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectOne");
		Board b = new Board();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				b.setBoardNo(rs.getInt("board_no"));
				b.setBoardTitle(rs.getString("board_title"));
				b.setBoardWriter(rs.getString("board_writer"));
				b.setBoardContent(rs.getString("board_content"));
				b.setBoardOriFile(rs.getString("board_original_filename"));
				b.setBoardReFile(rs.getString("board_renamed_filename"));
				b.setBoardDate(rs.getDate("board_date"));
				b.setReadCount(rs.getInt("board_readcount"));
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return b;
		
	}
	
	public int selectCount(Connection conn) {
		PreparedStatement pstmt =null;
		ResultSet rs=null;
		int result = 0;
		String sql = prop.getProperty("selectCount");
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result=rs.getInt("cnt");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	public int inserBoard(Connection conn, Board b)
	{
		PreparedStatement pstmt=null;
		int rs = 0;
		String sql = prop.getProperty("inserBoard");
		
		try {
			pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getBoardWriter());
			pstmt.setString(3, b.getBoardContent());
			pstmt.setString(4, b.getBoardOriFile());
			pstmt.setString(5, b.getBoardReFile());
			
			
			rs = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return rs;
		
	}
	
	public List<Board> selectList(Connection conn, int cPage, int numPerPage)
	{
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		String sql =prop.getProperty("selectList");
		Board b = null;
		List list = new ArrayList();
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				b = new Board();
				
				b.setBoardContent(rs.getString("board_content"));
				b.setBoardNo(rs.getInt("board_no"));
				b.setBoardTitle(rs.getString("board_title"));
				b.setBoardWriter(rs.getString("board_writer"));
				b.setBoardOriFile(rs.getString("board_original_filename"));
				b.setBoardReFile(rs.getString("board_renamed_filename"));
				b.setBoardDate(rs.getDate("board_date"));
				b.setReadCount(rs.getInt("board_readcount"));
				
				list.add(b);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
}

package com.kh.notice.dao;

import static common.JDBCTemplate.close;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.notice.model.vo.Notice;

public class NoticeDao {
	
	private Properties prop = new Properties();
	public NoticeDao() {
		
		try {
			String file = NoticeDao.class.getResource("./notice-query.properties").getPath();
			prop.load(new FileReader(file));
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int updateNotice(Connection conn, Notice n)
	{
		PreparedStatement pstmt=null;
		int rs = 0;
		String sql = prop.getProperty("updateNotice");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeContect());
			pstmt.setString(3, n.getFilePath());
			pstmt.setInt(4, n.getNoticeNo());
			
			rs=pstmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return rs;
	}
	
	public int upNotice(Connection conn, Notice n) {
		PreparedStatement pstmt =null;
		int rs =0;
		String sql = prop.getProperty("upNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeWriter());
			pstmt.setString(3, n.getNoticeContect());
			pstmt.setString(4, n.getFilePath());
			
			rs = pstmt.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		
		return rs;
	}
	
	public List<Notice> selectList(Connection conn)
	{
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		String sql = prop.getProperty("selectList");
		List<Notice> list = new ArrayList();
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				Notice n=new Notice();
				
				n.setNoticeNo(rs.getInt("notice_no"));
				n.setNoticeTitle(rs.getString("notice_title"));
				n.setNoticeWriter(rs.getString("notice_writer"));
				n.setNoticeContect(rs.getString("notice_content"));
				n.setNoticeDate(rs.getDate("notice_date"));
				n.setFilePath(rs.getString("filepath"));
				
				list.add(n);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}
	
	public Notice selectOne(Connection conn, int no) {
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		Notice n=null;
		String sql =prop.getProperty("selectOne");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				n = new Notice();
				n.setNoticeContect(rs.getString("notice_content"));
				n.setNoticeNo(rs.getInt("notice_no"));
				n.setNoticeWriter(rs.getString("notice_writer"));
				n.setNoticeTitle(rs.getString("notice_title"));
				n.setNoticeDate(rs.getDate("notice_date"));
				n.setFilePath(rs.getString("filepath"));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(pstmt);
		}
		return n;
		
	}
}

package com.kh.admin.dao;

import static common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.bs.model.vo.Member;

public class AdminDao {

	Properties prop = new Properties();
	public AdminDao() 
	{
		String fileName = AdminDao.class.getResource("./adminquery.properties").getPath();
		
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int selectMemberCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		int result =0 ;
		String sql = prop.getProperty("selectMemberCount");
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("cnt");
				
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}
	
	public List<Member> selectMemberList(Connection conn, int cPage, int numPerPage){
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Member> list = new ArrayList();
		String sql = prop.getProperty("selectMemberList");
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, (cPage-1)*numPerPage+1);
			pstmt.setInt(2, cPage*numPerPage);
			rs = pstmt.executeQuery();
			Member m = null;
			
			while(rs.next()) 
			{
				m = new Member();
				m.setMemberId(rs.getString("userid"));
				m.setMemberPwd(rs.getString("password"));
				m.setMemberName(rs.getString("username"));
				m.setGender(rs.getString("gender"));
				m.setAge(rs.getInt("age"));
				m.setAddress(rs.getString("address"));
				m.setPhone(rs.getString("phone"));
				m.setHobby(rs.getString("hobby"));
				m.setEmail(rs.getString("email"));
				m.setEnrollDate(rs.getDate("enrolldate"));
				
				list.add(m);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	
	public List<Member> selectId(Connection conn, String keyword)
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = prop.getProperty("selectId");
		ArrayList<Member> list = new ArrayList();
		Member m =null;
		
		try {

			pstmt =conn.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%");

			rs = pstmt.executeQuery();
			
			while(rs.next()) 
			{
				m = new Member();
				m.setMemberId(rs.getString("userid"));
				m.setMemberPwd(rs.getString("password"));
				m.setMemberName(rs.getString("username"));
				m.setGender(rs.getString("gender"));
				m.setAge(rs.getInt("age"));
				m.setAddress(rs.getString("address"));
				m.setPhone(rs.getString("phone"));
				m.setHobby(rs.getString("hobby"));
				m.setEmail(rs.getString("email"));
				m.setEnrollDate(rs.getDate("enrolldate"));
				
				list.add(m);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	
	public List<Member> selectAll(Connection conn)
	{
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		List<Member> list = new ArrayList();
		Member m = null;
		String sql = prop.getProperty("selectAll");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) 
			{
				m = new Member();
				m.setMemberId(rs.getString("userid"));
				m.setMemberPwd(rs.getString("password"));
				m.setMemberName(rs.getString("username"));
				m.setGender(rs.getString("gender"));
				m.setAge(rs.getInt("age"));
				m.setAddress(rs.getString("address"));
				m.setPhone(rs.getString("phone"));
				m.setHobby(rs.getString("hobby"));
				m.setEmail(rs.getString("email"));
				m.setEnrollDate(rs.getDate("enrolldate"));
				
				list.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
		return list;
	}
}

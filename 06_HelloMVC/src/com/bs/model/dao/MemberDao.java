package com.bs.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import static common.JDBCTemplate.close;
import com.bs.model.vo.Member;

public class MemberDao {
	Properties prop = new Properties();
	public MemberDao() {
		
		String fileName = MemberDao.class.getResource("./memberquery.properties").getPath();
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
	
	public Member selectMember(Connection conn, Member m) {
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String sql= prop.getProperty("loginCheck");
		Member data = null;
		
		try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, m.getMemberId());
		rs = pstmt.executeQuery();
		
		if(rs.next())
		{
			data =new Member();
			data.setMemberId(rs.getString("userid"));
			data.setMemberPwd(rs.getString("password"));
			data.setMemberName(rs.getString("username"));
			data.setAge(rs.getInt("age"));
			data.setGender(rs.getString("gender"));
			data.setEmail(rs.getString("email"));
			data.setPhone(rs.getString("phone"));
			data.setAddress(rs.getString("address"));
			data.setHobby(rs.getString("hobby"));
			//data.setEnrollDate(rs.getString("enrolldate"));
		}
		
	 }catch (Exception e) {
		 e.printStackTrace();
	}
		finally {
			close(rs);
			close(pstmt);
		}
		return data;
	}
	
	public int enrollMember(Connection conn, Member m) 
	{
		PreparedStatement pstmt = null;
		int rs=0;
		String sql = prop.getProperty("enroll");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getMemberPwd());
			pstmt.setString(3, m.getMemberName());
			pstmt.setString(4, m.getGender());
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getAddress());
			pstmt.setString(9, m.getHobby());
			
			rs = pstmt.executeUpdate();
			System.out.println(rs);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		return rs;
	}
	public int updatePassword(Connection conn, Member data)
	{
		int rs = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updatePassword");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, data.getMemberPwd());
			pstmt.setString(2, data.getMemberId());
			rs = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			close(pstmt);
		}
		
		return rs;
	}
	
	public int updateMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("update");
		int rs = 0;
		
		try {
			
			pstmt =conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getMemberName());
			pstmt.setString(2, m.getGender());
			pstmt.setInt(3, m.getAge());
			pstmt.setString(4, m.getEmail());
			pstmt.setString(5, m.getPhone());
			pstmt.setString(6, m.getAddress());
			pstmt.setString(7, m.getHobby());
			pstmt.setString(8, m.getMemberId());
			
			rs = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(pstmt);
		}
		return rs;
	}
}

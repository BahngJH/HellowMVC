package com.bs.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;

import com.bs.model.dao.MemberDao;
import com.bs.model.vo.Member;
//컨트롤러가 전달한 정보와 DB접속정보를 DAO에게 전달
//DB접속정보(connection)에 대한 관리: 객체반환(close())
//insert, update, delete한 후 rollback, commit관리!!
public class MemberService {
	
	private MemberDao dao = new MemberDao();
	
	public Member selectMember(Member m) {
		
		Connection conn = getConnection();
		Member data = dao.selectMember(conn, m);
		close(conn);
		return data;
	}
	
	public int updatePassword(Member data) {
		Connection conn = getConnection();
		int result = new MemberDao().updatePassword(conn,data);
		if(result>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return result;
	}
	
	public int enrollMember(Member m) 
	{
		Connection conn = getConnection();
		int rs = dao.enrollMember(conn,m);
		if(rs>0) {
			System.out.println("회원가입 완료");
			commit(conn);
		}
		else {
			System.out.println("회원가입 실패");
			rollback(conn);
		}
		close(conn);
		return rs;
	}
	public int updateMember(Member m) {
		Connection conn = getConnection();
		int rs = new MemberDao().updateMember(conn,m);
		
		if(rs>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return rs;
	}
	
}

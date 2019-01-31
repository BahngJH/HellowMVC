package com.kh.admin.model.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.bs.model.vo.Member;
import com.kh.admin.dao.AdminDao;

public class AdminService {

	public List<Member> selectAll() {
		Connection conn = getConnection();
		List<Member> list = new AdminDao().selectAll(conn);
		if(list!=null) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return list;
	}
	public int selectMemberCount() {
		Connection conn = getConnection();
		int result = new AdminDao().selectMemberCount(conn);
		close(conn);
		return result;
	}
	public List<Member> selectMemberList(int cPage, int numPerPage) {
		Connection conn = getConnection();
		List<Member> list = new AdminDao().selectMemberList(conn, cPage, numPerPage);
		close(conn);
		return list;
	}
	
	public List<Member> selectId(String keyword)
	{
		Connection conn = getConnection();
		List<Member> list = new AdminDao().selectId(conn, keyword);
		close(conn);
		return list;
	}
	
}

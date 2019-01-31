package com.kh.notice.service;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.kh.notice.dao.NoticeDao;
import com.kh.notice.model.vo.Notice;

public class NoticeService {
	
	public List<Notice> selectList(){
		Connection conn = getConnection();
		List<Notice> list = new NoticeDao().selectList(conn);
		close(conn);
		return list;
	}
	
	public Notice selectOne(int no)
	{
		Connection conn = getConnection();
		Notice n = new NoticeDao().selectOne(conn,no);
		close(conn);
		return n;
	}
	
	public int upNotice(Notice n) {
		Connection conn = getConnection();
		int rs = new NoticeDao().upNotice(conn,n);
		if(rs>0)
			commit(conn);
		else
			rollback(conn);
		
		close(conn);
		return rs;
	}
	public int updateNotice(Notice n) {
		Connection conn = getConnection();
		int rs = new NoticeDao().updateNotice(conn,n);
		if(rs>0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		return rs;
	}
}

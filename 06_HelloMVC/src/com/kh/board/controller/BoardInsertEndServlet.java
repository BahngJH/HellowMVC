package com.kh.board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.board.model.vo.Board;
import com.kh.board.service.BoardService;
import com.oreilly.servlet.MultipartRequest;

import common.MyFileRenamePolicy;

/**
 * Servlet implementation class BoardInsertEndServlet
 */
@WebServlet("/board/boardFormEnd")
public class BoardInsertEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardInsertEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(!ServletFileUpload.isMultipartContent(request)) 
		{
			request.setAttribute("msg", "게시판 등록 오류[B-001]");
			request.setAttribute("loc", "/board/boardList");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			return;
		}
		
		String root = getServletContext().getRealPath("/");
		//가끔 호환 되지않는 운영체제를 만났을 경우 아래 처럼 사용
		String saveDir = root+"upload"+File.separator+"board";
		int maxSize = 1024*1024*10;
		
		
		MultipartRequest mr = new MultipartRequest(request, saveDir,maxSize,"utf-8", new MyFileRenamePolicy());
		
		Board b = new Board();
		b.setBoardTitle(mr.getParameter("title"));
		b.setBoardWriter(mr.getParameter("writer"));
		b.setBoardContent(mr.getParameter("content"));
		b.setBoardOriFile(mr.getOriginalFileName("upfile"));
		b.setBoardReFile(mr.getFilesystemName("upfile"));
		
		System.out.println(b);
		String msg ="";
		String loc ="";
		String view="/views/common/msg.jsp";
		
		int result = new BoardService().inserBoard(b);
		if(result>0) {
			//등록 성공
			msg="게시판 등록 성공했습니다";
			loc="/board/boardList";
			
		}else {
			//등록 실패
			msg="게시판 등록에 실패 했습니다";
			loc="/board/boardInsert";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher(view).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

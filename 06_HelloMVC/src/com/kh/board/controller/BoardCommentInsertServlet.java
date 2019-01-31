package com.kh.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.vo.BoardComment;
import com.kh.board.service.BoardService;

/**
 * Servlet implementation class BoardCommentInsertServlet
 */
@WebServlet("/board/commentInsert")
public class BoardCommentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardCommentInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int boardRef = Integer.parseInt(request.getParameter("boardRef"));
		String boardCommnetWriter = request.getParameter("boardCommentWriter");
		String boardCommentContent = request.getParameter("boardCommentContent");
		int boardCommentLevel = Integer.parseInt(request.getParameter("boardCommentLevel"));
		int boardCommentRef = Integer.parseInt(request.getParameter("boardCommentRef"));
		
		BoardComment bc = new BoardComment();
		bc.setBoardCommentContent(boardCommentContent);
		bc.setBoardCommnetWriter(boardCommnetWriter);
		bc.setBoardRef(boardRef);
		bc.setBoardCommentLevel(boardCommentLevel);
		bc.setBoardCommentRef(boardCommentRef);
		System.out.println(bc);
		
		int rs = new BoardService().inserComment(bc);
		String msg="";
		String loc="/board/boardView?no="+boardRef;
		if(rs>0) {
			msg="댓글 등록 성공";
			
		}else {
			msg="댓글 등록 실패";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

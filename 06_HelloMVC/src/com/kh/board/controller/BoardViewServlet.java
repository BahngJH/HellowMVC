package com.kh.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bs.model.vo.Member;
import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.BoardComment;
import com.kh.board.service.BoardService;

/**
 * Servlet implementation class BoardViewServlet
 */
@WebServlet("/board/boardView")
public class BoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardViewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int no =Integer.parseInt(request.getParameter("no"));
		
		Cookie[] cookies = request.getCookies();
		String boardCookieVal="";
		boolean hasRead = false;
		Member loginMember = (Member)request.getSession().getAttribute("loginMember");
		if(cookies!=null)
		{
			outer:
				for(Cookie c: cookies) {
					String name = c.getName();
					String value = c.getValue();
					if("boardCookie".equals(name))
					{
						boardCookieVal=value;
						if(value.contains("|"+no+"|")) 
						{
							hasRead=true;
						}
						break outer;
					}
				}
		}
		if(!hasRead)
		{
			Cookie boardCookie = new Cookie("boardCookie", boardCookieVal+"|"+no+"|");
			boardCookie.setMaxAge(-1); //브라우저 종료시 삭제
			response.addCookie(boardCookie);
		}
		
		String view="";
		Board b = new BoardService().selectOne(no,hasRead);
		
		
		if(b!=null) {
			List<BoardComment> bclist = new BoardService().selectCommentList(no);
			
			view="/views/board/boardView.jsp";
			request.setAttribute("bclist", bclist);
			request.setAttribute("board", b);
			
			
		}else {
			view="/views/common/msg.jsp";
			request.setAttribute("msg", "게시글이 없습니다");
			request.setAttribute("loc", "/board/boardList");
		}
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

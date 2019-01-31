package com.kh.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bs.model.vo.Member;
import com.kh.admin.model.service.AdminService;

/**
 * Servlet implementation class SearchMemberServlet
 */
@WebServlet("/admin/memberFinder")
public class SearchMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchMemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Member logginMember = (Member)request.getSession().getAttribute("loginMember");
		if(logginMember == null || !logginMember.getMemberId().equals("admin")) 
		{
			request.setAttribute("msg", "잘못된 경로로 접속하셨습니다");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			return;
		}
		String searchType = request.getParameter("searchType");
		String searchKeyword = request.getParameter("searchKeyword");
		
		System.out.println(searchKeyword);
		
		//비즈니스 로직 수행
		List<Member> list =null;
		switch(searchType)
		{
		case "userId" : list = new AdminService().selectId(searchKeyword);break;
		//case "userName" : list = new AdminService().selectName(serachKeyword);break;
		//case "gender" : list = new AdminService().selectGender(serachKeyword);break;
		}
		request.setAttribute("list", list);
		request.getRequestDispatcher("/views/admin/memberFinder.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

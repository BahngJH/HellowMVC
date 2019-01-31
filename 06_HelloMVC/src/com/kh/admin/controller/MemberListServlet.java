package com.kh.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bs.model.vo.Member;
import com.kh.admin.model.service.AdminService;

/**
 * Servlet implementation class MemberListServlet
 */
@WebServlet("/admin/memberList")
public class MemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Member logginMember = (Member)request.getSession().getAttribute("loginMember");
		if(logginMember == null || !logginMember.getMemberId().equals("admin")) 
		{
			request.setAttribute("msg", "잘못된 경로로 접속하셨습니다");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			return;
		}
		
		//페이징처리 하자
		int cPage; //현재 페이지를 의미
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		}catch (NumberFormatException e) {
			cPage = 1;
		}
		int numPerPage;	//페이지당 자료수
		try {
			numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
		}catch (NumberFormatException e) {
			numPerPage = 10;
		}
		
		//페이지수 만큼 데이터를 불러옴
		List<Member> list = new AdminService().selectMemberList(cPage,numPerPage);
		
		//페이지 구성해보기
		//전체 자료수를 확인
		int totalMember = new AdminService().selectMemberCount();
		//전체 페이수
		int totalPage = (int)Math.ceil((double)totalMember/numPerPage);
		//페이지바 html코드 누적변수
		String pageBar="";
		//페이지바 길이, 밑에 숫자 몇개 띄워줄것인지 나타내는 변수
		int pageBarSize =5;
		//pageNo를 기준으로 5개를 띄워주기위해 존재 1~4까지는 하앙 1이 나옴, 그 1을 기준으로 5개를 보여줌
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
		//pageEnd가 5를 넘어가면 다음 페이지로 나타내주기 위해 존재
		int pageEnd = pageNo+pageBarSize-1;
		
		//페이지바를 구성
		if(pageNo==1) {
			pageBar +="<span>[이전]</span>";
		}else {
			pageBar += "<a href='"+request.getContextPath()+
					"/admin/memberList?cPage="+(pageNo-1)+"&numPerPage="+numPerPage+"'>[이전]</a>";
		}
		//선택페이지 만들기
		while(!(pageNo>pageEnd || pageNo>totalPage))
		{
			if(cPage==pageNo) {
				pageBar += "<span class='cPage'>"+pageNo+"</span>";
			}else {
				pageBar += "<a href='"+request.getContextPath()+
						"/admin/memberList?cPage="+(pageNo)+"&numPerPage="+numPerPage+"'>"+pageNo+"</a>";
			}
			pageNo++;
		}
		//[다음] 구현
		if(pageNo>totalPage) 
		{
			pageBar += "<span>[다음]</span>";
		}else {
			pageBar +="<a href='"+request.getContextPath()+
					"/admin/memberList?cPage="+pageNo+"&numPerPage="+numPerPage+"'>[다음]</a>";
		}
		request.setAttribute("list", list);
		request.setAttribute("cPage", cPage);
		request.setAttribute("numPerPage", numPerPage);
		request.setAttribute("pageBar", pageBar);
		request.getRequestDispatcher("/views/admin/memberList.jsp").forward(request, response);
		
		
		/*페이징 처리하기전의 로직
		List<Member> list = new AdminService().selectAll();
		request.setAttribute("list", list);
		RequestDispatcher rd = request.getRequestDispatcher("/views/admin/memberList.jsp");
		rd.forward(request, response);*/
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package com.bs.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bs.model.service.MemberService;
import com.bs.model.vo.Member;

/**
 * Servlet implementation class UpdatePasswordEndServlet
 */
@WebServlet(name="UpdatePasswordEndServlet",urlPatterns="/updatePasswordEnd")
public class UpdatePasswordEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePasswordEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String cPassword = request.getParameter("password");
		String nPassword = request.getParameter("password_new");
		String id =request.getParameter("userId");
		
		Member m = new Member();
		m.setMemberId(id);
		String msg ="";
		String loc ="";
		String view ="views/common/msg.jsp";
		
		m = new MemberService().selectMember(m);
		if(m.getMemberPwd().equals(cPassword)) 
		{
			Member data = new Member();
			data.setMemberId(id);
			data.setMemberPwd(nPassword);
			int result = new MemberService().updatePassword(data);
			if(result>0) 
			{
				msg="비밀번호가 변경되었습니다";
				String script = "self.close()";
				request.setAttribute("script", script);
				
			}
			else 
			{
				msg="비밀번호 변경 실패했습니다.";
				loc="/member/updatePassword?userId="+id;
			}
		}else {
			msg="현재 암호와 일치하지 않습니다";
			loc="/member/updatePassword?userId="+id;
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

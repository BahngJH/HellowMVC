package com.bs.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bs.model.service.MemberService;
import com.bs.model.vo.Member;

/**
 * Servlet implementation class MemberUpdateServlet
 */
@WebServlet(name="MemberUpdateServlet", urlPatterns="/member/memberUpdate")
public class MemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String id = request.getParameter("userId");
		String pw = request.getParameter("password");
		String name = request.getParameter("userName");
		int age = Integer.parseInt(request.getParameter("age"));
		String addr = request.getParameter("address");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String[] hobby = request.getParameterValues("hobby");
		String gender = request.getParameter("gender");
		String ho="";
		
		String msg="";
		String loc="";
		String view="/views/common/msg.jsp";
		
		for(String i:hobby) {
			ho+=i+" ";
		}
		
		Member m = new Member();
		m.setMemberId(id);
		m.setMemberPwd(pw);
		m.setMemberName(name);
		m.setAge(age);
		m.setAddress(addr);
		m.setEmail(email);
		m.setPhone(phone);
		m.setGender(gender);
		m.setHobby(ho);
		
		int rs = new MemberService().updateMember(m);
		if(rs >0) {
			msg="회원정보 수정에 성공했습니다.";
			loc="/";
		}else {
			msg="회원정보 수정에 실패 하였습니다.";
			loc="/views/member/memberView.jsp";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

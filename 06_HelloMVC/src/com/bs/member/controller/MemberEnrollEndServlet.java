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
 * Servlet implementation class MemberEnrollEnd
 */
@WebServlet(name="MemberEnrollEndServlet", urlPatterns="/memberEnrollEnd")
public class MemberEnrollEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberEnrollEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//가입데이터를 받아오기!
		String id = request.getParameter("userId");
		String pw = request.getParameter("password");
		System.out.println("servlet : "+pw);
		String name = request.getParameter("userName");
		String age = request.getParameter("age");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String gender = request.getParameter("gender");
		String[] ho = request.getParameterValues("hobby");
		
		String hobby="";
		for(String i:ho) {
			hobby+=i+" ";
		}
		//문자열 합치는 다른 방법
		//String.join(",",ho);
		
		Member m = new Member();
		m.setMemberId(id);
		m.setMemberPwd(pw);
		m.setMemberName(name);
		m.setAge(Integer.parseInt(age));
		m.setEmail(email);
		m.setPhone(phone);
		m.setAddress(address);
		m.setGender(gender);
		m.setHobby(hobby);
		
		System.out.println(m.toString());
		MemberService service = new MemberService();
		int rs = service.enrollMember(m);
		
		String view ="views/common/msg.jsp";
		String loc="/";
		String msg="";
		
		if(rs>0)
		{
			msg="회원가입을 축하합니다";
			loc="/";
		}else {
			msg = "회원가입 실패";
			loc="views/member/enrollMember.jsp";
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

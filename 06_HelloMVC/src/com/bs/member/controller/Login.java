package com.bs.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bs.model.service.MemberService;
import com.bs.model.vo.Member;

/**
 * Servlet implementation class Login
 */
@WebServlet(name="Login", urlPatterns="/login.do")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String id = request.getParameter("userId");
		String pw = request.getParameter("password");
		

		Member m = new Member();
		m.setMemberId(id);
		m.setMemberPwd(pw);

		MemberService service = new MemberService();
		Member data = service.selectMember(m);

		//saveId값 확인해보기 
		String saveId = request.getParameter("saveId");
		System.out.println(saveId);
		
		String view = ""; //응답페이지 주소
		String msg = ""; //찾을 수 없으면 메시지!
		
		if (data == null) {
			// 아이디가 없다.
			System.out.println("아이디없음");
			msg= "입력하신 아이디가 존재하지 않습니다.";
			view = "/views/common/msg.jsp";
			request.setAttribute("loc", "/");
			request.setAttribute("msg", msg);
			RequestDispatcher rd = request.getRequestDispatcher(view);
			rd.forward(request, response);
			
		} else {
			if (pw.equals(data.getMemberPwd())) {
				// 로그인 성공
				System.out.println("로그인 성공!");
				//회원아이디 저장하기(쿠키이용해서)
				if(saveId!=null)
				{
					Cookie c = new Cookie("saveId",id);
					c.setMaxAge(7*24*60*60); //7일
					response.addCookie(c);
				}
				else {
					Cookie c =new Cookie("saveId",id);
					c.setMaxAge(0);
					response.addCookie(c);
				}
				
				view="/"; //localhost:9090/06_HelloMVC로 이동
				//request.setAttribute("loginMember", data);
				
				//로그인이 성공했으므로 session객체에 값을 넣고 유지하자!
				HttpSession sessetion = request.getSession(); //마지막 인자 디폴트 true값이다.
				sessetion.setAttribute("loginMember", data);
				//세션 최대 허용시간(초), 기본적으로 30분이면 끊어짐
				//sessetion.setMaxInactiveInterval(5);
				response.sendRedirect(request.getContextPath()+"/");
				
				
				
			} else {
				// 패스워드가 일치하지않다
				view = "views/common/msg.jsp";
				msg="입력하신 비밀번호가 맞지않습니다.";
				System.out.println("패스워드 불일치");
				request.setAttribute("loc", "/");
				request.setAttribute("msg", msg);
				RequestDispatcher rd = request.getRequestDispatcher(view);
				rd.forward(request, response);
			}
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

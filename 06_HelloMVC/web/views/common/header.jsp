<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.bs.model.vo.Member" %>
	
<%
	Member loginMember = (Member)session.getAttribute("loginMember");
	Cookie[] cookies = request.getCookies();
	String cookieValue = "";
	
	if(cookies!=null){
		for(Cookie c:cookies)
		{
			if(c.getName().equals("saveId")){
			
				cookieValue = c.getValue();
			}
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HelloMyPage</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/Css/style.css?ver=1">
<!--request.getContextPath()h() %> -> localhost:9090/06_HelloMVC 까지 나온다. -->
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
	//제이쿼리를 사용하겠다는 의미
	
		function validate(){
			if($('#userId').val().trim().length==0)
				{
					alert('아이디를 입력하세요');
					$('#userId').focus();
					return false;
				}
			if($('#password').val().trim().length==0)
				{
				alert('패스워드를 입력하세요');
				$('#password').focus();
				return false;
					}
			return true;
				}

</script>
</head>
<body>
	<div id="container">
		<header>
			<h1>Hello MVC</h1>
			<div class="login-container">
			<% if(loginMember == null) {%>
				<form id="loginFrm" action="<%=request.getContextPath()%>/login.do"
					method="post" onsubmit="return validate();">
					<table>
						<tr>
							<td><input type="text" id="userId" name="userId" placeholder="아이디" value='<%=cookieValue !="" ? cookieValue:"" %>'></td>
						</tr>
						<tr>
							<td><input type="password" id="password" name="password"
								placeholder="비밀번호"></td>
							<td><input type="submit" value="로그인"></td>
						</tr>
						<tr>
							<td colspan='2'><input type="checkbox" id="saveId" <%=cookieValue!=""? "checked": "" %>
								name="saveId"> <label for='saveid'>아이디 저장</label> &nbsp;
								<input type="button" value="회원가입"
								onclick="location.href='<%=request.getContextPath() %>/memberEnroll'"/></td>
						</tr>
					</table>
				</form>
				<% } 
				else { %>
				<table id="logged-in">
				<tr>
					<td><%= loginMember.getMemberName() %>님, 안녕하세요!</td>
				</tr>
				<tr>
				<td><input type="button" value="내정보보기" 
				onclick="location.href='<%=request.getContextPath()%>/member/memberView?userId=<%=loginMember.getMemberId()%>'"/>
				<input type="button" value="로그아웃" 
				onclick="location.href='<%= request.getContextPath()%>/member/logout'"/>
				</td>
				</tr>
				</table>
				<% } %>
			</div>
			<nav>
				<ul class="main-nav">
					<li class="home"><a href="<%=request.getContextPath()%>">Home</a></li>
					<li class="notice"><a href="<%=request.getContextPath()%>/notice/noticeList">공지사항</a></li>
					<li class="board"><a href="<%=request.getContextPath()%>/board/boardList">게시판</a></li>
					<li class="photo-board"><a href="">사진게시판</a></li>
					<% if(loginMember!=null && loginMember.getMemberId().equals("admin")) { %>
					<li class="admin-member"><a href="<%=request.getContextPath()%>/admin/memberList">회원관리</a></li>
					<% } %>
				</ul>
			</nav>
		</header>
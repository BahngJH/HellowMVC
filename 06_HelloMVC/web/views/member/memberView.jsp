<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Member m = (Member)request.getAttribute("member");

	String id = m.getMemberId();
	String name = m.getMemberName();
	String gender = m.getGender();
	int age = m.getAge();
	String address = m.getAddress();
	String phone = m.getPhone();
	String hobby = m.getHobby();
	String email = m.getEmail();
	
	String[] checkHobby = new String[5];
	if(hobby!=null){
		String[] hobbys = hobby.split(" ");
		
		for(String i:hobbys){
			switch(i){
			case "운동" : checkHobby[0]="checked";break;
			case "코딩" : checkHobby[1]="checked";break;
			case "여행" : checkHobby[2]="checked";break;
			case "독서" : checkHobby[3]="checked";break;
			}
		}
	}
%>    

<%@ include file="/views/common/header.jsp" %>
<link rel="stylesheet" href="Css/style.css">
	<section id = "enroll-container">
		<h1>회원정보 수정 페이지</h1> <!-- member/MemberUpdate -->
		<form id="memberFrm" method="post" onsubmit="return fn_update_validate();">
			<table>
			<tr>
				<th>아이디</th>
				<td><input type="text" value="<%=id %>" name="userId" id="userId_" readonly>
					</td>
			</tr>
			<!-- <tr>
				<th>패스워드</th>
				<td><input type="password" name="password" id="password_"
					required></td>
			</tr>
			<tr>
				<th>패스워드 확인</th>
				<td><input type="password" id="password_2" required></td>
			</tr> -->
			<tr>
				<th>이름</th>
				<td><input type="text" name="userName" id="userName" value="<%=name%>" required></td>
			</tr>
			<tr>
				<th>나이</th>
				<td><input type="number" name="age" value="<%=age%>" id="age" required></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="email" name="email" id="email" value="<%=email%>"  placeholder="db123@dfd.com" required></td>
			</tr>
			<tr>
				<th>휴대폰</th>
				<td><input type="tel" name="phone" id="phone" value="<%=phone%>" placeholder="(-)없이 입력" required></td>
			</tr>
			<tr>
				<th>주소</th>
				<td><input type="text" name="address" id="address" value="<%=address%>" required></td>
			</tr>
			<tr>
				<th>성별</th>
				<td>
				<% if(gender.equals("M")){ %>
				<input type="radio" name="gender" id="gender0" value="M" checked><label for="gender0">남</label>
				<input type="radio" name="gender" id="gender1" value="F" ><label for="gender1">여</label>
					<%}else { %>
					<input type="radio" name="gender" id="gender0" value="M" ><label for="gender0">남</label>
				<input type="radio" name="gender" id="gender1" value="F" checked><label for="gender1">여</label>
				<% } %>
				</td>
			</tr>
			<tr>
				<th>취미</th>
				<td>
				<input type="checkbox" name="hobby" id="hobby0" value="운동" <%=checkHobby[0] %>><label for="hobby0">운동</label>
				<input type="checkbox" name="hobby" id="hobby1" value="코딩" <%=checkHobby[1] %>><label for="hobby1">코딩</label>
				<input type="checkbox" name="hobby" id="hobby2" value="여행" <%=checkHobby[2] %> ><label for="hobby2">여행</label>
				<input type="checkbox" name="hobby" id="hobby3" value="독서" <%=checkHobby[3] %>><label for="hobby3">독서</label>
			
				</td>
			</tr>
		</table>
		<input type="button" onclick = "fn_update_member();" value="정보수정">
		<input type="button" onclick = "fn_update_password();" value="비밀번호 변경">
		<input type="button" onclick="confirmDelete();" value="탈퇴">
		</form>
	</section>
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script>
		function fn_update_password(){
			var url = "<%=request.getContextPath()%>/member/updatePassword?userId=<%=id%>";
			var title = "updatePassword";
			var status ="left=200px, top=200px, width=400px, height=250px";
			var popUp =open(url,title,status);
		}
	
	
		function fn_update_validate(){
			
			return true;
		}
		
		//회원정보 수정 했을 때 동작
		function fn_update_member(){
			var frm = $('#memberFrm');
			
			var url="<%= request.getContextPath()%>/member/memberUpdate";
			frm.attr('action',url);
			frm.submit();
			
		}
	
	</script>

<%@ include file="/views/common/footer.jsp" %>
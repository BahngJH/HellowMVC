<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/views/common/header.jsp"%>

<script>

	function fn_enroll_validate(){
		if($('input[name=idValid]')[0].value=='0'){
			alert("아이디 중복체크 하세요");
			return false;
		}
		var userId=$('#userId');
		if(userId.val().length<4){
			alert("최소 4자리 이상 입력하세요");
			userId.focus();
			return false;
		}
		return true;
	};
	
	$(function(){
		$("#password_2").blur(function(){
			var p1 = $('#password_').val();
			var p2 = $("#password_2").val();
			if(p1!=p2){
				alert("패스워드가 일치하지 않습니다.");
				$("#password_2").val('');
				$("#password_").focus();
			}
		})
	});
	
	//아이디 중복검사하기 : 팝업창을 띄워서 해보자~
	function fn_checkduplicate(){
		var userId=$("#userId_").val().trim();
		if(!userId || userId.length<4)
			{
				alert("아이디를 4글자 이상 입력하세요");
				return;
			}
		//팝업창에 대한 설정해주기!!
		var url ="<%=request.getContextPath()%>/checkIdDuplicate";
		var title ="checkIdDuplicate";
		var shape = "left = 200px, top = 100px, width = 300px, height=200px";
		
		var popup = open("",title,shape);
		
		//현재페이지에 있는값을 새창으로 옮기는 작업
		checkIdDuplicateFrm.userId.value = userId;
		//popup 창에서 이 폼을 작동시키게 하는 구문!
		checkIdDuplicateFrm.target = title;
		checkIdDuplicateFrm.action = url;
		checkIdDuplicateFrm.method = "post";
		checkIdDuplicateFrm.submit();
		
		//window.open(url, "명칭/여는방식", shape)
	}

</script>

<section id="enroll-container">
	<form action='<%=request.getContextPath()%>/memberEnrollEnd'
		method="post" name="memberEnrollFrm" onsubmit="return fn_enroll_validate()">
		<table>
			<tr>
				<th>아이디</th>
				<td><input type="text" placeholder="4글자 이상" name="userId" id="userId_" required>
					<input type="button" value="중복검사" onclick="fn_checkduplicate();">
					<input type="hidden" name="idValid" value="0">
					</td>
			</tr>
			<tr>
				<th>패스워드</th>
				<td><input type="password" name="password" id="password_"
					required></td>
			</tr>
			<tr>
				<th>패스워드 확인</th>
				<td><input type="password" id="password_2" required></td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" name="userName" id="userName" required></td>
			</tr>
			<tr>
				<th>나이</th>
				<td><input type="number" name="age" id="age" required></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td><input type="email" name="email" id="email" placeholder="db123@dfd.com" required></td>
			</tr>
			<tr>
				<th>휴대폰</th>
				<td><input type="tel" name="phone" id="phone" placeholder="(-)없이 입력" required></td>
			</tr>
			<tr>
				<th>주소</th>
				<td><input type="text" name="address" id="address" required></td>
			</tr>
			<tr>
				<th>성별</th>
				<td><input type="radio" name="gender" id="gender0" value="M" required><label for="gender0">남</label>
				<input type="radio" name="gender" id="gender1" value="F" required><label for="gender1">여</label></td>
			</tr>
			<tr>
				<th>취미</th>
				<td><input type="checkbox" name="hobby" id="hobby0" value="운동" ><label for="hobby0">운동</label>
				<input type="checkbox" name="hobby" id="hobby1" value="코딩" ><label for="hobby1">코딩</label>
				<input type="checkbox" name="hobby" id="hobby2" value="여행" ><label for="hobby2">여행</label>
				<input type="checkbox" name="hobby" id="hobby3" value="독서" ><label for="hobby3">독서</label></td>
			</tr>
		</table>
		<input type="submit" value="가입">
		<input type="reset" value="취소">
	</form>
	<form action="" name="checkIdDuplicateFrm">
		<input type="hidden" name ="userId">
	
	</form>
</section>



<%@ include file="/views/common/footer.jsp"%>
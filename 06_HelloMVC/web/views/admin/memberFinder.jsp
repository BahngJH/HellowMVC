<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.ArrayList"%>

<%
	ArrayList<Member> list = (ArrayList)request.getAttribute("list");
	String searchType = request.getParameter("searchType");
	String searchKeyword = request.getParameter("searchKeyword");

%>
    
<%@ include file="/views/common/header.jsp" %>


<style type ="text/css">

	section#memberList-container table#tbl-member{
	width:"100%"; border:1px solid gray;
	border-collapse:collapse;
	}
	section#memberList-container table#tbl-member th, table#tbl-member td{
		border:1px solid gray;
		padding:10px
	}
	div#search-container{
		margin:0 0 10px 0;
		padding: 3px;
		background-color:rgba(0,188,212,0.3);
	}
	div#search-userId{
		display: inline-block;
	}
	div#search-userName{
		display:none;
	}
	div#search-gender{
		display:none;
	}
	#memberList-container{
		text-align:center;
	}
	
</style>

<script>
	var searchType = document.querySelector("#searchType");
	searchType.addEventListener("change", function(){
		document.memberFinderFrm.searchType.value = this.value;
		var key = $("input[name=searchKeyword]");
		changeSearchKeyword(key);
		submitMemberFinder();
		
	});
	function changeSearchKeyword(obj){
		
		document.memberFinderFrm.searchKeyword.value = obj.value;
	}
	function submitMemberFinder(){
		document.memberFinderFrm.submit();
	}

</script>

	<section id="memberList-container">
		<h2>회원관리</h2>
			<!-- 검색기능 추가 -->
			<div id="search-container">
				검색타입:
				<select id="searchType">
					<option value="userId">아이디</option>	
					<option value="userName">회원명</option>
					<option value="gender">성별</option>
				</select>
				<div id="search-userId">
					<form action="<%=request.getContextPath()%>/admin/memberFinder">
						<input type="hidden" name="searchType" value="userId">
						<input type="text" name="searchKeyword" size="25" placeholder="검색할 아이디를 입력하세요">	
						<button type="submit">검색</button>
					</form>
				</div>
				<div id="search-userName">
					<form action="<%=request.getContextPath()%>/admin/memberFinder">
						<input type="hidden" name="searchType" value="userName">
						<input type="text" name="searchKeyword" size="25" placeholder="검색할 이름을 입력하세요">	
						<button type="submit">검색</button>
					</form>
				</div>
				<div id="search-gender">
					<form action="<%=request.getContextPath()%>/admin/memberFinder">
						<input type="hidden" name="searchType" value="gender">
						<input type="radio" name="searchKeyword" value="M" >남	
						<input type="radio" name="searchKeyword" value="F" >여	
						<button type="submit">검색</button>
					</form>
				</div>
			</div>
		
		
		<table id="tbl-member">
			<thead>
			<tr>
				<th>아이디</th>
				<th>이름</th>
				<th>성별</th>
				<th>나이</th>
				<th>이메일</th>
				<th>전화번호</th>
				<th>주소</th>
				<th>취미</th>
				<th>가입날짜</th>
			</tr>
		</thead>
		<tbody>
			<%if(list == null || list.isEmpty()) { %>
				<tr>
					<td colspan="9" align="center">검색결과 없습니다.</td>
				</tr>		
		<%}else { 
			for(Member m: list){%>
				<tr>
					<td><%=m.getMemberId()%></td>
					<td><%=m.getMemberName()%></td>
					<td><%=m.getGender()%></td>
					<td><%=m.getAge()%></td>
					<td><%=m.getEmail()%></td>
					<td><%=m.getPhone()%></td>
					<td><%=m.getAddress()%></td>
					<td><%=m.getHobby()%></td>
					<td><%=m.getEnrollDate()%></td>
				</tr>
				 <%}
			} %>
		</tbody>
		
		</table>
		
	</section>

<%@ include file="/views/common/footer.jsp" %>
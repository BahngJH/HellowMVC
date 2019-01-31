<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import ="com.kh.notice.model.vo.Notice"%>
    
<%
	Notice n =(Notice) request.getAttribute("notice");
%>
<%@ include file="/views/common/header.jsp" %>
     <style>
    secition#notice-container{width:600px; margin:0 auto; text-align:center;}
    section#notice-container h2{margin:10px 0;}
    table#tbl-notice{width:500px; margin:0 auto; border:1px solid black; border-collapse:collapse; clear:both; }
    table#tbl-notice th {width: 125px; border:1px solid; padding: 5px 0; text-align:center;} 
    table#tbl-notice td {border:1px solid; padding: 5px 0 5px 10px; text-align:left;}
    </style>

<section id='notice-container'>
	<h2>공지사항</h2>
	<table id='tbl-notice'>
		<tr>
			<td>제목</td>
			<td><%=n.getNoticeTitle() %></td>
		</tr>
		<tr>
			<td>작성자</td>
			<td><%=n.getNoticeWriter()%></td>
		</tr>
		<tr>
			<td>첨부파일</td>
			<td><%if(n.getFilePath()!=null){%>
					<img alt="첨부파일" src="<%=request.getContextPath()%>/images/file.png" width='16px'>
			<%} %></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><%=n.getNoticeContect()%></td>
		</tr>
		<tr>
			<td>작성일</td>
			<td><%=n.getNoticeDate()%></td>
		</tr>
		<%if(loginMember!=null && loginMember.getMemberId().equals("admin")){ %>
		<tr>
			<th colspan='2'>
				<input type="button" value="수정하기" onclick="fn_updateNotice()">
				<input type="button" value="삭제하기" onclick="fn_deleteNotice()">
			</th>
		</tr>
		<%} %>
	</table>
	<script>
	 function fn_updateNotice(){
		 //페이지를 정보수정으로 변경
		 location.href="<%=request.getContextPath()%>/notice/updateNotice?no=<%=n.getNoticeNo()%>";
		 
		 
	 }
	</script>
</section>

<%@ include file="/views/common/footer.jsp" %>
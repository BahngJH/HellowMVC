<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.kh.notice.model.vo.Notice"%>
    
<%
	List<Notice> list = (List)request.getAttribute("list");


%>

<%@ include file="/views/common/header.jsp" %>
<style>
	section#notice-container{width:600px; margin:0 auto; text-align:center;}
	section#notice-container h2{
		margin:10px 0;
	}
	table#tbl-notice{
		width:100%;
		margin:0 auto;
		border:1px solid black;
		border-collapse:collapse;
	}
	table#tbl-notice th, table#tbl-notice td{
		border: 1px solid black;
		padding: 5px 0;
		text-align:center;
		
	}
	input#btn-add{
		float:right;
		margin:0 0 15px;
	}

</style>


<script>
	function fn_add(){
		location.href="<%=request.getContextPath()%>/notice/noticeForm";
	}
	
</script>
<section id="notice-container">
	<h2>공지사항</h2>
	<% if(loginMember!=null && loginMember.getMemberId().equals("admin")){ %>
	<input type="button" value="글쓰기" id="btn-add" onclick="fn_add()">
	<%} %>
	
	<table id="tbl-notice">
		<tr>
			<td>번호</td>
			<td>제목</td>
			<td>작성자</td>
			<td>첨부파일</td>
			<td>작성일</td>
		</tr>
		<%for(Notice n:list){ %>
		<tr>
			<td><%=n.getNoticeNo() %></td>
			<td><a href="<%=request.getContextPath()%>/notice/noticeView?no=<%=n.getNoticeNo()%>">
			<%=n.getNoticeTitle()%></a></td>
			<td><%=n.getNoticeWriter() %></td>
		<td><%if(n.getFilePath()!=null){ %>
		<img alt='첨부파일' src="<%=request.getContextPath()%>/images/file.png" width="16px">
		<%} %></td>
		<td><%=n.getNoticeDate() %></td>
		</tr>
		<%} %>
	</table>

</section>


<%@ include file="/views/common/footer.jsp" %>
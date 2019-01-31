<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.kh.notice.model.vo.Notice"%>

<%
	Notice n = (Notice) request.getAttribute("notice");
%>
<%@ include file="/views/common/header.jsp"%>

<style>
secition#notice-container {
	width: 600px;
	margin: 0 auto;
	text-align: center;
}

section#notice-container h2 {
	margin: 10px 0;
}

table#tbl-notice {
	width: 500px;
	margin: 0 auto;
	border: 1px solid black;
	border-collapse: collapse;
	clear: both;
}

table#tbl-notice th {
	width: 125px;
	border: 1px solid;
	padding: 5px 0;
	text-align: center;
}

table#tbl-notice td {
	position:relative;
	border: 1px solid;
	padding: 5px 0 5px 10px;
	text-align: left;
}
span#fname{
	position:absolute;
	left:86px;
	top:9px;
	width:285px;
	background: #f5f5f5;
}
</style>
<script>
	$(function(){
		$('[name=upfile]').change(function(){
			if($(this).val==""){
				$("#fname").show();
			}else{
				$("#fname").hide();
			}
		});
	});
	
	function validate()
	{
		var content=$("[name=content]").val().trim();
		if(content.length<0){
			alert("내용을 입력하세요");
			return false;
		}
		return true;
	}

</script>
<section id='notice-container'>
	<h2>공지사항</h2>
	<form action="<%=request.getContextPath()%>/notice/updateNoticeEnd"
		method="post" enctype="multipart/form-data">
		<table id='tbl-notice'>
			<tr>
				<td>번호</td>
				<td><input type="text" name="no" value="<%=n.getNoticeNo()%>"
					readonly></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="title"
					value="<%=n.getNoticeTitle()%>" required></td>
			</tr>
			<tr>
				<td>작성자</td>
				<td><input type="text" value="<%=n.getNoticeWriter()%>"
					readonly></td>
			</tr>
			<tr>
				<td>첨부파일</td>
				<td>
					<%
						if (n.getFilePath() != null) {
					%> <input type="file" name="upfile"
					value="<%=n.getFilePath()%>">
					<span id="fname"><%=n.getFilePath()%></span>
					<input type="hidden" name="old_file" value="<%=n.getFilePath()%>"> 
					<%} else {%> 
					<input type="file" name="upfile">
					<%}%>
				</td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea rows="5" cols="50" name="content"
						value="<%=n.getNoticeContect()%>"></textarea></td>
			</tr>
			<tr>
				<td>작성일</td>
				<td><%=n.getNoticeDate()%></td>
			</tr>
			<tr>
				<th colspan='2'><input type="submit" value="등록하기"
					onclick="validate()"></th>
			</tr>
		</table>
	</form>
</section>
<%@ include file="/views/common/footer.jsp"%>
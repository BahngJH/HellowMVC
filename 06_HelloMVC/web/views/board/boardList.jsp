<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.kh.board.model.vo.Board, java.util.*" %>

<%
	List<Board> list = (List)request.getAttribute("list");
	String pageBar = (String)request.getAttribute("pageBar");

%>    
<%@ include file="/views/common/header.jsp" %>
   
   <style>
   section#board-container{width:600px; margin:0 auto; text-align:center;}
   section#board-container h2{margin:10px 0;}
   table#tbl-board{width:100%; margin:0 auto; border:1px solid black; border-collapse:collapse; clear:both; }
   table#tbl-board th, table#tbl-board td {border:1px solid; padding: 5px 0; text-align:center;} 
   /*글쓰기버튼*/
   input#btn-add{float:right; margin: 0 0 15px;}
   /*페이지바*/
   div#pageBar{margin-top:10px; text-align:center; background-color:rgba(0, 188, 212, 0.3);}
   div#pageBar span.cPage{color: #0066ff;}
   </style>
   
	<section id="board-container">
		<h2>게시판</h2>
		<%if(loginMember!=null){ %>
		<input type="button" value="글쓰기" id="btn-add" onclick="fn_addBoard();">
		<%} %>
		<script>
			function fn_addBoard(){
				location.href="<%=request.getContextPath()%>/board/boardInsert";
			}
		</script>
		<table id="tbl-board">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>첨부파일</th>
				<th>내용</th>
				<th>조횟수</th>
			</tr>
			<% for(Board b : list) { %>
			<tr>
				<td><%=b.getBoardNo()%></td>
				<td><a href="<%=request.getContextPath()%>/board/boardView?no=<%=b.getBoardNo()%>">
				<%=b.getBoardTitle()%></a></td>	
				<td><%=b.getBoardWriter()%></td>	
				<td><%=b.getBoardDate()%></td>	
				<td><%if(b.getBoardReFile()!=null){ %>
		<img alt='첨부파일' src="<%=request.getContextPath()%>/images/file.png" width="16px">
		<%} %></td>
				<td><%=b.getBoardContent()%></td>		
				<td><%=b.getReadCount()%></td>			
			</tr>
			<%} %>
		</table>
	<div>
	<%=pageBar %>
	</div>
	</section>



<%@ include file="/views/common/footer.jsp" %>
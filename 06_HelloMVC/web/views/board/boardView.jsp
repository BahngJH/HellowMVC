<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="com.kh.board.model.vo.*, java.util.*" %>
<%
	Board b = (Board)request.getAttribute("board");
	List<BoardComment> commentList = (List)request.getAttribute("bclist");


%>

<%@ include file="/views/common/header.jsp" %>

<style>
    sectionboard-container{width:600px; margin:0 auto; text-align:center;}
    section#board-container h2{margin:10px 0;}
    table#tbl-board{width:500px; margin:0 auto; border:1px solid black; border-collapse:collapse; clear:both; }
    table#tbl-board th {width: 125px; border:1px solid; padding: 5px 0; text-align:center;} 
    table#tbl-board td {border:1px solid; padding: 5px 0 5px 10px; text-align:left;}
    div#comment-container{text-align:center;}
    div#comment-container button#btn-insert{width:60px; height:50px; color:white; background:#3300ff; position:relative; top:-20px;}
    
     table#tbl-comment{width:580px; margin:0 auto; border-collapse:collapse; clear:both; } 
    table#tbl-comment tr td{border-bottom:1px solid; border-top:1px solid; padding:5px; text-align:left; line-height:120%;}
    table#tbl-comment tr td:first-of-type{padding: 5px 5px 5px 50px;}
    table#tbl-comment tr td:last-of-type {text-align:right; width: 100px;}
    table#tbl-comment button.btn-reply{display:none;}
    table#tbl-comment button.btn-delete{display:none;}
    table#tbl-comment tr:hover {background:lightgray;}
    table#tbl-comment tr:hover button.btn-reply{display:inline;}
    table#tbl-comment tr:hover button.btn-delete{display:inline;}
    table#tbl-comment tr.level2 {color:gray; font-size: 14px;}
    table#tbl-comment sub.comment-writer {color:navy; font-size:14px}
    table#tbl-comment sub.comment-date {color:tomato; font-size:10px}
    table#tbl-comment tr.level2 td:first-of-type{padding-left:100px;}
    table#tbl-comment tr.level2 sub.comment-writer {color:#8e8eff; font-size:14px}
    table#tbl-comment tr.level2 sub.comment-date {color:#ff9c8a; font-size:10px}
    </style>
    
<section id="board-container">
	<h2>게시판</h2>
		<table id="tbl-board">
		<tr>
			<th>제목</th>
			<td><%=b.getBoardTitle()%></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><%=b.getBoardWriter()%></td>
		</tr>
		<tr>
			<th>조횟수</th>
			<td><%=b.getReadCount() %></td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>
			<%if(b.getBoardReFile()!=null){ %>
			<a href="javascript:fn_fileDounwLoad('<%=b.getBoardReFile()%>','<%=b.getBoardOriFile()%>');">
			<img alt="첨부파일" src="<%=request.getContextPath()%>/images/file.png" 
							width="16px">
				</a>
					<%=b.getBoardOriFile() %>
			<%} %>
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td><%=b.getBoardContent() %></td>
		</tr>
		<%if(loginMember.getMemberId().equals(b.getBoardWriter()) || loginMember.getMemberId().equals("admin")){ %>
		<tr>
			<th colspan="2">
				<input type="button" value="목록으로" onclick="fn_boardList();">
				<input type="button" value="수정하기" onclick="fn_updateBoard();">
				<input type="button" value="삭제하기" onclick="fn_deleteBoard();">
			</th>
		</tr>
		<%} %>
		</table>
		
		
		<div id="comment-container">
		
			<form action="<%=request.getContextPath()%>/board/commentInsert" name="boardCommentFrm" method="post">
				<input type="hidden" name="boardRef" value="<%=b.getBoardNo()%>">
				<input type="hidden" name="boardCommentWriter" value="<%=loginMember.getMemberId()%>">
				<input type="hidden" name="boardCommentLevel" value="1">
				<input type="hidden" name="boardCommentRef" value="0">
				<textarea rows="3" cols="60" name="boardCommentContent"></textarea>
				<button type="submit" id="btn-insert">등록</button> 
			</form>
		
		</div>
		
		<!-- 댓글목록 테이블 -->
		<table id="tbl-comment">
			<%if(commentList!=null){
				for(BoardComment c : commentList){
					if(c.getBoardCommentLevel()==1){
				%>
					<tr class='level1'>
						<td>
							<sub class="comment-writer"><%=c.getBoardCommnetWriter() %></sub>
							<sub class="comment-date"><%=c.getBoardCommentDate() %></sub>
							<br>
							<%=c.getBoardCommentContent() %>
						</td>
						<td>
							<button class="btn-reply" value="<%=c.getBoardCommentNo()%>">답글</button>
							<%if(loginMember.getMemberId()!=null && (loginMember.getMemberId().equals(c.getBoardCommnetWriter()) || loginMember.getMemberId().equals("admin"))) {%>
							<button class="btn-delete" value="<%=c.getBoardCommentNo()%>">삭제</button>
							<%} %>
						</td>
					</tr>
					<%}else{%>
					<tr class='level2'>
						<td>
							<sub class="comment-writer"><%=c.getBoardCommnetWriter() %></sub>
							<sub class="comment-date"><%=c.getBoardCommentDate() %></sub>
							<br>
							<%=c.getBoardCommentContent() %>
						</td>
						<td>
							
						</td>
					</tr>
					
					<% }
					}
				}%>
		</table>
		
		<form action="<%=request.getContextPath()%>/board/boardDelete" method="post" name="boardDelFrm">
			<input type="hidden" name="no" value="<%=b.getBoardNo() %>">
			<input type="hidden" name="renameFile" value="<%=b.getBoardReFile()%>">
			
		</form>
</section>
<script>
	$('.btn-reply').on('click',function(e){
		<%if(loginMember!=null){%>
			var tr = $("<tr></tr>");
			var html = "<td style = 'display:none; text-align:left;' colspan='2'>";
			html +="<form action='<%=request.getContextPath()%>/board/commentInsert' method='post'>";
			html +="<input type='hidden' name='boardRef' value='<%=b.getBoardNo()%>'>";
			html +="<input type='hidden' name='boardCommentWriter' value='<%=loginMember.getMemberId()%>'>";
			html +="<input type='hidden' name='boardCommentLevel' value='2'>";
			html +="<input type='hidden' name='boardCommentRef' value='"+$(this).val()+"'>";
			html +="<textarea name='boardCommentContent' cols='60' rows='1'></textarea>";
			html +="<button type='submit' class='btn-insert2'>등록</button>";
			html +="</form></td>";
			tr.html(html);
			tr.insertAfter($(this).parent().parent()).children("td").slideDown(800);
			$(this).off('click');
			
			
			//vaildate 해준것
			tr.find('form').submit(function(e){
				if(<%=loginMember==null%>){
						fn_logginAlert();
						e.preventDefault();
						return;
					}
				var len=$(this).children('textarea').val().trim().length;
				if(len==0){
					e.preventDefault();
					}
				});
			tr.find("textarea").focus();
			<%}%>
			
	});
	


	function fn_boardList(){
		location.href="<%=request.getContextPath()%>/board/boardList";
	}

	function fn_updateBoard() {
		location.herf ="<%=request.getContextPath()%>/board/boardUpdate?no=<%=b.getBoardNo()%>";
	}
	
	function fn_deleteBoard(){
		if(!confirm("정말로 삭제 하시겠습니까?"))
			{
				return;
			}
		$('[name=boardDelFrm]').submit();
	}
	function fn_fileDounwLoad(rName, oName){
		
		var url ="<%=request.getContextPath()%>/board/boardFileDounLoad";
		//한글 파일명이 있을 경우 인코딩 처리를 해줘야함!!
		oName = encodeURIComponent(oName);
		location.href=url+"?oName="+oName+"&rName="+rName;		
	}
	
	$(function(){
		
		//댓글 삭제
		$('btn-delete').click(function(e){
			if(!confirm('정말로 삭제 하시겠습니까?')){
				return;
			}
			location.href="<%=request.getContextPath()%>/board/boardCommentDelete?no=<%=b.getBoardNo()%>&del="+$(this).val();
				
		});
		
		$('[name=boardCommentContent]').focus(function(){
			if(<%=loginMember.getMemberId()==null%>){
				fn_loginAlert();
			}
		});
		$('[name=boardCommentFrm]').submit(function(e){
			if(<%=loginMember.getMemberId()==null%>)
				{
					fn_loginAlert();
					e.preventDefault();
					return;
				}
			var len =$('textarea[name=boardCommentContent]').val().trim().length;
			if(len==0){
				alert("내용을 입력하세요");
				e.preventDefault();
			}
		})
		
	});
	function fn_loginAlert(){
		alert("로그인 후 이용할 수 있습니다.");
		$('#userId').focus();
	}
</script>



<%@ include file="/views/common/footer.jsp" %>
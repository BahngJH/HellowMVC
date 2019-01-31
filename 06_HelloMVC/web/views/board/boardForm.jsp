<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/views/common/header.jsp" %>

<style>
    sectionboard-container{width:600px; margin:0 auto; text-align:center;}
    section#board-container h2{margin:10px 0;}
    table#tbl-board{width:500px; margin:0 auto; border:1px solid black; border-collapse:collapse; clear:both; }
    table#tbl-board th {width: 125px; border:1px solid; padding: 5px 0; text-align:center;} 
    table#tbl-board td {border:1px solid; padding: 5px 0 5px 10px; text-align:left;}
    </style>
    
<script>
	function validate(){
		var content = $('[name = content]').val();
		if(content.trim().length==0){
			alert("내용을 입력하세요");
			return false;
		}
		return true;
	}

</script>
<section>
	<form action="<%=request.getContextPath()%>/board/boardFormEnd" method="post" enctype="multipart/form-data">
		<table>
		<tr>
			<th>제목</th>
			<td><input type="text" name="title" required="required"></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><input type="text" value="<%=loginMember.getMemberId()%>" name="writer" readonly></td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td><input type="file" name="upfile"></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="5" cols="50" name="content"></textarea></td>
		</tr>
		<tr>
			<th colspan="2"></th>
			<td><input type="submit" value="등록하기" onclick="return validate();"></td>
		</tr>
		</table>
	</form>

</section>

<%@ include file="/views/common/footer.jsp" %>
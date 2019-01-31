<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	String msg = (String)request.getAttribute("msg");
	String loc = (String)request.getAttribute("loc");
	String script = (String)request.getAttribute("script");


%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	//서블릿에서 작성한 메시지 호출
	alert('<%=msg%>'); 
	//스크립트가 널이 아니면 script가 나오고 아니면 공란("");
	<%= script!=null? script : ""%>
	//호출 후 페이지 메인으로 이동
	location.href='<%=request.getContextPath()+loc%>';
</script>



</head>
<body>

</body>
</html>
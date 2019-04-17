<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<h2>구구단 테이블</h2>
		<hr>
		<br>
		<table border="1" style="text-align:center">
			<tr><th>*</th>
				<% for(int i=3; i<=9; i+=2) { %>
				<th><%=i %></th>
				<% }%>
			</tr>
			<% for(int i=3; i<=9; i+=2) { %>
				<th><%=i %></th>
				<% }%>
			<tr>
			
			</tr>
		</table>
	</center>
</body>
</html>
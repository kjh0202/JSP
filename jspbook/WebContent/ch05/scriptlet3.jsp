<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>scriptlet3</title>
</head>
<body>
	<center>
		<h2>스크립트릿 테스트3 : x*x출력</h2>
		<hr>
		<table border="1" style="text-align:center">
			<tr><th>X</th><th>Y (=X*X)</th></tr>
				<%
					for(int i=1; i<=10; i++) {
				%>
					<tr><td><%= i %></td><td><%= i*i %></td>
		
				<%
					}
				%>
				</tr>
		</table>
	</center>
</body>
</html>
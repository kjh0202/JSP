<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>구구단 테이블</title>
</head>
<body>
	<center>
		<h2>구구단 테이블</h2>
		<hr>
		<br>
		<table border="1" style="text-align:center">
			<tr><th></th><th>3&nbsp&nbsp5&nbsp&nbsp7&nbsp&nbsp9</th></tr>
			<tr><td width="30">3</td>
			<td width="100"><%
				for(int i=3; i<=9; i+=2) {
					out.println(3*i);
				}
				%>
			</td></tr>
			<tr><td>5</td>
			<td><%
				for(int i=3; i<=9; i+=2) {
					out.println(5*i);
				}
				%>
			</td></tr>
			<tr><td>7</td>
			<td><%
				for(int i=3; i<=9; i+=2) {
					out.println(7*i);
				}
				%>
			</td></tr>
			<tr><td>9</td>
			<td><%
				for(int i=3; i<=9; i+=2) {
					out.println(9*i);
				}
				%>
			</td></tr>	
		</table>
	</center>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="member.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>login화면</title>
	<style>
		lable {
		display : block;		
		}
	</style>
</head>
<body>
	<center>
	<h2>Member Login</h2>
	<hr>
	<form name="loginForm" action=/jspbook/member/memberProcServlet?action=login method=post>
		<lable>ID : 
			<input type="text" name="id" size="10"></lable><br>
		<lable>Password : 
			<input type="password" name="password" size="10"></lable><br>
		<input type="submit" value="로그인" name="B1">
		<input type="reset" value="취소"name="B2"><br><br>
	</form>
		<button onclick="location.href='register.html'">회원가입</button>
	</center>
</body>
</html>
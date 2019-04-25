<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Login</title>
	<style>
		input[type=text] { /* text 창에만 적용 */
			color : red; 
		}
		input:hover, textarea:hover { /* 마우스 올라 올 때 */
			background : aliceblue;
		}
		input[type=text]:focus, input[type=password]:focus { /* 포커스 받을 때 */
			font-size : 120%; 
		}
		label {
			display : block; /* 새 라인에서 시작 */
			padding : 5px;
		}
		label span {
			display : inline-block;
			width : 90px;
			text-align : right;
			padding : 10px;
		}
	</style>
</head>
<body>
	<center><br>
	<h3>Member Login</h3><br>
	<hr>
	<!-- <form name="loginForm" action=/jspbook/member/loginProcServlet method=post> -->
	<form name="loginForm" action="/jspbook/member/memberProcServlet?action=login" method=post>
		<label><span>ID:</span>
			<input type="text" name="id" size="10"></label>
		<label><span>Password:</span>
			<input type="password" name="password" size="10"></label><br>
		<input type="submit" value="로그인" name="B1">&nbsp;&nbsp;
		<input type="reset" value="취소" name="B2">
	</form>
		<br><br><button onclick="location.href='register.html'">회원 가입</button>
</body>
</html>
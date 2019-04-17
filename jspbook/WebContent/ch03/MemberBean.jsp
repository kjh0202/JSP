<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%request.setCharacterEncoding("UTF-8");%>
<jsp:useBean id="member" scope="page" class="jspbook.ch03.Member" />
<jsp:setProperty name="member" property="*" />
<%member.createId();%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>
</head>
<body>
	<h3>회원가입</h3>
	<hr>
	<p>회원에 가입하시려면 아래 정보를 입력해 주십시오.
	<form name="form1" method=post>
		이름 : <input type="text" name="name" width="500" size="15" /><br>
		이메일 : <input type = "text" name="email" width="500" size="15" /><br>
		전화번호 : <input type="text" name="tel" width="500" size="15" /><br><br>
		<input type="submit" value="가입" name="B1">
		<input type="reset" value="다시 입력" name="B2">
	<br>
	</form>
	<br>
	<hr>
	<br>
	회원가입을 축하합니다.<br>
	회원 아이디는 <jsp:getProperty name="member" property="id" /> 입니다.
</body>
</html>
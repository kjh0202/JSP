<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int result = 0;
	if (request.getMethod().equals("POST")) {
		String op = request.getParameter("operator");
		int num1 = Integer.parseInt(request.getParameter("num1"));
		int num2 = Integer.parseInt(request.getParameter("num2"));
		switch(op) {
		case "+":
			result = num1 + num2; break;
		case "-":
			result = num1 - num2; break;
		case "*":
			result = num1 * num2; break;
		case "/":
			result = num1 / num2; break;
		}
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>계산기1</title>
</head>
<body>
	<center>
	<h3>계산기1</h3>
	<hr>
	<form name="form1" method=post>
		<input type = "text" name="num1" width="200" size="5">
		<select name="operator">
			<option selected>+</option>
			<option>-</option>
			<option>*</option>
			<option>/</option>
		</select>
		<input type="text" name="num2" width="200" size="5">
		<input type="submit" value="계산" name="B1">
		<input type="reset" value="다시 입력" name="B2">
	</form>
	<hr>
	<br>
	계산 결과 : <%=result%> 
</body>
</html>
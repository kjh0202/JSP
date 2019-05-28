<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>설 문 조 사</h2>

	<form action="response.jsp" method="post">
		<label><span>이름 : </span>
			<input type="text" name="name" size="10"></label><br><br>
		<label><span>성별 :</span>
			<input type="radio" name="gender" value="male">남자
			<input type="radio" name="gender" value="woman">여자<br><br>
		<label><span>좋아하는 계절 :</span>
			<input type="checkbox" name="season" value="1">봄
			<input type="checkbox" name="season" value="2">여름
			<input type="checkbox" name="season" value="3">가을
			<input type="checkbox" name="season" value="4">겨울<br><br>
		<input type = "submit" value = "제출"/>
	</form>
</body>
</html>
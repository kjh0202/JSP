<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="pruduct_response.jsp" method="post">
		<label><span>물품을 선택하세요</span><br><br>
			<input type="checkbox" name="favorite" value="1">모자
			<input type="checkbox" name="favorite" value="2">TV
			<input type="checkbox" name="favorite" value="3">신발<br><br>
		<input type = "submit" value = "합계"/>
		<input type = "reset" value = "취소"/>
	</form>
</body>
</html>
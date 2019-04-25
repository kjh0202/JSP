<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%	request.setCharacterEncoding("UTF-8"); %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>트위터 메인 화면</title>
</head>
<body>
	<div align=center>
	<h3>My Simple Twitter!!</h3>
	<a href="bbsServlet?action=list&page=1">게시판</a>&nbsp;&nbsp;
	<a href="memberProcServlet?action=list&page=1">회원</a>&nbsp;&nbsp;
	<a href="memberProcServlet?action=logout">로그아웃</a>
	<hr>
	<form action="twitServlet" method="POST">
		<!-- 세션에 저장된 이름 출력 -->
		@${memberName}&nbsp;
		<input type="text" name="msg">&nbsp;&nbsp;
		<input type="submit" value="트윗">
	</form>
	<hr>
	<div align="left">
	<ul>
		<!-- application 내장객체를 통해 msgs 이름으로 저장된 ArrayList를 가지고 옴 -->
		<c:set var="msgs" value="${applicationScope.msgs}"/>
		<!-- msgs가 null 이 아닌 경우에만 목록 출력 -->
		<c:if test="${not empty msgs}">
			<c:forEach var="msg" items="${msgs}">
				<li>${msg}</li>
			</c:forEach>
		</c:if>
	</ul>
	</div>
	</div>
</body>
</html>
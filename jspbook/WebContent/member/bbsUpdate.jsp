<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, member.*" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>게시글 수정</title>
	<style>
		td, th { text-align: center; }
		.button {
			font-weight: bold; font-size: 9pt;
			border: 1px solid powderblue;
		}
		input[type=submit] {
			width: 5em; height: 2.5em;
			font-weight: bold; font-size: 10pt;
		}
	</style>
</head>
<body>
	<center>
	<h3>게시글 수정</h3>
	<hr>
	<c:set var="bm" value="${requestScope.bbsMember}"/>
	<table border="1" style="border-collapse:collapse;">
	<tr bgcolor="skyblue"><th height="30" width="80">항목</th><th width="300">내용</th></tr>
		<form name="updateBbs" action="bbsServlet?action=execute&id=${bm.id}" method=post>
		<tr><td height="25">번호</td><td>${bm.id}</td></tr>
		<tr><td height="25">제목</td><td><input type="text" name="title" size="40" value="${bm.title}"></td></tr>
		<tr><td height="25">글쓴이</td><td>${bm.name}</td></tr>
		<tr><td height="25">수정일시</td><td>${bm.date}</td></tr>
		<tr><td height="100">내용</td><td><textarea name="content" rows="10" cols="42">${bm.content}</textarea></td></tr>
		<tr><td colspan="2" height="30">
				<input type="submit" value="수정하기" name="U1">&nbsp;&nbsp;
				<input type="reset" value="재작성" name="U2"></label>
		</td></tr>
		</form>
	</table>
	<a href="bbsServlet?action=list&page=${currentBbspage}">목록으로</a>
	</center>
</body>
</html>
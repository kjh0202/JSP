<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>게시판 글 쓰기</title>
	<style>
		label {
			display : block; /* 새 라인에서 시작 */
			padding : 5px;
		}
		label span {
			display : inline-block;
			width : 90px;
			text-align : right;
			/* vertical-align : middle; */
			padding : 10px;
		}
	</style>
</head>
<body>
	<h3>글 쓰기</h3>
	<hr>
	<form name="writeForm" action="bbsServlet?action=write" method=post>
		<label><span>제목:</span>
			<input type="text" name="title" size="40"></label>
		<label><span>내용:</span>
			<textarea name="content" rows="10" cols="42"></textarea></label>	

		<label><span></span>&nbsp;&nbsp;<input type="submit" value="글 쓰기" name="W1">&nbsp;&nbsp;
			<input type="reset" value="재작성" name="W2">&nbsp;&nbsp;
			<a href="bbsServlet?action=list&page=${currentBbsPage}">목록으로</a></label>
	</form>
</body>
</html>
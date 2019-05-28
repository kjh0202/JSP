<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ch14:error_ch14.jsp</title>
</head>
<jsp:useBean id="now" class="java.util.Date" />
<body>
	<div align=center>
	<h2>ch14:error_ch14.jsp</h2>
	<hr>
	
	<table>
		<tr width=100% bgcolor="pink"><td>
		관리자에게 문의해 주세요.<br>
		</td></tr>
		<tr><td>
		${now}<p>
		요청 실패 URI : ${pageContext.errorData.requestURI}<br>
		상태 코드: ${pageContext.errorData.statusCode}<br>
		예외 유형: ${pageContext.errorData.throwable}
		</td></tr>
	</table>
	</div>
</body>
</html>
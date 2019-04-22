<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@ page import="member.*" %>
<%
	MemberDAO mDao = new MemberDAO();
	List<MemberDTO> list = mDao.selectAll();
	mDao.close();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>메인 페이지</title>
	<style>
		td, th { text-align : center; }
	</style>
	<style>
		th { backgound : pink; }
	</style>
</head>
<body>
	<h3>회원 명단</h3>
	<%=(String)session.getAttribute("memberName")%> 회원님 반갑습니다.<br>
	<a href="/jspbook/member/memberProcServlet?action=logout">로그아웃</a>
	<hr>
	<table border="1" style="border-collapse:collapse;">
	<tr><th>아이디</th><th>이름</th><th>생일</th><th>주소</th><th>액션</th></tr>
	<%
	for (MemberDTO member : list) {
	%>
		<tr><td><%=member.getId()%></td>
		<td><%=member.getName()%></td>
		<td><%=member.getBirthday()%></td>
		<td><%=member.getAddress()%></td>
		<%
			String updateUri = "memberProcServlet?action=update&id=" + member.getId();  
			String deleteUri = "memberProcServlet?action=delete&id=" + member.getId();
		%>
		<td>&nbsp;<button onclick="location.href='<%=updateUri%>'">수정</button>&nbsp;
			&nbsp;<button onclick="location.href='<%=deleteUri%>'">삭제</button>&nbsp;</td></tr>
	<%
	}
	%>
	</table>
</body>
</html>
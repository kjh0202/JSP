<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<h2>구구단 테이블</h2>
		<hr>
		<br>
		<table border="1" style="border-collapse:collapse;">
			<tr><th>아이디</th><th>이름</th><th>생일</th><th>주소</th><th>액션</th></tr>
		<%-- <%
		for (MemberDTO member : list) {
		%>
			<tr><td><%=member.getId()%></td>
			<td><%=member.getName()%></td>
			<td><%=member.getBirthday()%></td>
			<td><%=member.getAddress()%></td>
			<%
				String uri = "MemberProcServlet?action=update&id=" + member.getId();  
			%>
			<td>&nbsp;<button onclick="location.href='<%=uri%>'">수정</button>&nbsp;</td></tr>
		<%
		}
		%> --%>
	</table>
		<table border="0" style="text-align:center">
			<tr><th>*</th><th>3 5 7 9</th></tr>
			<tr>
				<%
					for(int i=3; i<=9; i+=2) {
				%>
				<td><%= i+"<br>" %><% } %></td>
				
			
				<%-- <%
					for(int k=3; k<=9; k+=2) {
				%>
				<td><%= i*k %></td>
				<% } %> --%>
			</tr>
		</table>
	</center>
</body>
</html>
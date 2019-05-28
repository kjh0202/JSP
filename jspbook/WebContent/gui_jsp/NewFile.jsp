<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%!
// 디클레이션 :
// 변수 선언 : 전역변수
// 선언 : 함수
int global_cnt=0;
%>

<%
// 스크립트 릿 : java 코드 작성
// 변수 선언 : 지역변수
int local_cnt = 0;
out.print("<br> local_cnt : ");
out.print(++local_cnt);

out.print("<br> global_cnt : ");
out.print(++global_cnt);

%>
 <!--
 html 주석 
  -->
  
  <%--
  jsp 주석
   --%>
</body>
</html>
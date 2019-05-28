<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%!
	int sum = 0;
	int check[] = {0,0,0};
%>

<%

String favorite[] = request.getParameterValues("favorite");	//배열일때는 getParameterValues임
for(String season : favorite ) {
	int n = Integer.parseInt(season);
	switch(n) {
	case 1 : 
		out.print("모자<br>");
		sum+=1000;
		break;
	case 2 : 
		out.print("TV<br>");
		sum+=200000;
		break;
	case 3 : 
		out.print("신발<br>");
		sum+=30000;
		break;
	}
}

%>
당신이 선택한 물품의 합계는 <%= sum %>입니다.
</body>
</html>
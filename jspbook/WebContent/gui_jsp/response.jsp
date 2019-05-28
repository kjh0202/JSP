<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>설문 조사 결과</h2>
	<%
	request.setCharacterEncoding("UTF-8");
	String name = request.getParameter("name");
	out.print("이름 : " + name + "<br><br>");

	
	String gender = request.getParameter("gender");
	if(gender.equals("male")) {
		gender = "남자";
	} else {
		gender = "여자";
	}
	out.print("성별 : " + gender + "<br><br>");

	
	String seasonArr[] = request.getParameterValues("season");	//배열일때는 getParameterValues임
	out.print("좋아하는 계절 :  ");
	for(String season : seasonArr ) {
		int n = Integer.parseInt(season);
		switch(n) {
		case 1 : 
			out.print("봄");
			break;
		case 2 : 
			out.print("여름");
			break;
		case 3 : 
			out.print("가을");
			break;
		case 4 : 
			out.print("겨울");
			break;
		}
	}
	%>
</body>
</html>
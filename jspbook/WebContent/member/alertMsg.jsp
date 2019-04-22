<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Alert Message</title>
</head>
<body>
<%
	String message = (String)request.getAttribute("message");
	String url = (String)request.getAttribute("url");
%>
<script type="text/javascript">
	var message = '<%=message%>';
	var returnUrl = '<%=url%>';
	alert(message);
	document.location.href = returnUrl;	
</script>
</body>
</html>
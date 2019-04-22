    
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*"%>
<%@ page import="jspbook.ch06.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>add.jsp</title>
</head>
<body>
<%
	request.setCharacterEncoding("UTF-8");
	String productname = request.getParameter("product");
	int quantity = Integer.parseInt(request.getParameter("quantity"));
	ArrayList<CartDTO> list = (ArrayList<CartDTO>)session.getAttribute("productlist");
	if(list == null) {
		list = new ArrayList<CartDTO>();
		session.setAttribute("productlist", list);
	}
	CartDTO cart = new CartDTO();
	cart.setProductName(productname);
	cart.setQuantity(quantity);	
	list.add(cart);
%>
	<script>
		alert("<%=productname %>이(가) 추가되었습니다!!");
		history.go(-1);
	</script>
</body>
</html>
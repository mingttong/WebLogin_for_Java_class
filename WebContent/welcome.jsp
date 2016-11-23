<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>welcome</title>
</head>
<body>
<%
HttpSession se = request.getSession();
%>

<h1>Welcome!&nbsp;<%=se.getAttribute("logged")%></h1>

<a href="login.html"><button>返回登录页面</button></a>

</body>
</html>
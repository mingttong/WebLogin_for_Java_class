<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>reg</title>
</head>
<style>
.reg_note {
    color: red;
}
</style>
<body>

<%
HttpSession se = request.getSession();
Object regMsg = se.getAttribute("REG_RES");
%>

<h1>注册</h1>
<hr />

<div class="reg_note">
<%
if (regMsg != null) {
	out.print(regMsg);
}
%>
</div>
<form action="reg.do" method="post">
用户名：<input type="text" name="usr" />
<br />
密码：<input type="password" name="pwd" />
<br />
<input type="submit" value="注册" />
</form>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="cn.bjfu.im.QueryUserVO, java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%!List<QueryUserVO> arr; %>
<%!int curPgNum; %>
<%!int totalPgNum; %>
<%!String keyword; %>
<%
   arr = (List<QueryUserVO>)request.getAttribute("QUERY_RES");
   Integer o = (Integer)request.getAttribute("curPgNum");
   curPgNum = o.intValue();//当前的页码
   Integer o2 = (Integer)request.getAttribute("totalPgNum");
   totalPgNum = o2.intValue();//总页数
   
   keyword = (String)request.getAttribute("keyword");
%>

<table border="1">
<tr><th>ID</th><th>NAME</th></tr>
<%for(QueryUserVO vo:arr) {%>
	<tr>
	   <td><%=vo.getUsr()%></td>
	   <td><%=vo.getPwd()%></td>
	</tr>
<%} %>
</table>

当前第<%=curPgNum%>页，共<%= totalPgNum%>页
<%if(curPgNum != 1) {%>
	<a href="queryUser.do?keyword=<%=keyword %>&page=1">首页</a>
	<a href="queryUser.do?keyword=<%=keyword %>&page=<%=(curPgNum-1)%>">上一页</a>
<%} %>
<%if(curPgNum != totalPgNum) {%>
   <a href="queryUser.do?keyword=<%=keyword %>&page=<%=(curPgNum+1)%>">下一页</a>
   <a href="queryUser.do?keyword=<%=keyword %>&page=<%=totalPgNum%>">末页</a>
<%} %>


</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>stronger</title>
</head>
<body>

<%-- <form action="${pageContext.request.contextPath}/login.htm"> --%>
<form action="${pageContext.request.contextPath}/auth/adminLogin.htm" method="POST">
<%-- <form action="${pageContext.request.contextPath}/auth/login.htm"> --%>
	<table>
		<tr>
			<th>用户名</th>
			<td><input type="text" name="userName" value="admin"/></td>
		</tr>
		<tr>
			<th>密码</th>
			<td><input type="password" name="pwd" value="sirding"/></td>
		</tr>
	</table>
	<input type="submit" value="ok" />
</form>
<br/>
<a href="http://localhost:8080/oauth/token?grant_type=password&username=mobile&password=sirding">GET TOKEN</a>
</body>
</html>
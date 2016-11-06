<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Index</title>
</head>
<body>
	
	<form action="${pageContext.request.contextPath}/oauth/token" id="authForm" method="post">
		<input type="hidden" name="client_id" value="mobile"/>
		<input type="hidden" name="client_secret" value="mobile"/>
		<input type="hidden" name="grant_type" value="password"/>
		<input type="hidden" name="scope" value="read write"/>
		<input type="hidden" name="username" value="mobile"/>
		<input type="hidden" name="password" value="sirding"/>
		<input type="submit" value="OK"/>
		
	</form>
</body>
</html>
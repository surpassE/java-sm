<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<jsp:include page="/static/layout/inc.jsp"></jsp:include>
</head>
<body>
<div class="container w-xxl w-auto-xs">
  <a class="navbar-brand block m-t">One Piece</a>
  <div class="m-b-lg">
    <div class="wrapper text-center">
      <strong>冒险，从这里开始......</strong>
    </div>
    <form name="form" class="form-validation" action="adminLogin" method="post">
      <div class="text-danger wrapper text-center">
          ${requestScope.errmsg }
      </div>
      <div class="list-group list-group-sm">
        <div class="list-group-item">
          <input type="text" placeholder="Username" name="userName" class="form-control no-border" required>
        </div>
        <div class="list-group-item">
           <input type="password" placeholder="Password" name="pwd" class="form-control no-border" required>
        </div>
      </div>
      <button type="submit" class="btn btn-lg btn-primary btn-block">登录</button>
      <div class="text-center m-t m-b"><a href="/forgotpwd.jsp">找回密码?</a></div>
      <div class="line line-dashed"></div>
      <p class="text-center"><small>还没有账户?</small></p>
      <a href="/regist.jsp" class="btn btn-lg btn-default btn-block">去注册</a>
    </form>
  </div>
</div>	
</body>
</html>
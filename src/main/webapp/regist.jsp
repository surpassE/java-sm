<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<jsp:include page="/static/layout/inc.jsp"></jsp:include>

<title>注册</title>
</head>
<body>
    <div class="container w-xxl w-auto-xs">
      <a class="navbar-brand block m-t">SIRDING</a>
      <div class="m-b-lg">
        <div class="wrapper text-center">
          <strong>第一步总是需要一些勇气......</strong>
        </div>
        <form name="form" class="form-validation">
          <div class="text-danger wrapper text-center">
              {{authError}}
          </div>
          <div class="list-group list-group-sm">
            <div class="list-group-item">
              <input placeholder="Username" name="userName" class="form-control no-border" required>
            </div>
            <div class="list-group-item">
              <input type="email" placeholder="Email" name="email" class="form-control no-border" required>
            </div>
            <div class="list-group-item">
               <input type="password" placeholder="Password" name="pwd" class="form-control no-border" required>
            </div>
          </div>
          <div class="checkbox m-b-md m-t-none">
            <label class="i-checks">
              <input type="checkbox" name="agree" required><i></i> 同意 <a href="#">协议条款</a>
            </label>
          </div>
          <button type="submit" class="btn btn-lg btn-primary btn-block">Sign up</button>
          <div class="line line-dashed"></div>
          <p class="text-center"><small>已经有个一个账号?</small></p>
          <a href="login.jsp" class="btn btn-lg btn-default btn-block">去登陆</a>
        </form>
      </div>
    </div>
</body>

</html>
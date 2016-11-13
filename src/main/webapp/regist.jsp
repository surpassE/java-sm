<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html data-ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<jsp:include page="layout/inc.jsp"></jsp:include>

<title>注册</title>
</head>
<body ng-controller="AppCtrl">
    <div class="container w-xxl w-auto-xs" ng-controller="SignupFormController" ng-init="app.settings.container = false;">
      <a href class="navbar-brand block m-t">{{app.name}}</a>
      <div class="m-b-lg">
        <div class="wrapper text-center">
          <strong>第一步总是需要一些勇气......</strong>
        </div>
        <form name="form" class="form-validation">
          <div class="text-danger wrapper text-center" ng-show="authError">
              {{authError}}
          </div>
          <div class="list-group list-group-sm">
            <div class="list-group-item">
              <input placeholder="Name" class="form-control no-border" ng-model="user.name" required>
            </div>
            <div class="list-group-item">
              <input type="email" placeholder="Email" class="form-control no-border" ng-model="user.email" required>
            </div>
            <div class="list-group-item">
               <input type="password" placeholder="Password" class="form-control no-border" ng-model="user.password" required>
            </div>
          </div>
          <div class="checkbox m-b-md m-t-none">
            <label class="i-checks">
              <input type="checkbox" ng-model="agree" required><i></i> 同意 <a href>协议条款</a>
            </label>
          </div>
          <button type="submit" class="btn btn-lg btn-primary btn-block" ng-click="signup()" ng-disabled='form.$invalid'>Sign up</button>
          <div class="line line-dashed"></div>
          <p class="text-center"><small>已经有个一个账号?</small></p>
          <a href="login.jsp" class="btn btn-lg btn-default btn-block">去登陆</a>
        </form>
      </div>
      <div class="text-center" ng-include="'tpl/blocks/page_footer.html'"></div>
    </div>
</body>

</html>
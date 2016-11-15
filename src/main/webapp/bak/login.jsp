<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html ng-app="app">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%-- <jsp:include page="layout/inc.jsp"></jsp:include> --%>

<title>登录</title>
</head>
<body ng-controller="AppCtrl">
<div class="container w-xxl w-auto-xs" ng-controller="SigninFormController" ng-init="app.settings.container = false;">
  <a href class="navbar-brand block m-t">{{app.name}}</a>
  <div class="m-b-lg">
    <div class="wrapper text-center">
      <strong>探索，从这里开始......</strong>
    </div>
    <form name="form" class="form-validation">
      <div class="text-danger wrapper text-center" ng-show="authError">
          {{authError}}
          
      </div>
      <div class="list-group list-group-sm">
        <div class="list-group-item">
          <input type="email" placeholder="Email" class="form-control no-border" ng-model="user.email" required>
        </div>
        <div class="list-group-item">
           <input type="password" placeholder="Password" class="form-control no-border" ng-model="user.password" required>
        </div>
      </div>
      <button type="submit" class="btn btn-lg btn-primary btn-block" ng-click="login()" ng-disabled='form.$invalid'>登录</button>
      <div class="text-center m-t m-b"><a href="/forgotpwd.jsp">找回密码?</a></div>
      <div class="line line-dashed"></div>
      <p class="text-center"><small>还没有账户?</small></p>
      <a href="/regist.jsp" class="btn btn-lg btn-default btn-block">去注册</a>
    </form>
  </div>
</div>	
</body>
<script type="text/javascript">

angular.module("app").controller("SigninFormController", function($scope, $http){
	$scope.user = {};
    $scope.authError = null;
    $scope.login = function() {
      $scope.authError = null;
      // Try to login
      $http.post('adminLogin.htm', {email: $scope.user.email, password: $scope.user.password})
      .then(function(response) {
        if ( !response.data.user ) {
          $scope.authError = 'Email or Password not right';
        }else{
          $state.go('app.dashboard-v1');
        }
      }, function(x) {
    	  
    	  $state.go('toHome');
        $scope.authError = 'Server Error';
      });
    };
	
	$http.post('/user/sysUserList', {}, {}).then(function(response){
		$scope.sysUsers = response.data;
	}, function(response){
		
	});
});




</script>
</html>
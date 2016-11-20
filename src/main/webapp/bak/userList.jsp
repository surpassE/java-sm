<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="sysUserNg" ng-controller="sysUserController">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统用户</title>
<jsp:include page="/layout/inc.jsp"></jsp:include>
</head>
<body>

<div ng-repeat="sysUser in sysUsers">
	{{sysUser.id + "-" + sysUser.loginName + "-" + sysUser.status + "-" + sysUser.note}}
</div>

<hr>
<div>
	<form id="_sysUserForm" action="${pageContext.request.contextPath}/user/addSysUser.htm" method="post">
		姓名：<input type="text" name="loginName"><br/>
		密码：<input type="password" name="loginPwd"><br/>
		状态：<input type="text" name="status"><br/>
		备注：<input type="text" name="note"><br/>
		<!-- <a href="javascript:void(0)" onclick="submit()">添加一个呗</a> -->
		<input type="submit" value="添加一个呗">
	</form>
</div>
<a href="/toHome">Go Home...</a><br/>
<a href="/logout.htm">Logout...</a><br/>

</body>
<script type="text/javascript">
var sysUserNg = angular.module("sysUserNg", []).controller("sysUserController", function($scope, $http){
	$http.post('/user/userList', {}, {}).then(function(response){
		$scope.sysUsers = response.data;
	}, function(response){
		
	});
	
	
});

function addSysUser(){
	
}
</script>
</html>
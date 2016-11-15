<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="roleNg" ng-controller="roleController">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/layout/inc.jsp"></jsp:include>
<title>角色管理</title>
</head>
<body>
角色管理
<div ng-repeat="role in roles">
	{{role.name + ":" + role.note}}
</div>
</body>

<script type="text/javascript">
var roleNg = angular.module("roleNg", []).controller("roleController", function($scope, $http){
	$http.post('/sec/roleList.htm', {}, {}).then(function(response){
		console.info(response);
		$scope.roles = response.data;
	}, function(response){
		
	});
});
</script>
</html>
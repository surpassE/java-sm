<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>获得access_token</title>
    <jsp:include page="/static/layout/inc.jsp"></jsp:include>
</head>
<body>
<div ng-app="getAccessToken">
<div style="width: 76%;margin-left: 12%">
    <a href="${pageContext.request.contextPath}/toHome">Home</a> | <a href="${pageContext.request.contextPath}/oauthClient/toClientDetail">Back</a>
    
    <h2>[${clientDetail.clientId}]</h2>
    
    <div ng-controller="getAccessTokenCtrl">
        <c:if test="${clientDetail.containsAuthorizationCode}">
            <div class="panel panel-default">
                <div class="panel-heading">类型：[authorization_code]</div>
                <div class="panel-body">
                    <p class="text-muted">输入每一步必要的信息后点击其下面的链接地址.</p>
                    <ol>
                        <li>
                            <p>
                                <code>
                                	第一步：客户端将用户请求重定向到认证服务上，用户授权并获得code后重定向到客户端的地址
                                </code>
                                <br/>
                                redirect_uri: <input type="text" value="" ng-model="redirectUri" size="70"/>
                                <br/>
                                <a href="${contextPath}/oauth/authorize?client_id={{clientId}}&redirect_uri={{redirectUri}}&response_type=code&scope={{scope}}&state=your_state" target="_blank">
                                /oauth/authorize?client_id={{clientId}}&redirect_uri={{redirectUri}}&response_type=code&scope={{scope}}&state=cust_state</a>
                                <span class="label label-info">GET</span>
                            </p>
                        </li>
                        <li>
                            <code>
                            	第二步：客户端用 第一步获得的[code]换取[access_token]
                            </code>
                            <br/>
                            authorize_code: <input type="text" name="code" value="" ng-model="code" size="70"/>
                            <br/>
                            <form id="passwordForm" action="{{'/oauth/token?client_id=' + clientId +'&client_secret=' + clientSecret + '&grant_type=authorization_code&code=' + code +'&redirect_uri=' + redirectUri}}" method="post" target="_blank">
                                <a href="javascript:$('#passwordForm').submit()">
                                	/oauth/token?client_id={{clientId}}&client_secret={{clientSecret}}&grant_type=authorization_code&code={{code}}&redirect_uri={{redirectUri}}
                                </a>
                                <span class="label label-warning">POST</span>
                            </form>
                        </li>
                    </ol>
                </div>
            </div>
        </c:if>

        <c:if test="${clientDetail.containsPassword}">
            <div class="panel panel-default">
                <div class="panel-heading">类型：[password]</div>
                <div class="panel-body">
                    <p class="text-muted">
                    	用户输入username, password.
                    </p>
                    username: <input type="text" required="required" ng-model="username"/>
                    <br/>
                    password: <input type="text" required="required" ng-model="password"/>
                    <br/>
                    <form id="passwordForm" action="{{'${contextPath}/oauth/token?client_id=' + clientId + '&client_secret=' + clientSecret +'&grant_type=password&scope=' + scope + '&username=' + username + '&password=' + password}}"
                          method="post" target="_blank">
                        <a href="javascript:$('#passwordForm').submit()">
                            /oauth/token?client_id={{clientId}}&client_secret={{clientSecret}}&grant_type=password&scope={{scope}}&username={{username}}&password={{password}}
                        </a>
                        <span class="label label-warning">POST</span>
                    </form>
                </div>
            </div>
        </c:if>

        <c:if test="${clientDetail.containsImplicit}">
            <div class="panel panel-default">
                <div class="panel-heading">类型：[implicit]</div>
                <div class="panel-body">
                    <code>
                    	输入redirect_uri获取access_token后注意查看redirect_uri的hash部分(#号后边部分)
                    </code>
                    <br/>
                    redirect_uri: <input type="text" value="" ng-model="implicitRedirectUri" size="70"/>
                    <p>
                        <a href="${contextPath}/oauth/authorize?client_id={{clientId}}&client_secret={{clientSecret}}&response_type=token&scope={{scope}}&redirect_uri={{implicitRedirectUri}}" method="post" target="_blank">
                        	/oauth/authorize?client_id={{clientId}}&client_secret={{clientSecret}}&response_type=token&scope={{scope}}&redirect_uri={{implicitRedirectUri}}
                        </a>
                        <span class="label label-info">GET</span>
                    </p>
                </div>
            </div>
        </c:if>

        <c:if test="${clientDetail.containsClientCredentials}">
            <div class="panel panel-default">
                <div class="panel-heading">类型：[client_credentials]</div>
                <div class="panel-body" style="background:red">
                    <p class="text-muted">点击链接地址即可测试</p>
                    <form id="clientCredentialsForm" action="${contextPath}/oauth/token" method="post" target="_blank">
                        <input type="hidden" name="grant_type" value="client_credentials">
                        <input type="text" name="client_id" value="{{clientId}}">
                        <input type="text" name="client_secret" value="{{clientSecret}}">
                        <input type="hidden" name="scope" value="{{scope}}">
                        <a href="javascript:$('#clientCredentialsForm').submit()" method="post" target="_blank">
                            /oauth/token?client_id={{clientId}}&client_secret={{clientSecret}}&grant_type=client_credentials&scope={{scope}}
                        </a>
                        <span class="label label-warning">POST</span>
                    </form>
                </div>
            </div>
        </c:if>

        <c:if test="${clientDetail.containsRefreshToken}">
            <div class="panel panel-default">
                <div class="panel-heading">类型：[refresh_token]</div>
                <div class="panel-body">
                    <code>
						通过refresh_token重新获得access_token
					</code>
					<br/>
                    refresh_token: <input type="text" ng-model="refreshToken" placeholder="refresh_token" required="required" size="70"/>
                    <br/>
                    <form id="refreshTokenForm" action="${contextPath}/oauth/token" method="post" target="_blank">
                        <input type="hidden" name="grant_type" value="refresh_token">
                        <input type="hidden" name="client_id" value="{{clientId}}">
                        <input type="hidden" name="client_secret" value="{{clientSecret}}">
                        <input type="hidden" name="refresh_token" value="{{refreshToken}}">
                        <a href="javascript:$('#refreshTokenForm').submit()" method="post" target="_blank">
                        	/oauth/token?client_id={{clientId}}&client_secret={{clientSecret}}&grant_type=refresh_token&refresh_token={{refreshToken}}
                        </a>
                        <span class="label label-warning">POST</span>
                    </form>
                </div>
            </div>
        </c:if>
    </div>
</div>
</div>

<script>
angular.module("getAccessToken", []).controller("getAccessTokenCtrl", function($scope){
	$scope.clientId = "${clientDetail.clientId}";
    $scope.clientSecret = "${clientDetail.clientSecret}";
    $scope.scope = "${clientDetail.scopeWithBlank}";

    <c:if test="${empty clientDetail.webServerRedirectUri}" var="eptRedUri">
	    $scope.implicitRedirectUri = location.href;
	    $scope.redirectUri = "http://localhost:8080/unity/dashboard";
    </c:if>
    <c:if test="${not eptRedUri}">
	    $scope.implicitRedirectUri = "${clientDetailsDto.webServerRedirectUri}";
	    $scope.redirectUri = "${clientDetailsDto.webServerRedirectUri}";
    </c:if>
    $scope.username = "mobile";
    $scope.password = "mobile";
    //a temp value
    $scope.refreshToken = "";
});
</script>
</body>
</html>
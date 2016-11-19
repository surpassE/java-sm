<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fun" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>client_details</title>
    <style>
        .list-group li:hover {
            background-color: #f9f9f9;
        }
    </style>
    
    <jsp:include page="/layout/inc.jsp" />
</head>
<body>
<div style="width: 76%;margin-left: 12%">
	<!-- 头部信息 -->
	<a href="/toHome">Home</a>
	<div class="row">
	    <div class="col-md-10">
	        <h3>client_details</h3>
	    </div>
	    <div class="col-md-2">
	        <div class="pull-right">
	            <a href="toRegistClient" class="btn btn-success btn-sm">注册client</a>
	        </div>
	    </div>
	</div>
	<hr/>
	<div>
	    <ul class="list-group">
	        <c:forEach items="${list}" var="cli">
	            <li class="list-group-item">
	                <div class="pull-right">
	                    <c:if test="${not cli.archived}">
	                        <a href="/oauthClient/toGetAccessToken?clientId=${cli.clientId}">test</a>
	                        <a href="archive_client/${cli.clientId}" class="text-danger"
	                           onclick="return confirm('Are you sure archive \'${cli.clientId}\'?')">archive</a>
	                    </c:if>
	                    <c:if test="${cli.archived}"><strong class="text-muted">Archived</strong></c:if>
	                    &nbsp;<strong class="text-muted"><a href="/oauthClient/delClient/${cli.clientId}">Delete</a></strong>
	                </div>
	                <h3 class="list-group-item-heading">
	                        ${cli.clientId}
	                    <small>${cli.authorizedGrantTypes}</small>
	                </h3>
	
	                <div class="list-group-item-text text-muted">
	                    client_id: <span class="text-danger">${cli.clientId}</span>&nbsp;<br/>
	                    client_secret: <span class="text-primary">${cli.clientSecret}</span>&nbsp;
	                    <br/>
	                    authorized_grant_types: <span class="text-primary">${cli.authorizedGrantTypes}</span>&nbsp;<br/>
	                    resource_ids: <span class="text-primary">${cli.resourceIds}</span>&nbsp;
	                    <br/>
	                    scope: <span class="text-primary">${cli.scope}</span>&nbsp;<br/>
	                    web_server_redirect_uri: <span class="text-primary">${cli.webServerRedirectUri}</span>&nbsp;
	                    <br/>
	                    authorities: <span class="text-primary">${cli.authorities}</span>&nbsp;<br/>
	                    access_token_validity: <span class="text-primary">${cli.accessTokenValidity}</span>&nbsp;<br/>
	                    refresh_token_validity: <span class="text-primary">${cli.refreshTokenValidity}</span>&nbsp;
	                    <br/>
	                    create_time: <span class="text-primary">${cli.createTime}</span>&nbsp;<br/>
	                    archived: <strong class="${cli.archived?'text-warning':'text-primary'}">${cli.archived}</strong>&nbsp;<br/>
	                    trusted: <span class="text-primary">${cli.trusted}</span>&nbsp;<br/>
	                    additional_information: <span class="text-primary">${cli.additionalInformation}</span>&nbsp;
	                </div>
	            </li>
	        </c:forEach>
	
	    </ul>
	    <p class="help-block">
	        每一个item对应<code>oauth_client_details</code>表中的一条数据; 共<code><strong>${fun:length(list)}</strong></code>条数据.
	        <br/>
	        <!-- 
	        对spring-oauth-server数据库表的详细说明请访问
	        <a href="http://andaily.com/spring-oauth-server/db_table_description.html" target="_blank">http://andaily.com/spring-oauth-server/db_table_description.html</a>
	        (或访问项目others目录的db_table_description.html文件)
	         -->
	    </p>
	</div>
</div>
</body>
</html>
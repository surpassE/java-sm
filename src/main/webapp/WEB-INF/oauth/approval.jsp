<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>

<html>
<head>
    <title>认证授权</title>
    <jsp:include page="/layout/inc.jsp"></jsp:include>
</head>
<body>
<div style="width: 76%;margin-left: 12%">
<h1>认证授权</h1>
<hr>
<p>是否允许<strong>[${authorizationRequest.clientId}]</strong>访问你保护的资源?</p>

<form id='approvalForm' name='approvalForm' action='${pageContext.request.contextPath}/oauth/authorize' method='post'>
    <input name='user_oauth_approval' id='user_oauth_approval' value='true' type='hidden'/>
    <label><a onclick="submitApproval(1)"  class="btn btn-success">同意</a></label>
    <label><a onclick="submitApproval(0)"  class="btn btn-warning">拒绝</a></label>
</form>
</div>
</body>
<script>
function submitApproval(flag){
	if(flag == 0)
		$("#user_oauth_approval").val(false);
	$("#approvalForm").submit();
}
</script>
</html>
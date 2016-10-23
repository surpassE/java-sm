<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myApp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="../comjsp/public.jsp"></jsp:include>
<%-- <script type="text/javascript" src="<%=request.getContextPath()%>/comjs/angular-1.5.8/angular.min.js"></script> --%>
<script type="text/javascript" src="<%=request.getContextPath()%>/comjs/ngtest.js"></script>
</head>
<body ng-controller="PhoneListCtrl">
I'm Home page!<br/>
<a href="${pageContext.request.contextPath}/user/findUser.htm">Go to User...</a><br/>
<a href="${pageContext.request.contextPath}/sec/toPerm.htm">Go to Perm...</a><br/>
<a href="${pageContext.request.contextPath}/sec/toRole.htm">Go to Role...</a><br/>

<p>============华丽的分割线(angularJS)============================</p>

<p>我的第一个表达式: {{ 5 + 5 + "}"}}</p>
<br>

Your name: <input type="text" ng-model="yourname" placeholder="World">
<hr>
Hello {{yourname || 'World'}}!<br/>
<hr>
Search: <input ng-model="query">
Sort by:
<select ng-model="orderProp">
  <option value="name">Alphabetical</option>
  <option value="age">Newest</option>
</select>


<ul class="phones">
  <li ng-repeat="phone in phones | filter:query | orderBy:orderProp">
    {{phone.name}}
    <p>{{phone.snippet}}</p>
  </li>
</ul>

</body>
</html>
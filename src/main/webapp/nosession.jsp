<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>连接超时</title>
<jsp:include page="/static/layout/inc.jsp"></jsp:include>
<script type="text/javascript">
	$(function(){
		var parent = getParentObj(window);
		parent.location = "login.jsp";
	});
	
	/* 
		递归获得父对象
	*/
	function getParentObj(win){
		if(win != null && win != undefined){
			var parent = win.parent;
			if(parent != null){
				return parent;
			}
			return getParent(parent);
		}
	}
</script>
</head>
<body>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html">

<link rel="shortcut icon" href="<%=request.getContextPath() %>/static/img/favicon.ico" type="image/x-icon"/>

<!-- Bootstrap Core CSS -->
<link href="<%=request.getContextPath() %>/static/template-bs/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<!-- MetisMenu CSS -->
<link href="<%=request.getContextPath() %>/static/template-bs/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">
<!-- DataTables CSS -->
<link href="<%=request.getContextPath() %>/static/template-bs/vendor/datatables-plugins/dataTables.bootstrap.css" rel="stylesheet">
<!-- DataTables Responsive CSS -->
<link href="<%=request.getContextPath() %>/static/template-bs/vendor/datatables-responsive/dataTables.responsive.css" rel="stylesheet">
<!-- Custom CSS -->
<link href="<%=request.getContextPath() %>/static/template-bs/dist/css/sb-admin-2.css" rel="stylesheet">
<!-- Morris Charts CSS -->
<link href="<%=request.getContextPath() %>/template-bs/vendor/morrisjs/morris.css" rel="stylesheet">
<!-- Custom Fonts -->
<link href="<%=request.getContextPath() %>/static/template-bs/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

 <!-- jQuery -->
<script src="<%=request.getContextPath() %>/static/template-bs/vendor/jquery/jquery.min.js"></script>

<!-- <script type="text/javascript"  src="/template-bs/vendor/datatables/js/jquery.js"></script> 
<script type="text/javascript">
     var $14 = $.noConflict(true);
</script> -->


<!-- Bootstrap Core JavaScript -->
<script src="<%=request.getContextPath() %>/static/template-bs/vendor/bootstrap/js/bootstrap.min.js"></script>
<!-- Metis Menu Plugin JavaScript -->
<script src="<%=request.getContextPath() %>/static/template-bs/vendor/metisMenu/metisMenu.min.js"></script>
<!-- Morris Charts JavaScript -->
<script src="<%=request.getContextPath() %>/static/template-bs/vendor/raphael/raphael.min.js"></script>
<script src="<%=request.getContextPath() %>/static/template-bs/vendor/morrisjs/morris.min.js"></script>
<script src="<%=request.getContextPath() %>/static/template-bs/data/morris-data.js"></script>

<!-- DataTables JavaScript -->
<script src="<%=request.getContextPath() %>/static/template-bs/vendor/datatables/js/jquery.dataTables.min.js"></script>
<script src="<%=request.getContextPath() %>/static/template-bs/vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
<script src="<%=request.getContextPath() %>/static/template-bs/vendor/datatables-responsive/dataTables.responsive.js"></script>


<!-- Custom Theme JavaScript -->
<script src="<%=request.getContextPath() %>/static/template-bs/dist/js/sb-admin-2.js"></script>

<script src="<%=request.getContextPath() %>/static/js/custUtil.js"></script>


<script>
function showDefaultTable(id){
	$('#' + id).DataTable({
	    responsive: true,
	    "sPaginationType" : "full_numbers",
	    "oLanguage" : {
	        "sLengthMenu": "每页显示 _MENU_ 条记录",
	        "sZeroRecords": "抱歉， 没有找到",
	        "sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
	        "sInfoEmpty": "没有数据",
	        "sInfoFiltered": "(从 _MAX_ 条数据中检索)",
	        "sZeroRecords": "没有检索到数据",
	         "sSearch": "搜索:",
	        "oPaginate": {
	        "sFirst": "首页",
	        "sPrevious": "前一页",
	        "sNext": "后一页",
	        "sLast": "尾页"
	        }
	    }
	});
}

/**
 * 将datatables分页查询信息转为字符串进行传递操作
 * 3个参数的名字可以随便命名,但必须是3个参数,少一个都不行
 */
function retrieveData( custSSource, custAoDataBack, custFnCallback) {
    $.ajax({
        url : custSSource,
        data : {"data":JSON.stringify(custAoDataBack)},//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
        type : 'post',
        dataType : 'json',
        async : false,
        success : function(result) {
       	//把返回的数据传给这个方法就可以了,datatable会自动绑定数据的
            custFnCallback(result);
        },
        error : function(msg) {
        }
    });
}
</script>
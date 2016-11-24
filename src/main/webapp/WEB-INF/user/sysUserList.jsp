<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统用户管理</title>
<jsp:include page="/layout/incTemplate.jsp"></jsp:include>

<script>
$(document).ready(function() {
    $('#example').DataTable( {
    	"responsive": true,
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
	    },
	    "aoColumnDefs": [
               	{ 
               		"orderable": false, 
               		"targets": [2, 3, 5] 
               	},
               	{
                    targets: [ 3 ],
                    orderData: [ 3, 1 ]  //如果第一列进行排序，有相同数据则按照第二列顺序排列
               	}
        ],
	    "bProcessing": false,
        "bServerSide": true,
        "sAjaxSource": "/sysUser/find",//这个是请求的地址
        "fnServerData": retrieveData, // 获取数据的处理函数
        "columns": [
	        { "data": "id" },
	        { "data": "loginName" },
	        { "data": function(){return "******";} },
	        { "data": function(obj){
	        		if(obj.status == 1)
	        			return "是";
	        		return "否"
	        	} 
	        },
	        { "data": "note" },
	        { "data": function(obj){
	         		return "<a href='#' id=" + obj.id + ">删除</a>";
	         	} 
	        }
      	],
      	"createdRow": function ( row, data, index ) {
	          if ( data['id'] > 1 ) {//操作次数大于1000的变红显示
	              $('td', row).eq(1).css('font-weight',"bold").css("color","red");
	          }
      	}  
    });
} );
	
</script>




</head>
<body>
<div id="wrapper">
	<!-- 引入菜单及头部信息 -->
    <jsp:include page="/layout/incAdminMenu.jsp"></jsp:include>

	<!-- 主面板 start -->
    <div id="page-wrapper">
		<div class="row">
           <div class="col-lg-12">
               <div class="panel panel-default">
                   <div class="panel-heading">
                       	OnePiece&nbsp;&#62;&#62;&nbsp;用户管理&nbsp;&#62;&#62;&nbsp;系统用户
                   </div>
                   <!-- /.panel-heading -->
                   <div class="panel-body">
                   		<table id="example" class="table table-striped table-bordered table-hover" style="cellspacing:0;width:100%">
					        <thead>
					            <tr>
					                <th>id</th>
					                <th>用户名</th>
					                <th>密码</th>
					                <th>状态</th>
					                <th>备注</th>
					                <th>操作</th>
					            </tr>
					        </thead>
					    </table>
                   </div>
                   <!-- /.panel-body -->
               </div>
               <!-- /.panel -->
           </div>
           <!-- /.col-lg-12 -->
       </div>
	</div>
</div>


 
 
</body>
</html>
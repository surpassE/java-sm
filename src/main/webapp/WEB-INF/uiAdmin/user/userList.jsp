<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统用户</title>
<jsp:include page="/static/layout/incTemplate.jsp"></jsp:include>

<script>
$(document).ready(function() {
    $('#example').DataTable( {
    	"responsive": true,
	    "sPaginationType" : "full_numbers",
	    "oLanguage" : _custUtil.dtl(),
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
        "sAjaxSource": "${pageContext.request.contextPath}/user/findUser",//这个是请求的地址
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
	         		return "<a href='/user/delUser?id=" + obj.id + "'>删除</a>";
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
	<!-- 描述信息 start -->
   	<div class="row">
       <div class="col-lg-12">
           <h4 class="page-header">One Piece&nbsp;&#62;&#62;&nbsp;用户管理&nbsp;&#62;&#62;&nbsp;应用用户</h4>
       </div>
   	</div>
   	<!-- 描述信息 end -->
	<div class="row">
         <div class="col-lg-12">
             <div class="panel panel-default">
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
</body>
</html>
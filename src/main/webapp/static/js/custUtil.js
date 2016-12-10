/**
 * 
 * 此文件中用于扩展常用的js工具函数
 * 
 */
var _custUtil = $.extend({}, _custUtil);/* 全局对象 */

/**
 * 获得请求的URl地址
 * @params type 地址中是否包含项目名称
 * 			无：http(https)://ip:port/
 * 			1 : http()://ip:port/xxx/		
 * @author zc.ding
 * @date 2016年11月12日
 */
_custUtil.url = function(type) {
	var curWwwPath = window.document.location.href;
	var pathName = window.document.location.pathname;
	var pos = curWwwPath.indexOf(pathName);
	var localhostPath = curWwwPath.substring(0, pos);
	if(type == undefined || type == "undefined")
		return localhostPath + "/";
	var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
	return (localhostPath + projectName+"/");
};

/**
 * 获得请求的源地址
 * @param type
 * 			无：ip
 * 			1 : port
 * @author zc.ding
 * @date 2016年11月12日
 */
_custUtil.getIpPort = function(type){
	var curWwwPath = window.document.location.href;
	var arr = curWwwPath.split("/");
	var ip = arr[2];
	if(type == undefined || type == "undefined")
		return ip.split(":")[0];
	return ip.split(":")[1];
};




/************************************************************************************************
/***************************** datatables tool **************************************************
/************************************************************************************************
/**
 * dataTable Language
 */
_custUtil.dtl = function(){
	return {
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
    };
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

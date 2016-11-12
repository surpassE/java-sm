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

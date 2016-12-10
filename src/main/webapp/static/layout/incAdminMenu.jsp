<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Navigation 导航 -->
 <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
     <div class="navbar-header">
         <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
             <span class="sr-only">Toggle navigation</span>
             <span class="icon-bar"></span>
             <span class="icon-bar"></span>
             <span class="icon-bar"></span>
         </button>
         <a class="navbar-brand" href="${pageContext.request.contextPath }/toAdminIndex">One Piece</a>
     </div>

	 <!-- 头部信息开始 -->
     <ul class="nav navbar-top-links navbar-right">
         <li class="dropdown">
             <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                 <i class="fa fa-bell fa-fw"></i> <i class="fa fa-caret-down"></i>
             </a>
             <ul class="dropdown-menu dropdown-alerts">
                 <li class="divider"></li>
                 <li>
                     <a href="#">
                         <span>
                             <i class="fa fa-envelope fa-fw"></i> 新消息
                             <span class="pull-right text-muted small">4 minutes ago</span>
                         </span>
                     </a>
                 </li>
                 <li class="divider"></li>
                 <li>
                     <a href="#">
                         <span>
                             <i class="fa fa-tasks fa-fw"></i> 新任务
                             <span class="pull-right text-muted small">4 minutes ago</span>
                         </span>
                     </a>
                 </li>
                 <li class="divider"></li>
                 <li>
                     <a class="text-center" href="#">
                         <strong>See all Alerts</strong>
                         <i class="fa fa-angle-right"></i>
                     </a>
                 </li>
             </ul>
             <!-- /.dropdown-alerts -->
         </li>
         <!-- /.dropdown -->
         <li class="dropdown">
             <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                 <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
             </a>
             <ul class="dropdown-menu dropdown-user">
                 <li><a href="#"><i class="fa fa-user fa-fw"></i> 个人信息</a>
                 </li>
                 <li><a href="#"><i class="fa fa-gear fa-fw"></i> 系统设置</a>
                 </li>
                 <li class="divider"></li>
                 <li><a href="login.html"><i class="fa fa-sign-out fa-fw"></i> 安全退出</a>
                 </li>
             </ul>
             <!-- /.dropdown-user -->
         </li>
         <!-- /.dropdown -->
     </ul>
     <!-- 头部结束 -->

	 <!-- 菜单开始 -->
     <div class="navbar-default sidebar" role="navigation">
         <div class="sidebar-nav navbar-collapse">
             <ul class="nav" id="side-menu">
                 <li class="sidebar-search">
                     <div class="input-group custom-search-form">
                         <input type="text" class="form-control" placeholder="Search...">
                         <span class="input-group-btn">
                         <button class="btn btn-default" type="button">
                             <i class="fa fa-search"></i>
                         </button>
                     </span>
                     </div>
                     <!-- /input-group -->
                 </li>
                 <li>
                     <a href="#"><i class="fa fa-user fa-fw"></i> 用户管理</a>
                     <ul class="nav nav-second-level">
                         <li>
                             <a href="${pageContext.request.contextPath}/sysUser/toSysUser">系统用户</a>
                         </li>
                         <li>
                             <a href="${pageContext.request.contextPath}/user/toUser">应用用户</a>
                         </li>
                     </ul>
                 </li>
                 <li>
                     <a href="#"><i class="fa fa-sitemap fa-fw"></i> Multi-Level Dropdown<span class="fa arrow"></span></a>
                     <ul class="nav nav-second-level">
                         <li>
                             <a href="#">一级菜单</a>
                         </li>
                         <li>
                             <a href="#">二级菜单 <span class="fa arrow"></span></a>
                             <ul class="nav nav-third-level">
                                 <li>
                                     <a href="/toAdminIndex">二级菜单1</a>
                                 </li>
                             </ul>
                             <!-- /.nav-third-level -->
                         </li>
                     </ul>
                     <!-- /.nav-second-level -->
                 </li>
             </ul>
         </div>
         <!-- /.sidebar-collapse -->
     </div>
     <!-- 菜单结束 -->
 </nav>
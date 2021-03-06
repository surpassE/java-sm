<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>One Piece</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <jsp:include page="/static/layout/incTemplate.jsp"></jsp:include>

	<script type="text/javascript">
		function loadData(url){
			var path = '${pageContext.request.contextPath}';
			url = path + "/" + url
			//$("#page-wrapper").load(url, {}, function(data){ $("#page-wrapper").html(data);});
			$.ajax({
			   type: "POST",
			   url: url,
			   async : true,
			   success: function(data, textStatus){
				   $("#page-wrapper").empty();
				   $("#page-wrapper").html(data);
			   }
			});
		}
	</script>
</head>

<body>
    <div id="wrapper">
		<!-- 引入菜单及头部信息 -->
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
		                 <li><a href="${pageContext.request.contextPath}/logout"><i class="fa fa-sign-out fa-fw"></i> 安全退出</a>
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
		                             <a href="javascript:void(0)" onclick="loadData('sysUser/toSysUser')">系统用户</a>
		                         </li>
		                         <li>
		                             <a href="javascript:void(0)" onclick="loadData('user/toUser')">应用用户</a>
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
 

		<!-- 主面板 start -->
        <div id="page-wrapper">
            <!-- 描述信息 start -->
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">One Piece</h1>
                </div>
            </div>
            <!-- 描述信息 end -->
            
			<!-- 通知 start -->
            <div class="row">
            	<div class="col-lg-3 col-md-6">
                    <div class="panel panel-green">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-tasks fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">12</div>
                                    <div>新任务</div>
                                </div>
                            </div>
                        </div>
                        
                        <a href="#">
                            <div class="panel-footer">
                                <span class="pull-left">详情</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <span class="clearfix"></span>
                            </div>
                        </a>
                    </div>
                </div>
                <div class="col-lg-3 col-md-6">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-comments fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">26</div>
                                    <div>新消息!</div>
                                </div>
                            </div>
                        </div>
                        <a href="#">
                            <div class="panel-footer">
                                <span class="pull-left">详情</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
                
                <div class="col-lg-3 col-md-6">
                    <div class="panel panel-yellow">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-envelope-o fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">124</div>
                                    <div>新通知</div>
                                </div>
                            </div>
                        </div>
                        <a href="#">
                            <div class="panel-footer">
                                <span class="pull-left">详情</span>
                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div>
            </div>
            <!-- 通知 end -->
            
            <!-- 时间轴和及时通讯 start -->
            <div class="row">
                <!-- 时间轴  start -->
                <div class="col-lg-8">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-clock-o fa-fw"></i> Responsive Timeline
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <ul class="timeline">
                                <li>
                                    <div class="timeline-badge"><i class="fa fa-check"></i>
                                    </div>
                                    <div class="timeline-panel">
                                        <div class="timeline-heading">
                                            <h4 class="timeline-title">Lorem ipsum dolor</h4>
                                            <p><small class="text-muted"><i class="fa fa-clock-o"></i> 11 hours ago via Twitter</small>
                                            </p>
                                        </div>
                                        <div class="timeline-body">
                                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Libero laboriosam dolor perspiciatis omnis exercitationem. Beatae, officia pariatur? Est cum veniam excepturi. Maiores praesentium, porro voluptas suscipit facere rem dicta, debitis.</p>
                                        </div>
                                    </div>
                                </li>
                                <li class="timeline-inverted">
                                    <div class="timeline-badge warning"><i class="fa fa-credit-card"></i>
                                    </div>
                                    <div class="timeline-panel">
                                        <div class="timeline-heading">
                                            <h4 class="timeline-title">Lorem ipsum dolor</h4>
                                        </div>
                                        <div class="timeline-body">
                                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Autem dolorem quibusdam, tenetur commodi provident cumque magni voluptatem libero, quis rerum. Fugiat esse debitis optio, tempore. Animi officiis alias, officia repellendus.</p>
                                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Laudantium maiores odit qui est tempora eos, nostrum provident explicabo dignissimos debitis vel! Adipisci eius voluptates, ad aut recusandae minus eaque facere.</p>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="timeline-badge danger"><i class="fa fa-bomb"></i>
                                    </div>
                                    <div class="timeline-panel">
                                        <div class="timeline-heading">
                                            <h4 class="timeline-title">Lorem ipsum dolor</h4>
                                        </div>
                                        <div class="timeline-body">
                                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Repellendus numquam facilis enim eaque, tenetur nam id qui vel velit similique nihil iure molestias aliquam, voluptatem totam quaerat, magni commodi quisquam.</p>
                                        </div>
                                    </div>
                                </li>
                                <li class="timeline-inverted">
                                    <div class="timeline-panel">
                                        <div class="timeline-heading">
                                            <h4 class="timeline-title">Lorem ipsum dolor</h4>
                                        </div>
                                        <div class="timeline-body">
                                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Voluptates est quaerat asperiores sapiente, eligendi, nihil. Itaque quos, alias sapiente rerum quas odit! Aperiam officiis quidem delectus libero, omnis ut debitis!</p>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="timeline-badge info"><i class="fa fa-save"></i>
                                    </div>
                                    <div class="timeline-panel">
                                        <div class="timeline-heading">
                                            <h4 class="timeline-title">Lorem ipsum dolor</h4>
                                        </div>
                                        <div class="timeline-body">
                                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nobis minus modi quam ipsum alias at est molestiae excepturi delectus nesciunt, quibusdam debitis amet, beatae consequuntur impedit nulla qui! Laborum, atque.</p>
                                            <hr>
                                            <div class="btn-group">
                                                <button type="button" class="btn btn-primary btn-sm dropdown-toggle" data-toggle="dropdown">
                                                    <i class="fa fa-gear"></i> <span class="caret"></span>
                                                </button>
                                                <ul class="dropdown-menu" role="menu">
                                                    <li><a href="#">Action</a>
                                                    </li>
                                                    <li><a href="#">Another action</a>
                                                    </li>
                                                    <li><a href="#">Something else here</a>
                                                    </li>
                                                    <li class="divider"></li>
                                                    <li><a href="#">Separated link</a>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="timeline-panel">
                                        <div class="timeline-heading">
                                            <h4 class="timeline-title">Lorem ipsum dolor</h4>
                                        </div>
                                        <div class="timeline-body">
                                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Sequi fuga odio quibusdam. Iure expedita, incidunt unde quis nam! Quod, quisquam. Officia quam qui adipisci quas consequuntur nostrum sequi. Consequuntur, commodi.</p>
                                        </div>
                                    </div>
                                </li>
                                <li class="timeline-inverted">
                                    <div class="timeline-badge success"><i class="fa fa-graduation-cap"></i>
                                    </div>
                                    <div class="timeline-panel">
                                        <div class="timeline-heading">
                                            <h4 class="timeline-title">Lorem ipsum dolor</h4>
                                        </div>
                                        <div class="timeline-body">
                                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Deserunt obcaecati, quaerat tempore officia voluptas debitis consectetur culpa amet, accusamus dolorum fugiat, animi dicta aperiam, enim incidunt quisquam maxime neque eaque.</p>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- 时间轴  end -->
                
                <!-- 及时通讯 start -->
                <div class="col-lg-4">
                    <div class="chat-panel panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-comments fa-fw"></i> 交流
                            <div class="btn-group pull-right">
                                <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">
                                    <i class="fa fa-chevron-down"></i>
                                </button>
                                <ul class="dropdown-menu slidedown">
                                    <li>
                                        <a href="#">
                                            <i class="fa fa-refresh fa-fw"></i> 刷新
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <i class="fa fa-check-circle fa-fw"></i> 在线
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <i class="fa fa-times fa-fw"></i> 忙碌
                                        </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <i class="fa fa-clock-o fa-fw"></i> 离线
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                        
                        <div class="panel-body">
                            <ul class="chat">
                                <li class="left clearfix">
                                    <span class="chat-img pull-left">
                                        <img src="http://placehold.it/50/55C1E7/fff" alt="User Avatar" class="img-circle" />
                                    </span>
                                    <div class="chat-body clearfix">
                                        <div class="header">
                                            <strong class="primary-font">Jack Sparrow</strong>
                                            <small class="pull-right text-muted">
                                                <i class="fa fa-clock-o fa-fw"></i> 12 mins ago
                                            </small>
                                        </div>
                                        <p>
                                            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur bibendum ornare dolor, quis ullamcorper ligula sodales.
                                        </p>
                                    </div>
                                </li>
                                <li class="right clearfix">
                                    <span class="chat-img pull-right">
                                        <img src="http://placehold.it/50/FA6F57/fff" alt="User Avatar" class="img-circle" />
                                    </span>
                                    <div class="chat-body clearfix">
                                        <div class="header">
                                            <small class=" text-muted">
                                                <i class="fa fa-clock-o fa-fw"></i> 13 mins ago</small>
                                            <strong class="pull-right primary-font">Bhaumik Patel</strong>
                                        </div>
                                        <p>
                                            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur bibendum ornare dolor, quis ullamcorper ligula sodales.
                                        </p>
                                    </div>
                                </li>
                            </ul>
                        </div>

                        <div class="panel-footer">
                            <div class="input-group">
                                <input id="btn-input" type="text" class="form-control input-sm" placeholder="Type your message here..." />
                                <span class="input-group-btn">
                                    <button class="btn btn-warning btn-sm" id="btn-chat">
                                		发送
                                    </button>
                                </span>
                            </div>
                        </div>
                    </div>
                    
                </div>
                <!-- 及时通讯 end -->
            </div>
            <!-- 时间轴和及时通讯 end -->
        </div>
        <!-- 主面板 end -->

    </div>
    <!-- /#wrapper -->
</body>
</html>
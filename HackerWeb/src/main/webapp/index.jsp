<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="team.AI.bean.UserBean" %>
<%@ page import="team.AI.bean.HistroyAct" %>
<%@ page import="team.AI.serviceIMP.UserServiceIMP" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
	<meta charset="utf-8" />
	<title>首页</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- App favicon -->
	<link rel="shortcut icon" href="assets/images/favicon.ico">
	
	<!-- App css -->
	<link href="assets/css/icons.min.css" rel="stylesheet" type="text/css" />
	<link href="assets/css/app.min.css" rel="stylesheet" type="text/css" />

</head>

<body>
<%
	UserBean userBean = (UserBean) session.getAttribute("userinfo");
	//int sum=(int)session.getAttribute("sum");
	//int count =(int)session.getAttribute("count");
%>

<!--modal start-->
<div id="info-modal" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-body">
				<div class="text-center mt-2 mb-4">
					<a href="index.html" class="text-success">
						<span><img src="assets/images/logo.png" alt="" height="18"></span>
					</a>
				</div>
				<form id="changeform" class="pl-3 pr-3">
					
					<div class="form-group">
						<label for="username">用户名</label>
										<input class="form-control" name="username" type="text" id="username" required="" placeholder="" value="<%= userBean.getName()%>">
									</div>
									
									<div class="form-group">
										<label for="password">密码</label>
										<input class="form-control" name="password" type="text" id="password" required="" placeholder="" value="<%= userBean.getPassword()%>">
									</div>
									
									<div class="form-group">
										<label for="email">邮箱</label>
										<input class="form-control" name="email" type="email" id="email" required="" placeholder="" value="<%= userBean.getEmail()%>">
									</div>
									
									<div class="form-group">
										<label for="tel">联系方式</label>
										<input class="form-control" name="tel" type="tel" id="tel" required="" placeholder="" value="<%=userBean.getPhone()%>">
									</div>
									
									<div class="form-group text-center">
										<button class="btn btn-primary" onclick="changeInfo()" type="button">修改</button>
									</div>
									
				</form>
			</div>
		</div>
	</div>
</div>
<!--modal end-->
						
						<!-- Topbar Start -->
						<div class="navbar-custom topnav-navbar">
							<div class="container-fluid">
								<!-- LOGO -->
								<a href="index.html" class="topnav-logo">
                    <span class="topnav-logo-lg">
                        <img src="assets/images/logo-light.png" alt="" height="16">
                    </span>
									<span class="topnav-logo-sm">
                        <img src="assets/images/logo_sm.png" alt="" height="16">
                    </span>
								</a>
								
								<ul class="list-unstyled topbar-right-menu float-right mb-0">
									<li class="dropdown notification-list">
										<a class="nav-link right-bar-toggle" href="javascript: void(0);">
											<i class="dripicons-gear noti-icon"></i>
										</a>
									</li>
									
									<li class="dropdown notification-list">
										<a class="nav-link dropdown-toggle nav-user arrow-none mr-0" data-toggle="dropdown" id="topbar-userdrop" href="#" role="button" aria-haspopup="true"
										   aria-expanded="false">
                            <span class="account-user-avatar">
                                <img src="assets/images/users/avatar-1.jpg" alt="user-image" class="rounded-circle">
                            </span>
											<span>
                                <span class="account-user-name"><%=userBean.getName()%></span>
                            </span>
										</a>
										<div class="dropdown-menu dropdown-menu-right dropdown-menu-animated topbar-dropdown-menu profile-dropdown" aria-labelledby="topbar-userdrop">
											<!-- item-->
											<div class=" dropdown-header noti-title">
												<h6 class="text-overflow m-0">欢迎！</h6>
											</div>
											
											<!-- item-->
											<a href="javascript:void(0);" class="dropdown-item notify-item">
												<i class="mdi mdi-account-circle mr-1"></i>
												<span>我的账户</span>
											</a>
											
											<!-- item-->
											<a href="javascript:void(0);" class="dropdown-item notify-item">
												<i class="mdi mdi-account-edit mr-1"></i>
												<span>设置</span>
											</a>
											
											<!-- item-->
											<a href="javascript:void(0);" class="dropdown-item notify-item">
												<i class="mdi mdi-lifebuoy mr-1"></i>
												<span>支持</span>
											</a>
											
											<!-- item-->
											<a href="javascript:void(0);" class="dropdown-item notify-item">
												<i class="mdi mdi-lock-outline mr-1"></i>
												<span>锁定屏幕</span>
											</a>
											
											<!-- item-->
											<a href="javascript:void(0);" class="dropdown-item notify-item">
												<i class="mdi mdi-logout mr-1"></i>
												<span>退出</span>
											</a>
										
										</div>
									</li>
								
								</ul>
								<a class="button-menu-mobile disable-btn">
									<div class="lines">
										<span></span>
										<span></span>
										<span></span>
									</div>
								</a>
							
							</div>
						</div>
						<!-- end Topbar -->
						<div class="container-fluid">
							
							<!-- Begin page -->
							<div class="wrapper">
								
								<!-- ============================================================== -->
								
								<!-- Start Page Content here -->
								<!-- ============================================================== -->
								
								<!-- Start Content-->
								
								<!-- ========== Left Sidebar Start ========== -->
								<div class="left-side-menu">
									
									<div class="leftbar-user">
										<a href="#">
											<img src="assets/images/users/avatar-1.jpg" alt="user-image" height="42" class="rounded-circle shadow-sm">
											<span class="leftbar-user-name"><%=userBean.getName()%></span>
										</a>
									</div>
									
									<!--- Sidemenu -->
									<ul class="metismenu side-nav">
										
										<li class="side-nav-title side-nav-item">功能列表</li>
										
										<li class="side-nav-item">
											<a href="index.jsp" class="side-nav-link">
												<i class="dripicons-meter"></i>
												<span> 个人首页 </span>
											</a>
										</li>
										
										<li class="side-nav-item">
											<a href="minganci.html" class="side-nav-link">
												<i class="dripicons-copy"></i>
												<span> 网站敏感词查询 </span>
											</a>
										</li>
										
										<li class="side-nav-title side-nav-item mt-1">入侵检测</li>
										
										<li class="side-nav-item">
											<a href="Attackrecord.html" class="side-nav-link">
												<i class="dripicons-view-apps"></i>
												<span> 攻击信息 </span>
											</a>
										</li>
										
										<li class="side-nav-item">
											<a href="monitor.html" class="side-nav-link">
												<i class="dripicons-briefcase"></i>
												<span>网站监控</span>
											</a>
										</li>
										
										<li class="side-nav-item">
											<a href="imgcheck.html" class="side-nav-link">
												<i class="dripicons-heart"></i>
												<!--<span class="badge badge-light float-right">New</span>-->
												<span>黑客入侵检测</span>
											</a>
										</li>
									</ul>
									
									<!-- End Sidebar -->
									<div class="clearfix"></div>
									<!-- Sidebar -left -->
								
								</div>
								<!-- Left Sidebar End -->
								
								<div class="content-page">
									<div class="content">
										
										<!-- start page title -->
										<div class="row">
											<div class="col-12">
												<div class="page-title-box">
													<div class="page-title-right">
														<ol class="breadcrumb m-0">
															<li class="breadcrumb-item"><a href="javascript: void(0);">Hyper</a></li>
															
															<li class="breadcrumb-item active">个人首页</li>
														</ol>
													</div>
													<h4 class="page-title">首页</h4>
												</div> <!-- end page-title-box -->
											</div> <!-- end col-->
										</div>
										
										<div class="row">
											<div class="col-sm-12">
												<!-- Profile -->
												<div class="card bg-primary">
													<div class="card-body profile-user-box">
														
														<div class="row">
															<div class="col-sm-8">
																<div class="media">
																	<span class="float-left m-2 mr-4"><img src="assets/images/users/avatar-2.jpg" style="height: 100px;" alt="" class="rounded-circle img-thumbnail"></span>
																	<div class="media-body">
																		
																		<h4 class="mt-1 mb-1 text-white"><%=userBean.getName()%></h4>
																		<p class="font-13 text-white-50"> <%=userBean.getEmail()%></p>
																		
																		<ul class="mb-0 list-inline text-light">
																			<li class="list-inline-item mr-3">
																				<h5 class="mb-1">
																					10
																				</h5>
																				<p class="mb-0 font-13 text-white-50">任务数</p>
																			</li>
																			<li class="list-inline-item">
																				<h5 class="mb-1">2</h5>
																				<p class="mb-0 font-13 text-white-50">进行中</p>
																			</li>
																		</ul>
																	</div> <!-- end media-body-->
																</div>
															</div> <!-- end col-->
															
															<div class="col-sm-4">
																<div class="text-center mt-sm-0 mt-3 text-sm-right">
																	<button type="button" class="btn btn-light" data-toggle="modal" data-target="#info-modal">
																		<i class="mdi mdi-account-edit mr-1"></i>编辑信息
																	</button>
																</div>
															</div> <!-- end col-->
														</div> <!-- end row -->
													
													</div> <!-- end card-body/ profile-user-box-->
												</div><!--end profile/ card -->
											</div> <!-- end col-->
										</div>
										
										
										<div class="row">
											<!-- 开始-->
											<div class="col-lg-4">
												<div class="card">
													<div class="card-body">


														<div class="dropdown float-right">
															<a href="#" class="dropdown-toggle arrow-none card-drop" data-toggle="dropdown" aria-expanded="false">
																<i class="mdi mdi-dots-vertical"></i>
															</a>
															<div class="dropdown-menu dropdown-menu-right" x-placement="bottom-end" style="position: absolute; will-change: transform; top: 0px; left: 0px; transform: translate3d(20px, 22px, 0px);">
																<!-- item-->
																<a href="javascript:void(0);" class="dropdown-item"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">设置</font></font></a>
																<!-- item-->
																<a href="javascript:void(0);" class="dropdown-item"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">操作</font></font></a>
															</div>
														</div>
														<h4 class="header-title mb-2"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">近期活动</font></font></h4>
														
														<div class="slimScrollDiv" style="position: relative; overflow: hidden; width: auto; height: 456px;"><div class="slimscroll" style="max-height: 330px; overflow: hidden; width: auto; height: 456px;">
															
															<div class="timeline-alt pb-0">
															
															
															
						<%
							UserServiceIMP userServiceIMP=new UserServiceIMP();
							HistroyAct histroyAct1=new HistroyAct();
							histroyAct1.setUser(userBean.getName());
							List<HistroyAct> list = userServiceIMP.Selecthistroyinfo(histroyAct1);
							if(list!=null){
							    if(list.size()==1){
							    	for(int i=0;i<2;i++){
									    HistroyAct histroyAct2=new HistroyAct();
									    histroyAct2.setActcontent("暂无");
									    histroyAct2.setActname("暂无");
									    histroyAct2.setActtime("暂无");
									    histroyAct2.setUser("暂无");
									    histroyAct2.setId(100);
									    list.add(histroyAct2);
								    }
							    }
								if(list.size()==2){
								    HistroyAct histroyAct2=new HistroyAct();
								    histroyAct2.setActcontent("暂无");
								    histroyAct2.setActname("暂无");
								    histroyAct2.setActtime("暂无");
								    histroyAct2.setUser("暂无");
								    histroyAct2.setId(100);
								    list.add(histroyAct2);
								}
								for(int i=0;i<3;i++){
									HistroyAct histroyAct =(HistroyAct) list.get(i);
									session.setAttribute("histroy"+i,histroyAct);
								}
								
							for(int i=0;i<3;i++){
								HistroyAct histroyAct =(HistroyAct) session.getAttribute("histroy"+i);
							
    					%>
										
										<div class="timeline-item">
											<i class="mdi mdi-microphone bg-info-lighten text-info timeline-icon"></i>
											<div class="timeline-item-info">
												<a href="#" class="text-info font-weight-bold mb-1 d-block"><font
														style="vertical-align: inherit;"><font
														style="vertical-align: inherit;">
												  <%=histroyAct.getActname()%>
												</font></font></a>
												<small><span class="font-weight-bold"><font
														style="vertical-align: inherit;"><font
														style="vertical-align: inherit;"><%=histroyAct.getActcontent()%></font></font></span>
												</small>
												<p class="mb-0 pb-2">
													<small class="text-muted"><font
															style="vertical-align: inherit;"><font
															style="vertical-align: inherit;">
														
														<%=histroyAct.getActtime()%></font></font>
													</small>
												</p>
											</div>
										</div>
								<%}}else{
									for(int j=0;j<3;j++){
							    %>
																
																<div class="timeline-item">
																	<i class="mdi mdi-microphone bg-info-lighten text-info timeline-icon"></i>
																	<div class="timeline-item-info">
																		<a href="#" class="text-info font-weight-bold mb-1 d-block"><font
																				style="vertical-align: inherit;"><font
																				style="vertical-align: inherit;">
																			null
																		</font></font></a>
																		<small><span class="font-weight-bold"><font
																				style="vertical-align: inherit;"><font
																				style="vertical-align: inherit;">null</font></font></span>
																		</small>
																		<p class="mb-0 pb-2">
																			<small class="text-muted"><font
																					style="vertical-align: inherit;"><font
																					style="vertical-align: inherit;">
																				
																				null</font></font>
																			</small>
																		</p>
																	</div>
																</div>
								
								<%}}%>
															</div>
									<!-- end timeline -->
								</div><div class="slimScrollBar" style="background: rgb(158, 165, 171); width: 8px; position: absolute; top: 0px; opacity: 0.4; display: none; border-radius: 7px; z-index: 99; right: 1px; height: 247.5px;"></div><div class="slimScrollRail" style="width: 8px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; background: rgb(51, 51, 51); opacity: 0.2; z-index: 90; right: 1px;"></div></div> <!-- end slimscroll -->
							</div>
							<!-- end card-body -->
						</div>
						<!-- end card   结束-->
					</div>
					
					<div class="col-xl-8" style="padding-left: 0px;">
						<div class="card">
							<div class="card-body">
								<div class="dropdown float-right">
									<a href="#" class="dropdown-toggle arrow-none card-drop" data-toggle="dropdown" aria-expanded="false">
										<i class="mdi mdi-dots-vertical"></i>
									</a>
									<div class="dropdown-menu dropdown-menu-right">
										<!-- item-->
										<a href="javascript:void(0);" class="dropdown-item">Weekly Report</a>
										<!-- item-->
										<a href="javascript:void(0);" class="dropdown-item">Monthly Report</a>
										<!-- item-->
										<a href="javascript:void(0);" class="dropdown-item">Action</a>
										<!-- item-->
										<a href="javascript:void(0);" class="dropdown-item">Settings</a>
									</div>
								</div>
								
								
								<h4 class="header-title mb-3">最近重要大事件</h4>
								
								<!-- <p><b>107</b> Tasks completed out of 195</p> -->
								
								<div class="table-responsive">
									<table class="table table-centered table-hover mb-0">
										<tbody>
										<tr>
											<td>
												
												<h5 class="font-14 my-1"><a href="javascript:void(0);" class="text-body">事件</a></h5>
												<!--<span class="text-muted font-13">Due in 5 days</span>-->
											</td>
											<td>
												<h5 class="font-14 my-1"><a href="javascript:void(0);" class="text-body">网址链接</a></h5>
<%--												<span class="text-muted font-13">Status</span> <br/>--%>
<%--												<span class="badge badge-warning-lighten">In-progress</span>--%>
											</td>
											<td>
												<h5 class="font-14 my-1"><a href="javascript:void(0);" class="text-body">时间</a></h5>
<%--												<span class="text-muted font-13">Total time spend</span>--%>
<%--												<h5 class="font-14 mt-1 font-weight-normal">26h 58min</h5>--%>
											</td>
<%--											<td class="table-action" style="width: 90px;">--%>
<%--												<a href="javascript: void(0);" class="action-icon"> <i class="mdi mdi-pencil"></i></a>--%>
<%--												<a href="javascript: void(0);" class="action-icon"> <i class="mdi mdi-delete"></i></a>--%>
<%--											</td>--%>
										</tr>
										
										
										
										
										
										<tr>
											<td>
												<h5 class="font-14 my-1"><a href="javascript:void(0);" class="text-body">攻克江苏无锡某Weblogic服务器平台</a></h5>
												<!--<span class="text-muted font-13">Due in 27 days</span>-->
											</td>
											<td>
												<h5 class="font-14 my-1"><a href="http://58.214.20.23:9001" class="text-body">http://58.214.20.23:9001</a></h5>
<%--												<span class="text-muted font-13">http://58.214.20.23:9001/bea_wls_internal/Index.html</span> <br/>--%>
												<!--<span class="badge badge-danger-lighten">Outdated</span>-->
											</td>
<%--											<td>--%>
<%--												<!--<span class="text-muted font-13">Assigned to</span>-->--%>
<%--												<h5 class="font-14 mt-1 font-weight-normal">战果展示：在网站挂上“中国共产党，祝您诸事兴旺！ ”</h5>--%>
<%--											</td>--%>
											<td>
												<h5 class="font-14 my-1"><a href="javascript:void(0);" class="text-body">2019年10月25日晨</a></h5>
												<!--<span class="text-muted font-13">Total time spend</span>-->
<%--												<h5 class="font-14 mt-1 font-weight-normal">2019年10月25日晨</h5>--%>
											</td>
<%--											<td class="table-action" style="width: 90px;">--%>
<%--												<a href="javascript: void(0);" class="action-icon"> <i class="mdi mdi-pencil"></i></a>--%>
<%--												<a href="javascript: void(0);" class="action-icon"> <i class="mdi mdi-delete"></i></a>--%>
<%--											</td>--%>
										</tr>
										
										<tr>
											<td>
												<h5 class="font-14 my-1"><a href="javascript:void(0);" class="text-body">攻克深圳某公司akl系统平台</a></h5>
<%--												<h5 class="font-14 my-1"><a href="javascript:void(0);" class="text-body">攻克深圳某公司akl系统平台</a></h5>--%>
<%--												<span class="text-muted font-13">Due in 7 days</span>--%>
											</td>
											<td>
												<h5 class="font-14 my-1"><a href="javascript:void(0);" class="text-body">http://218.16.62.178:99 </a></h5>
<%--												<span class="text-muted font-13">Status</span> <br/>--%>
<%--												<span class="badge badge-success-lighten">Completed</span>--%>
											</td>
<%--											<td>--%>
<%--												<span class="text-muted font-13">Assigned to</span>--%>
<%--												<h5 class="font-14 mt-1 font-weight-normal">Scot M. Smith</h5>--%>
<%--											</td>--%>
											<td>
												<h5 class="font-14 my-1"><a href="javascript:void(0);" class="text-body">2019年10月21日晨</a></h5>
<%--												<span class="text-muted font-13">Total time spend</span>--%>
<%--												<h5 class="font-14 mt-1 font-weight-normal">78h 05min</h5>--%>
											</td>
<%--											<td class="table-action" style="width: 90px;">--%>
<%--												<a href="javascript: void(0);" class="action-icon"> <i class="mdi mdi-pencil"></i></a>--%>
<%--												<a href="javascript: void(0);" class="action-icon"> <i class="mdi mdi-delete"></i></a>--%>
<%--											</td>--%>
										</tr>
										
										
										<tr>
											<td>
												<h5 class="font-14 my-1"><a href="javascript:void(0);" class="text-body">攻克贵阳职业技术学院数字化信息平台</a></h5>
<%--												<h5 class="font-14 my-1"><a href="javascript:void(0);" class="text-body">Poster illustation design</a></h5>--%>
<%--												<span class="text-muted font-13">Due in 5 days</span>--%>
											</td>
											<td>
												<h5 class="font-14 my-1"><a href="javascript:void(0);" class="text-body">http://crp.gyvtc.edu.cn</a></h5>
<%--												<span class="text-muted font-13">Status</span> <br/>--%>
<%--												<span class="badge badge-warning-lighten">In-progress</span>--%>
											</td>
<%--											<td>--%>
<%--												<span class="text-muted font-13">Assigned to</span>--%>
<%--												<h5 class="font-14 mt-1 font-weight-normal">John P. Ritter</h5>--%>
<%--											</td>--%>
											<td>
												<h5 class="font-14 my-1"><a href="javascript:void(0);" class="text-body">2019年10月19日晨</a></h5>
<%--												<span class="text-muted font-13">Total time spend</span>--%>
<%--												<h5 class="font-14 mt-1 font-weight-normal">26h 58min</h5>--%>
											</td>
<%--											<td class="table-action" style="width: 90px;">--%>
<%--												<a href="javascript: void(0);" class="action-icon"> <i class="mdi mdi-pencil"></i></a>--%>
<%--												<a href="javascript: void(0);" class="action-icon"> <i class="mdi mdi-delete"></i></a>--%>
<%--											</td>--%>
										</tr>
										
										<tr>
											<td>
												<h5 class="font-14 my-1"><a href="javascript:void(0);" class="text-body">攻克北京佳网科技围群登录页面</a></h5>
												<%--												<h5 class="font-14 my-1"><a href="javascript:void(0);" class="text-body">Poster illustation design</a></h5>--%>
												<%--												<span class="text-muted font-13">Due in 5 days</span>--%>
											</td>
											<td>
												<h5 class="font-14 my-1"><a href="javascript:void(0);" class="text-body">http://124.163.223.253:8002</a></h5>
												<%--												<span class="text-muted font-13">Status</span> <br/>--%>
												<%--												<span class="badge badge-warning-lighten">In-progress</span>--%>
											</td>
											<%--											<td>--%>
											<%--												<span class="text-muted font-13">Assigned to</span>--%>
											<%--												<h5 class="font-14 mt-1 font-weight-normal">John P. Ritter</h5>--%>
											<%--											</td>--%>
											<td>
												<h5 class="font-14 my-1"><a href="javascript:void(0);" class="text-body">2019年10月16日晨</a></h5>
												<%--												<span class="text-muted font-13">Total time spend</span>--%>
												<%--												<h5 class="font-14 mt-1 font-weight-normal">26h 58min</h5>--%>
											</td>
											<%--											<td class="table-action" style="width: 90px;">--%>
											<%--												<a href="javascript: void(0);" class="action-icon"> <i class="mdi mdi-pencil"></i></a>--%>
											<%--												<a href="javascript: void(0);" class="action-icon"> <i class="mdi mdi-delete"></i></a>--%>
											<%--											</td>--%>
										</tr>
										
										</tbody>
									</table>
								</div> <!-- end table-responsive-->
							
							</div> <!-- end card body-->
						</div> <!-- end card -->
					</div><!-- end col-->
				</div>
				
				<!-- ======================================================================================= -->
				<!-- end page title -->
			
			</div> <!-- content -->
			
			<!-- Footer Start -->
			<footer class="footer">
				<div class="row">
					<div class="col-md-6">
					<!--	2018 - 2019 © Hyper - Coderthemes.com-->
					</div>
					<div class="col-md-6">
						<div class="text-md-right footer-links d-none d-md-block">
							<a href="javascript: void(0);">About</a>
							<a href="javascript: void(0);">Support</a>
							<a href="javascript: void(0);">Contact Us</a>
						</div>
					</div>
				</div>
			</footer>
			<!-- end Footer -->
		</div> <!-- content-page -->
	
	</div> <!-- end wrapper-->
	
	<!-- ============================================================== -->
	<!-- End Page content -->
	<!-- ============================================================== -->


</div>
<!-- END Container -->


<!-- Right Sidebar -->
<div class="right-bar">
	
	<div class="rightbar-title">
		<a href="javascript:void(0);" class="right-bar-toggle float-right">
			<i class="dripicons-cross noti-icon"></i>
		</a>
		<h5 class="m-0">Settings</h5>
	</div>
	
	<div class="slimscroll-menu rightbar-content">
		
		<!-- Settings -->
		<hr class="mt-0" />
		<h5 class="pl-3">Basic Settings</h5>
		<hr class="mb-0" />
		
		<div class="p-3">
			<div class="custom-control custom-checkbox mb-2">
				<input type="checkbox" class="custom-control-input" id="notifications-check" checked>
				<label class="custom-control-label" for="notifications-check">Notifications</label>
			</div>
			
			<div class="custom-control custom-checkbox mb-2">
				<input type="checkbox" class="custom-control-input" id="api-access-check">
				<label class="custom-control-label" for="api-access-check">API Access</label>
			</div>
			
			<div class="custom-control custom-checkbox mb-2">
				<input type="checkbox" class="custom-control-input" id="auto-updates-check" checked>
				<label class="custom-control-label" for="auto-updates-check">Auto Updates</label>
			</div>
			
			<div class="custom-control custom-checkbox mb-2">
				<input type="checkbox" class="custom-control-input" id="online-status-check" checked>
				<label class="custom-control-label" for="online-status-check">Online Status</label>
			</div>
			
			<div class="custom-control custom-checkbox mb-2">
				<input type="checkbox" class="custom-control-input" id="auto-payout-check">
				<label class="custom-control-label" for="auto-payout-check">Auto Payout</label>
			</div>
		
		</div>
		
		
		<!-- Timeline -->
		<hr class="mt-0" />
		<h5 class="pl-3">Recent Activity</h5>
		<hr class="mb-0" />
		<div class="pl-2 pr-2">
			<div class="timeline-alt">
				<div class="timeline-item">
					<i class="mdi mdi-upload bg-info-lighten text-info timeline-icon"></i>
					<div class="timeline-item-info">
						<a href="#" class="text-info font-weight-bold mb-1 d-block">You sold an item</a>
						<small>Paul Burgess just purchased “Hyper - Admin Dashboard”!</small>
						<p class="mb-0 pb-2">
							<small class="text-muted">5 minutes ago</small>
						</p>
					</div>
				</div>
				
				<div class="timeline-item">
					<i class="mdi mdi-airplane bg-primary-lighten text-primary timeline-icon"></i>
					<div class="timeline-item-info">
						<a href="#" class="text-primary font-weight-bold mb-1 d-block">Product on the Bootstrap Market</a>
						<small>Dave Gamache added
							<span class="font-weight-bold">Admin Dashboard</span>
						</small>
						<p class="mb-0 pb-2">
							<small class="text-muted">30 minutes ago</small>
						</p>
					</div>
				</div>
				
				<div class="timeline-item">
					<i class="mdi mdi-microphone bg-info-lighten text-info timeline-icon"></i>
					<div class="timeline-item-info">
						<a href="#" class="text-info font-weight-bold mb-1 d-block">Robert Delaney</a>
						<small>Send you message
							<span class="font-weight-bold">"Are you there?"</span>
						</small>
						<p class="mb-0 pb-2">
							<small class="text-muted">2 hours ago</small>
						</p>
					</div>
				</div>
				
				<div class="timeline-item">
					<i class="mdi mdi-upload bg-primary-lighten text-primary timeline-icon"></i>
					<div class="timeline-item-info">
						<a href="#" class="text-primary font-weight-bold mb-1 d-block">Audrey Tobey</a>
						<small>Uploaded a photo
							<span class="font-weight-bold">"Error.jpg"</span>
						</small>
						<p class="mb-0 pb-2">
							<small class="text-muted">14 hours ago</small>
						</p>
					</div>
				</div>
				
				<div class="timeline-item">
					<i class="mdi mdi-upload bg-info-lighten text-info timeline-icon"></i>
					<div class="timeline-item-info">
						<a href="#" class="text-info font-weight-bold mb-1 d-block">You sold an item</a>
						<small>Paul Burgess just purchased “Hyper - Admin Dashboard”!</small>
						<p class="mb-0 pb-2">
							<small class="text-muted">1 day ago</small>
						</p>
					</div>
				</div>
			
			</div>
		</div>
	</div>
</div>


<div class="rightbar-overlay"></div>
<!-- /Right-bar -->
</div>
</body>
</html>
<!-- App js -->
<script src="assets/js/app.min.js"></script>

<script>
    function changeInfo() {

        $.ajax({
            type: "post",
            url: "ChangeInfoServlet",
            data: $('#changeform').serialize(),
            dataType: "json",
            success: function (data) {

            }
        });
    }
</script>
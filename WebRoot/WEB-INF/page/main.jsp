<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'main.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/css.css">
  </head>
  
  <body >
   <h1>欢迎使用本OA自动化办公系统</h1>
   <div>
   <ul>
   	<li>1、我的办公模块包括个人信息展示,收发文,公告显示,日程安排等内容</li>
   	<li>2、行政管理模块包括撰写公告,审核公告,以及请假销假模块</li>
   	<li>3、信息交流模块包括留言板功能</li>
   	<li>4、人事档案模块包块对人事档案的添加和查询</li>
   	<li>5、系统管理模块包括系统参数的设置,部门，单位，用户,权限设置等等功能</li>
   </ul>
   <h2 style="margin-left:180px">重庆大学计算机学院张新兴 2016年5月</h2>
   </div>
  </body>
</html>

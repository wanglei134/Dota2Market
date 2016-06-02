<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>CSGO出库存  DOTA2出库存</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="CSGO出库存  DOTA2出库存">
		<meta http-equiv="description" content="CSGO出库存  DOTA2出库存">	
</head>
 <frameset  rows="109,*"  frameborder="0" framespacing="0" border="0">
  	<frame name="top" src="top.jsp"  frameborder="0" noresize marginhight=0 marginwidth="0" scrolling="no" z-index="-100">
    <frameset id="below" rows="*,78"  frameborder="0" framespacing="0" border="0">
        <frame name="main" src="main.jsp" frameborder="0" noresize marginhight=0 marginwidth="0" scrolling="auto" >
        <frameset id="footer" frameborder="0" framespacing="0" border="0">
        <frame name="footer" src="footer.jsp" frameborder="0" scrolling="no" 	noresize marginhight=0 marginwidth="0">
        </frameset>        
    </frameset>
  </frameset><noframes></noframes>
</html>

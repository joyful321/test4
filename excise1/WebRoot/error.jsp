<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">   
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<meta content="utf-8">
	<title>出错啦</title>
	<link href="css/error.css" rel="stylesheet"/>
	<script language="JavaScript" src="js/error.js"></script>
	

  </head>
  
  <body>
    <div id="errorDiv">
    	<div id="errorHint">
    		<p id="errorInfo">${info}</p>
    		<p><span id="leaveTime">10</span>秒后自动返回到登陆页面</p>
    		<p>不能跳转，请<a href="login.html">点击这儿</a></p>
    	</div>
    </div>
  </body>
</html>

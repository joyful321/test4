<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>用户登录</title>
    <link rel="stylesheet" href="css/main.css">
    <!-- <script src="js/login.js"></script> -->
   
</head>
<body>
    
   <div id="headDiv">
       <div id="upDiv">
           <p>欢迎你：<p >${currentUser.userName}</p>  <a href="login.html">【安全退出】</a></p>
          
       </div>
       <div id="options">
           <a href="#">首页</a>
           <a href="download.jsp">下载</a>
           <a href="personalCenter.jsp">个人中心</a>
           <a href="resourceManage.jsp">资源管理</a>
           <a href="userManage.html">用户管理</a>
           <a href="#">关于</a>

       </div>
   </div>
   <div id="bottomDiv">
   </div>

</body>
</html>
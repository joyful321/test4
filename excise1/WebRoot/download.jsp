<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>下载页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/download.css" rel="stylesheet"/>

  </head>
  
  <body>
      <div id="title">
        <p >资源下载</p>
      </div>
      <form action="download.do" method="get" style="height: 85px; ">
    
        <c:forEach items="${downloadList}" var="download" varStatus="mess">
           <div id="resource">
                <div id="name">
                    <p>${download.name}</p>
                </div>
                <div id="bottomDiv">
                    <div id="img">
                        <img src=${download.image} alt="" >
                    </div>
                    <div id="description">
                        <p>${download.description}</p>
                    </div>
                    <div id="dlDiv">
                        <button id="dl" type="submit" name="下载" value="${download.id}">下载</button>
                    </div>
                </div>
           </div>
       </c:forEach>
   
   </form>
  </body>
</html>

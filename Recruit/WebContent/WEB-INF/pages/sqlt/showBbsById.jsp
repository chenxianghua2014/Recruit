<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'showBbsById.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link href="css/htgl/bbs/sq_style.css" rel="stylesheet" type="text/css" />
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
  </head>
  
  <body>
 <div class="title">当前位置:科工论坛>评论详情</div> 
 <div style="text-align: left;"><input type="button" class="inputButton" value="返回" onclick="history.back()" /></div>
 <div class="dataGrid">
  <div class='comments-item-bd'>
  	<span class='name'>${bbs.bbsFbr}:</span>
  	<span class='con'>${bbs.bbsNr}</span>
  	<div style='clear:both;'></div>
  	<div class='time'>${bbs.fbsj}</div>
  	<c:forEach items="${reviewList}" var="map">
  		<div class='comments-list'>
  			<span class='name'>${map.reviewPlr}:</span>
  			<span class='con'>${map.reviewNr}</span>
  			<div style='clear:both;'></div>
  			<div class='time'>${map.plsj}</div>
  		</div>
    </c:forEach>
  </div>
 </div>
  </body>
</html>

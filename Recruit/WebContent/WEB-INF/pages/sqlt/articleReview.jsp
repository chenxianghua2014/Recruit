<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>社区-查看详情</title>
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<style style"text/css">
body{ margin:0px; padding:0px; font-family:"宋体"; font-size:12px;}
#shequ_all{ width:95%; overflow:hidden; margin:0 auto;}
#shequ_all h2{ width:100%; height:45px; line-height:45px; text-align:center; font-size:20px;}
.location{ width:100%; height:30px; line-height:30px; text-align:left; padding-right:0px; color:#999;}
.shequ_title{ width:100%; height:30px; line-height:30px; text-align:center;}
.shequ_title span{ padding:0px 10px;}
.shequ_title .time{ color:#999;}
.shequ_all .content{ width:100%; overflow:hidden;}
.content p{width:100%; line-height:30px; text-indent:2em;}
.huifu{ width:100%; overflow:hidden; margin-top:30px;}
.huifu td{ height:40px; line-height:40px;}
</style>
  </head>
  
  <body>
<div id="shequ_all">
  <div class="location"> 当前位置：读取用户记录>博文内容</div>
  <input type="button" value="返回" onclick="window.history.go(-1)">
    <input type="hidden" value="${article.articleId}" name="articleId"/>
    <h2>${article.articleTitle}</h2>
    <div class="shequ_title">
    	<span>作者:${article.articleAuthor}</span>
        <span>类型:${article.articleType}</span>
        <span class="time">时间：${article.addTime}</span>
    </div>
    <div class="content">
    ${article.articleContent}
    </div>
</div>
  </body>
</html>

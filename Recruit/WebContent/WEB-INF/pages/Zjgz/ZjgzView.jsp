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
  <title>组卷规则-查看详情</title>
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
.content p{width:100%; line-height:30px; text-indent:2em; margin-top: 40px;}
.huifu{ width:100%; overflow:hidden; margin-top:30px;}
.huifu td{ height:40px; line-height:40px;}
</style>
  </head>
  
  <body>
<div id="shequ_all">
  <div class="location"> 当前位置：组卷规则-查看详情</div>
    <form action="zjgzsh" method="post">
    <input type="button" class="inputButton" value="返回" onclick="history.back()" />
    <input type="hidden" value="${zjgz.zjgzId}" name="zjgzId"/>
    <div class="shequ_title">
    	<span>规则编号:${zjgz.zjgzBh}</span>
        <span>优先等级:${zjgz.zjgzYxdj}</span>
    	 <span>类型:${zjgz.zjgzSykslx}</span>
    </div>
    <div class="content" align="center">
  		  ${zjgz.zjgzGznr}
    </div>
    </form>
</div>
  </body>
</html>

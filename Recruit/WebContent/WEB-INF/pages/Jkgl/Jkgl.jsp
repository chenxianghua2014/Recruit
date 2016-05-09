<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>测评卷库列表</title>
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>
<link rel="stylesheet" href="resources/pagination/pagination.css"
	type="text/css"></link>
<script type="text/javascript" src="resources/jBox/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-zh-CN.js"></script>
<link type="text/css" rel="stylesheet" href="resources/jBox/Skins/Blue/jbox.css"/>
<script type="text/javascript"
	src="resources/pagination/jquery.pagination.js"></script>
<script type="text/javascript" src="js/Jk.js"></script>
</head>
  </head>
  <body>
  <form name="ff" id="ff">
    <input type="hidden" name="zzjgEjmm" id="zzjgEjmm" value="${loginSession.zzjgEjmm}"/>
    <input type="hidden" name="ejmm" id="ejmm" value="${ejmm}"/>
  </form>
  <div class="title">当前位置:题库卷库管理>卷库管理</div> 

    <div class="editBlock" id="showWork">
		<table>
			<tbody>
				<tr>
					<td height="32" colspan="4" class="subtitle">查询</td>
				</tr>
				<tr>
				   <td style="text-align: left;"><input name="button"
						type="button" class="inputButton" value="添加" onclick="JkAdd();"/>
					</td>
					<td align="right">卷库名称：<input name="jkName" type="text" class="inputText"
						id="jkName" />
					<input name="button"
						type="button" class="inputButton" value="查询" onclick="loadData()" />
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="dataGrid">
		<table>
			<caption>
				查询结果 
			</caption>
			<thead>
				<tr>
					<th>序号</th>
					<th>卷库名称</th>
					<th>添加时间</th>
					<th width="60"  class="alignCenter">修改</th>
					<th width="60"  class="alignCenter">删除</th>
					<th width="60"  class="alignCenter">生成试卷</th>
					<th width="60"  class="alignCenter">查看试卷</th>
				</tr>
			</thead>
			<tbody id="tbList"></tbody>
		</table>
		<table class="page">
			<tr>
				<td style="text-align: right;"><div id="Pagination"
						class="pagination"></div>
				</td>
				<td style="width:265px;text-align: center;">当前显示 <span
					id="start">1</span> - <span id="end">10</span> 条记录 共 <span
					id="count">0</span> 条记录</td>
			</tr>
		</table>

	</div>
    <table boder="0" id="tbList">  
	</table>
	<div id="Pagination" class="pagination">
  </body>
  
</html>

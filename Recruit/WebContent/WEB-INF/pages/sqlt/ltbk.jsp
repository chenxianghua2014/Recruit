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
    
    <title>社区-论坛板块列表</title>
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="resources/jBox/Skins/Default/jbox.css"
	type="text/css"></link>
<link rel="stylesheet" href="resources/pagination/pagination.css"
	type="text/css"></link>
<script type="text/javascript" src="resources/jBox/jquery-1.4.2.min.js"></script>
<script type="text/javascript"
	src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript"
	src="resources/pagination/jquery.pagination.js"></script>
<script type="text/javascript" src="js/Ltbk.js"></script>
</head>
  </head>
  
  <body>
  <div class="title">当前位置:内容管理及审核>板块管理</div> 
    <div class="editBlock">
		<table>
			<tbody>
				<tr>
					<td height="32" colspan="4" class="subtitle">查询</td>
				</tr>
				<tr>
				   <td style="text-align: left;"><input name="button"
						type="button" class="inputButton" value="添加" onclick="javascript:LtbkAdd();"/>
					</td>
					<td align="right">板块名称：<input name="ltbkName" type="text" class="inputText"
						id="ltbkName" />
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
					<th>板块名称</th>
					<th>所属讨论区</th>
					<th>添加时间</th>
					<th width="60">修改</th>
					<th width="60">删除</th>
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

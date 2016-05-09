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
    
    <title>社区-标签管理列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
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
<script type="text/javascript" src="js/Ltbq.js"></script>
<script type="text/javascript">

function ltbqAdd(){
	window.location.href="ltbqAdd";
}

</script>
</head>
  </head>
  
  <body>
  <div class="title">当前位置:内容管理及审核>标签管理</div> 
    <div class="editBlock">
		<table>
			<tbody>
				<tr>
					<td height="32" colspan="4" class="subtitle">查询</td>
				</tr>
				<tr>
				   <td style="text-align: left;"><input name="button"
						type="button" class="inputButton" value="添加" onclick="javascript:ltbqAdd();"/>
					</td>
					<td align="right">标签名称：<input name="ltbqName" type="text" class="inputText"
						id="ltbqName" />
					类型： <select name="ltbqType" id="ltbqType">
								<option value="">--请选择--</option>
					          <option value="论坛">论坛</option>
							  <option value="博文">博文</option>
							  <option value="兴趣圈子">兴趣圈子</option>

						  </select>
					<input name="button"
						type="button" class="inputButton" value="查询" onclick="loadData('')" />
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
					<th>标签名称</th>
					<th>标签类别</th>
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

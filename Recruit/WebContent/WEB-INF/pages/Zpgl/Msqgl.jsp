<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<link rel="stylesheet" href="skins/default/main.css" type="text/css" />
<link rel="stylesheet" href="resources/jBox/Skins/Blue/jbox.css"
	type="text/css"></link>
<link rel="stylesheet" href="resources/pagination/pagination.css"
	type="text/css"></link>
<script type="text/javascript" src="resources/jquery/jquery-1.9.1.js"></script>
<script src="resources/jquery/jquery-migrate-1.1.1.js"></script>
<script type="text/javascript"
	src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-zh-CN.js"></script>
<script type="text/javascript"
	src="resources/pagination/jquery.pagination.js"></script>
<script type="text/javascript" src="js/Msqgl.js"></script>
<script type="text/javascript" src="js/common.js"></script>
</head>
<body>
	<div class="title">当前位置:面试圈>面试圈管理</div>
	<div class="editBlock">
		<table>
			<tbody>
				<tr>
					<td height="32" colspan="4" class="subtitle">面试圈管理查询</td>
				</tr>
				<tr>
					<td style="text-align: left"><input name="button" type="button"
						class="inputButton" value="添加" onclick="AddMsq()" /></td>
					<td style="text-align:right;">面试圈名称:<input name="keywords"
						type="text" class="inputText" id="keywords" />&nbsp;<input
						name="button" type="button" onclick="loadData();"
						class="inputButton" value="查询" />
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="dataGrid">
		<table>
			<caption>面试圈管理查询结果</caption>
			<thead>
				<tr>
					<th>序号</th>
					<th>面试圈名称</th>
					<th>面试类别</th>
					<th>面试时间</th>
					<th>面试地点</th>
					<th>面试官</th>
					<th>状态</th>
					<th width="60" class="alignCenter">编辑</th>
					<th width="120" class="alignCenter">查看临时账号</th>
					<th width="120" class="alignCenter">删除临时账号</th>
					<th width="60" class="alignCenter">删除</th>
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
</body>
</html>

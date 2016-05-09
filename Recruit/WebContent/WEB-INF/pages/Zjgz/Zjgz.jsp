<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
String path = request.getContextPath();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<title></title>
<link rel="stylesheet" href="resources/pagination/pagination.css"
	type="text/css"></link>
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="resources/pagination/jquery.pagination.js"></script>
<script type="text/javascript" src="js/Zjgz.js"></script>
</head>
<body>
	<div class="title">当前位置:题库卷库管理>组卷规则管理</div>
	<div class="editBlock">
		<table>
			<tbody>
				<tr>
					<td height="32" colspan="4" class="subtitle">组卷规则</td>
				</tr>
				<tr>
					<td style="text-align: left;"><input name="button" type="button"
						class="inputButton" value="添加" onclick="AddZjgz()" /></td>
						
					<td style="text-align:right;">考试类型:<select id="keywords"
						name="keywords">
							<option value="">--请选择--</option>
							<option value="技术岗">技术岗</option>
							<option value="管理岗">管理岗</option>
					</select>&nbsp;<input name="button" type="button"
						onclick="loadData()" class="inputButton" value="查询" />
					</td>
			</tbody>
		</table>
	</div>
	<div class="dataGrid">
		<table>
			<caption>管理岗</caption>
			<thead>
				<tr>
					<th>规则编号</th>
					<th>优先等级</th>
					<th>适用考试类型</th>
					<th width="60" class="alignCenter">查看</th>
					<th width="60" class="alignCenter">修改</th>
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

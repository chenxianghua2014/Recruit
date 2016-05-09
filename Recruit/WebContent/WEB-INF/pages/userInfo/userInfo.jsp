<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title></title>
<link rel="stylesheet" href="resources/pagination/pagination.css"
	type="text/css"></link>
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="resources/pagination/jquery.pagination.js"></script>
<script type="text/javascript" src="js/userInfo.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<style type="text/css">
.inputText {
	width: 65px;
}
</style>
</head>
<body>
	<div class="title">当前位置:应聘者管理>应聘者管理</div>
	<div class="editBlock">
		<table>
			<tbody>
				<tr>
					<td height="32" colspan="4" class="subtitle">应聘者查询</td>
				</tr>
				<tr>
					<td style="text-align:right;">姓名:<input name="userName"
						type="text" class="inputText" id="userName" />&nbsp; 登录名:<input
						name="userIdcard" type="text" class="inputText" id="userIdcard" />&nbsp;
						身份证号:<input name="userSfzh" type="text" class="inputText"
						id="userSfzh" />&nbsp; 电话:<input name="userTelephone" type="text" class="inputText"
						id="userTelephone" />&nbsp; 邮箱:<input name="userEmail" type="text" class="inputText"
						id="userEmail" />&nbsp;<input name="button" type="button" onclick="loadData();"
						class="inputButton" value="查询" /></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="dataGrid">
		<table>
			<caption>应聘者查询结果</caption>
			<thead>
				<tr>
					<th width="50">姓名</th>
					<th width="50">性别</th>
					<th width="50">年龄</th>
					<th width="120">登录名</th>
					<th width="180">身份证号</th>
					<th width="120">电话</th>
					<th width="220">邮箱</th>
					<th width="220">注册时间</th>
					<th width="60" class="alignCenter">重置密码</th>
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

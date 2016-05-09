<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<link href="css/htgl/user/jygl.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="resources/pagination/pagination.css"
	type="text/css"></link>
<script type="text/javascript" src="resources/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-zh-CN.js"></script>
<link type="text/css" rel="stylesheet" href="resources/jBox/Skins/Blue/jbox.css"/>
<script type="text/javascript"
	src="resources/pagination/jquery.pagination.js"></script>
<script type="text/javascript" src="js/userList.js"></script>
<style type="text/css">
.editBlock a{background-color:#fff; border:1px solid #ccc; padding:5px; font-size:12px; color:#000; text-decoration: none;}
</style>
</head>
<body>
<div class="title">当前位置:调取用户信息>用户列表</div> 
	<div class="editBlock">
		<table>
			<tbody>
				<tr>
					<td align="right">
						姓名：<input name="userName" type="text" class="inputText" id="userName" />
						账号：<input name="userIdcard" type="text" class="inputText" id="userIdcard" />
						电话：<input name="userTelephone" type="text" class="inputText" id="userTelephone" />
						邮箱：<input name="userEmail" type="text" class="inputText" id="userEmail" />
					</td>
					<td class="toolbar"><input name="button"
						type="button" class="inputButton" value="查询" onclick="loadData()" />
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="dataGrid">
		<table>
			<caption>
				用户信息查询结果 
			</caption>
			<thead>
				<tr>
					<th>姓名</th>
					<th>账号</th>
					<th>电话</th>
					<th>邮箱</th>
					<th width="60" class="alignCenter">操作</th>
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

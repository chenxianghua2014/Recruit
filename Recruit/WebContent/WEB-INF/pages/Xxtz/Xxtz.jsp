<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/Xxtz.js"></script>
</head>
<body>
	<div class="title">当前位置:消息通知>消息发送通知</div>
	<div class="editBlock">
		<table>
			<tbody>
				<tr>
					<td height="32" colspan="4" class="subtitle">新的简历查询</td>
				</tr>
				<tr>

					<td style="text-align:right;">接收人:<input name="userName"
						type="text" class="inputText" id="userName">&nbsp;状态:<select
						name="status" type="text" class="inputText" id="status">
							<option></option>
							<option value="Success">发送成功</option>
							<option value="Faild">发送失败</option>
					</select>&nbsp;<input name="button" type="button" onclick="loadData();"
						class="inputButton" value="查询"></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="dataGrid">
		<table>
			<caption>消息发送失败查询结果</caption>
			<thead>
				<tr class="odd">
					<th>接收人</th>
					<th>接收号码</th>
					<th>通知类型</th>
					<th>通知内容</th>
					<th>通知时间</th>
					<th>状态</th>
					<th width="60" class="alignCenter">操作</th>
				</tr>
			</thead>
			<tbody id="tbList">
			</tbody>
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

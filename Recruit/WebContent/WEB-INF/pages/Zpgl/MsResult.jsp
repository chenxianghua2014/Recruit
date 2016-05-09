<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<link rel="stylesheet"
	href="resources/validator-0.7.1/jquery.validator.css" type="text/css"></link>
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="resources/pagination/pagination.css"
	type="text/css"></link>
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="resources/pagination/jquery.pagination.js"></script>
<script type="text/javascript"
	src="resources/validator-0.7.1/jquery.validator.js"></script>
<script type="text/javascript"
	src="resources/validator-0.7.1/local/zh_CN.js"></script>
<script type="text/javascript" src="js/MsResult.js"></script>
<script type="text/javascript" src="js/common.js"></script>
</head>
<body>
	<form action="MsjgSave" method="post" autocomplete="off"
		data-validator-option="{theme:'yellow_right_effect',stopOnError:true}">
		<div class="title">当前位置:面试圈>面试结果</div>
		<div class="dataGrid">
			<table>
				<caption>待填写面试结果列表</caption>
				<thead>
					<tr>
						<th>序号</th>
						<th>面试类别</th>
						<th>面试时间</th>
						<th>面试地点</th>
						<th>面试官</th>
					</tr>
				</thead>
				<tbody id="tbList"></tbody>
			</table>
			<table class="page">
				<tr>
					<td style="text-align: right;"><div id="Pagination"
							class="pagination"></div></td>
					<td style="width:265px;text-align: center;">当前显示 <span
						id="start">1</span> - <span id="end">10</span> 条记录 共 <span
						id="count">0</span> 条记录</td>
				</tr>
			</table>
		</div>
		<div class="editBlock">
			<table>
				<tr>
					<th width="150"><span class="warning">*</span>评价</th>
					<td><input type="hidden" id="msqglDetailedId"
						name="msqglDetailedId"> <textarea id="msqglDetailedPj"
							name="msqglDetailedPj" style="width:600px;height:100px;"
							data-rule="评价:required;">${ msq[0].msqglDetailedPj }</textarea></td>
				</tr>
				<tr>
					<th width="150"><span class="warning">*</span>结论</th>
					<td><textarea id="msqglDetailedJl" name="msqglDetailedJl"
							style="width:600px;height:100px;" data-rule="结论:required;">${ msq[0].msqglDetailedJl }</textarea>
					</td>
				</tr>
				<tr>
					<th width="150"></th>
					<td><input type="submit" class="inputButton" value="确定" /></td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>

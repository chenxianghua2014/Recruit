<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<link type="text/css" rel="stylesheet"
	href="resources/jBox/Skins/Blue/jbox.css" />
<script type="text/javascript" src="resources/jBox/jquery-1.4.2.min.js"></script>
<script type="text/javascript"
	src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-zh-CN.js"></script>
<script type="text/javascript"
	src="resources/My97DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" href="resources/pagination/pagination.css"
	type="text/css"></link>
<script type="text/javascript"
	src="resources/pagination/jquery.pagination.js"></script>
<script type="text/javascript" src="js/Kcgl.js"></script>
</head>
<body>
	<input type="hidden" name="zzjgEjmm" id="zzjgEjmm"
		value="${loginSession.zzjgEjmm}" />
	<input type="hidden" name="zzjgSjdw" id="zzjgSjdw"
		value="${loginSession.zzjgSjdw}" />
	<div class="title">当前位置:考试管理>考场管理</div>
	<div class="editBlock">
		<table>
			<tbody>
				<tr>
					<td height="32" colspan="4" class="subtitle">查询</td>
				</tr>
				<tr>
					<td style="text-align: left;"><input name="button"
						type="button" class="inputButton" value="添加" onclick="addKcgl();" />
					</td>
					<td style="text-align: right;">考场名称：<input name="kcglName"
						type="text" class="inputText" id="kcglName" /> 日期：<input
						type="text" id="kcglKsrq" style="width: 80px;" name="kcglKsrq"
						onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" /> 开始时间：<input
						type="text" id="kcglKssjStart" style="width: 80px;"
						name="kcglKssjStart" onFocus="WdatePicker({dateFmt:'HH:mm:ss'})" />
						结束时间：<input type="text" id="kcglKssjEnd" style="width: 80px;"
						name="kcglKssjEnd" onFocus="WdatePicker({dateFmt:'HH:mm:ss'})" />
						状态：<select name="kcglKczt" id="kcglKczt">
							<option value="">--请选择--</option>
							<option value="开放">开放</option>
							<option value="关闭">关闭</option>
					</select> <input name="button" type="button" class="inputButton" value="查询"
						onclick="loadData()" />
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="dataGrid">
		<table>
			<caption>查询结果</caption>
			<thead>
				<tr>
					<th>考场名称</th>
					<th>考试地址</th>
					<th width="100">考试日期</th>
					<th>考试时间</th>
					<th width="80">考场容量</th>
					<th width="80">已报名</th>
					<th width="80">剩余容量</th>
					<th width="80">考场状态</th>
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
	<table boder="0" id="tbList">
	</table>
	<div id="Pagination" class="pagination">
</body>

</html>

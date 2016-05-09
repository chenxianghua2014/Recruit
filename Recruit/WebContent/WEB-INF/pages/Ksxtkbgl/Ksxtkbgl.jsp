<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
<script type="text/javascript"
	src="resources/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/validator.js"></script>
<script type="text/javascript" src="js/Ksxtkbgl.js"></script>
<script type="text/javascript">

</script>
</head>
<body>
	<div class="title">当前位置:考试管理>考试系统开闭管理</div>
	<div class="editBlock">
	</div>
	<div class="dataGrid">
		<table>
			<caption>统考管理</caption>
			<thead>
				<tr>
					<th>序号</th>
					<th>考试开始时间</th>
					<th>考试结束时间</th>
					<th width="60" class="alignCenter">修改</th>
					<th width="60" class="alignCenter">删除</th>
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
					id="count">0</span> 条记录
				</td>
			</tr>
		</table>
	</div>
		<form name="ff" id="ff">
			<input type="hidden" id="ksxtkbglId" name="ksxtkbglId"
				value="${ ksxtkbgl.ksxtkbglId }" />
				<table>
					<tr>
						<th><span class="warning">*</span>考试开始时间：</th>
						<td><input type="text" id="ksxtkbglStarttime" name="ksxtkbglStarttime" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
							value="${ ksxtkbgl.ksxtkbglStarttime }"  dataType="Require"  msg="必填"/></td>
			
						<th><span class="warning">*</span>考试结束时间：</th>
						<td><input type="text" id="ksxtkbglEndtime"name="ksxtkbglEndtime"onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
							value="${ ksxtkbgl.ksxtkbglEndtime }"  dataType="Require"  msg="必填"/></td>
						<td colspan="2">&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" class="inputButton" value="添加" onclick="KsxtkbglSava();"/>
						</td>
					</tr>
				</table>
		</form>
</body>
</html>

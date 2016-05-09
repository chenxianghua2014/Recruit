<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="resources/pagination/pagination.css"
	type="text/css"></link>
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="resources/pagination/jquery.pagination.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/Mbgl.js"></script>
</head>
<body>
	<div class="title">当前位置:模板管理>消息通知模板</div>
	<div class="editBlock">
		<table>
			<tbody>
				<tr>
					<td height="32" colspan="4" class="subtitle">消息通知模板查询</td>
				</tr>
				<tr>
					<td width="150"><input name="button" type="button"
						class="inputButton" value="添加" onclick="AddMbgl('消息通知')" /></td>
					<td style="text-align:right;">模板名称:<input name="keywords"
						type="text" class="inputText" id="keywords" />&nbsp;<input
						name="button" type="button" onclick="loadData();"
						class="inputButton" value="查询" /></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="dataGrid">
		<table>
			<caption>消息通知模板查询结果</caption>
			<thead>
				<tr>
					<th>模板名称</th>
					<th width="60" class="alignCenter">类型</th>
					<th width="60" class="alignCenter">内容</th>
					<th width="60" class="alignCenter">编辑</th>
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
<script type="text/javascript">
	var mbType = "测评通知,面试通知,面试圈通知,录用通知,淘汰通知";
</script>
</html>

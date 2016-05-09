<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/Mbgl.js"></script>
</head>
<body>
	<form action="WriteDoc" method="post">
		<div class="title">当前位置:模板管理>查看职位信息模板内容</div>
		<div class="editBlock">
			<table>
				<tbody>
					<tr>
						<td height="32" colspan="2" class="subtitle">查看职位信息模板内容</td>
					</tr>
					<tr>
						<th width="150">模板名称:</th>
						<td>${ mbgl.mbglName}</td>
					</tr>
					<tr>
						<th width="150">模板类型:</th>
						<td>${ mbgl.mbglType}</td>
					</tr>
					<tr>
						<th width="150">模板内容:</th>
						<td>${ mbgl.mbglContent }</td>
					</tr>
					<tr>
						<th width="150"></th>
						<td><input
							type="button" class="inputButton" value="返回"
							onclick="history.back()" /></td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
</body>
</html>

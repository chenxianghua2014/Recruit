<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>My JSP 'Grbw.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<link href="css/htgl/user/jygl.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="resources/jBox/Skins/Default/jbox.css"
	type="text/css"></link>
<link rel="stylesheet" href="resources/pagination/pagination.css"
	type="text/css"></link>
<script type="text/javascript" src="resources/jBox/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="resources/pagination/jquery.pagination.js"></script>
<script type="text/javascript" src="js/Jygl.js"></script>
<style type="text/css">
.editBlock a{background-color:#fff; border:1px solid #ccc; padding:5px; font-size:12px; color:#000; text-decoration: none;}
</style>
</head>
<body>
<div class="title">当前位置:内容管理及审核>禁言管理</div> 
<div class="jygl">
<a href="userJygl">用户禁言列表</a>
<a href="jygl">组织机构禁言列表</a>
</div>
	<div class="editBlock">
		<table>
			<tbody>
				<tr>
					<td height="32" colspan="4" class="subtitle">组织机构禁言查询</td>
				</tr>
				<tr>
					<td align="right">
						单位名称：<input name="zzjgDwmc" type="text" class="inputText" id="zzjgDwmc" />
						单位代码：<input name="zzjgDwdm" type="text" class="inputText" id="zzjgDwdm" />
						单位账号：<input name="zzjgDwzh" type="text" class="inputText" id="zzjgDwzh" />
						单位负责人：<input name="zzjgDwfzr" type="text" class="inputText" id="zzjgDwfzr" />
					<input name="button"
						type="button" class="inputButton" value="查询" onclick="loadData()" />
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="dataGrid">
		<table>
			<caption>
				组织机构禁言查询结果 
			</caption>
			<thead>
				<tr>
					<th>单位名称</th>
					<th>单位代码</th> 
					<th>单位账号</th>
					<th>单位负责人</th>
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

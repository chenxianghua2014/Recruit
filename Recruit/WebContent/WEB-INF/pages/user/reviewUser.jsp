<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
</head>

<body>
<div class="title">当前位置:单位详细 信息</div> 
<div class="editBlock">
	<form action="" method="post" name="form1" id="form1"
		autocomplete="off">
		<input type="hidden" name="zzjgId" value="${zzjg.zzjgId}" id="userId">
		<table style="width: 400px; margin-left:120px;">
			<tr>
				<th  width="150" align="right">单位名称：</th>
				<td>${zzjg.zzjgDwmc}</td>
			</tr>
			<tr>
				<th  width="150" align="right">单位代码：</th>
				<td>${zzjg.zzjgDwdm}</td>
			</tr>
			<tr>
				<th  width="150" align="right">单位简称：</th>
				<td>${zzjg.zzjgDwjc}</td>
			</tr>
			<tr>
				<th  width="150" align="right">用户账号：</th>
				<td>${zzjg.zzjgDwzh}</td>
			</tr>
			<tr>
				<th  width="150" align="right">负责人：</th>
				<td>${zzjg.zzjgDwfzr}</td>
			</tr>
			<tr>
				<th  width="150" align="right">联系电话：</th>
				<td>${zzjg.zzjgLxrdh}</td>
			</tr>
			<tr>
				<th  width="150" align="right">电子邮箱：</th>
				<td>${zzjg.zzjgLxremail}
					</td>
			</tr>
			<tr>
				<th  width="150" align="right">招聘联系人：</th>
				<td>${zzjg.zzjgZplxr}</td>
			</tr>
		</table>

	</form>
</div>
</body>
</html>

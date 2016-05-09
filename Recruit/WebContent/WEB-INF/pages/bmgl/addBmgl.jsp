<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>考试报名</title>
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="resources/jBox/Skins/Blue/jbox.css"
	type="text/css"></link>
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>
<script src="resources/jquery/jquery-migrate-1.1.1.js"></script>
<script type="text/javascript"
	src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-zh-CN.js"></script>
<script type="text/javascript" src="js/AddBmgl.js"></script>
</head>
<body>
	<div class="title">当前位置:报名管理>添加报名管理</div>
	<div class="editBlock">
		<table boder="0">
			<tr>
				<th width="150"><span class="warning">*</span>身份证号</th>
				<td><input type="text" id="bmglSfzh" class="inputText"
					name="bmglSfzh" dataType="IdCard" />&nbsp;<input
					type="button" class="inputButton" onclick="selJtjlk()" value="查询" /><input
					type="hidden" id="jtjlkId" name="jtjlkId" /> <input type="hidden"
					id="userId" name="userId" />
				</td>
			</tr>
		</table>
	</div>
	<div class="dataGrid">
		<table>
			<caption>适合的简历查询结果</caption>
			<thead>
				<tr>
					<th width="50">姓名</th>
					<th width="80">出生日期</th>
					<th>身份证号</th>
					<th width="40">性别</th>
					<th>毕业院校</th>
					<th>专业</th>
					<th width="40">学历</th>
					<th>职位</th>
					<th>报考岗位</th>
					<th width="120" class="alignCenter">安排测评岗位</th>
					<th width="60" class="alignCenter">操作</th>
				</tr>
			</thead>
			<tbody id="tbList"></tbody>
		</table>
	</div>
</body>
</html>

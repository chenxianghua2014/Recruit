<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="resources/pagination/pagination.css" type="text/css"></link>
<script type="text/javascript" src="resources/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="resources/pagination/jquery.pagination.js"></script>
<script type="text/javascript" src="js/Zpgl.js"></script>
<script type="text/javascript" src="js/common.js"></script>
</head>
<body>
	<div class="title">当前位置:招聘管理>简历</div>
	<div class="editBlock">
		<table>
			<tbody>
				<tr>
					<td height="32" colspan="4" class="subtitle"></td>
				</tr>
				<tr>
					<%--					<th width="150"></th>--%>
					<td style="text-align:right;">姓名:<input name="jtjlkName"
						type="text" class="inputText" id="jtjlkName" />&nbsp; 毕业院校:<input
						name="jtjlkByyx" type="text" class="inputText" id="jtjlkByyx" />&nbsp;
						专业:<input name="jtjlkZy" type="text" class="inputText"
						id="jtjlkZy" />&nbsp; 学历:<input name="jtjlkXl" type="text"
						class="inputText" id="jtjlkXl" />&nbsp; 职位:<input name="jtjlkZw"
						type="text" class="inputText" id="jtjlkZw" />&nbsp;<input
						name="button" type="button" onclick="loadData();"
						class="inputButton" value="查询" />
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="dataGrid">
		<table>
			<caption id="result"></caption>
			<thead>
				<tr>
					<th>姓名</th>
					<th>性别</th>
					<th>毕业院校</th>
					<th>专业</th>
					<th>学历</th>
					<th>职位</th>
					<th>简历状态</th>
					<th width="60" class="alignCenter">查看</th>
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
				<td colspan="2" class="subtitle">简历详情</td>
			</tr>
			<tbody>
				<tr>
					<td></td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>

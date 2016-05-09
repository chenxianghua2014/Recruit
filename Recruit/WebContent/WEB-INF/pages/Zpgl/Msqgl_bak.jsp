<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<link rel="stylesheet" href="skins/default/main.css" type="text/css" />
<link rel="stylesheet" href="resources/jBox/Skins/Blue/jbox.css"
	type="text/css"></link>
<link rel="stylesheet" href="resources/pagination/pagination.css"
	type="text/css"></link>
<script type="text/javascript" src="resources/jquery/jquery-1.9.1.js"></script>
<script src="resources/jquery/jquery-migrate-1.1.1.js"></script>
<script type="text/javascript"
	src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-zh-CN.js"></script>
<script type="text/javascript"
	src="resources/pagination/jquery.pagination.js"></script>
<script type="text/javascript" src="js/Msqgl.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<style type="text/css">
.inputText {
	width: 60px;
}
</style>
</head>
<body>
	<div class="title">当前位置:面试圈>面试圈管理</div>
	<div class="editBlock">
		<table>
			<tbody>
				<tr>
					<td height="32" colspan="4" class="subtitle">面试圈管理查询</td>
				</tr>
				<tr>
					<th width="150"></th>
					<td style="text-align:right;">姓名:<input name="jtjlkName"
						type="text" class="inputText" id="jtjlkName" />&nbsp; 毕业院校:<input
						name="jtjlkByyx" type="text" class="inputText" id="jtjlkByyx" />&nbsp;
						专业:<input name="jtjlkZy" type="text" class="inputText"
						id="jtjlkZy" />&nbsp; 学历:<input name="jtjlkXl" type="text"
						class="inputText" id="jtjlkXl" />&nbsp; 职位:<input name="jtjlkZw"
						type="text" class="inputText" id="jtjlkZw" />&nbsp; 岗位类型:<select
						name="jtjlkGwlb" id="jtjlkGwlb">
							<option value="">全部岗位</option>
							<option value="技术岗">技术岗</option>
							<option value="管理岗">管理岗</option>
					</select>&nbsp;<input name="button" type="button" onclick="loadData();"
						class="inputButton" value="查询" />
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="dataGrid">
		<table>
			<caption>面试圈管理查询结果</caption>
			<thead>
				<tr>
					<th>姓名</th>
					<th>性别</th>
					<th>毕业院校</th>
					<th>专业</th>
					<th>学历</th>
					<th>职位</th>
					<th>职位类别</th>
					<th width="60" class="alignCenter">面试状态</th>
					<th width="60" class="alignCenter">面试结果</th>
					<th width="60" class="alignCenter">面试官评论、结论</th>
					<th width="60" class="alignCenter">删除所有临时账号</th>
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
	<div class="dataGrid" id="divControll" style="display: none;">
		<table>
			<caption>面试结果</caption>
			<thead>
				<tr>
					<th>序号</th>
					<th>面试类别</th>
					<th>面试时间</th>
					<th>面试地点</th>
					<th>面试官</th>
					<th width="60" class="alignCenter">评价、结论</th>
					<th width="60" class="alignCenter">删除临时账号</th>
				</tr>
			</thead>
			<tbody id="tbList_Msq"></tbody>
			<tfoot>
				<tr>
					<td style="text-align: center;" colspan="7">筛选结果：<input
						name="button" type="button" onclick="excute('通过');"
						class="inputButton" value="通过" />&nbsp;<input name="button"
						type="button" onclick="excute('淘汰');" class="inputButton"
						value="淘汰" /><input type="hidden" id="Id" /></td>
				</tr>
			</tfoot>
		</table>
	</div>
</body>
</html>

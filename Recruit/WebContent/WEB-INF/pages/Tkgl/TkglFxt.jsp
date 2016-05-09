<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>
<link rel="stylesheet" href="resources/pagination/pagination.css"
	type="text/css"></link>
<script type="text/javascript" src="resources/jBox/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-zh-CN.js"></script>
<link type="text/css" rel="stylesheet" href="resources/jBox/Skins/Blue/jbox.css"/>
<script type="text/javascript"
	src="resources/pagination/jquery.pagination.js"></script>
<script type="text/javascript" src="js/TkglFxt.js"></script>
<script type="text/javascript">
function importTkgl() {
	window.location = "pldrTkglFxt";
}
</script>
</head>
<body>
<form name="ff" id="ff">
    <input type="hidden" name="zzjgZhmm" id="zzjgZhmm" value="${loginSession.zzjgEjmm}"/>
    <input type="hidden" name="zhmmfxt" id="zhmmfxt" value="${zhmmfxt}"/>
  </form>
	<div class="title">当前位置:题库卷库管理 > 分析题试题管理</div>
	<div class="editBlock" id="showWork">
		<table>
			<tbody>
				<tr>
					<td height="32" colspan="4" class="subtitle"> 分析题试题管理</td>
				</tr>
				<tr>
					<td style="text-align: left;"><input name="button" type="button"
						class="inputButton" value="添加" onclick="AddTkgl()" />
						<input name="button" type="button"
						class="inputButton" value="批量导入" onclick="importTkgl();" /></td>
					<td style="text-align: right;">
					编号：<input name="stbh" type="text" class="inputText" id="stbh" />
					类型：<input name="stlx" type="text" class="inputText" id="stlx" />
					题干：<input name="sttg" type="text" class="inputText" id="sttg" />
					<input name="button"
						type="button" class="inputButton" value="查询" onclick="loadData()" />
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
					<th width="95px;">编号</th>
					<th width="30px;">类型</th>
					<th>题干</th>
					<th width="30px;">答案</th>
					<th  width="60px;" class="alignCenter">修改</th>
					<th width="60px;" class="alignCenter">删除</th>
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
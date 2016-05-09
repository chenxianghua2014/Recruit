<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
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
<script type="text/javascript" src="js/Tkgl.js"></script>
<script type="text/javascript">
function importTkgl() {
	window.location = "pldrTkgl";
}
function importTkglThree() {
	window.location = "pldrTkglThree";
}
function importTkglOne() {
	window.location = "pldrTkglOne";
}
function importTkglZero() {
	window.location = "pldrTkglZero";
}
</script>
</head>
<body>
 <form name="ff" id="ff">
    <input type="hidden" name="zzjgZhmm" id="zzjgZhmm" value="${loginSession.zzjgEjmm}"/>
    <input type="hidden" name="zhmm" id="zhmm" value="${zhmm}"/>
  </form>
	<div class="title">当前位置:题库卷库管理 > 综合题库管理</div>
	<div class="editBlock" id="showWork">
		<table>
			<tbody>
				<tr>
					<td height="32" colspan="4" class="subtitle">综合题库管理</td>
				</tr>
				<tr>
					<td  style="text-align: left;"><input name="button" type="button" class="inputButton" value="添加" onclick="AddTkgl()" />
						<input name="button" type="button" class="inputButton" value="四选题导入" onclick="importTkgl();" />
						<input name="button" type="button" class="inputButton" value="三选题导入" onclick="importTkglThree();" />
						<input name="button" type="button" class="inputButton" value="一选题导入" onclick="importTkglOne();" />
						<input name="button" type="button" class="inputButton" value="零选题导入" onclick="importTkglZero();" />
					</td>
					<td style="text-align: right;">
						编号：<input type="text" name="tkglStbh" class="inputText" id="tkglStbh" />
						类型：<input type="text" name="tkglStlx" class="inputText" id="tkglStlx" />
						题干：<input type="text" name="tkglSttg" class="inputText" id="tkglSttg" />
					<input name="button" type="button" class="inputButton" value="查询" onclick="loadData()" />
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
					<th  width="60px;" class="alignCenter">编辑</th>
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
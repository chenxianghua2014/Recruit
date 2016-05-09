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
<base href="<%=basePath%>">

<title>My JSP 'luntan.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery-1.4.2.min.js"></script>
<script type="text/javascript"
	src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-zh-CN.js"></script>
<link type="text/css" rel="stylesheet"
	href="resources/jBox/Skins/Blue/jbox.css" />
<link rel="stylesheet" href="resources/pagination/pagination.css"
	type="text/css"></link>
<script type="text/javascript"
	src="resources/pagination/jquery.pagination.js"></script>
<script type="text/javascript" src="js/Cpfygl.js"></script>
<script type="text/javascript">
	function exportCpfygl() {
		if (confirm("您确定要导出测评给用（会员）么?")) {
			$.jBox.tip("正在生成测评费用（会员），请耐心等待！", 'loading');
			window.location = "exportCpfygl";
			window.setTimeout(function() {
				$.jBox.tip('已完成。', 'success');
			}, 2000);
		}
	}
</script>
</head>
</head>

<body>
	<div class="title">当前位置:成绩管理>费用管理（会员）</div>
	<div class="editBlock">
		<table>
			<tbody>
				<tr>
					<td height="32" colspan="4" class="subtitle">查询</td>
				</tr>
				<tr>
					<c:if
						test="${loginSession.zzjgSjdw eq null || loginSession.zzjgSjdw eq ''}">
						<td style="text-align: left;"><input name="button"
							type="button" class="inputButton" value="导出费用管理（会员）"
							onclick="exportCpfygl();" /></td>
					</c:if>
					<td align="right">费用余额小于<select name="fyye" id="fyye">
							<option value="">--请选择--</option>
							<option value="0">0</option>
							<option value="100">100</option>
							<option value="500">500</option>
							<option value="1000">1000</option>
					</select> 单位名称：<input name="memberName" type="text" class="inputText"
						id="memberName" /> 单位代码：<input name="memberIdcard" type="text"
						class="inputText" id="memberIdcard" /> 会员等级：<input
						name="memberHydj" type="text" class="inputText" id="memberHydj" />

						<input name="button" type="button" class="inputButton" value="查询"
						onclick="loadData()" /></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="dataGrid">
		<table>
			<caption>
				查询结果
			</caption>
			<thead>
				<tr>
					<th width="80">单位代码</th>
					<th>单位名称</th>
					<th width="80">会员等级</th>
					<th width="120">应缴纳费用（元）</th>
					<th width="100">已缴费用（元）</th>
					<th width="80">测评次数</th>
					<th width="80">剩余次数</th>
					<th width="100">费用余额（元）</th>
					<th width="60" class="alignCenter">修改</th>
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
	<table boder="0" id="tbList">
	</table>
	<div id="Pagination" class="pagination">
</body>

</html>

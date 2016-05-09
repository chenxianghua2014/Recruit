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
<script type="text/javascript" src="resources/jBox/jquery-1.4.2.min.js"></script>
<script type="text/javascript"
	src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-zh-CN.js"></script>
<link type="text/css" rel="stylesheet"
	href="resources/jBox/Skins/Blue/jbox.css" />
<title></title>
<link rel="stylesheet" href="resources/pagination/pagination.css"
	type="text/css"></link>
<script type="text/javascript"
	src="resources/pagination/jquery.pagination.js"></script>
<script type="text/javascript" src="js/Fhyfy.js"></script>
<style type="text/css">
.editBlock a {
	background-color: #fff;
	border: 1px solid #ccc;
	padding: 5px;
	font-size: 12px;
	color: #000;
	text-decoration: none;
}
</style>
<script type="text/javascript">
	function exportFhyfy() {
		if (confirm("您确定要导出测评给用（非会员）么?")) {
			$.jBox.tip("正在生成测评费用（非会员），请耐心等待！", 'loading');
			window.location = "exportFhyfy";
			window.setTimeout(function() {
				$.jBox.tip('已完成。', 'success');
			}, 2000);
		}
	}
</script>
</head>
<body>
	<div class="title">当前位置:成绩管理 > 费用管理（非会员）</div>
	<div class="editBlock">
		<table>
			<tbody>
				<tr>
					<td height="32" colspan="4" class="subtitle">组织机构禁言查询</td>
				</tr>
				<tr>
					<c:if
						test="${loginSession.zzjgSjdw eq null || loginSession.zzjgSjdw eq ''}">
						<td style="text-align: left;"><input name="button"
							type="button" class="inputButton" value="导出费用管理（非会员）"
							onclick="exportFhyfy();" /></td>
					</c:if>
					<td align="right">费用余额小于<select name="fyye" id="fyye">
							<option value="">--请选择--</option>
							<option value="0">0</option>
							<option value="100">100</option>
							<option value="500">500</option>
							<option value="1000">1000</option>
					</select> 单位代码：<input name="zzjgDwdm" type="text" class="inputText"
						id="zzjgDwdm" /> 单位名称：<input name="zzjgDwmc" type="text"
						class="inputText" id="zzjgDwmc" /> <input name="button"
						type="button" class="inputButton" value="查询" onclick="loadData()" />
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="dataGrid">
		<table>
			<thead>
				<tr>
					<th width="80">单位代码</th>
					<th>单位名称</th>
					<th width="120">应缴纳费用（元）</th>
					<th width="100">已缴费用（元）</th>
					<th width="80">测评次数</th>
					<th width="100">费用余额（元）</th>
					<th width="60" class="alignCenter">操作</th>
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
</body>
</html>

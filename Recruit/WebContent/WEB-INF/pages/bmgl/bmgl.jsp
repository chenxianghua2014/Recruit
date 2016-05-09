<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="resources/My97DatePicker/WdatePicker.js"></script>
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
<script type="text/javascript" src="js/Bmgl.js"></script>
<style type="text/css">
.inputText {
	width: 100px;
}
</style>
<script type="text/javascript">
function exportBmgl() {
	if (confirm("您确定要导出名单么?")) {
		$.jBox.tip("正在生成考生名单，请耐心等待！", 'loading');
		window.setTimeout(function() {
			window.location = "exportBmgl";
			$.jBox.tip('已完成。', 'success');
		}, 2000);

	}
}

</script>
</head>
<body>
	<div class="title">当前位置:考试管理>报名管理</div>
	<div class="editBlock">
		<table>
			<tbody>
				<tr>
					<td height="32" colspan="4" class="subtitle">查询</td>
				</tr>
				<tr rowspan="2">
					<td style="text-align: left"><input name="button" type="button"
						class="inputButton" value="添加" onclick="addBmgl()" />
						&nbsp;&nbsp;<input name="button" type="button"
							class="inputButton" value="导出名单" onclick="exportBmgl();" />
					</td>
					<td style="text-align:right;">考生姓名：<input name="bmglKsxm" type="text"
						class="inputText" id="bmglKsxm" /> 身份证号：<input name="bmglSfzh"
						type="text" class="inputText" id="bmglSfzh" /> 毕业院校：<input
						name="bmglByxx" type="text" class="inputText" id="bmglByxx" />
						报考单位：<input name="bmglBkdw" type="text" class="inputText"
						id="bmglBkdw" /> <br/><br/> 
						考场名称：<select name="bmglKcglId" id="bmglKcglId">
							<option value=""></option>
							<c:forEach var="item" items="${kcgls}" varStatus="status">
								<option value="${item.kcglId}">${item.kcglName}</option>
							</c:forEach>
					</select> 考试日期：<input name="bmglKsdrq"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" type="text"
						class="inputText" id="bmglKsdrq" /> 报考岗位：<select name="bmglBkgw"
						id="bmglBkgw">
							<option value=""></option>
							<option value="技术岗">技术岗</option>
							<option value="管理岗">管理岗</option>
							<option value="个性测试">个性测试</option>
					</select>  </td><td style="text-align: center;"><input name="button" type="button" class="inputButton" value="查询"
						onclick="loadData()" />
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
					<th width="40">序号</th>
					<th width="68">考生姓名</th>
					<th width="150">身份证号</th>
					<th>报考单位</th>
					<th width="68">报考岗位</th>
					<th>毕业学校</th>
					<th>考场名称</th>
					<th width="110">考试日期</th>
					<th width="140">考试时间</th>
					<th width="60">是否确认</th>
					<th width="100">联系电话</th>
					<th width="60" class="alignCenter">删除</th>
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
	<table boder="0" id="tbList">
	</table>
	<div id="Pagination" class="pagination"></div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<title></title>
<link rel="stylesheet" href="resources/jBox/Skins/Blue/jbox.css" type="text/css"></link>
<link rel="stylesheet" href="skins/default/main.css" type="text/css" />
<link rel="stylesheet" href="resources/pagination/pagination.css" type="text/css"></link>
<script type="text/javascript" src="resources/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="resources/jquery/jquery-migrate-1.1.1.js"></script>
<script type="text/javascript" src="resources/pagination/jquery.pagination.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-zh-CN.js"></script>
<script type="text/javascript" src="js/Ksxcgl.js"></script>
</head>
<body>
	<div class="title">当前位置:考试管理>考试现场管理</div>
	<div class="editBlock">
		<table>
			<tbody>
				<tr>
					<td height="32" colspan="4" class="subtitle">考试现场管理</td>
				</tr>
				<tr>
					<td style="text-align: right;">姓名:<input name="ksxcglName"
						type="text" class="inputText" id="ksxcglName" /> 身份证号:<input
						name="ksxcglIdcard" type="text" class="inputText"
						id="ksxcglIdcard" /> 报考单位:<input name="ksxcglBkdw" type="text"
						class="inputText" id="ksxcglBkdw" /> 报考岗位:<select
						name="ksxcglKkslx" id="ksxcglKkslx">
							<option value="">--请选择--</option>
							<option value="技术岗">技术岗</option>
							<option value="管理岗">管理岗</option>
					</select> 考场名称:<select name="ksxcglKc" id="ksxcglKc">
							<option value="">--请选择--</option>
							<c:forEach var="map" items="${kcglList}">
								<option value="${map.kcglName }">${map.kcglName }</option>
							</c:forEach>
					</select> <input name="button" type="button" class="inputButton" value="查询"
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
					<th>考生姓名</th>
					<th>身份证号</th>
					<th>报考单位</th>
					<th>可考试类型</th>
					<th>考场名称</th>
					<th>违纪情况</th>
					<th>特殊情况</th>
					<th width="60" class="alignCenter">修改</th>
					<th width="60" class="alignCenter">删除</th>
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
					id="count">0</span> 条记录
				</td>
			</tr>
		</table>
	</div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>社区-兴趣圈子列表</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="resources/jBox/Skins/Default/jbox.css"
	type="text/css"></link>
<link rel="stylesheet" href="resources/pagination/pagination.css"
	type="text/css"></link>
<script type="text/javascript" src="resources/jBox/jquery-1.4.2.min.js"></script>
<script type="text/javascript"
	src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript"
	src="resources/pagination/jquery.pagination.js"></script>
<script type="text/javascript" src="js/Xqqz.js"></script>
<style type="text/css">
.editBlock a{background-color:#fff; border:1px solid #ccc; padding:5px; font-size:12px; color:#000; text-decoration: none;}
</style>
</head>
<body >
<div class="title">当前位置:内容管理及审核>兴趣圈子</div> 
	<div class="editBlock">
		<table>
			<tbody>
			<tr>
					<td height="32" colspan="4" class="subtitle"><c:forEach items="${ltbqList}" var="map">
					<a href="javascript:void(0);" onclick="loadData('${map.ltbqName}','${map.ltbqId}')">${map.ltbqName}</a>
					</c:forEach></td>
				</tr>
				<tr>
					<td height="32" colspan="4" class="subtitle">圈子查询</td>
				</tr>
				<tr>
				<td style="text-align: left;"><input name="button"
						type="button" class="inputButton" value="添加" onclick="AddXqqz()"/>
				</td>
					<td align="right">标题：<input name="xqqzTitle" type="text" class="inputText"
						id="xqqzTitle" />
						类型：<select id="xqqzType" name="xqqzType">
						<option value="" >--请选择--</option>
						<c:forEach items="${xqqzLxglList}" var="map">
							<option value="${map.xqqzlxglId}" >${map.xqqzlxglLxmc}</option>
						</c:forEach>
						</select>
					<input name="button"
						type="button" class="inputButton" value="查询" onclick="loadData();" />
					</td>
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
					<th>兴趣标题</th>
					<th>发布人</th>
					<th>兴趣类型</th>
					<th width="60" class="alignCenter">管理评论</th>
					<th width="60" class="alignCenter">移动分组</th>
					<th width="60" class="alignCenter">修改</th>
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
</body>
</html>

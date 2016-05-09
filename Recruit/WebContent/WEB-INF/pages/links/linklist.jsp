<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="resources/jquery/jquery-1.9.1.min.js"></script>
</head>
<body>
	<div class="title">当前位置:友情链接>友情链接</div>
	<div class="editBlock">
		<table>
			<tbody>
				<tr>
					<td height="32" colspan="4" class="subtitle">友情链接查询</td>
				</tr>
				<tr>
					<td width="150"><input name="button" type="button" class="inputButton" value="添加" onclick="javascript:location.href='tolinkadd';" />
					</td>
					<td style="text-align:right;">&nbsp;
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="dataGrid">
		<table>
			<caption>友情链接查询结果</caption>
			<thead>
					<tr>
						<th width="150" class="alignCenter">链接栏目</th>
						<th width="300" class="alignCenter">链接名称</th>
						<th class="alignCenter">链接地址</th>
						<th width="80" class="alignCenter" class="alignCenter">删除</th>
					</tr>
			</thead>
			<tbody id="tbList">
				<c:forEach items="${ll}" var="map" varStatus="status">
					<tr>
						<td class="alignCenter">${map.linkCat}</td>
						<td class="alignCenter">${map.linkName}</td>
						<td class="alignCenter">${map.linkl}</td>
						<td class="alignCenter"><input name="button" type="button" class="inputButton" value="删除" onclick="javascript:if(confirm('确认要删除?')){location.href='linkdel?linkid=${map.linkId}';}"/></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>
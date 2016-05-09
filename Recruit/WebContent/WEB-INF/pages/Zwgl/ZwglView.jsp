<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="editBlock">
		<table>
			<tr>
				<td colspan="2" class="subtitle">岗位信息</td>
			</tr>
			<tbody>
				<tr>
					<th width="150"><span class="warning"></span>职位名称</th>
					<td>${ position.positionName }</td>
				</tr>
				<tr>
					<th><span class="warning"></span>职位类别</th>
					<td>${position.positionType}</td>
				</tr>
				<tr>
					<th><span class="warning"></span>工作地点</th>
					<td>${position.positionWorkaddress}</td>
				</tr>
				<tr>
					<th><span class="warning"></span>学历要求</th>
					<td>${position.positionXlyq}</td>
				</tr>
				<tr>
					<th><span class="warning"></span>专业类别</th>
					<td>${position.positionZylb}</td>
				</tr>
				<tr>
					<th width="150"><span class="warning"></span>招聘专业</th>
					<td>${position.positionZpzy}</td>
				</tr>
				<tr>
					<th width="150"><span class="warning"></span>需求人数</th>
					<td>${ position.positionXqrs }人</td>
				</tr>
				<tr>
					<th><span class="warning"></span>职位状态</th>
					<td>${position.positionStatus}</td>
				</tr>
				<tr>
					<th>职位职责</th>
					<td><textarea id="positionDuty"
							style="width:400px;height:100px;" name="positionDuty">${ position.positionDuty }</textarea>
					</td>
				</tr>
				<tr>
					<th>职位要求</th>
					<td><textarea id="positionRequierd"
							style="width:400px;height:100px;" name="positionRequierd">${ position.positionRequierd }</textarea>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>

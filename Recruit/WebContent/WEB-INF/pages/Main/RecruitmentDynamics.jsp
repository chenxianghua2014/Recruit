<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>中国航天科工集团公司人才招聘平台</title>
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" href="css/style.css" rel="stylesheet" />
<link type="text/css" href="resources/jBox/Skins/Blue/jbox.css"
	rel="stylesheet"></link>
<link rel="stylesheet" href="resources/pagination/pagination.css"
	type="text/css"></link>
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="resources/jquery/jquery-migrate-1.1.1.js"></script>
<script type="text/javascript"
	src="resources/pagination/jquery.pagination.js"></script>
<script type="text/javascript"
	src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-zh-CN.js"></script>
<script type="text/javascript" src="js/RecruitmentDynamics.js"></script>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<div class="wrap clr">
		<div class="menu">
			<div class="menuTop">
				<img src="images/title07.png" />
			</div>
			<ul>
				<li><a href="InstituteOf?id=<%=request.getParameter("id")%>">单位简介</a>
				</li>
				<li><a
					href="RecruitmentDynamics?id=<%=request.getParameter("id")%>"
					style="color:#0046ae;">招聘动态</a>
				</li>
			</ul>
		</div>
		<div class="content">
			<div class="conTop">
				<span>您的当前位置：首页 >> 单位概况 >> 招聘动态</span> <img src="images/tag04.png" />&nbsp;
				<strong>招聘动态</strong> RecruitmentDynamics
			</div>
			<div
				style="width: 380px;height: 0px;text-align:right;margin:0px 420px;position: absolute;z-index: 999;">
				<select id="selSubOrg" name="selSubOrg">
					<c:forEach items="${ mv.model['zzjgs']}" var="map"
						varStatus="status">
						<c:choose>
							<c:when test="${map.zzjgId eq param.id }">
								<option value="${map.zzjgId }" selected="selected">${map.zzjgDwmc
									}</option>
							</c:when>
							<c:otherwise>
								<option value="${map.zzjgId }">${map.zzjgDwmc }</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select> <input type="button" value="跳转"
					onclick="document.location.href='RecruitmentDynamics?id='+$('#selSubOrg').val();" />
			</div>
			<div class="Possearch cls">
				<div class="seacriteria">
					关键字: <input name="zpnr" id="zpnr" type="text"
						placeholder="--------------关键字-------------" />
				</div>
				<div class="seabtn">
					<a href="javascript:void(0);" onclick="loadData()">搜索</a>
				</div>
			</div>
			<div class="result">
				<div class="resultTitle">
					<img src="images/tag05.png" />搜索结果
				</div>
				<table width="800px">
					<thead>
						<tr>
							<td width="600">资讯名称</td>
							<td width="100">发布单位</td>
							<td width="100">发布时间</td>
						</tr>
					</thead>
					<tbody id="tbList"></tbody>
				</table>
				<table width="800px">
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
		</div>
	</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>
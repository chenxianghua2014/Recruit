<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>个人微博</title>
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon"/> 
<link type="text/css" href="css/jlsq/style.css" rel="stylesheet" />
<link rel="stylesheet" href="resources/pagination/pagination.css"
	type="text/css"></link>
<script type="text/javascript" src="resources/jBox/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-zh-CN.js"></script>
<link type="text/css" rel="stylesheet" href="resources/jBox/Skins/Blue/jbox.css"/>
<script type="text/javascript"
	src="resources/pagination/jquery.pagination.js"></script>
<script type="text/javascript" src="js/qtXqqz.js"></script>
</head>
<body>
	<%@ include file="../Main/Header.jsp"%>
	<div class="wrap clr">
		<div class="menu">
			<div class="menuTop">
				<img src="images/cpgl/title06.png" />
			</div>
			<ul>
				<li><a  href="javascript:void(0);" onclick="javascript:window.location='jlsq'">科工论坛</a></li>
	            <li><a  href="javascript:void(0);" onclick="javascript:window.location='zyty'">经验博文</a></li>
	            <li><a  style="color:#0046ae;" href="javascript:void(0);" onclick="javascript:window.location='qtXqqz'">兴趣圈子</a></li>

			</ul>
		</div>
		<div class="content">
			<div class="conTop">
			<span>您的当前位置：首页 >> 交流社区>>兴趣圈子</span>
				<img src="images/tag04.png" />&nbsp; <strong>兴趣圈子</strong>Interest group
				Introduction
			</div>
			<div class="main">
				<div class="Tab">
					<span>标签:</span> 
					<c:forEach items="${ltbqList}" var="map">
						<a href="javascript:void(0);" onclick="loadData('${map.ltbqName}','${map.ltbqId}')">${map.ltbqName}</a>
					</c:forEach>
				</div>
			</div>
			<div class="Tabbtn">
					<a href="javascript:void(0);" onclick="javascript:window.location='qtxqqzAdd'">发文</a>
					<div class="Tabsearch1">
					标题：<input name="xqqzTitle" type="text" class="inputText"id="xqqzTitle" />
					作者：<input name="xqqzFbr" type="text" class="inputText"id="xqqzFbr" />
					类型：<select id="xqqzType" name="xqqzType">
						<option value="" >--请选择--</option>
						<c:forEach items="${xqqzLxglList}" var="map">
							<option value="${map.xqqzlxglId}" >${map.xqqzlxglLxmc}</option>
						</c:forEach>
						</select>
						<input name="button" type="button" class="inputButton" value="查询" onclick="loadData('','');" />
					</div> 
			</div>
			<div>
				<hr/>
			</div>
			<div class="dataGrid">
				<table>
					<tbody id="tbList"></tbody>
				</table>
				<table width="800px">
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
		</div>
	</div>
<%@ include file="../Main/Footer.jsp"%>
</body>
</html>


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
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>
<script src="resources/jquery/jquery-migrate-1.1.1.js"></script>
<script type="text/javascript"
	src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-zh-CN.js"></script>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<div class="wrap clr">
		<div class="menu">
			<div class="menuTop">
				<img src="images/title07.png" />
			</div>
			<ul>
				<li><a href="InstituteOf?id=<%=request.getParameter("id")%>"
					style="color:#0046ae;">单位简介</a></li>
				<li><a
					href="RecruitmentDynamics?id=<%=request.getParameter("id")%>">招聘动态</a>
				</li>
			</ul>
		</div>
		<div class="content">
			<div class="conTop">
				<span>您的当前位置：首页 >> 单位概况 >> 单位简介</span> <img src="images/tag04.png" />&nbsp;
				<strong>单位简介</strong> About Us
			</div>
			<div style="width: 380px;height: 22px;text-align:right;float:right;">
				<select id="selSubOrg" name="selSubOrg" onchange="javascript:Go();">
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
				</select>
			</div>
			<div class="main">
				<br>${ mv.model['xcxxs'][0].xcxxContent}
					<div
						style="width: 100px;height:24px;font-size:12pt;text-align:center; float:right; background-color: #629AD7;cursor: pointer;">
						<a style="color: white;text-decoration: none;"
							href="javascript:void(0);" onclick="JoinUs()">加入我们</a>
					</div>
			</div>
		</div>
	</div>
	<%@ include file="Footer.jsp"%>
</body>
<script type="text/javascript">
	function Go() {
		document.location.href = "InstituteOf?id=" + $('#selSubOrg').val();
	}
	function JoinUs() {
		window.location.href = "SearchPosition?zpdw="
				+ escape($("#selSubOrg option:selected").text());
	}
	function getQueryString(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null)
			return unescape(r[2]);
		return null;
	}
</script>
</html>
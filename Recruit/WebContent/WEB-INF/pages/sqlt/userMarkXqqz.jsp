<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<title>社区-读取用户兴趣圈子记录</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link href="css/htgl/user/user_style.css" rel="stylesheet"
	type="text/css" />
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />

</head>

<body>
	<div class="title">当前位置:内容管理及审核>调取用户信息</div>
	<div class="subtitle_pl" style="margin-bottom:20px;">
		<div class="title">
			<a href="queryMarkBbs">科工论坛</a> <a href="queryMarkArticle">个人博文</a> <a
				href="queryMarkXqqz">兴趣圈子</a>
		</div>
		<div class="pl">
			<c:if test="${empty xqqzList}">
				<span style="color:#CD4F39;">此用户在此没有操作记录。</span>
			</c:if>
			<c:forEach items="${xqqzList}" var="map">
				<div class="blog_article">
					<span class="blog_name">${map.xqqzFbr }:</span>${map.xqqzTitle}</div>
				<div class="blog_writer">
					<span>${map.fbsj}</span>
				</div>
				<div class="blog_con">
					<c:if test="${map.nr eq ''}">
						<p style="color: #B5B5B5;">您没有添加内容</p>
					</c:if>
					<c:if test="${map.nr ne ''}">
						<p>${map.nr}</p>

					</c:if>
				</div>
				<div class="blog_comment">
					<a href="xqqzRview?xqqzId=${map.xqqzId}">查看详细</a> <a
						href="delXqqz1?xqqzId=${map.xqqzId}">删除</a>
				</div>
				<div style="border-bottom:1px dashed #ccc;"></div>
			</c:forEach>
		</div>
	</div>
</body>
</html>

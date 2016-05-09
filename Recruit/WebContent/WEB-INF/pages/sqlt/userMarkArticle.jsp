<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>社区-读取用户博文记录</title>
<link href="css/htgl/user/user_style.css" rel="stylesheet"
	type="text/css" />
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
	
</script>
</head>

<body>
	<div class="title">当前位置:内容管理及审核>调取用户信息</div>
	<div class="subtitle_pl" style="margin-bottom:20px;">
		<div class="title">
			<a href="queryMarkBbs">科工论坛</a> <a href="queryMarkArticle">个人博文</a> <a
				href="queryMarkXqqz">兴趣圈子</a>
		</div>
		<div class="pl">
			<c:if test="${empty articleList}">
				<span style="color:#CD4F39;">此用户在此没有操作记录。</span>
			</c:if>
			<c:forEach items="${articleList}" var="map">
				<div class="blog_article">
					<span class="blog_name">${map.articleFbr }:</span>${map.articleTitle}</div>
				<div class="blog_writer">
					作者：<span>${map.articleAuthor}</span> 时间：<span>${map.fbsj}</span>
				</div>
				<div class="blog_con">
					<c:if test="${map.content eq ''}">
						<p style="color: #B5B5B5;">您没有添加内容</p>
					</c:if>
					<c:if test="${map.content ne ''}">
						<p>${map.content}</p>

					</c:if>
				</div>
				<div class="blog_comment">
					<a href="articleRview?articleId=${map.articleId}">查看详细</a> <a
						href="delArticle1?articleId=${map.articleId}">删除</a>
				</div>
				<div style="border-bottom:1px dashed #ccc;"></div>
			</c:forEach>
		</div>
	</div>

</body>
</html>

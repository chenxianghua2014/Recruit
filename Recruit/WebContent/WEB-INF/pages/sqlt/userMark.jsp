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

<title>社区-读取用户论坛记录</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<link href="css/htgl/user/user_style.css" rel="stylesheet" type="text/css" />

</head>

<body>
<div class="title">当前位置:内容管理及审核>调取用户信息</div> 
	<div class="subtitle_pl">
		<div class="title">
		<a href="queryMarkBbs">科工论坛</a>
		<a href="queryMarkArticle">个人博文</a>
		<a href="queryMarkXqqz">兴趣圈子</a>
		</div>
        <!--发表内容-->
        <div class="pl">
        	<div class="comments-item-bd">
        	<c:if test="${empty bbsList}" >
        		<span style="color:#CD4F39;">此用户在此没有操作记录。</span>
        	</c:if>
        	<c:forEach items="${bbsList}" var="bbs">
				<div class="name"><span class="name_n">${bbs.bbsFbr }:</span>${bbs.bbsNr}</div> 
				 <div class="time">${bbs.fbsj}<a href="delBbs1?bbsId=${bbs.bbsId}">删除</a></div>
				 <div class="comments-list">
					<c:forEach items="${reviewList}" var="map">
							<c:if test="${bbs.bbsId eq map.bbsId}">
						<ul>
							<li>
							<div class="name"><span class="name_n">${map. reviewPlr}:</span>${map. reviewNr}</div> 
							<div class="con"></div>  
							<div class="time">${map. plsj}<a href="delReview1?reviewId=${map.reviewId}">删除</a></div>
							</li>
						</ul>
							</c:if>
						</c:forEach>
				</div>
			<div style="border-bottom:1px dashed #ccc;"></div>
			</c:forEach>
  </div>
  </div>
  </div>     
      
</body>
</html>

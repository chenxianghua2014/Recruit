<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>交流社区</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon"/> 
<script type="text/javascript" src="resources/jBox/jquery-1.4.2.min.js"></script>
<script type="text/javascript"
	src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-zh-CN.js"></script>
<link type="text/css" rel="stylesheet"
	href="resources/jBox/Skins/Blue/jbox.css" />
<link type="text/css" href="css/jlsq/style.css" rel="stylesheet" />
</head>

<body>
	<%@ include file="../Main/Header.jsp"%>
	<div class="wrap clr">
		<div class="menu">
			<div class="menuTop">
				<img src="images/cpgl/title06.png" />
			</div>
			<ul>
				<li><a style="color:#0046ae;" href="javascript:void(0);"
					onclick="javascript:window.location='jlsq'">科工论坛</a>
				</li>
				<li><a href="javascript:void(0);"
					onclick="javascript:window.location='zyty'">经验博文</a>
				</li>
				<li><a href="javascript:void(0);"
					onclick="javascript:window.location='qtXqqz'">兴趣圈子</a>
				</li>
			</ul>
		</div>
		<div class="content">
			<div class="conTop">
			<span>您的当前位置：首页 >> 交流社区 >> 科工论坛</span>
				<img src="images/cpgl/tag04.png" />&nbsp; <strong>科工论坛</strong>BBS
				
			</div>
			<div class="main">
				<div class="Tab">
					<span>标签:</span>
					<c:forEach items="${ltbqList}" var="map">
						<a href="javascript:void(0);"
							onclick="javascript:window.location='queryByBq?ltbqName=${map.ltbqName}&ltbqId=${map.ltbqId}'"
							title="${map.ltbqName}">${map.ltbqName}</a>
					</c:forEach>
				</div>
				<c:forEach items="${tlqList}" var="map">
					<div class="title">
						<img src="images/cpgl/tag05.png" /> ${map.tlqName}
					</div>
					<div class="bbs clr">
						<ul>
							<c:forEach items="${ltbkList}" var="bkmap">
								<c:if test="${map.tlqId eq bkmap.tlqId}">
									<li>
										<div class="bbsImg">
											<a href="javascript:void(0);"
												onclick="javascript:window.location='queryBbsByLtbkId?ltbkId=${bkmap.ltbkId}'"><img
												src="images/cpgl/qipao.png" />
											</a>
										</div>
										<div class="bbsUpdate">
											<p class="bbstitle">
												<a href="javascript:void(0);"
													onclick="javascript:window.location='queryBbsByLtbkId?ltbkId=${bkmap.ltbkId}'"
													title="${bkmap.ltbkName}">${bkmap.ltbkName}</a>
											</p>
											<p class="bbstime">更新时间：${bkmap.addTime}</p>
										</div></li>
								</c:if>
							</c:forEach>
						</ul>
					</div>
				</c:forEach>
			</div>

		</div>

	</div>
	<%@ include file="../Main/Footer.jsp"%>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>中国航天科工集团公司人才招聘平台</title>
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" href="css/style.css" rel="stylesheet" />
<link rel="stylesheet" href="resources/pagination/pagination.css" type="text/css"></link>
<link type="text/css" href="resources/jBox/Skins/Blue/jbox.css" rel="stylesheet"></link>
<script type="text/javascript" src="resources/jquery/jquery-1.9.1.min.js"></script>
<script src="resources/jquery/jquery-migrate-1.1.1.js"></script>
<script type="text/javascript" src="resources/pagination/jquery.pagination.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-zh-CN.js"></script>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<div class="wrap clr">
		<div class="menu">
			<div class="menuTop">
				<img src="images/title03.png" />
			</div>
			<ul>
				<li><a href="SearchEnterprise" >招聘日程</a></li>
				<li><a href="SearchPosition" >招聘职位</a></li>
				<li><a href="MyRecruit" >我的求职</a></li>
			</ul>
		</div>
		<div class="content">
			<div class="conTop">
				<span>您的当前位置：首页 >> 校园招聘 >> 我的求职 >> 我的申请</span> <img
					src="images/tag04.png" />&nbsp; <strong>我的求职</strong>My Recruit
			</div>
			<div class="result">
				<div class="Tabbg">
					<a href="MyRecruit">我的简历</a><a href="MyApplication">我的申请</a>
					<a href="MyCollection">我的收藏</a><a class="Tabactive" href="Message">消息中心</a>
				</div>
				<div class="resultTitle">
					<img src="images/tag05.png" />我的消息记录
				</div>
				<div class="subtitle_pl">
						<c:choose>
							<c:when test="${userLoginSession eq null}">
								<script type="text/javascript">
									$.jBox.open("iframe:LoginFrm", "用户登录", 280,
											200, {
												buttons : {}
											});
								</script>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${mv.model['xxtzs'].size() == 0}">
										<li>暂无消息……</li>
									</c:when>
									<c:otherwise>
										<c:forEach items="${ mv.model['xxtzs']}" var="map"
											varStatus="status">
											<li><c:if test="${ map.xxtzIsread eq 1}">
													<strong><a
														style="text-decoration:none;color: black;"
														href="MessageDetails?id=${ map.xxtzId}">${
															map.xxtzType}</a>
														<span><fmt:formatDate value="${ map.xxtzAddtime}"
																type="date" /> </span> </strong>
												</c:if> <c:if test="${ map.xxtzIsread eq 0}">
													<a style="text-decoration:none;color: black;"
														href="MessageDetails?id=${ map.xxtzId}">${
														map.xxtzType}</a>
													<span><fmt:formatDate value="${ map.xxtzAddtime}"
															type="date" /> </span>
												</c:if></li>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
			</div>
		</div>
	</div>
	</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>
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
				<img src="images/title03.png" />
			</div>
			<ul>
				<li><a href="SearchEnterprise">招聘日程</a>
				</li>
				<li><a href="SearchPosition">招聘职位</a>
				</li>
				<li><a href="MyRecruit">我的求职</a>
				</li>
				<li><a href="Message" style="color:#0046ae;">消息中心</a>
				</li>
			</ul>
		</div>
		<div class="content">
			<div class="conTop">
				<span>您的当前位置：首页 >> 校园招聘>> 消息中心</span> <img src="images/tag04.png" />&nbsp;
				<strong>消息中心</strong>Message Notification
			</div>
			<div class="main">
				<br />
				<c:choose>
					<c:when test="${userLoginSession eq null}">
						<script type="text/javascript">
							$.jBox.open("iframe:LoginFrm", "用户登录", 280, 200, {
								buttons : {}
							});
						</script>
					</c:when>
					<c:otherwise>
						<table style="width: 100%;">
							<tr>
								<th width="150">通知企业</th>
								<td>${ mv.model['zzjg'] }</td>
							</tr>
							<tr>
								<th>通知类型</th>
								<td>${ mv.model['xxtz'].xxtzType }</td>
							</tr>
							<tr>
								<th>通知时间</th>
								<td><fmt:formatDate
										value="${ mv.model['xxtz'].xxtzAddtime}" type="both" /></td>
							</tr>
							<tr>
								<th>通知内容</th>
								<td>${ mv.model['xxtz'].xxtzEmailContent }</td>
							</tr>
						</table>
						<div class="seabtn">
							<table align="center">
								<tr>
									<td><a href="javascript:void(0);" onclick="history.back()">返回</a>
									</td>
								</tr>
							</table>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
	</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>
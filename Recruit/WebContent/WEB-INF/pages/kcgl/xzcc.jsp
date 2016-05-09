<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>中国航天科工集团公司人才招聘平台</title>
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<link type="text/css" href="css/jlsq/style.css" rel="stylesheet" />
<link rel="stylesheet" href="resources/pagination/pagination.css"
	type="text/css"></link>
<link type="text/css" href="resources/jBox/Skins/Blue/jbox.css"
	rel="stylesheet"></link>
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>
<script src="resources/jquery/jquery-migrate-1.1.1.js"></script>
<script type="text/javascript"
	src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript"
	src="resources/pagination/jquery.pagination.js"></script>
<script type="text/javascript" src="js/qtXzcc.js"></script>
</head>
<body>
	<%@ include file="../Main/Header.jsp"%>
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
				<li><a href="MyRecruit" style="color:#0046ae;">我的求职</a>
				</li>
				<li><a href="Message">消息中心</a>
				</li>
			</ul>
		</div>
		<div class="content">
			<div class="conTop">
				<img src="images/tag04.png" />&nbsp; <strong>我的求职</strong>My
				Recruit <span>您的当前位置：首页 >> 校园招聘 >> 我的求职 >> 网上测评预约</span>
			</div>
			<c:choose>
				<c:when test="${userLoginSession eq null}">
					<script type="text/javascript">
						$.jBox.open("iframe:LoginFrm", "用户登录", 280, 200, {
							buttons : {}
						});
					</script>
				</c:when>
				<c:otherwise>
					<div class="subtitle_pl">
						<table cellspacing="0" cellpadding="0" width="100%">
							<thead>
								<tr>
									<th>考场名称</th>
									<th>考试地址</th>
									<th>考试日期</th>
									<th>考试时间</th>
									<th>考场容量</th>
									<th>已报名</th>
									<th>剩余容量</th>
									<%--							<th>考场状态</th>--%>
									<th width="60" class="alignCenter">报名</th>
								</tr>
							</thead>
							<tbody id="tbList"></tbody>
						</table>
					</div>
					<div class="dataGrid">
						<table class="page">
							<tr>
								<td style="text-align: right;"><div id="Pagination"
										class="pagination"></div></td>
								<td style="width:265px;text-align: center;">当前显示 <span
									id="start">1</span> - <span id="end">10</span> 条记录 共 <span
									id="count">0</span> 条评论记录</td>
							</tr>
						</table>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<%@ include file="../Main/Footer.jsp"%>
</body>
</html>



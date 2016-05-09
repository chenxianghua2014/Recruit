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
				<img src="images/title03.png" />
			</div>
			<ul>
				<li><a href="SearchEnterprise">招聘日程</a>
				</li>
				<li><a href="SearchPosition" style="color:#0046ae;">招聘职位</a>
				</li>
				<li><a href="MyRecruit">我的求职</a>
				</li>
				<li><a href="Message">消息中心</a>
				</li>
			</ul>
		</div>
		<div class="content">
			<div class="conTop">
				<span>您的当前位置：招聘职位 >> 查询职位 >> 职位详情</span> <img src="images/tag04.png" />&nbsp;
				<strong>查询职位</strong>Search Position
			</div>
			<div class="main">
				<br />
				<table style="width: 100%;">
					<tr>
						<th width="150">招聘单位</th>
						<td>${ position.positionZsdw }</td>
					</tr>
					<tr>
						<th>职位名称</th>
						<td>${ position.positionName }</td>
					</tr>
					<tr>
						<th>职位类别</th>
						<td>${ position.positionType }</td>
					</tr>
					<tr>
						<th>专业类别</th>
						<td>${ position.positionZylb }</td>
					</tr>
					<tr>
						<th>工作地点</th>
						<td>${ position.positionWorkaddress }</td>
					</tr>
					<tr>
						<th>学历要求</th>
						<td>${ position.positionXlyq }</td>
					</tr>
					<tr>
						<th>招聘专业</th>
						<td>${ position.positionZpzy }</td>
					</tr>
					<tr>
						<th>需求人数</th>
						<td>${ position.positionXqrs }</td>
					</tr>
					<tr>
						<th>招聘分类</th>
						<td>${ position.positionZpfl }</td>
					</tr>
					<tr>
						<th>职位状态</th>
						<td>${ position.positionStatus }</td>
					</tr>
					<tr>
						<th>职位职责</th>
						<td>${ position.positionDuty }</td>
					</tr>
					<tr>
						<th>职位要求</th>
						<td>${ position.positionRequierd }</td>
					</tr>
				</table>
				<div class="seabtn">
					<table align="center">
						<tr>
							<c:choose>
								<c:when test="${userLoginSession eq null}">
									<td><a href="javascript:void(0);" onclick="ShowLogin();">申请岗位</a>
									</td>
									<td><a href="javascript:void(0);" onclick="ShowLogin();">加入收藏</a>
									</td>
								</c:when>
								<c:otherwise>

									<td><a
										href="ApplyPosition?id=${ position.positionId }&page=SearchPosition">申请岗位</a>
									</td>
									<td><a href="DoCollection?id=${ position.positionId }">加入收藏</a>
									</td>
								</c:otherwise>
							</c:choose>

							<td><a href="javascript:void(0);" onclick="history.back()">返回</a>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
	</div>
	<%@ include file="Footer.jsp"%>
</body>
<script type="text/javascript">
	function ShowLogin() {
		$.jBox.open("iframe:LoginFrm", "用户登录", 280, 200, {
			buttons : {}
		});
	}
</script>
</html>
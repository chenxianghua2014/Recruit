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
				<img src="images/title02.png" />
			</div>
			<ul>
				<li><a href="TalentStrategy">人才战略</a>
				</li>
				<li><a href="AcademiciansAndExperts">院士专家</a>
				</li>
				<li><a href="Personnel">人才队伍</a>
				</li>
				<li><a href="Career" style="color: #0046ae;">职业生涯</a>
				</li>
				<li><a href="Employee">员工风采</a>
				</li>
			</ul>
		</div>
		<div class="content">
			<div class="conTop">
				<span>您的当前位置：首页 >> 员工发展 >>职业生涯</span> <img src="images/tag04.png" />&nbsp;
				<strong>职业生涯</strong>Career
			</div>
			<div class="main">${xcxxs[0].xcxxContent}</div>
		</div>
	</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>
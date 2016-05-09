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
<style type="text/css">
.tb {
	margin: 0 auto;
	font-family: "宋体";
	font-size: 12px;
	line-height: 24px;
	color: #000000;
}

.subtitle {
	color: #555555;
	text-decoration: none;
	line-height: 22px;
	font-size: 12px;
}

.fs {
	color: #00408f;
	text-decoration: none;
	line-height: 20px;
	font-size: 12px;
}

.a {
	color: #00408f;
	text-decoration: none;
	line-height: 20px;
	font-size: 12px;
}
</style>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<div class="wrap clr">
		<div class="menu">
			<div class="menuTop">
				<img src="images/title04.png" />
			</div>
			<ul>
				<li><a href="InformationCenter" style="color: #0046ae;">资讯中心</a></li>
			</ul>
		</div>
		<div class="content">
			<div class="conTop">
				<span>您的当前位置：首页 >> 资讯中心 >> 资讯详情</span> <img src="images/tag04.png" />&nbsp;
				<strong>资讯中心</strong>Information Center
			</div>
			<div align="center"
				style="color:#0074cd;text-decoration: none;line-height:50px;font-size:26px; font-weight:bold">${zpxw.zpxwTitle
				}</div>
			<table class="tb" border="0" cellspacing="0" cellpadding="0"
				width="750" align="center" height="30" style=" margin-bottom:20px;">
				<tbody>
					<tr>
						<td width="350" class="subtitle">发布时间：<span id="con_time">${zpxw.zpxwFbsj
								}</span>
						</td>
						<td width="350" class="subtitle">文章来源： ${zpxw.zpxwAddcompany
							}</td>
						<td width="300">字体：<span class="fs">【<a class="a"
								onclick="main.style.fontSize='18px';lineHeight='24px';" href="#">大</a>】
								【<a class="a" onclick="main.style.fontSize='16px';" href="#">中</a>】
								【<a class="a" onclick="main.style.fontSize='14px';" href="#">小</a>】</span>
						</td>
					</tr>
				</tbody>
			</table>
			<div id="main" class="main" style="font-size: 16px;">${zpxw.zpxwContent
				}</div>
		</div>
	</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>
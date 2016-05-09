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
</head>
<body>
	<%@ include file="Header.jsp"%>
	<div class="wrap clr">
		<div class="menu">
			<div class="menuTop">
				<img src="images/title07.png" />
			</div>
			<ul>
				<li><a href="InstituteOf?id=<%=request.getParameter("id")%>">单位简介</a>
				</li>
				<li><a
					href="RecruitmentDynamics?id=<%=request.getParameter("id")%>"
					style="color:#0046ae;">招聘动态</a>
				</li>
			</ul>
		</div>
		<div class="content">
			<div class="conTop">
				<span>您的当前位置：首页 >> 单位概况 >> 招聘动态 >> 招聘动态详情</span> <img
					src="images/tag04.png" />&nbsp; <strong>招聘动态</strong>
				RecruitmentDynamics
			</div>
			<div class="main">${zpxw.zpxwContent }</div>
		</div>
	</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>
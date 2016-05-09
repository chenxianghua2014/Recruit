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
	<div id="header">
		<div class="top">
			<img src="images/logo.png" />
			<div class="loginLink">
				<a href="#">社会招聘</a> | <a href="#">海外招聘</a> | <a href="#">登陆</a> | <a
					href="#">注册</a>
			</div>
		</div>
		<div class="nav">
			<div class="navL">
				<div class="navR">
					<ul class="clr">
						<li><a href="#">首页</a></li>
						<li><a href="#">集团概况</a></li>
						<li><a href="#">员工发展</a></li>
						<li><a href="#">招聘职位</a></li>
						<li><a href="#">招聘行程</a></li>
						<li><a href="#">招聘指南</a></li>
						<li><a href="#">交流社区</a></li>
						<li style="background:none;"><a href="#"><img
								src="images/btnIndex.png" /> </a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<div id="otherbanner">
		<img src="images/otherbanner.jpg" />
	</div>
	<div class="wrap clr">
		<div class="menu">
			<div class="menuTop">
				<img src="images/title01.png" />
			</div>
			<ul>
				<li><a href="#">集团简介</a></li>
				<li><a href="#">领导致辞</a></li>
				<li><a href="#">企业领导</a></li>
				<li><a href="#">组织机构</a></li>
				<li><a href="#">发展历程</a></li>
				<li><a href="#">企业文化</a></li>
			</ul>
		</div>
		<div class="content">
			<div class="conTop">
				<span>您的当前位置：首页 >> 集团概况 >> 集团简介</span> <img src="images/tag04.png" />&nbsp;
				<strong>集团简介</strong>Group Introduction
			</div>
			<div class="main">${xcxx.xcxxContent }</div>
		</div>
	</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>
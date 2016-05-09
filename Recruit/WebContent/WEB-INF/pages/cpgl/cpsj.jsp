<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>测评试卷</title>
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/cpgl/cpstyle.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="resources/jBox/jquery-1.4.2.min.js"></script>
<script type="text/javascript"
	src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-zh-CN.js"></script>
<link type="text/css" rel="stylesheet"
	href="resources/jBox/Skins/Blue/jbox.css" />
</head>

<body>
	<div id="top"></div>
	<div id="contentmain">
		<div class="conLeft">
			<div class="conLeftPic">
				<img src="images/pic.jpg" />
				<p>${cpglLoginSession.bmglKsxm}</p>
				<p>${cpglLoginSession.bmglSfzh}</p>
			</div>
		</div>
		<div class="conRight">
			<div class="time">
				<img src="images/cpgl/time.jpg" />考试剩余时间 ：<span id="timer"></span>
			</div>
			<div class="questions">
				试题放在此处， 结尾加分页 <br /> 要在考试结束前5分钟弹出提示框，告诉考生考试还剩5分钟

			</div>
		</div>
	</div>
	<a href="successSubmit" class="btn">提交答卷</a>
	<%@ include file="../Main/cpglFooter.jsp"%>
</body>
</html>

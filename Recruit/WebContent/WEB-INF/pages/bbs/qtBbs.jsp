<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>

<title></title>
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon"/> 
<link type="text/css" href="css/style.css" rel="stylesheet" />
<link href="css/htgl/bbs/sq_style.css" rel="stylesheet" type="text/css" />
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="resources/pagination/pagination.css"
	type="text/css"></link>
<script type="text/javascript" src="resources/jBox/jquery-1.4.2.min.js"></script>
<script type="text/javascript"
	src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript"
	src="resources/pagination/jquery.pagination.js"></script>
<script type="text/javascript" src="js/qtBbs.js"></script>
<script type="text/javascript">
	function checkLength(which) {
		var maxChars = 200; //
		if (which.value.length > maxChars) {
			alert("您出入的字数超出限制!");
			// 超过限制的字数了就将 文本框中的内容按规定的字数 截取
			which.value = which.value.substring(0, maxChars);
			return false;
		} else {
			var curr = which.value.length; //250 减去 当前输入的
			document.getElementById("sy").innerHTML = curr.toString();
			return true;
		}
	}
</script>

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
						<li><a href="qtBbs">交流社区</a></li>
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
				<li><a href="qtBbs">社区论坛</a></li>
				<li><a href="rmbw">个人博文</a></li>
				<li><a href="qtXqqz">兴趣圈子</a></li>
				<li><a href="zyty">经验博文</a></li>
			</ul>
		</div>

		<div class="content">
			<div class="conTop">
				<span>您的当前位置：首页 >> 交流社区>> 社区论坛</span> <img src="images/tag04.png" />&nbsp;
				<strong>社区论坛</strong>Group Introduction
			</div>
			<div class="main">
				<div id="all">
					<!--查询模块-->
					<div class="subtitle">
						<div class="title">
							<c:forEach items="${ltbkList}" var="map" varStatus="mapStatus">
								<a href="javascript:void(0);"
									onclick="loadData('','','${map.ltbkName}');">${map.ltbkName
									}</a>
							</c:forEach>
						</div>
						<div class="editBlock1">
							<c:forEach items="${ltbqList}" var="map">
								<a
									href="queryByBq?ltbqName=${map.ltbqName}&ltbqId=${map.ltbqId}">${map.ltbqName}</a>
							</c:forEach>
						</div>
					</div>
					<div class="dGrid">
						<form action="BkBbsSave" method="post">
							<span> <textarea rows="5" cols="100" name="bbsNr"
									onkeyup="checkLength(this);">
									  发表您的论坛内容
									 </textarea> <input type="hidden" name="bbsBqid" id="bbsBqid" value="" />
							</span>
							<div class="data_btn">
								<span class="two"> / 200 &nbsp; <input name="button"
									type="submit" class="inputButton" value="发表" />
								</span><span id="sy" style="color:grey;" class="one">0</span>
							</div>
						</form>
					</div>
					<!--查询结果-->
					<div class="subtitle_pl">
						<div class="title">&nbsp; >> 查询结果</div>
						<div id="tbList"></div>
					</div>
				</div>
				<div class="dataGrid">
					<table class="page">
						<tr>
							<td style="text-align: right;"><div id="Pagination"
									class="pagination"></div>
							</td>
							<td style="width:265px;text-align: center;">当前显示 <span
								id="start">1</span> - <span id="end">10</span> 条记录 共 <span
								id="count">0</span> 条帖子记录</td>
						</tr>
					</table>
				</div>

			</div>
		</div>
	</div>
	<%@ include file="../Main/Footer.jsp"%>
</body>
<script type="text/javascript">
	function setBqid(v) {
		if ($("#bbsBqid").val() == "")
			$("#bbsBqid").val("${ltbkName}");
		else
			$("#bbsBqid").val(v);
	}
</script>
</html>

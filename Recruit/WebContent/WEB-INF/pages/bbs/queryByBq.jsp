<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>科工论坛</title>
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon"/> 
<link type="text/css" href="css/jlsq/style.css" rel="stylesheet" />
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>
<link type="text/css" href="resources/jBox/Skins/Blue/jbox.css"
	rel="stylesheet"></link>
<script src="resources/jquery/jquery-migrate-1.1.1.js"></script>
<script type="text/javascript"
	src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-zh-CN.js"></script>
<link rel="stylesheet" href="resources/pagination/pagination.css"
	type="text/css"></link>
<script type="text/javascript"
	src="resources/pagination/jquery.pagination.js"></script>
<script type="text/javascript" src="js/queryByBq.js"></script>
</head>
<body>
	<%@ include file="../Main/Header.jsp"%>
	<div class="wrap clr">
		<div class="menu">
			<div class="menuTop">
				<img src="images/cpgl/title06.png" />
			</div>
			<ul>
				<li><a style="color:#0046ae;" href="javascript:void(0);" onclick="javascript:window.location='jlsq'">科工论坛</a></li>
	            <li><a href="javascript:void(0);" onclick="javascript:window.location='zyty'">经验博文</a></li>
	            <li><a href="javascript:void(0);" onclick="javascript:window.location='qtXqqz'">兴趣圈子</a></li>
			</ul>
		</div>

		<div class="content">
			<div class="conTop">
	        	<img src="images/tag04.png" />&nbsp; 
	            <strong>科工论坛</strong>BBS
	            <span>您的当前位置：首页 >> 交流社区 >> 科工论坛</span>
       		 </div>
			<div class="main">
				<input type="hidden" name="username" id="username" value="${userLoginSession.userName}"/>
				<input type="hidden" name="userId" id="userId" value="${userLoginSession.userId}"/>
				  <div class="Tab">
		        		<span>标签:</span>
		        		<c:forEach items="${ltbqList}" var="map">
	        				<a href="javascript:void(0);" onclick="javascript:window.location='queryByBq?ltbqName=${map.ltbqName}&ltbqId=${map.ltbqId}'" title="${map.ltbqName}">${map.ltbqName}</a>
	        			</c:forEach>
		          </div>
					<div class="subtitle_pl">
						<div id="tbList"></div>
					</div>
				<div>
					<table width="800px">
						<tr>
							<td style="text-align: right;"><div id="Pagination"
									class="pagination"></div></td>
							<td style="width:265px;text-align: center;">当前显示 <span
								id="start">1</span> - <span id="end">10</span> 条记录 共 <span
								id="count">0</span> 条帖子记录
							</td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="../Main/Footer.jsp"%>
	<!--悬浮-->
</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>成功交卷</title>
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon"/> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/cpgl/cpstyle.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="resources/jBox/jquery-1.4.2.min.js"></script>
<script type="text/javascript"
	src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-zh-CN.js"></script>
<link type="text/css" rel="stylesheet"
	href="resources/jBox/Skins/Blue/jbox.css" />
<script type="text/javascript" src="js/KeyDown.js"></script>
<script type="text/javascript">
function queryGlgEng() {
	$.jBox.tip("正在生成试卷，请耐心等待一会！", 'loading');
	setTimeout(function() {
		window.location = "queryGlgEng";
	}, 20000);

}
</script>
</head>

<body onload="KeyDown();">
	<div id="top"></div>
	<div id="contentmain">
		<div class="successConRight">
			<p style="margin-top:200px;">
				<span style="color: #FF6347;">${cpglLoginSession1.bmglKsxm}</span>,您已成功提交答案
			</p>
			<p>请您稍作调整，集中精力，继续完成后续测试。</p>
			<a  href="javascript:void(0);" onclick="queryGlgEng();" class="btn">进入英语考试</a>
		</div>
	</div>
	<%@ include file="../Main/cpglFooter.jsp"%>
</body>
</html>

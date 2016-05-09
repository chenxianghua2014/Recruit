<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link rel="stylesheet" href="resources/jBox/Skins/Blue/jbox.css"
	type="text/css"></link>
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>
<script src="resources/jquery/jquery-migrate-1.1.1.js"></script>
<script type="text/javascript"
	src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-zh-CN.js"></script>
</head>
<body style="margin: 0;padding: 0;">
	<img src="images/error.jpg" style="width:100%;height:100%;" />
</body>
<script language="JavaScript" type="text/JavaScript">
	$(function() {
		$.jBox.tip("出错了,请刷新页面重试操作!", 'loading');
	});
</script>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<script type="text/javascript" src="resources/jquery/jquery-1.9.1.min.js"></script>
</head>
<body>
	<div id="d" style="font-size: 12pt;">
		下载成功!本窗口将在<span id="t" style="color: red;">30</span>秒后关闭。<input type="button" value="关闭" onclick="closeW()" />
	</div>
</body>
<script type="text/javascript">
	var i = 30;
	function autoClose() {
		$("#t").html(i);
		i = i - 1;
		if (i == -1) {
			closeW();
		}
	}
	function closeW() {
		window.opener = null;
		window.open('', '_self');
		window.close();
	}
	window.setInterval("autoClose()", 1000);
	//window.location.href = "uppics/" + "${ id }" + ".doc";
	window.location.href = "${ id }";
</script>
</html>

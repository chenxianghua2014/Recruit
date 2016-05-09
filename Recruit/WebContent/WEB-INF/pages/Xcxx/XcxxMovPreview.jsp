<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="resources/upload/swfupload.js"></script>
<style type="text/css">
html {
	overflow: hidden;
}

body {
	margin: 0;
}
</style>
</head>
<body>
	<div id="movYl"></div>
</body>
<script type="text/javascript">
	$(document).ready(
			function() {
				var obj = window.dialogArguments;
				var params = {
					'allowfullscreen' : 'true',
					'allowscriptaccess' : 'always',
					'bgcolor' : '#000000'
				};
				var attributes = {
					'align' : 'bottom',
					'name' : 'playerID',
					'id' : 'playerID'
				};
				var flashvars = {
					'file' : "${path}" + "/" + obj.file,
					'id' : 'playerID',
					'image' : "${path}" + "/" + obj.image
				};
				swfobject.embedSWF('images/swfobject.swf', 'movYl', '600',
						'400', '9.0.124', 'images/expressInstall.swf',
						flashvars, params, attributes);
			});
</script>
</html>
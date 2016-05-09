<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理员门户</title>
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<link href="skins/default/index.css" rel="stylesheet" type="text/css"
	title="css0" />
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>  
<script type="text/javascript" src="js/index.js"></script>
<script type="text/javascript">

	function autoIframe(obj) {
		var iframe = document.getElementById(obj);
		var oldHeight = iframe.height
		try {
			var bHeight = iframe.contentWindow.document.body.scrollHeight;
			var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
			var height = Math.max(bHeight, dHeight);

			iframe.height = height;

		} catch (e) {
		}
	}

	window.setInterval("autoIframe('leftFrame')", 1000);
	window.setInterval("autoIframe('mainFrame')", 500);
</script>
</head>
<body>
<div class="title">当前位置:账号设置>添加单位信息</div>
	<table id="mainContent">
		<tr height="100%">
			<td id="leftBar" valign="top"><iframe src="queryZzjgFO"
					id="leftFrame" name="leftFrame" width="180" height="600"
					scrolling="auto" frameborder="0" allowtransparency="true" ></iframe>
			</td>
			<td id="rightBar" valign="top"><iframe src="zzjgAdd"
					id="mainFrame" name="mainFrame" width="100%" height="600"
					frameborder="0" allowtransparency="true" scrolling="no"></iframe>
			</td>
		</tr>
	</table>
	<!--end maincontent-->
	<!-- begin copyright-->
	<div id="copyright">
		<script type="text/javascript">
			document.write(new Date().getFullYear());
		</script>
		中国航天科工集团公司. 版权所有
	</div>
	<!--end copyright-->
</body>
</html>

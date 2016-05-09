<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理员门户</title>
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon"/> 
<link href="skins/default/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	function zzjgLogout() {
		if (confirm("确认退出本次登陆么？")) {
			window.location.href = "zzjgLogout";
		}
	}
	
	function redirect(url){
		document.getElementById("mainFrame").src = url;
	}
</script>
<style style="text/css">
html {
	overflow-x: hidden;
}

* {
	margin: 0px;
	padding: 0px;
}

body {
	margin: 0px;
	padding: 0px;
}

.top {
	width: 100%;
	height: 80px;
	background-color: #015bac;
	background: url(images/top.jpg) no-repeat left top;
}

.tishi {
	text-align: right;
	height: 80px;
}

.tishi p {
	height: 40px;
	line-height: 40px;
	font-family: "微软雅黑";
	font-size: 14px;
	color: #FFF;
	font-weight: bold;
	padding-right: 20px;
}

.tishi a {
	font-family: "微软雅黑";
	font-size: 14px;
	color: #FFF;
	text-decoration: none;
}

.tishi p img {
	vertical-align: middle;
	padding-right: 5px;
}

.left_menu {
	width: 180px;
	float: left;
}
</style>
</head>
<body>
	<div class="top">
		<div class="tishi">
			<p>
				<a href="Index">管理系统首页</a> | <a href="http://www.casic.com.cn">集团公司首页</a>
			</p>
			<p>
				<a title="查看用户基本信息" href="reviewZzjg" target="mainFrame">
					<img src="images/admin.png" />
				</a> 您好！<span style="color: black; size: 4em;">
				 <%
				 	Object o = session.getAttribute("loginType");
				 	if (o == null) {
				 %> ${loginSession.zzjgDwmc} <%
				 	} else {
				 %> ${loginSession.userName} <%
				 	}
				 %>
				</span> &nbsp;&nbsp;<span id="localtime"></span> 【<a title="注销登录"
					href="javascript: void(0);" onclick="zzjgLogout();">注销</a>】
			</p>
			<script type="text/javascript">
				function showLocale(objD) {
					var str, colorhead, colorfoot;
					var yy = objD.getYear();
					if (yy < 1900)
						yy = yy + 1900;
					var MM = objD.getMonth() + 1;
					if (MM < 10)
						MM = '0' + MM;
					var dd = objD.getDate();
					if (dd < 10)
						dd = '0' + dd;
					var hh = objD.getHours();
					if (hh < 10)
						hh = '0' + hh;
					var mm = objD.getMinutes();
					if (mm < 10)
						mm = '0' + mm;
					var ss = objD.getSeconds();
					if (ss < 10)
						ss = '0' + ss;
					var ww = objD.getDay();
					if (ww == 0)
						colorhead = "<font color=\"#dbe6b7\">";
					if (ww > 0 && ww < 6)
						colorhead = "<font color=\"#FFFFFF\">";
					if (ww == 6)
						colorhead = "<font color=\"#FFFFFF\">";
					if (ww == 0)
						ww = "星期日";
					if (ww == 1)
						ww = "星期一";
					if (ww == 2)
						ww = "星期二";
					if (ww == 3)
						ww = "星期三";
					if (ww == 4)
						ww = "星期四";
					if (ww == 5)
						ww = "星期五";
					if (ww == 6)
						ww = "星期六";
					colorfoot = "</font>"
					str = colorhead + yy + "-" + MM + "-" + dd + " " + hh + ":"
							+ mm + ":" + ss + "  " + ww + colorfoot;
					return (str);
				}
				function tick() {
					var today;
					today = new Date();
					document.getElementById("localtime").innerHTML = showLocale(today);
					window.setTimeout("tick()", 1000);
				}
				tick();
			</script>
		</div>
	</div>
	<table id="mainContent">
		<tr height="100%">
			<td id="leftBar" valign="top">
				<div class="left_menu">
					<iframe src="LeftAdmin" id="leftFrame" name="leftFrame" width="180"
						height="680" scrolling="no" frameborder="0"
						allowtransparency="true"></iframe>
				</div>
			</td>
			<td id="rightBar" valign="top">
				<%
					if (o == null) {
				%> <iframe src="RightAdmin" id="mainFrame" name="mainFrame"
					width="100%" height="680" frameborder="0" allowtransparency="true"></iframe>
				<%
					} else {
				%> <iframe src="MsResult" id="mainFrame" name="mainFrame"
					width="100%" height="680" frameborder="0" allowtransparency="true"></iframe>
				<%
					}
				%>
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

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>在线招聘社区</title>
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon"/> 
<link rel="stylesheet" href="css/login.css" type="text/css"></link>
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="resources/jquery/jquery.form.js"></script>
<script type="text/javascript" src="js/Login.js"></script>
</head>
<body>
<div class="weather">
		<iframe allowtransparency="true" frameborder="0" width="385"
			height="96" scrolling="no"
			src="http://tianqi.2345.com/plugin/widget/index.htm?s=2&z=1&t=0&v=0&d=3&bd=0&k=&f=&q=1&e=1&a=1&c=54511&w=385&h=96&align=center"></iframe>
</div>
<div class="ht">
	<span id="localtime" class="localtime" ></span>
	<div class="index_link"><a href="#">社会招聘 ||</a>  <a href="#">海外招聘 ||</a> <a href="Main">首页</a> </div>
</div>
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
						colorhead = "<font color=\"#fff\">";
					if (ww > 0 && ww < 6)
						colorhead = "<font color=\"#fff\">";
					if (ww == 6)
						colorhead = "<font color=\"#fff\">";
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
	<form method="post" name="FormMain" id="FormMain" action="UserLogin">
		<div class="logo">
			<img src="images/logo.png" />
		</div>
		<div class="login">
			<table width="100%">
				<tr>
					<td colspan="3" class="biaoti">后台登录</td>
				</tr>
				<tr>
					<td colspan="3">用户名</td>
				</tr>
				<tr>
					<td colspan="3"><input type="text" id="txtUserName"
						name="txtUserName" class="username" /></td>
				</tr>
				<tr>
					<td colspan="3">密 码</td>
				</tr>
				<tr>
					<td colspan="3"><input type="password" id="txtPassword"
						name="txtPassword" class="password" /></td>
				</tr>
				<tr>
					<td colspan="3">验证码</td>
				</tr>
				<tr>
					<td style="width: 200px;"><input name="rand" id="rand" type="text" value=""
						style="width: 150px;" />
					</td>
					<td colspan="2"><img title="换一张" onclick="javascript:RefreshCode();"
						id="randcode" src="image.jsp" /></td>
				</tr>
				<tr>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td colspan="3" align="center"><input type="button"
						class="inputButton" onclick="onSubmitLogin();"
						style="width:134px;height:39px;background:#0067b6 url(images/login_button.gif) repeat-x center;" />
				</tr>
			</table>
		</div>
	</form>
   <div id="footer"></div>
</body>
</html>

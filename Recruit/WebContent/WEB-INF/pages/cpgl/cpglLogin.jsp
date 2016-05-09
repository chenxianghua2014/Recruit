<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>测评-登录界面</title>
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
<link href="css/cpgl/cpstyle.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/validator.js"></script>
<style>
body {
	background: #FFF;
}

.cpgldl a {
	text-decoration: none;
}
</style>
<script type="text/javascript">
	function CpglLogin() {
		if (Validator.Validate(document.getElementById('ff'), 2)) {
			document.ff.action = "CpglLogin";
			document.ff.method = "post";
			document.ff.submit();
		}
	}
</script>
</head>
<body>
	<div id="cploginBg">
		<div class="cplogin">高校毕业生校园招聘综合素质测评</div>
	</div>
	<div class="cpList">
		<form name="ff" id="ff">
			<table width="100%">
				<c:if test="${message ne null}">
					<tr>
						<td colspan="2" align="center"><span style="color: red;">${message}</span>
						</td>
					</tr>

				</c:if>
				<tr>
					<td>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名：</td>
					<td><input type="text" name="bmglKsxm" dataType="Require"
						msg="请填写您的报考姓名" /></td>
				</tr>
				<tr>
					<td>身份证号：</td>
					<td><input type="text" name="bmglSfzh" dataType="Require"
						msg="请填写正确身份证号" /></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td colspan="2" align="center" class="cpgldl"><a
						href="javascript:void(0);" onclick="CpglLogin();">登录</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<%@ include file="../Main/cpglFooter.jsp"%>
</body>
</html>

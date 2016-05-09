<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>中国航天科工集团公司人才招聘平台</title>
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon"/> 
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link type="text/css" href="css/jlsq/style.css" rel="stylesheet" />
<link rel="stylesheet" href="resources/pagination/pagination.css"
	type="text/css"></link>
<link type="text/css" href="resources/jBox/Skins/Blue/jbox.css"
	rel="stylesheet"></link>
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>
<script src="resources/jquery/jquery-migrate-1.1.1.js"></script>
<script type="text/javascript"
	src="resources/pagination/jquery.pagination.js"></script>
<script type="text/javascript"
	src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-zh-CN.js"></script>
<script type="text/javascript" src="js/validator.js"></script>
<script type="text/javascript">
function finishZhmm(){
	if (document.ff.userPassword.value == "") {
		alert("请输入密码!");
	}else{
		if (document.ff.userPassword.value != document.ff.userPassword1.value) {
			alert("两次输入密码不一致！");
		}else{
			if(Validator.Validate(document.getElementById('ff'),3) ){
				document.ff.action="successCzUserUpdPD";
				document.ff.method="post";
				document.ff.submit();
			}
		}
	}
}

</script>
<style type="text/css">
.content {
	margin-left: 0px;
	width: 980px;
}

.conTop {
	width: 980px;
}
</style>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<div class="wrap clr">
		<div class="content">
			<div class="conTop">
				<img src="images/tag04.png" />&nbsp; <strong>密码管理</strong>The password of enterprises
			</div>
			<div class="main">
				 <form name="ff" id="ff">
				 <input type="hidden" name="userId" id="userId" value="${user.userId}"/>
				 	<table align="center">
				 		<tr ><td colspan="2" align="center" style="font-size: 18px;">【<span style="color: green;font-size: 18px;">信息输入正确，请重置您的密码</span>】</td></tr>
				 		<tr>
				 			<td>&nbsp;&nbsp;</td>
				 		</tr>
				 		<tr>
				 		
				 			<td style="font-size: 18px;"><span style="color: red;">*</span>&nbsp;&nbsp;新的密码：</td>
				 			<td><input type="password" name="userPassword" id="userPassword" dataType="Require"  msg="必填"/></td>
				 			
				 		</tr>
				 		<tr>
				 		
				 			<td style="font-size: 18px;"><span style="color: red;">*</span>&nbsp;&nbsp;确认密码：</td>
				 			<td><input type="password" name="userPassword1" id="userPassword1"dataType="Require"  msg="必填"/></td>
				 			
				 		</tr>
				 		<tr>
				 			<td>&nbsp;&nbsp;</td>
				 		</tr>
				 		<tr>
					 		<td colspan="2" align="center">
					 		<input type="button" class="inputButton" value="提交" onclick="finishZhmm();"/>
					 		</td>
				 		</tr>
				 	</table>
				 
				 </form>
			</div>
		</div>
	</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>中国航天科工集团公司人才招聘平台</title> 
<link type="text/css" href="css/jlsq/style.css" rel="stylesheet" />
<link type="text/css" rel="stylesheet" href="resources/jBox/Skins/Blue/jbox.css" />
<script type="text/javascript" src="resources/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="js/validator.js"></script>
<script type="text/javascript">
	function checkPass() {
		if (Validator.Validate(document.getElementById('form1'), 3)) {
			if (document.form1.userPassword.value != document.form1.userPassword1.value) {
				alert("两次输入密码不一致！");
				return false;
			}
			if (document.form1.userPassword.value = document.form1.userPassword1.value) {
				document.form1.action = "registerUser";
				document.form1.method = "post";
				document.form1.submit();
			}

		}
	}
	function goMain() {
		window.location.href = "Main";
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
	<%@ include file="Main/Header.jsp"%>
	<div class="wrap clr">
		<div class="content">
			<div class="conTop">
				<img src="images/tag04.png" />&nbsp; <strong>用户注册</strong>User Registration <span>您的当前位置：首页 >>用户注册</span>
			</div>
			<div>
				<form name="form1" id="form1">
					<input type="hidden" name="userId" value="${user.userId}" />
					<div class="cpList">
						<table width="100%">
							<tr>
								<th><span class="warning">*</span>登&nbsp;&nbsp;录&nbsp;&nbsp;名:</th>
								<td><input type="text" maxlength="20" id="userIdcard"
									name="userIdcard" value="${user.userIdcard}"
									onblur="checkUserIdcard()" disabled="disabled"
									dataType="Require" class="inputReg" msg="请认真填写您的登录名,以免忘记" />
								</td>
							</tr>
							<tr>
								<th><span class="warning">*</span>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:</th>
								<td><input type="text" id="userName" name="userName"
									value="${user.userName}" dataType="Require" maxlength="10"
									disabled="disabled" class="inputReg" msg="请填写您的真实姓名" /><span
									style="color: green;">&nbsp;请输入您的真实姓名，将作为简历信息，无法更改。</span>
								</td>
							</tr>
							<tr>
								<th><span class="warning">*</span>身份证号:</th>
								<td><input type="text" id="userSfzh" name="userSfzh"
									value="${user.userSfzh}" onblur="checkUserSfzh()"
									disabled="disabled" class="inputReg" dataType="IdCard"
									msg="请填写您的真实身份证号" /><span style="color: green;">&nbsp;请正确填写，否则将影响您身份核对和测评考试。</span>
								</td>
							</tr>
							<tr>
								<th><span class="warning">*</span>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别:</th>
								<td><input type="radio" id="userSex" name="userSex"
									value="男" checked="checked" />男 <input type="radio"
									id="userSex" name="userSex" value="女" />女</td>
							</tr>
							<tr>
								<th><span class="warning">*</span>年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;龄:</th>
								<td><input type="text" id="userAge" name="userAge"
									value="${user.userAge}" dataType="Number" maxlength="2"
									class="inputReg" msg="必需填写数字" />
								</td>
							</tr>
							<tr>
								<th><span class="warning">*</span>电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话:</th>
								<td><input type="text" maxlength="11" id="userTelephone"
									name="userTelephone" value="${user.userTelephone}"
									class="inputReg" dataType="Mobile" msg="请填写正确格式" /><span
									style="color: green;">&nbsp;请核对、准确填写，否则会影响到接收测评与面试通知 </span></td>
							</tr>
							<tr>
								<th><span class="warning">*</span>邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱:</th>
								<td><input type="text" id="userEmail" name="userEmail"
									class="inputReg" value="${user.userEmail}" dataType="Email"
									msg="请填写正确格式" /><span style="color: green;">&nbsp;请核对、准确填写，否则会影响到接收测评与面试通知</span>
								</td>
							</tr>
							<tr>
								<th><span class="warning">*</span>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:</th>
								<td><input type="password" id="userPassword"
									class="inputReg" value="${user.userPassword}"
									name="userPassword" dataType="Require" msg="请填写密码" />
								</td>
							</tr>
							<tr>
								<th><span class="warning">*</span>确认密码:</th>
								<td><input type="password" id="userPassword1"
									class="inputReg" name="userPassword1" dataType="Require"
									msg="请确认密码" />
								</td>
							</tr>
							<tr>
								<td></td>
								<td><input type="button" class="inputButton" value="确定"
									onclick="checkPass();" /> &nbsp;&nbsp; <input type="button"
									class="inputButton" value="取消"
									onclick="goMain('${user.userId}');" /></td>
							</tr>
						</table>
					</div>
				</form>
			</div>
		</div>
	</div>
	<%@ include file="Main/Footer.jsp"%>
</body>
</html>

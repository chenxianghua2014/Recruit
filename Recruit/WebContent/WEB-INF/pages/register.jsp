<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>中国航天科工集团公司人才招聘平台</title>
<link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
<link type="text/css" href="css/jlsq/style.css" rel="stylesheet" />
<link type="text/css" rel="stylesheet"
	href="resources/jBox/Skins/Blue/jbox.css" />
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>
<script src="resources/jquery/jquery-migrate-1.1.1.js"></script>
<script type="text/javascript"
	src="resources/jBox/jquery.jBox-2.3.min.js"></script>
<script type="text/javascript" src="resources/jBox/jquery.jBox-zh-CN.js"></script>
<script type="text/javascript" src="js/validator.js"></script>
<script type="text/javascript">
	function checkPass() {
		if (Validator.Validate(document.getElementById('form1'), 3)) {
			if (document.form1.userPassword.value != document.form1.userPassword1.value) {
				alert("两次输入密码不一致！");
				return false;
			}
			if (document.form1.userPassword.value = document.form1.userPassword1.value) {
				$.jBox.tip("正在注册！", 'loading');
				$("#userAge").val(
						new Date().getFullYear()
								- $("#userSfzh").val().substring(6, 10));
				document.getElementById("btnSubmit").disabled = false;
				document.form1.action = "registerUser";
				document.form1.method = "post";
				document.form1.submit();
			}
		}
	}
	function goMain() {
		window.location.href = "Main";
	}
	function checkUserIdcard() {
		var card = document.getElementById("userIdcard").value;
		$.ajax({
			type : 'POST',
			url : "checkUserIdcard",
			data : {
				userIdcard : card
			},
			dataType : "json",
			success : function(result) {
				if (result) {
					alert("此用户名已经存在，请重新填写");
					document.getElementById("userIdcard").value = "";
				}

			}
		});
	}
	function checkUserName() {
		var card = document.getElementById("userEmail").value;
		$.ajax({
			type : 'POST',
			url : "checkUserName",
			data : {
				userEmail : card
			},
			dataType : "json",
			success : function(result) {
				if (result) {
					alert("此邮箱已经存在，请重新填写");
					document.getElementById("userEmail").value = "";
				}

			}
		});
	}
	function checkUserTelephone() {
		var card = document.getElementById("userTelephone").value;
		$.ajax({
			type : 'POST',
			url : "checkUserTelephone",
			data : {
				userTelephone : card
			},
			dataType : "json",
			success : function(result) {
				if (result) {
					alert("此手机号已经存在，请重新填写");
					document.getElementById("userTelephone").value = "";
				}

			}
		});
	}
	function checkUserSfzh() {
		var card = document.getElementById("userSfzh").value;
		$.ajax({
			type : 'POST',
			url : "checkUserSfzh",
			data : {
				userSfzh : card
			},
			dataType : "json",
			success : function(result) {
				if (result) {
					alert("此身份证号已经存在，请重新填写");
					document.getElementById("userSfzh").value = "";
				}

			}
		});
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
				<img src="images/tag04.png" />&nbsp; <strong>用户注册</strong>User
				Registration <span>您的当前位置：首页 >>用户注册</span>
			</div>
			<div>
				<form name="form1" id="form1">
					<input type="hidden" name="userId" value="${user.userId}" /> <input
						type="hidden" id="userAge" name="userAge" value="${user.userAge}" />
					<div class="cpList">
						<table width="100%">
							<tr>
								<th><span class="warning">*</span>登&nbsp;&nbsp;录&nbsp;&nbsp;名:</th>
								<td><input type="text" maxlength="20" id="userIdcard"
									name="userIdcard" value="${user.userIdcard}"
									onblur="checkUserIdcard()" dataType="Require" class="inputReg"
									msg=" 请填写您的登录名" /></td>
							</tr>
							<tr>
								<th><span class="warning">*</span>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:</th>
								<td><input type="text" id="userName" name="userName"
									value="${user.userName}" dataType="Limit" min="1" max="5"
									class="inputReg" msg=" 请填写您的真实姓名" /><span
									style="color: green;">&nbsp;请输入您的真实姓名，将作为简历信息，无法更改。</span></td>
							</tr>
							<tr>
								<th><span class="warning">*</span>身份证号:</th>
								<td><input type="text" id="userSfzh" name="userSfzh"
									value="${user.userSfzh}" onblur="checkUserSfzh()"
									class="inputReg" dataType="IdCard" msg=" 请填写您的真实身份证号" /><span
									style="color: green;">&nbsp;请正确填写，否则将影响您身份核对。</span></td>
							</tr>
							<tr>
								<th><span class="warning">*</span>性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别:</th>
								<td><input type="radio" id="userSex" name="userSex"
									value="男" checked="checked" />男 <input type="radio"
									id="userSex" name="userSex" value="女" />女</td>
							</tr>
							<!-- 							<tr> -->
							<!-- 								<th><span class="warning">*</span>年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;龄:</th> -->
							<!-- 								<td><input type="text" id="userAge" name="userAge" -->
							<!-- 									value="${user.userAge}" dataType="Number" maxlength="2" -->
							<!-- 									class="inputReg" msg=" 必需填写数字" /></td> -->
							<!-- 							</tr> -->
							<tr>
								<th><span class="warning">*</span>手机号码:</th>
								<td><input type="text" maxlength="11" id="userTelephone"
									name="userTelephone" value="${user.userTelephone}"
									onblur="checkUserTelephone()" class="inputReg"
									dataType="Mobile" msg=" 请填写正确格式" /><span style="color: green;">&nbsp;请正确填写，否则将影响您接收面试通知。
								</span></td>
							</tr>
							<tr>
								<th><span class="warning">*</span>电子邮箱:</th>
								<td><input type="text" id="userEmail" name="userEmail"
									class="inputReg" value="${user.userEmail}"
									onblur="checkUserName()" dataType="Email" msg=" 请填写正确格式" /><span
									style="color: green;">&nbsp;请正确填写，否则将影响您接收面试通知。 </span></td>
							</tr>
							<tr>
								<th><span class="warning">*</span>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:</th>
								<td><input type="password" id="userPassword"
									class="inputReg" value="${user.userPassword}"
									name="userPassword" dataType="Require" msg=" 请填写密码" /></td>
							</tr>
							<tr>
								<th><span class="warning">*</span>确认密码:</th>
								<td><input type="password" id="userPassword1"
									class="inputReg" name="userPassword1" dataType="Require"
									msg=" 请确认密码" /></td>
							</tr>
							<tr>
								<td></td>
								<td><input type="button" class="inputButton" id="btnSubmit"
									value="确定" onclick="checkPass();" /> &nbsp;&nbsp; <input
									type="button" class="inputButton" value="取消"
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

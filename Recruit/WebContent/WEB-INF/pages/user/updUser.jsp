<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="resources/jquery/jquery-1.9.1.min.js"></script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<link rel="stylesheet" href="resources/validator-0.7.1/jquery.validator.css" type="text/css"></link>
<script type="text/javascript" src="resources/validator-0.7.1/jquery.validator.js"></script>
<script type="text/javascript" src="resources/validator-0.7.1/local/zh_CN.js"></script>
<script type="text/javascript" src="resources/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript">
	function checkPass() {
		if (document.form1.userPassword.value == "") {
			alert("请输入密码!");
			return false;
		}
		if (document.form1.userPassword.value != document.form1.userPassword1.value) {
			alert("两次输入密码不一致！");
			return false;
		}
		return true;
	}
</script>
</head>
<body>
<div class="title">当前位置:账户管理>单位账号管理</div> 
<div class="editBlock">
	<form action="updZzjg" method="post" name="form1" id="form1" autocomplete="off">
		<input type="hidden" name="zzjgId" value="${zzjg.zzjgId}" id="userId">
		<table>
			<tr>
				<td  width="250" align="right">单位账号：</td>
				<td><input type="text" value="${zzjg.zzjgDwzh}" name="zzjgDwzh"
					disabled="disabled" /></td>
			</tr>
			<tr>
				<td  width="250" align="right">单位简称：</td>
				<td><input type="text" value="${zzjg.zzjgDwjc}" name="zzjgDwjc"
					disabled="disabled" /></td>
			</tr>
			<tr>
				<td  width="250" align="right">用户名称：</td>
				<td><input type="text" value="${zzjg.zzjgDwmc}" name="zzjgDwmc"
					disabled="disabled" /></td>
			</tr>
			<tr>
				<td  width="250" align="right">单位管理员姓名：</td>
				<td><input type="text" value="${zzjg.zzjgDwfzr}" name="zzjgDwfzr" /></td>
			</tr>
			<tr>
				<td  width="250" align="right">联系电话：</td>
				<td><input type="text" value="${zzjg.zzjgLxrdh}"
					name="zzjgLxrdh" id="zzjgLxrdh" data-rule="联系电话:required;mobile"
					placeholder="联系电话" /></td>
			</tr>
			<tr>
				<td  width="250" align="right">电子邮箱：</td>
				<td><input type="text" value="${zzjg.zzjgLxremail}"
					name="zzjgLxremail" id="zzjgLxremail"
					data-rule="电子邮箱:required;email" placeholder="电子邮箱" />
					</td>
			</tr>
			<tr>
				<td  width="250" align="right">新的密码：</td>
				<td><input type="password" name="zzjgZhmm" id="userPassword"
					data-rule="新的密码:required;!digits; length[6~]"
					data-msg-digits="{0}不能使用纯数字" data-msg-length="{0}至少6位"
					placeholder="新的密码" /></td>
			</tr>
			<tr>
				<td  width="250" align="right">确认密码：</td>
				<td><input type="password" name="zzjgZhmm1" id="userPassword1"
					data-rule="确认密码:required;userPassword1" placeholder="确认密码" /></td>
			</tr>
			<tr><td  width="250" align="right"></td>
				<td  ><input type="submit"
						class="inputButton" value="确定" onclick="return checkPass()" /></td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>

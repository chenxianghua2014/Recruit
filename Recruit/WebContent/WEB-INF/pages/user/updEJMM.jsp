<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="resources/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="js/validator.js"></script>
<script type="text/javascript">
	function checkPass() {
		if (document.ff.zzjgEjmm.value != document.ff.zzjgEjmm1.value) {
			alert("两次输入密码不一致！");
		}
		if(Validator.Validate(document.getElementById('ff'),3) ){
			document.ff.action = "updZzjgEjmm";
			document.ff.method = "POST";
			document.ff.submit();
		}
	}
	function newcheckPass() {
		if (document.ff.newZzjgEjmm.value != document.ff.newZzjgEjmm1.value) {
			alert("两次输入密码不一致！");
		}
		if(Validator.Validate(document.getElementById('ff'),3) ){
			document.ff.action = "updNewZzjgEjmm";
			document.ff.method = "POST";
			document.ff.submit();
		}
	}
</script>
</head>

<body>
<div class="title">当前位置:账户管理>二级密码管理</div> 
<div class="editBlock">
	<form name="ff" id="ff">
	<input type="hidden" name="zzjgId" value="${loginSession.zzjgId}"/>
	<c:choose>
		<c:when test="${loginSession.zzjgEjmm eq null || loginSession.zzjgEjmm eq ''}">
			<table>
				<tr>
					<td  width="250" align="right"><span class="warning">*</span>新的密码：</td>
					<td><input type="password" name="zzjgEjmm" id="zzjgEjmm"  dataType="Require" msg="必填"/></td>
				</tr>
				<tr>
					<td  width="250" align="right"><span class="warning">*</span>确认密码：</td>
					<td><input type="password" name="zzjgEjmm1" id="zzjgEjmm1"  dataType="Require" msg="必填"/></td>
				</tr>
				<tr><td  width="250" align="right"></td>
					<td  ><input type="button" class="inputButton" value="确定" onclick="checkPass()" /></td>
				</tr>
			</table>
		</c:when>
		<c:otherwise>
			<table>
				<tr>
					<td  width="250" align="right">您的密码：</td>
					<td><input type="password" name="oldZzjgEjmm" id="oldZzjgEjmm"  dataType="Require" msg="必填"/></td>
				</tr>
				<tr>
					<td  width="250" align="right">新的密码：</td>
					<td><input type="password" name="newZzjgEjmm" id="newZzjgEjmm"  dataType="Require" msg="必填"/></td>
				</tr>
				<tr>
					<td  width="250" align="right">确认密码：</td>
					<td><input type="password" name="newZzjgEjmm1" id="newZzjgEjmm1"  dataType="Require" msg="必填"/></td>
				</tr>
				<tr><td  width="250" align="right"></td>
					<td  ><input type="button" class="inputButton" value="确定" onclick="newcheckPass();"/></td>
				</tr>
			</table>
		</c:otherwise>
	</c:choose>
	</form>
</div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<link rel="stylesheet"
	href="resources/validator-0.7.1/jquery.validator.css" type="text/css"></link>
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="resources/validator-0.7.1/jquery.validator.js"></script>
<script type="text/javascript"
	src="resources/validator-0.7.1/local/zh_CN.js"></script>
<script type="text/javascript" src="js/common.js"></script>
</head>
<body>
	<div class="title">当前位置:成绩管理>成绩推送管理</div>
	<div class="editBlock">
		<form action="CjtsglSave" method="post" id="Cjts"
			data-validator-option="{theme:'yellow_right_effect',stopOnError:true}">
			<input type="hidden" id="cjtsglId" name="cjtsglId"
				value="${cjtsgl.cjtsglId }" />
			<table boder="0">
				<tr>
					<td height="32" colspan="4" class="subtitle">成绩推送管理</td>
				</tr>
				<tr>
					<th style="width: 40%;text-align: right;">延迟时间：</th>
					<td><input type="text" maxlength="4" id="cjtsglTsyssj"
						name="cjtsglTsyssj" value="${cjtsgl.cjtsglTsyssj }"
						class="inputText" style="width: 54px" />&nbsp;分钟 <input
						type="submit" Class="inputbutton" value="保存" />（现延迟时间为
						${cjtsgl.cjtsglTsyssj }min）</td>
				</tr>
			</table>
			<table boder="0">
				<tr>
					<th style="width: 40%;text-align: right;">推送方式：</th>
					<td><input type="checkbox" id="rdoN2" name="cjtsglTsfs"
						value="邮件" />邮件&nbsp;&nbsp;<input type="checkbox" id="rdoN3"
						name="cjtsglTsfs" value="短信" />短信 <input type="submit"
						Class="inputbutton" value="保存" />（现推送方式为 ${cjtsgl.cjtsglTsfs }）</td>
				</tr>
				<tr>
					<th></th>
				</tr>
			</table>
		</form>
	</div>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		var tsfs = "${cjtsgl.cjtsglTsfs}";
		if (tsfs.indexOf("邮件") != -1)
			$("#rdoN2").attr("checked", true);
		if (tsfs.indexOf("短信") != -1)
			$("#rdoN3").attr("checked", true);
	});
</script>
</html>
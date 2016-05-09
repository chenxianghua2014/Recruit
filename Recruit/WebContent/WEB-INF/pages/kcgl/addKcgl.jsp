<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>考试报名</title>
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" charset="utf-8" src=""></script>
<script type="text/javascript"
	src="resources/validator-0.7.1/local/zh_CN.js"></script>
<script type="text/javascript"
	src="resources/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/validator.js"></script>
<script type="text/javascript">
	function saveKcgl() {
		if (Validator.Validate(document.getElementById('ff'), 3)) {
			document.ff.action = "saveKcgl";
			document.ff.method = "POST";
			document.ff.submit();
		}
	}
</script>
</head>
<body>
	<div class="title">当前位置:考试管理>考场管理</div>
	<div class="editBlock">
		<form name="ff" id="ff">
			<input type="hidden" id="kcglId" name="kcglId"
				value="${ kcgl.kcglId }" />
			<table>
				<tr>
					<th width="150"><span class="warning">*</span>考场名称</th>
					<td><input type="text" id="kcglName" name="kcglName"
						value="${ kcgl.kcglName }" dataType="Require" msg="必填(8字以内)" /></td>
				</tr>
				<tr>
					<th width="150"><span class="warning">*</span>考场地址</th>
					<td><input type="text" id="kcglDz" name="kcglDz"
						value="${ kcgl.kcglDz }" dataType="Require" msg="必填(30字以内)" /></td>
				</tr>
				<tr>
					<th width="150"><span class="warning">*</span>考试日期</th>
					<td><input type="text" id="kcglKsrq" name="kcglKsrq"
						value="${ kcgl.kcglKsrq }"
						onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" dataType="Require"
						msg="请选取日期" /></td>
				</tr>
				<tr>
					<th width="150"><span class="warning">*</span>考试开始时间</th>
					<td><input type="text" id="kcglKssjStart" name="kcglKssjStart"
						value="${ kcgl.kcglKssjStart }"
						onFocus="WdatePicker({dateFmt:'HH:mm:ss'})" dataType="Require"
						msg="请选取开始时间" /></td>
				</tr>
				<tr>
					<th width="150"><span class="warning">*</span>考试结束时间</th>
					<td><input type="text" id="kcglKssjEnd" name="kcglKssjEnd"
						value="${ kcgl.kcglKssjEnd }"
						onFocus="WdatePicker({dateFmt:'HH:mm:ss'})" dataType="Require"
						msg="请选取结束时间" /></td>
				</tr>
				<tr>
					<th width="150"><span class="warning">*</span>考场容量</th>
					<td><input type="text" id="kcglKcrl" name="kcglKcrl"
						value="${ kcgl.kcglKcrl }" dataType="Number" msg="必需填写数字" /></td>
				</tr>
				<tr>
					<th width="150"><span class="warning">*</span>考场状态</th>
					<td>开放<input type="radio" name="kcglKczt" id="kcglKczt"
						value="开放" checked="checked" /> 关闭<input type="radio"
						name="kcglKczt" id="kcglKczt" value="关闭" />
					</td>
				</tr>
				<tr>
					<th width="150"></th>
					<td><input type="button" class="inputButton" value="确定"
						onclick="saveKcgl();" /> &nbsp;&nbsp; <input type="button"
						class="inputButton" value="取消" onclick="history.back()" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>

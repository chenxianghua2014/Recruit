<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>
</head>
<body>
	<div class="title">当前位置:宣传信息>单位简介管理>单位简介审核</div>
	<div class="editBlock">
		<form action="XcxxDoCheck2" method="post">
			<input type="hidden" id="xcxxCheckstatus" name="xcxxCheckstatus" />
			<input type="hidden" id="xcxxId" name="xcxxId" value="${id} " />
			<table boder="0">
				<tr>
					<th width="150">审核结果:</th>
					<td><input type="radio" id="rdoY" name="rdo" value="已发布"
						checked="true" />通过&nbsp;<input type="radio" id="rdoN" name="rdo"
						value="不通过" />不通过</td>
				</tr>
				<tr>
					<th>审核意见:</th>
					<td><textarea id="xcxxCheckmsg" name="xcxxCheckmsg"
							style="width: 500;height:100px;"></textarea></td>
				</tr>
				<tr>
					<th></th>
					<td><input type="button" onclick="frmSubmit();"
						class="inputButton" value="保存" />&nbsp;&nbsp; <input
						type="button" class="inputButton" value="取消"
						onclick="history.back()" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
<script type="text/javascript">
	function frmSubmit() {
		$("#xcxxCheckstatus").val($("input[name='rdo']:checked").val());
		document.forms[0].submit();
	}
</script>
</html>

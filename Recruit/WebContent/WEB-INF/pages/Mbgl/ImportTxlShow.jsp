<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base target="_self"> 
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>
</head>

<body>
	<form id="file_upload_id" name="file_upload_name" action="DoFileUploadTxl"
		method="post" enctype="multipart/form-data">
		<div>
			<input type="file" name="excel" id="excel" />
		</div>
		<div>
			<input type="button" onclick="frmSubmit()" class="inputButton"value="上传" />
		</div>
	</form>
</body>
<script type="text/javascript">
	${CloseWindow}
	function frmSubmit() {
		var fileName = $("#excel").val();
		var extend = fileName.substring(fileName.lastIndexOf(".") + 1); 
		if (extend == "") {
			return;
		} else {
			if (extend != "xls") {
				alert("请上传后缀名为xls的文件!");
				return;
			}
		}
		document.forms[0].submit();
	}
</script>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="resources/jquery/jquery.form.js"></script>
</head>
<body>
	<form id="file_upload_id" name="file_upload_name" action="PicYlUpload"
		method="post" enctype="multipart/form-data">
		<div>
			<input type="file" name="file" id="file" />
		</div>
		<div>
			<input type="button" onclick="frmSubmit()" class="inputButton"
				value="上传" />
		</div>
	</form>
</body>
<script type="text/javascript">
	function frmSubmit() {
		var fileName = $("#file").val();
		var extend = fileName.substring(fileName.lastIndexOf(".") + 1);
		if (extend == "") {
			return;
		} else {
			if (extend != "jpg" && extend != "gif" && extend != "jpeg"
					&& extend != "png") {
				alert("图片格式不正确!");
				return;
			}
		}
		var options = {
			type : 'post',
			url : "PicYlUpload",
			success : showResponse,
			error : showResponse,
			clearForm : false
		};
		//ajax上传表单
		$("#file_upload_id").ajaxSubmit(options);
	}
	function showResponse(responseText, statusText) {
		window.dialogArguments.$("#imgSrc").val(responseText);
		window.dialogArguments.$("#picResult").html("导入成功!");
		window.close();
	}
</script>
</html>
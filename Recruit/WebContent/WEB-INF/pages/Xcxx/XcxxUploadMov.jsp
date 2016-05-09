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
	<form id="file_upload_id" name="file_upload_name" action="MovUpload"
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
			if (extend != "mp4") {
				alert("请选择mp4类型的视频文件!");
				return;
			}
		}
		var options = {
			type : 'post',
			url : "MovUpload",
			success : showResponse,
			error : showResponse,
			clearForm : false
		};
		//ajax上传表单
		$("#file_upload_id").ajaxSubmit(options);
	}

	function showResponse(responseText, statusText) {
		window.dialogArguments.$("#movSrc").val(responseText);
		window.dialogArguments.$("#movResult").html("导入成功!&nbsp;<input type=\"button\" class=\"inputButton\" onclick=\"Yl();\" value=\"预览\" />");
		window.close();
	}
</script>
</html>
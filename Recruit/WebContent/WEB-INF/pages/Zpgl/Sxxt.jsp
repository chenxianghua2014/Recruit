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
<script type="text/javascript" charset="utf-8"
	src="resources/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="resources/ueditor/ueditor.all.js"></script>
<script type="text/javascript" charset="utf-8"
	src="resources/ueditor/lang/zh-cn/zh-cn.js"></script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8" src=""></script>
<script type="text/javascript"
	src="resources/validator-0.7.1/jquery.validator.js"></script>
<script type="text/javascript"
	src="resources/validator-0.7.1/local/zh_CN.js"></script>
<script type="text/javascript"
	src="resources/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/common.js"></script>
</head>
<body>
	<div class="title">当前位置:招聘管理>筛选协同</div>
	<div class="editBlock">
		<form action="DoSxxt" method="post" autocomplete="off"
			data-validator-option="{theme:'yellow_right_effect',stopOnError:true}">
			<input type="hidden" id="Id" name="Id" value="${ Id }" />
			<table>
				<tr>
					<td colspan="3" class="subtitle">筛选协同信息</td>
				</tr>
				<tbody>
					<tr>
						<th width="150"><span class="warning">*</span>收件人</th>
						<td><input type="text" style="width:100%" class="inputText"
							data-rule="required; email"
							data-rule-email="[/^\s*\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*(\,\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*)*(\;)*\s*$/, '格式不正确']"
							id="Receive" name="Receive" /></td>
						<td rowspan="4" width="250"
							style="font-weight: bold;font-size: 12pt;">联系人:<br> <select
							id="selLxr" name="selLxr" multiple="multiple"
							style="width:250px;height:500px;" onclick="addLxr(this.value);">
						</select></td>
					</tr>
					<tr>
						<th><span class="warning">*</span>标题</th>
						<td><input type="text" style="width:100%" class="inputText"
							data-rule="required;" id="Title" name="Title" />
						</td>
					</tr>
					<tr>
						<th>模版</th>
						<td><select id="selMb" name="selMb"></select>&nbsp;<input
							type="button" class="inputButton" value="加载"
							onclick="loadMbContent()" />
						</td>
					</tr>
					<tr>
						<th><span class="warning">*</span>正文</th>
						<td><script id="Content" name="Content" type="text/plain"
								style="width:100%;height:300px;"></script>
						</td>
					</tr>
				</tbody>
				<tr>
					<td colspan="3" class="toolbar"><input type="submit"
						class="inputButton" value="发送" /> &nbsp;&nbsp; <input
						type="button" class="inputButton" value="取消"
						onclick="history.back()" />
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
<script type="text/javascript">
	var editor;
	$(document).ready(function() {
		editor = UE.getEditor('Content');
		bindSxxtMb();
		bindMsg();
	});
	function bindSxxtMb() {
		$.ajax({
			type : "POST",
			url : "LoadMbgl",
			data : {
				type : "筛选协同"
			},
			dataType : "json",
			async : false,
			success : function(_result) {
				$("#selMb").empty();
				$("#selMb").append("<option value=''></option>");
				for ( var i = 0; i < _result.length; i++) {
					$("#selMb").append(
							"<option value='" + _result[i].mbglId+ "'>"
									+ _result[i].mbglName + "</option>");
				}
			}
		});
	}

	function loadMbContent() {
		if ($("#selMb").val() == "")
			return;
		$.ajax({
			type : "POST",
			url : "loadMbContent",
			data : {
				id : $("#selMb").val()
			},
			dataType : "json",
			async : false,
			success : function(_result) {
				editor.setContent(_result.mbglContent);
			}
		});
	}
	function addLxr(_v) {
		if ($("#Receive").val().indexOf(_v) == -1)
		{
			if ($("#Receive").val() == "")
				$("#Receive").val(_v);
			else
				$("#Receive").val($("#Receive").val() + "," + _v);
		}
			
	}
	function bindMsg() {
		$.ajax({
			type : 'POST',
			url : "LoadMsg",
			data : {
				zw : "",
				bm : ""
			},
			dataType : "json",
			success : function(msgs) {
				$("#selLxr").empty();
				if (msgs.length > 0) {
					for ( var i = 0; i < msgs.length; i++) {
						$("#selLxr").append(
								"<option value='" + msgs[i].txlEmail + "'>"
										+ msgs[i].txlDepartment + "-"
										+ msgs[i].txlPosition + "-"+ msgs[i].txlName
										+ "</option>");
					}
				}

			}
		});
	}
</script>
</html>

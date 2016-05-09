<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet"
	href="resources/validator-0.7.1/jquery.validator.css" type="text/css"></link>
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript"
	src="resources/validator-0.7.1/jquery.validator.js"></script>
<script type="text/javascript"
	src="resources/validator-0.7.1/local/zh_CN.js"></script>
<script type="text/javascript" src="js/common.js"></script>
</head>
<body>
	<form action="SaveMbgl" method="post" autocomplete="off"
		data-validator-option="{theme:'yellow_right_effect',stopOnError:true}">
		<div class="title">当前位置:模板管理>编辑职位信息模板</div>
		<input name="mbglId" type="hidden" class="mbglId" id="mbglName"
			value="${ mbgl.mbglId}" />
		<div class="editBlock">
			<table>
				<tbody>
					<tr>
						<td height="32" colspan="2" class="subtitle">编辑职位信息模板</td>
					</tr>
					<tr>
						<th width="150"><span class="warning">*</span>模板类型:</th>
						<td><select id="mbglType" name="mbglType"
							data-rule="required;" >
								<option value="职位职责">职位职责</option>
								<option value="职位要求">职位要求</option>
						</select></td>
					</tr>
					<tr>
						<th width="150"><span class="warning">*</span>模板名称:</th>
						<td><input name="mbglName" type="text" class="inputText"
							data-rule="required;" id="mbglName" value="${ mbgl.mbglName}" />
						</td>
					</tr>
					<tr>
						<th width="150"><span class="warning">*</span>模板内容:</th>
						<td><textarea id="mbglContent" name="mbglContent"
								data-rule="required;" type="text/plain"
								style="width:99%;height:300px;"></textarea></td>
					</tr>
					<tr>
						<th width="150"></th>
						<td><input type="submit" class="inputButton" value="保存" />&nbsp;<input
							type="button" class="inputButton" value="返回"
							onclick="history.back()" /></td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("#mbglType").val('${ mbgl.mbglType }');
	});
</script>
</html>

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
<script type="text/javascript" src="js/common.js"></script>
</head>
<body>
	<form action="SaveMbgl" method="post" autocomplete="off"
		data-validator-option="{theme:'yellow_right_effect',stopOnError:true}">
		<div class="title">当前位置:模板管理>编辑筛选协同模板</div>
		<input name="mbglId" type="hidden" class="mbglId" id="mbglName"
			value="${ mbgl.mbglId}" /> <input type="hidden" id="mbglType"
			name="mbglType" value="筛选协同" />
		<div class="editBlock">
			<table>
				<tbody>
					<tr>
						<td height="32" colspan="2" class="subtitle">编辑筛选协同模板</td>
					</tr>
					<tr>
						<th width="150"><span class="warning">*</span>模板名称:</th>
						<td><input name="mbglName" type="text" class="inputText"
							id="mbglName" value="${ mbgl.mbglName}" data-rule="required;" />
						</td>
					</tr>
					<tr>
						<th width="150"><span class="warning">*</span>模板内容:</th>
						<td><script id="mbglContent" name="mbglContent"
								type="text/plain" style="width:100%;height:300px;"
								data-rule="required;"></script>
						</td>
					</tr>
					<tr>
						<th width="150"></th>
						<td><input type="submit" class="inputButton" value="保存" />&nbsp;<input
							type="button" class="inputButton" value="返回"
							onclick="history.back()" />
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
</body>
<script type="text/javascript">
	var editor;
	$(document).ready(function() {
		editor = UE.getEditor('mbglContent');
		editor.addListener("ready", function() {
			editor.setContent('${ mbgl.mbglContent }');
		});
	});
</script>
</html>

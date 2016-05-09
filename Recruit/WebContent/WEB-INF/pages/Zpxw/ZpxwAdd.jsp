<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="stylesheet"
	href="resources/validator-0.7.1/jquery.validator.css" type="text/css"></link>
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
	var fbsj = "${zpxw.zpxwFbsj }";
</script>
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
	<div class="title">当前位置:招聘新闻>添加招聘新闻</div>
	<div class="editBlock">
		<form action="ZpxwSava" method="post" autocomplete="off"
			onsubmit="frmSubmit()"
			data-validator-option="{theme:'yellow_right_effect',stopOnError:true}">
			<input type="hidden" id="zpxwId" name="zpxwId"
				value="${ zpxw.zpxwId }" />
			<table>
				<tr>
					<td colspan="2" class="subtitle">招聘新闻管理</td>
				</tr>
				<tbody>
					<tr>
						<th width="150"><span class="warning">*</span>新闻标题</th>
						<td><input type="text" id="zpxwTitle" name="zpxwTitle"
							value="${ zpxw.zpxwTitle }" data-rule="required;" /></td>
					</tr>
					<tr>
						<th width="150"><span class="warning">*</span>发布时间</th>
						<td><input type="text" id="zpxwFbsj" name="zpxwFbsj"
							value="${ zpxw.zpxwFbsj }" data-rule="required;"
							readonly="readonly" style="background-color:#D3D3D3;" />
						</td>
					</tr>
					<tr>
						<th width="150">是否为招聘日程</th>
						<td><input type="hidden" id="zpxwSfzprc" name="zpxwSfzprc" /><input
							type="radio" id="rdoY" name="rdo" value="是" />是&nbsp;<input
							type="radio" id="rdoN" name="rdo" value="否" checked="true" />否</td>
					</tr>
					<tr>
						<th width="150">招聘日程编辑注意事项</th>
						<td style="color: red;">1，在编辑界面5列*n行的表格。<br />
							2，表头字段名称以“学校”，“宣讲会时间”，“宣讲会地点”，“招聘会时间”，“招聘会时间”顺序进行编辑。<br />
							3，为了保证招聘日程在移动端正常显示，在编辑过程中注意上述事项。</td>
					</tr>
					<tr>
						<td colspan="2"><script id="zpxwContent" name="zpxwContent"
								type="text/plain" style="width:100%;height:300px;"
								data-rule="required;"></script>
						</td>
					</tr>
				</tbody>
				<tr>
					<td colspan="2" class="toolbar"><input type="submit"
						class="inputButton" value="确定" /> &nbsp;&nbsp; <input
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
	var date = new Date();
	$(document).ready(function() {
		editor = UE.getEditor('zpxwContent');
		if (fbsj == "")
			$("#zpxwFbsj").val(date.format('yyyy-MM-dd'));
	});
	function frmSubmit() {
		$("#zpxwSfzprc").val($("input[name='rdo']:checked").val());
	}
	//日期格式化
	Date.prototype.format = function(format) {
		var o = {
			"M+" : this.getMonth() + 1, //month
			"d+" : this.getDate(), //day
			"h+" : this.getHours(), //hour
			"m+" : this.getMinutes(), //minute
			"s+" : this.getSeconds(), //second
			"q+" : Math.floor((this.getMonth() + 3) / 3), //quarter
			"S" : this.getMilliseconds()
		//millisecond
		}
		if (/(y+)/.test(format))
			format = format.replace(RegExp.$1, (this.getFullYear() + "")
					.substr(4 - RegExp.$1.length));
		for ( var k in o)
			if (new RegExp("(" + k + ")").test(format))
				format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
						: ("00" + o[k]).substr(("" + o[k]).length));
		return format;
	}
</script>
</html>

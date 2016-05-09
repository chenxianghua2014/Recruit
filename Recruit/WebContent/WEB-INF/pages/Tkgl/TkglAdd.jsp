<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<link href="skins/default/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="resources/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" charset="utf-8"
	src="resources/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="resources/ueditor/ueditor.all.js"></script>
<script type="text/javascript" charset="utf-8"
	src="resources/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript "
	src="resources/ueditor/dialogs/image/image.js"></script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8" src=""></script>
<script type="text/javascript"
	src="resources/validator-0.7.1/local/zh_CN.js"></script>
<script type="text/javascript"
	src="resources/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/validator.js"></script>
<script type="text/javascript">
	function TkglSava() {
		if (Validator.Validate(document.getElementById('ff'), 3)) {
			document.ff.action = "TkglSava";
			document.ff.method = "post";
			document.ff.submit();
		}
	}
</script>
</head>
<body>
	<div class="title">当前位置:考试管理>考试现场管理</div>
	<div class="editBlock">
		<form name="ff" id="ff">
			<input type="hidden" id="tkglId" name="tkglId"
				value="${ tkgl.tkglId }" />
			<table>
				<tr>
					<td colspan="2" class="subtitle">编辑考试现场信息</td>
				</tr>
				<tbody>
					<tr>
						<th width="150"><span class="warning">*</span>试题编号</th>
						<td><input type="text" id="TKGL_STBH" name="tkglStbh"
							value="${tkgl.tkglStbh}" dataType="Require" msg="必填" /></td>
					</tr>
					<tr>
						<th width="150"><span class="warning">*</span>试题类型</th>
						<td><input type="text" id="TKGL_STLX" name="tkglStlx"
							value="${tkgl.tkglStlx}" dataType="Require" msg="必填" /></td>
					</tr>
					<tr>
						<th><span class="warning">*</span>试题题干</th>
						<td><script id="tkglSttg" name="tkglSttg" type="text/plain"
								style="width:100%;height:100px;" dataType="Require" msg="必填"></script>
						</td>
					</tr>
					<c:if test="${tkgl.tkglStlx ne 'L'}">
						<tr>
							<th>A</th>
							<td><script id="tkglStxxA" name="tkglStxxA"
									type="text/plain" style="width:100%;height:60px;"></script></td>
						</tr>
						<tr>
							<th>B</th>
							<td><script id="tkglStxxB" name="tkglStxxB"
									type="text/plain" style="width:100%;height:60px;"></script></td>
						</tr>
						<tr>
							<th>C</th>
							<td><script id="tkglStxxC" name="tkglStxxC"
									type="text/plain" style="width:100%;height:60px;"></script></td>
						</tr>
						<tr>
							<th>D</th>
							<td><script id="tkglStxxD" name="tkglStxxD"
									type="text/plain" style="width:100%;height:60px;"></script></td>
						</tr>
					</c:if>
					<tr>
						<th>选择题答案</th>
						<c:if test="${tkgl.tkglStlx eq 'L'}">
							<td><input type="text" id="tkglStda" name="tkglStda"
								value="${tkgl.tkglStda}" /></td>
						</c:if>
						<c:if test="${tkgl.tkglStlx ne 'L' }">
							<td>A <input type="radio" id="tkglStda" name="tkglStda"
								value="A" <c:out value="${tkgl.tkglStda== 'A'?'checked':''}"/> />
								B <input type="radio" id="tkglStda" name="tkglStda" value="B"
								<c:out value="${tkgl.tkglStda== 'B'?'checked':''}"/> /> C <input
								type="radio" id="tkglStda" name="tkglStda" value="C"
								<c:out value="${tkgl.tkglStda== 'C'?'checked':''}"/> /> D <input
								type="radio" id="tkglStda" name="tkglStda" value="D"
								<c:out value="${tkgl.tkglStda== 'D'?'checked':''}"/> />
							</td>
						</c:if>
					</tr>
					<tr>
						<th></th>
						<td><input type="button" class="inputButton" value="确认添加"
							onclick="TkglSava();" />&nbsp;&nbsp; <input type="button"
							class="inputButton" value="取消" onclick="history.back()" /></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
<script type="text/javascript">
	var editor;
	var editorA;
	var editorB;
	var editorC;
	var editorD;
	var toobars = [ 'fullscreen', 'source', '|', 'undo', 'redo', '|', 'bold',
			'italic', 'underline', 'fontborder', 'strikethrough',
			'superscript', 'subscript', 'removeformat', 'formatmatch',
			'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor',
			'backcolor', 'insertorderedlist', 'insertunorderedlist',
			'selectall', 'cleardoc', '|', 'rowspacingtop', 'rowspacingbottom',
			'lineheight', '|', 'justifyleft', 'justifycenter', 'justifyright',
			'justifyjustify', 'simpleupload' ];
	$(document).ready(function() {
		editor = UE.getEditor('tkglSttg', {
			toolbars : [ toobars ],
			maximumWords : 1000
		});
		editor.addListener("ready", function() {
			editor.setContent('${tkgl.tkglSttg }');
		});
		editorA = UE.getEditor('tkglStxxA', {
			toolbars : [ toobars ],
			maximumWords : 200
		});
		editorA.addListener("ready", function() {
			editorA.setContent('${tkgl.tkglStxxA }');
		});
		editorB = UE.getEditor('tkglStxxB', {
			toolbars : [ toobars ],
			maximumWords : 200
		});
		editorB.addListener("ready", function() {
			editorB.setContent('${tkgl.tkglStxxB }');
		});
		editorC = UE.getEditor('tkglStxxC', {
			toolbars : [ toobars ],
			maximumWords : 200
		});
		editorC.addListener("ready", function() {
			editorC.setContent('${tkgl.tkglStxxC }');
		});
		editorD = UE.getEditor('tkglStxxD', {
			toolbars : [ toobars ],
			maximumWords : 200
		});
		editorD.addListener("ready", function() {
			editorD.setContent('${tkgl.tkglStxxD }');
		});
	});
</script>
</html>
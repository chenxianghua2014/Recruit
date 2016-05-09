<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<link rel="stylesheet" href="resources/upload/upload.css"
	type="text/css"></link>
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
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript">
	var fbsj = "${ xcxx.xcxxFbsj}";
</script>
</head>
<body>
	<div class="title">当前位置:宣传信息>编辑单位简介></div>
	<div class="editBlock">
		<form action="XcxxSava" method="post" autocomplete="off"
			data-validator-option="{theme:'yellow_right_effect',stopOnError:true}">
			<input type="hidden" id="xcxxId" name="xcxxId"
				value="${ xcxx.xcxxId }" /> <input type="hidden" id="orgId"
				name="orgId" value="${ xcxx.xcxxAdduser }" />
			<table>
				<tr>
					<td colspan="2" class="subtitle">编辑简介信息</td>
				</tr>
				<tbody>
					<tr>
						<th width="150"><span class="warning">*</span>简介类别</th>
						<td><select id="xcxxXclb" name="xcxxXclb" data-rule="required;"
							style="width: 142px;" onchange="bindXcnr(this.value)">
						</select></td>
					</tr>
					<tr style="display: none;">
						<th width="150"><span class="warning">*</span>视频预览图片</th>
						<td><input type="hidden" id="imgSrc" name="imgSrc">&nbsp;<input
							type="button" class="inputButton"
							onclick="ImportShow('XcxxUploadImg');" value="导入" />&nbsp;<span
							id="picResult" style="color: #339900;"></span>
						</td>
					</tr>
					<tr style="display: none;">
						<th width="150"><span class="warning">*</span>视频</th>
						<td><input type="hidden" id="movSrc" name="movSrc">&nbsp;<input
							type="button" class="inputButton"
							onclick="ImportShow('XcxxUploadMov');" value="导入" />&nbsp;<span
							id="movResult" style="color: #339900;"></span>
						</td>
					</tr>
					<tr>
						<th width="150"><span class="warning">*</span>简介内容</th>
						<td><select id="xcxxTitle" name="xcxxTitle" data-rule="required;"
							style="width: 142px;">
						</select></td>
					</tr>
					<tr>
						<th width="150"><span class="warning">*</span>发布时间</th>
						<td><input type="text" class="inputText" id="xcxxFbsj"
							name="xcxxFbsj" value="${ xcxx.xcxxFbsj }" data-rule="required;"
							readonly="readonly" style="background-color: #D3D3D3;" /></td>
					</tr>
					<tr>
						<td colspan="2"><script id="xcxxContent" name="xcxxContent"
								type="text/plain" style="width:100%;height:300px;"></script></td>
					</tr>
				</tbody>
				<tr>
					<td colspan="2" class="toolbar"><input type="submit"
						class="inputButton" value="确定" /> &nbsp;&nbsp; <input
						type="button" class="inputButton" value="取消"
						onclick="history.back()" /></td>
				</tr>
			</table>
		</form>
	</div>
	<div class="demo" style="display: none;">
		<div class="logoupload">
			<div class="btnbox">
				<object id="SWFUpload_0" type="application/x-shockwave-flash"
					data="resources/upload/swfupload.swf?preventswfcaching=1402472575330"
					width="143" height="45 " class="swfupload">
					<param name="wmode" value="transparent">
					<param name="movie"
						value="resources/upload/swfupload.swf?preventswfcaching=1402472575330">
					<param name="quality" value="high">
					<param name="allowScriptAccess" value="always">
					<param name="flashvars"
						value="movieName=SWFUpload_0&uploadURL=fileUpload&useQueryString=false&requeueOnError=false&httpSuccess=&assumeSuccessTimeout=0&params=&filePostName=Filedata&fileTypes=*.jpg;*.gif;*.png;*.jpeg;&fileTypesDescription=Image Files&fileSizeLimit=2 MB&fileUploadLimit=0&fileQueueLimit=0&debugEnabled=false&buttonImageURL=resources/upload/upload-btn.png&buttonWidth=143&buttonHeight=45 &buttonText=&buttonTextTopPadding=0&buttonTextLeftPadding=0&buttonTextStyle=color: #000000; font-size: 16pt;&buttonAction=-110&buttonDisabled=false&buttonCursor=-2">
				</object>
			</div>
			<div class="progress-box fl-progress" style="display: none;">
				<div class="progress-num">
					上传进度：<b>0%</b>
				</div>
				<div class="progress-bar">
					<div style="width: 0%;" class="bar-line"></div>
				</div>
			</div>
		</div>
		<div class="batch-pic">
			<ul>
			</ul>
		</div>
	</div>
	<!-- 上传必备插件 -->
	<script type="text/javascript" src="resources/upload/swfupload.js"></script>
	<script type="text/javascript"
		src="resources/upload/swfupload.queue.js"></script>
	<script type="text/javascript"
		src="resources/upload/swfupload.speed.js"></script>
	<script type="text/javascript" src="resources/upload/handlers.js"></script>
	<script type="text/javascript" src="js/Upload.js"></script>
	<script type="text/javascript" src="js/XcxxAdd.js"></script>
</body>
<script type="text/javascript">
	var editor;
	$(document).ready(function() {
		editor = UE.getEditor('xcxxContent');
		editor.addListener("ready", function() {
			editor.setContent('${ xcxx.xcxxContent }');
			$("#xcxxXclb").val('${ xcxx.xcxxXclb }');
			bindXcnr('${ xcxx.xcxxXclb }');

			$("#xcxxTitle").val('${ xcxx.xcxxTitle }');
		});
	});
	function ImportShow(_url) {
		window.showModalDialog(_url, window,
				"dialogWidth:300px;dialogHeight:80px;scroll:no;status:no");
	}
</script>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>社区-博文管理添加</title>
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
	src="resources/validator-0.7.1/local/zh_CN.js"></script>
<script type="text/javascript"
	src="resources/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/validator.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#ltbkId").find("option[value='${bbs.ltbkId}']").attr("selected",true);
});

function htBbsSave(){
	if(Validator.Validate(document.getElementById('ff'),3) ){
		if(editor.getContent('bbsNr') == ""){
			alert("您还没有填写内容！请填写您的评论在发表");
		}else{
			document.ff.action="htBbsSave";
			document.ff.method="post";
			document.ff.submit();
		}
	}
}
</script>
</head>
<body>
<div class="title">当前位置:科工论坛>编辑帖子内容</div> 
<div class="editBlock">
	<form  name="ff" id="ff">
		<input type="hidden" id="bbsId" name="bbsId"
			value="${ bbs.bbsId }" />
		<table boder="0">
			<tr>
				<th width="150"><span class="warning">*</span>所属板块:</th>
				<td>
				<select name="ltbkId" id="ltbkId" dataType="Require"  msg="未选择类型" >
				<option value="">--请选择--</option>
					<c:forEach items="${ltbkList}" var="map">
						<option value="${map.ltbkId}">${map.ltbkName}</option>
					</c:forEach>
				</select>
				</td>
			</tr>
			<tr>
			<th width="150"><span class="warning">*</span>帖子内容:</th>
			<td colspan="2"><script id="bbsNr" name="bbsNr" type="text/plain"
				style="width:100%;height:300px;"></script></td>
			</tr>
			<tr>
			<th width="150"></th>
			<td>
			<input type="button" class="inputButton" value="确定" onclick="htBbsSave();"/> &nbsp;&nbsp; 
			<input type="button" class="inputButton" value="取消" onclick="history.back()" />
			</td>
			</tr>
		</table>
	</form>
	</div>
</body>
<script type="text/javascript">
var editor;
var toobars = ['emotion'];
$(document).ready(function() {
	editor = UE.getEditor('bbsNr', {
		toolbars : [ toobars ],
		maximumWords : 1000
	});
	editor.addListener("ready", function() {
		editor.setContent('${bbs.bbsNr }');
	});
});
</script>
</html>
